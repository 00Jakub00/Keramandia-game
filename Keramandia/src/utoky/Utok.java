package utoky;

import fri.shapesge.Manazer;
import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.StatieNaMieste;

/**
 * Trieda predstavuje šablonu pre útok v Keramandii
 * Názov útoku, manazera...
 *
 * @author Jakub Gály
 */
public abstract class Utok {
    private final String nazovUtoku;
    private final Manazer vykonavacUtokov;
    private SmerPostavy smerUtoku;
    private int vychodziaXovaPoziciaHraca;
    private Nepriatel nepriatel;
    private Hrac hrac;

    /**
     * Vytvorenie nového útoku
     * @param nazovUtoku Reťazec
     */
    public Utok(String nazovUtoku) {
        this.nazovUtoku = nazovUtoku;
        this.vykonavacUtokov = new Manazer();
        this.hrac = null;
        this.nepriatel = null;
        this.smerUtoku = null;
        this.vychodziaXovaPoziciaHraca = 0;
    }

    /**
     * Vráti hráča
     * @return Inštancia triedy Hrac
     */
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Vráti nepriatela
     * @return Inštancia triedy Postava
     */
    public Nepriatel getNepriatel() {
        return this.nepriatel;
    }

    /**
     * Vráti smer útoky postavy
     * @return Inštancia triedy SmerPostavy
     */
    public SmerPostavy getSmerUtoku() {
        return this.smerUtoku;
    }

    /**
     * Vráti názov útoki
     * @return Reťazec
     */
    public String getNazovUtoku() {
        return this.nazovUtoku;
    }

    /**
     * Vráti x-ová pozíciu hráča na ktorej začal útok
     * @return Celočíselná hodnota
     */
    public int getVychodziaXovaPoziciaHraca() {
        return this.vychodziaXovaPoziciaHraca;
    }

    /**
     * Nastavenie počiatočných parametrov pred útokom pre hráča
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     */
    public void pripravSaNaUtok(Hrac hrac, Nepriatel nepriatel) {
        this.hrac = hrac;
        this.nepriatel = nepriatel;
        this.vychodziaXovaPoziciaHraca = hrac.getSuradnicaXHraca();
        int vzdialenostMedzi = Math.abs(hrac.getSuradnicaXHraca() - nepriatel.getSuradnicaX());
        int dalsiaVzdialenost = Math.abs(hrac.getSuradnicaXHraca() + 10 - nepriatel.getSuradnicaX());
        if (dalsiaVzdialenost < vzdialenostMedzi) {
            this.smerUtoku = SmerPostavy.VPRAVO;
        } else {
            this.smerUtoku = SmerPostavy.VLAVO;
        }
        hrac.getPostava().zmenSmerPostavy(this.smerUtoku);
    }

    /**
     * Nastavenie počiatočných parametrov pred útokom pre nepriatela
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     */
    public void pripravSaNaUtok(Nepriatel nepriatel, Hrac hrac) {
        this.hrac = hrac;
        this.nepriatel = nepriatel;
        this.vychodziaXovaPoziciaHraca = nepriatel.getSuradnicaX();
        int vzdialenostMedzi = Math.abs(nepriatel.getSuradnicaX() - hrac.getSuradnicaXHraca());
        int dalsiaVzdialenost = Math.abs(nepriatel.getSuradnicaX() + 10 - hrac.getSuradnicaXHraca());
        if (dalsiaVzdialenost < vzdialenostMedzi) {
            this.smerUtoku = SmerPostavy.VPRAVO;
        } else {
            this.smerUtoku = SmerPostavy.VLAVO;
        }
        nepriatel.zmenSmerPostavy(this.smerUtoku);
        nepriatel.manazerPrestanSpravovatObjekt();
    }

    /**
     * Ukončenie útoky pre hráča
     */
    public void ukonciUtok() {
        Postava postavaHraca = this.hrac.getPostava();
        this.hrac.zmenXovuSuracnicuHrac(this.vychodziaXovaPoziciaHraca);
        postavaHraca.zmenAktualnyUkon(new StatieNaMieste());
        this.manazerPrestanSpravovatObjekt();
        this.hrac.manazerSpravujObjekt();
        this.hrac.nechZautociNepriatel();
    }
    /**
     * Ukončenie útoky pre nepriatela
     */
    public void ukonciUtok(boolean nepriatel) {
        this.nepriatel.nastavPolohu(this.vychodziaXovaPoziciaHraca, this.nepriatel.getSuradnicaY());
        this.nepriatel.zmenAktualnyUkon(new StatieNaMieste());
        this.manazerPrestanSpravovatObjekt();
        this.nepriatel.resetujUtoky();
        Nepriatel n = ( Nepriatel )this.nepriatel;
        n.manazerSPravujObjekt();
        this.hrac.mozesUtocic();
    }

    /**
     * Správa polymorfizmu
     * Táto metóda sa nikdy nevykoná
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */
    public abstract void zautoc(Hrac hrac, Nepriatel nepriatel, double sila);

    /**
     * Správa manazerovi, aby sa začal starať o objekt
     */

    public void manazerSpravujObjekt() {
        this.vykonavacUtokov.spravujObjekt(this);
    }

    /**
     * Správa manazerovi, aby sa prestal starať o objekt
     */

    public void manazerPrestanSpravovatObjekt() {
        this.vykonavacUtokov.prestanSpravovatObjekt(this);
    }

    /**
     * Nastavenie smeru útoku, v ktorom bude útočiť postava
     * @param smerUtoku inštancia triedy SmerPostavy
     */
    protected void nastavSmerUtoku(SmerPostavy smerUtoku) {
        this.smerUtoku = smerUtoku;
    }
}
