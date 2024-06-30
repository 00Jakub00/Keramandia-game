package utoky.utokyBERSERK;

import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.ChodenieDolava;
import postavy.ukonyPostav.ChodenieDoprava;
import postavy.ukonyPostav.Zomretie;
import utoky.Utok;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že nepriatel pribehne k hracovi a sekne ho sablou
 *
 * @author Jakub Gály
 */
public class SekSablou extends Utok {
    private int poradoveCisloObrazkaUtoku;
    public SekSablou() {
        super("sekSablou");
        this.poradoveCisloObrazkaUtoku = 1;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku šabla
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */
    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        if (!nepriatel.isSomMrtva() && !(nepriatel.getAktualnyUkonPostavy() instanceof Zomretie)) {
            super.pripravSaNaUtok(nepriatel, hrac);
            if (super.getSmerUtoku() == SmerPostavy.VPRAVO) {
                nepriatel.zmenAktualnyUkon(new ChodenieDoprava());
            } else {
                nepriatel.zmenAktualnyUkon(new ChodenieDolava());
            }
            hrac.nastavZablokovanyPohyb();
            super.manazerSpravujObjekt();
        }
    }

    /**
     * Metóda spravovaná manazerom.
     * Vykonáva tento útok
     */

    public void vykonavajUtok() {
        Hrac hrac = super.getHrac();
        Nepriatel nepriatel = super.getNepriatel();
        Postava postavaHraca = hrac.getPostava();
        int vzdialenostMedzi = 0;

        switch (super.getSmerUtoku()) {
            case VPRAVO -> vzdialenostMedzi = Math.abs((nepriatel.getSuradnicaX() + nepriatel.getTypPostavy().getSirkaPostavy()) - (hrac.getSuradnicaXHraca()));
            case VLAVO -> vzdialenostMedzi = Math.abs(nepriatel.getSuradnicaX() - (hrac.getSuradnicaXHraca() + 70));
        }

        if (vzdialenostMedzi >= 8) {
            nepriatel.vykonajUkon("" + nepriatel.getSmerPostavy().toString(), "nepriatelia", 10);
        } else {
            if (this.poradoveCisloObrazkaUtoku < 5) {
                nepriatel.getStavPostavy().zmenObrazok("src/assets/nepriatelia/" + nepriatel.getTypPostavy().toString() + "/utoky/sekSablou" + super.getSmerUtoku().toString() + "/" + this.poradoveCisloObrazkaUtoku + ".png");
                this.poradoveCisloObrazkaUtoku++;
            } else {
                super.ukonciUtok(true);
                hrac.uberZivot(4);
                hrac.nastavZablokovanyPohyb();
                if (nepriatel.isSomMrtva()) {
                    nepriatel.skryPostavu();
                    hrac.skryPostavuHrac();
                }
            }
        }
    }
}
