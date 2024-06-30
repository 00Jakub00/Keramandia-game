package utoky.utokyBERSERK;

import fri.shapesge.Kruh;
import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.Zomretie;
import utoky.Utok;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že nepriatel spawne nad hracom balvan a hrac sa mu musi uhnut
 *
 * @author Jakub Gály
 */
public class UtokBalvan extends Utok {
    private Kruh balvan;
    private int suradnicaXBalvana;
    private int suradnicaYBalvana;
    private final int priemerBalvana;

    public UtokBalvan() {
        super("balvan");
        this.balvan = null;
        this.suradnicaXBalvana = 0;
        this.suradnicaYBalvana = -30;
        this.priemerBalvana = 50;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku balvana
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        if (!nepriatel.isSomMrtva() && !(nepriatel.getAktualnyUkonPostavy() instanceof Zomretie)) {
            super.pripravSaNaUtok(nepriatel, hrac);
            this.balvan = new Kruh(hrac.getSuradnicaXHraca(), this.suradnicaYBalvana);
            this.suradnicaXBalvana = hrac.getSuradnicaXHraca();
            this.balvan.zmenFarbu("black");
            this.balvan.zmenPriemer(this.priemerBalvana);
            this.balvan.zobraz();
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

        this.balvan.posunZvisle(15);
        this.suradnicaYBalvana += 15;
        if (this.nastalaKolizia(hrac)) {
            hrac.uberZivot(7);
            this.balvan.skry();
            super.manazerPrestanSpravovatObjekt();
            nepriatel.resetujUtoky();
            hrac.mozesUtocic();
        } else if (this.suradnicaYBalvana > 750) {
            this.balvan.skry();
            super.manazerPrestanSpravovatObjekt();
            nepriatel.resetujUtoky();
            hrac.mozesUtocic();
        }
    }
    private boolean nastalaKolizia(Hrac hrac) {
        if (hrac.getPostava().getSuradnicaX() >= this.suradnicaXBalvana &&
                hrac.getSuradnicaXHraca() <= this.suradnicaXBalvana + this.priemerBalvana) {
            return this.suradnicaYBalvana + this.priemerBalvana >= hrac.getPostava().getSuradnicaY() + 20;
        } else if (hrac.getPostava().getSuradnicaX() + hrac.getPostava().getTypPostavy().getSirkaPostavy() >= this.suradnicaXBalvana &&
                hrac.getPostava().getTypPostavy().getSirkaPostavy() <= this.suradnicaXBalvana + this.priemerBalvana) {
            return this.suradnicaYBalvana + this.priemerBalvana >= hrac.getPostava().getSuradnicaY() + 20;
        }
        return false;
    }
}
