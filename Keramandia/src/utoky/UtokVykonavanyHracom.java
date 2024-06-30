package utoky;

import fri.shapesge.Obrazok;
import fri.shapesge.Stvorec;
import hlavnyBalikHry.Hrac;
import postavy.nepriatelia.Nepriatel;

/**
 * Predstavuje triedy útoku pre hráča
 * V tejto triede sa vytvára aj ikona útoku
 *
 * @author Jakub Gály
 */
public abstract class  UtokVykonavanyHracom extends Utok {
    public static final int ROZMER_IKONY = 65;
    private Stvorec pozadie;
    private Obrazok ikonaUtoku;
    private int suradnicaXIkony;
    private int suradnicaYIkony;
    private int silaUtoku;

    /**
     * Vytvorenie útoku
     * @param nazovUtoku Reťazec
     */
    public UtokVykonavanyHracom(String nazovUtoku) {
        super(nazovUtoku);
        this.pozadie = null;
        this.ikonaUtoku = null;
        this.suradnicaXIkony = -878;
        this.suradnicaYIkony = -878;
        this.silaUtoku = 0;
    }

    /**
     * Správa polymorfizmu
     * Táto metóda sa nikdy nevykoná
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */
    @Override
    public abstract void zautoc(Hrac hrac, Nepriatel nepriatel, double sila);

    /**
     * Vráti x-ovú súradnicu ikony
     * @return Celočíselná hodnota
     */
    public int getSuradnicaXIkony() {
        return this.suradnicaXIkony;
    }

    /**
     * Vráti y-ovú súradnicu ikony
     * @return Celočíselná hodnota
     */
    public int getSuradnicaYIkony() {
        return this.suradnicaYIkony;
    }

    /**
     * Vytvorí ikonu útoky na danom mieste plátna
     * @param x x-ová súradnica
     * @param y y-ová súradnica
     */
    public void vytvorIkonuUtoku(int x, int y) {
        this.pozadie = new Stvorec(x, y);
        this.pozadie.zmenStranu(UtokVykonavanyHracom.ROZMER_IKONY);
        this.pozadie.zmenFarbu("red");
        this.pozadie.zobraz();
        this.ikonaUtoku = new Obrazok("src/assets/ikonyUtokov/" + super.getNazovUtoku() + ".png", x + 10, y + 10);
        this.ikonaUtoku.zobraz();
        this.suradnicaXIkony = x;
        this.suradnicaYIkony = y;
    }

    /**
     * Zmení farbu ikony na danú farbu
     * @param farba Farba
     */
    public void zmenFarbuIkony(String farba) {
        this.pozadie.zmenFarbu(farba);
    }

    /**
     * Skryje ikonu útoku
     */
    public void skry() {
        this.pozadie.skry();
        this.ikonaUtoku.skry();
    }

    /**
     * Nastaví silu útoku pre daná útok
     * @param sila Celočíselná hodnota
     */
    public void nastavSiluUtoku(int sila) {
        this.silaUtoku = sila;
    }

    /**
     *Vráti silu útoku
     * @return Celočíselná hodnota
     */
    public int getSilaUtoku() {
        return this.silaUtoku;
    }

}
