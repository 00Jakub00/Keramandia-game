package utoky.utokyBERSERK;

import fri.shapesge.Elipsa;
import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.Zomretie;
import utoky.Utok;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že hrac sa snazi utekat a nepriatel sa zakazdym teleportne na
 * hraca a snazi sa ho seknut
 *
 * @author Jakub Gály
 */
public class Prenasledovanie extends Utok {
    private int casCakania;
    private int suradnicaXTiena;
    private int poradoveCisloObrazkaUtoku;
    private boolean vytvorilSaTien;
    private boolean nastaloPremiestenie;
    private int pocetVykonanychCyklov;
    private int pocetPremiesteni;
    private Elipsa tien;
    public Prenasledovanie() {
        super("prenasledovanie");
        this.casCakania = 0;
        this.suradnicaXTiena = 0;
        this.poradoveCisloObrazkaUtoku = 1;
        this.vytvorilSaTien = false;
        this.nastaloPremiestenie = false;
        this.pocetVykonanychCyklov = 0;
        this.pocetPremiesteni = 0;
        this.tien = null;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku prenásledovania
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        if (!nepriatel.isSomMrtva() && !(nepriatel.getAktualnyUkonPostavy() instanceof Zomretie)) {
            super.pripravSaNaUtok(nepriatel, hrac);
            Nepriatel postavaNepriatela = super.getNepriatel();
            postavaNepriatela.skryPostavu();
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

        if (this.casCakania < 35) {
            nepriatel.skryPostavu();
            if (!this.vytvorilSaTien) {
                this.suradnicaXTiena = hrac.getSuradnicaXHraca();
                this.tien = new Elipsa(this.suradnicaXTiena, 655);
                this.tien.zmenOsi(90, 40);
                this.tien.zmenFarbu("black");
                this.tien.zobraz();
                this.vytvorilSaTien = true;
            }
            this.casCakania++;
        } else {
            if (!this.nastaloPremiestenie) {
                nepriatel.nastavPolohu(this.suradnicaXTiena, nepriatel.getSuradnicaY());
                nepriatel.zobrazPostavu();
                this.nastaloPremiestenie = true;
            }
            if (this.poradoveCisloObrazkaUtoku < 2) {
                nepriatel.getStavPostavy().zmenObrazok("src/assets/nepriatelia/" + nepriatel.getTypPostavy().toString() + "/utoky/prenasledovanie" + super.getSmerUtoku().toString() + "/" + this.poradoveCisloObrazkaUtoku + ".png");
                this.poradoveCisloObrazkaUtoku++;
            } else if (this.pocetVykonanychCyklov < 3) {
                this.pocetVykonanychCyklov++;
                this.poradoveCisloObrazkaUtoku = 1;
            } else if (this.pocetPremiesteni < 5) {
                if (this.hitolNepriatelHraca(hrac,  nepriatel)) {
                    hrac.uberZivot(4);
                }
                this.nastavNovySmerUtoku();
                this.pocetPremiesteni++;
                this.nastaloPremiestenie = false;
                this.vytvorilSaTien = false;
                this.casCakania = 0;
                this.poradoveCisloObrazkaUtoku = 1;
                this.pocetVykonanychCyklov = 0;
                this.tien.skry();
            } else {
                this.tien.skry();
                super.ukonciUtok(true);
                if (nepriatel.isSomMrtva()) {
                    nepriatel.skryPostavu();
                    hrac.skryPostavuHrac();
                }
            }
        }
    }

    private void nastavNovySmerUtoku() {
        Hrac hrac = super.getHrac();
        Nepriatel nepriatel = super.getNepriatel();

        int vzdialenostMedzi = Math.abs(nepriatel.getSuradnicaX() - hrac.getSuradnicaXHraca());
        int dalsiaVzdialenost = Math.abs(nepriatel.getSuradnicaX() + 10 - hrac.getSuradnicaXHraca());
        if (dalsiaVzdialenost < vzdialenostMedzi) {
            super.nastavSmerUtoku(SmerPostavy.VPRAVO);
        } else {
            super.nastavSmerUtoku(SmerPostavy.VLAVO);
        }
    }

    private boolean hitolNepriatelHraca(Hrac hrac, Nepriatel nepriatel) {
        return hrac.getSuradnicaXHraca() >= nepriatel.getSuradnicaX() && hrac.getSuradnicaXHraca() + hrac.getPostava().getTypPostavy().getSirkaPostavy() <= nepriatel.getSuradnicaX() + 168;
    }
}
