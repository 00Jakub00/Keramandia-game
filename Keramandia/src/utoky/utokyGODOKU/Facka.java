package utoky.utokyGODOKU;

import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.ChodenieDolava;
import postavy.ukonyPostav.ChodenieDoprava;
import utoky.UtokVykonavanyHracom;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že sa hrac pribehne k nepriatelovi a da mu facku
 *
 * @author Jakub Gály
 */
public class Facka extends UtokVykonavanyHracom {
    private int poradoveCisloObrazkaUtoku;

    public Facka() {
        super("facka");
        this.poradoveCisloObrazkaUtoku = 1;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku facky
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        super.pripravSaNaUtok(hrac, nepriatel);
        int silaUtoku = ( int )sila * 6;
        super.nastavSiluUtoku(silaUtoku);
        if (super.getSmerUtoku() == SmerPostavy.VPRAVO) {
            hrac.getPostava().zmenAktualnyUkon(new ChodenieDoprava());
        } else {
            hrac.getPostava().zmenAktualnyUkon(new ChodenieDolava());
        }

        hrac.manazerPrestanSpravovatObjekt();
        super.manazerSpravujObjekt();
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
            case VPRAVO -> vzdialenostMedzi = Math.abs((hrac.getSuradnicaXHraca() + 70) - (nepriatel.getSuradnicaX() + 30));
            case VLAVO -> vzdialenostMedzi = Math.abs(hrac.getSuradnicaXHraca() - (nepriatel.getSuradnicaX() + nepriatel.getTypPostavy().getSirkaPostavy() - 20));
        }

        if (vzdialenostMedzi >= 8) {
            hrac.getPostava().vykonajUkon("Hrac" + hrac.getPostava().getSmerPostavy().toString(), "postavy", 10);
        } else {
            if (this.poradoveCisloObrazkaUtoku < 5) {
                postavaHraca.getStavPostavy().zmenObrazok("src/assets/postavy/" + postavaHraca.getTypPostavy().toString() + "/utoky/facka" + super.getSmerUtoku().toString() + "/" + this.poradoveCisloObrazkaUtoku + ".png");
                this.poradoveCisloObrazkaUtoku++;
            } else {
                super.ukonciUtok();
                nepriatel.uberZivot(super.getSilaUtoku());
            }
        }
    }
}

