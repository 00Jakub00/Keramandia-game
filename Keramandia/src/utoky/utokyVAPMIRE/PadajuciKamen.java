package utoky.utokyVAPMIRE;

import fri.shapesge.Kruh;
import hlavnyBalikHry.Hrac;
import postavy.nepriatelia.Nepriatel;
import utoky.UtokVykonavanyHracom;

import java.util.Random;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že hráč nad nepriatelom spawne kamen, kamen zacne padat
 * a nepriatel sa mu bud vyhne alebo nie
 *
 * @author Jakub Gály
 */
public class PadajuciKamen extends UtokVykonavanyHracom {
    private final int priemerKamena;
    private int suradicaYKamena;
    private int suradicaXKamena;
    private boolean daSaEsteZautocit;
    private Kruh kamen;

    /**
     * Vytvorenie útoku
     */
    public PadajuciKamen() {
        super("padajuciKamen");
        this.priemerKamena = 50;
        this.suradicaYKamena = -30;
        this.suradicaXKamena = 0;
        this.daSaEsteZautocit = true;
        this.kamen = null;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku padajúceho kameňa
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */
    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        super.pripravSaNaUtok(hrac, nepriatel);
        int silaUtoku = ( int )sila * 5;
        super.nastavSiluUtoku(silaUtoku);
        super.manazerSpravujObjekt();
        hrac.nastavZablokovanyPohyb();
        this.suradicaXKamena = super.getNepriatel().getSuradnicaX() + 50;
        this.kamen = new Kruh(this.suradicaXKamena, this.suradicaYKamena);
        this.kamen.zmenPriemer(this.priemerKamena);
        this.kamen.zmenFarbu("black");
        this.kamen.zobraz();
    }

    /**
     * Metóda spravovaná manazerom.
     * Vykonáva tento útok
     */

    public void vykonavajUtok() {
        this.kamen.posunZvisle(20);
        this.suradicaYKamena += 20;

        if (this.suradicaYKamena + this.priemerKamena + 20 > super.getNepriatel().getSuradnicaY() && this.daSaEsteZautocit) {
            Random r = new Random();
            int a = r.nextInt(5) + 1;
            if (a == 1) {
                int suradncicaXPremiestenia = 0;
                do {
                    suradncicaXPremiestenia = r.nextInt(901) + 30;
                } while (!this.dotykaSaSuradincaKamenaAleboHraca(suradncicaXPremiestenia));
                super.getNepriatel().nastavPolohu(suradncicaXPremiestenia, super.getNepriatel().getSuradnicaY());
            } else {
                super.getNepriatel().uberZivot(super.getSilaUtoku());
            }
            this.daSaEsteZautocit = false;
        }
        if (this.suradicaYKamena > 750) {
            super.getHrac().nastavZablokovanyPohyb();
            super.manazerPrestanSpravovatObjekt();
            super.getNepriatel().zobrazPostavu();
            super.getHrac().nechZautociNepriatel();
            super.getHrac().getPostava().resetujUtoky();
        }
    }

    private boolean dotykaSaSuradincaKamenaAleboHraca(int suradnicaX) {
        boolean a = suradnicaX >= this.suradicaXKamena - 15 && suradnicaX <= this.suradicaXKamena + this.priemerKamena + 15;
        boolean b = suradnicaX >= super.getHrac().getSuradnicaXHraca() - 15 && suradnicaX <= super.getHrac().getSuradnicaXHraca() + super.getHrac().getPostava().getTypPostavy().getSirkaPostavy() + 15;
        return !a && !b;
    }
}
