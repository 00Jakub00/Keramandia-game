package utoky.utokyBERSERK;

import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.ChodenieDolava;
import postavy.ukonyPostav.ChodenieDoprava;
import postavy.ukonyPostav.StatieNaMieste;
import postavy.ukonyPostav.Zomretie;
import utoky.Utok;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že sa nepriatel bezi, bezi do predu, zastane na urcitom
 * mieste, hrac a nepriatel si vymenia polohy a obidvom sa uberie urcity pocet zivotov
 *
 * @author Jakub Gály
 */
public class VymenaStranUtok extends Utok {
    private int pocetKrokov;
    private int poradoveCisloObrazka;
    public VymenaStranUtok() {
        super("vymenaStran");
        this.pocetKrokov = 0;
        this.poradoveCisloObrazka = 1;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku výmeny strán
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        if (!nepriatel.isSomMrtva() && !(nepriatel.getAktualnyUkonPostavy() instanceof Zomretie)) {
            super.pripravSaNaUtok(nepriatel, hrac);
            super.manazerSpravujObjekt();
            nepriatel.manazerPrestanSpravovatObjekt();
            if (super.getSmerUtoku() == SmerPostavy.VPRAVO) {
                nepriatel.zmenAktualnyUkon(new ChodenieDoprava());
            } else {
                nepriatel.zmenAktualnyUkon(new ChodenieDolava());
            }
        }
    }

    /**
     * Metóda spravovaná manazerom.
     * Vykonáva tento útok
     */

    public void vykonavajUtok() {
        Nepriatel nepriatel = super.getNepriatel();
        if (this.poradoveCisloObrazka < 7) {
            nepriatel.vykonajUkon("" + nepriatel.getSmerPostavy().toString(), "nepriatelia", 10);
            this.poradoveCisloObrazka++;
        } else if (this.pocetKrokov < 7) {
            this.poradoveCisloObrazka = 1;
            this.pocetKrokov++;
        } else {
            Hrac hrac = super.getHrac();
            hrac.getPostava().skryPostavu();
            nepriatel.skryPostavu();
            int suradnicaXHraca = hrac.getSuradnicaXHraca();

            hrac.zmenPolohuHrac(nepriatel.getSuradnicaX(), nepriatel.getSuradnicaY());
            nepriatel.nastavPolohu(suradnicaXHraca, nepriatel.getSuradnicaY());

            hrac.getPostava().zmenSmerPostavy(super.getSmerUtoku());
            nepriatel.nastavOpacnySmer();

            hrac.getPostava().zobrazPostavu();
            nepriatel.zobrazPostavu();

            hrac.uberZivot(3);
            nepriatel.uberZivot(2);

            super.manazerPrestanSpravovatObjekt();
            nepriatel.zmenAktualnyUkon(new StatieNaMieste());
            nepriatel.resetujUtoky();
            Nepriatel n = ( Nepriatel )nepriatel;
            n.manazerSPravujObjekt();

            if (hrac.getSuradnicaXHraca() < 20 || hrac.getSuradnicaXHraca() + hrac.getSuradnicaXHraca() + hrac.getPostava().getTypPostavy().getSirkaPostavy() > 1050) {
                hrac.getPostava().nastavPolohu(300, hrac.getPostava().getSuradnicaY());
            }

            hrac.mozesUtocic();

            if (nepriatel.isSomMrtva()) {
                nepriatel.skryPostavu();
                hrac.skryPostavuHrac();
            }

        }
    }
}
