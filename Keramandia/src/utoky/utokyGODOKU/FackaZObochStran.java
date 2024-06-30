package utoky.utokyGODOKU;

import hlavnyBalikHry.Hrac;
import postavy.nepriatelia.Nepriatel;
import utoky.UtokVykonavanyHracom;

import java.util.Random;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že hráč sa snaží klikať na nepriatela, ked sa mu to
 * podari tak nepriatelovi ubere zivot
 * Nepriatel sa premiestnuje.
 *
 * @author Jakub Gály
 */
public class FackaZObochStran extends UtokVykonavanyHracom {

    //Nefunkcna metoda
    private boolean ukazeMysNaNepriatela;
    private int cakaciaDoba;
    private int pocetPremiestneni;
    public FackaZObochStran() {
        super("fackaZObochStran");
        this.ukazeMysNaNepriatela = false;
        this.cakaciaDoba = 0;
        this.pocetPremiestneni = 0;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku facky z oboch strán
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        super.pripravSaNaUtok(hrac, nepriatel);
        int silaUtoku = ( int )sila * 2;
        super.nastavSiluUtoku(silaUtoku);
        super.manazerSpravujObjekt();
    }

    /**
     * Metóda spravovaná manazerom.
     * Vykonáva tento útok
     */

    public void vykonavajUtok() {
        if (this.ukazeMysNaNepriatela) {
            this.cakaciaDoba++;
            if (this.cakaciaDoba > 12) {
                this.cakaciaDoba = 0;
                this.ukazeMysNaNepriatela = false;
                this.zmenPoziciuNepriatel();
                this.pocetPremiestneni++;
            }
            if (this.pocetPremiestneni == 4) {
                super.manazerPrestanSpravovatObjekt();
                super.getNepriatel().zobrazPostavu();
                super.getHrac().nechZautociNepriatel();
            }
        }
    }

    /**
     * Metóda spravovaná manazerom, sleduje pozíciu myšky
     * @param x x-ová poloha myšky
     * @param y y-ová poloha myšky
     */
    public void mouseMove(int x, int y) {
        this.ukazeMysNaNepriatela = this.ukazeMyskaNaNepriatela(x , y);
    }

    /**
     * Metóda spravovaná manazerom, sleduje na aké miesto sme klikli
     * @param x x-ová pozícia myšky
     * @param y y-ová pozícia myšky
     */
    public void zvolKliknutim(int x, int y) {
        if (this.ukazeMysNaNepriatela && this.ukazeMyskaNaNepriatela(x , y)) {
            super.getNepriatel().uberZivot(super.getSilaUtoku());
        }
    }

    private void zmenPoziciuNepriatel() {
        Random r = new Random();
        int suradnicaX = r.nextInt(981) + 20;
        this.getNepriatel().nastavPolohu(suradnicaX, this.getNepriatel().getSuradnicaY());
    }

    private boolean ukazeMyskaNaNepriatela(int x, int y) {
        int sirkaPostavy = super.getNepriatel().getTypPostavy().getSirkaPostavy();
        if (x >= super.getNepriatel().getSuradnicaX() && x <= super.getNepriatel().getSuradnicaX() + sirkaPostavy) {
            if (y >= super.getNepriatel().getSuradnicaY() && y <= super.getNepriatel().getSuradnicaY() + 163) {
                return true;
            }
        }
        return false;
    }
}
