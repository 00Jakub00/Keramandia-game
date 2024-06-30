package hlavnyBalikHry;

import oknaHry.Okno;
import oknaHry.UvitacieOkno;
import postavy.Postava;
import postavy.PostavaGodoku;
import postavy.PostavaVampire;
import postavy.SmerPostavy;

/**
 * Základná trieda celého programu, ktorá riadi program, prepíná
 * medzi oknami hry atď...
 *
 * @author Jakub Gály
 */
public class Hra implements ZlozkaHry {
    public static final String CESTA_K_POZADIU = "src/assets/pozadie.jpg";
    private final Hudba hudba;
    private Okno aktualneOkno;
    private Hrac hrac;

    /**
     * Konštruktok triedy Hrac, vytvorí prvé okno (uvítacie okno),
     * Vytvorí hráča a hudbu.
     */
    public Hra() {
        this.aktualneOkno = new UvitacieOkno(this);
        this.aktualneOkno.nastavOkno();
        this.hrac = new Hrac(this);
        this.hudba = new Hudba();
    }

    /**
     * Metóda vracia hráča
     * @return inštancia triedy Hráč
     */
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Správa polymorfizmu, pri tejto metóde sa načíta nové okno hry.
     * @param sprava
     */

    public void vykonajZmenu(PosuvanaSprava sprava) {
        this.aktualneOkno.vsetkoSkry();
        this.aktualneOkno = ( Okno )sprava;
        this.aktualneOkno.nastavOkno();
    }

    /**
     * Metóda vypne progran
     */
    public void vypniHru() {
        System.exit(0);
    }

    /**
     * Metóda všetko, čo bolo nakreslnené na plátne skryje, vypíše sa Game Over,
     * vypne sa hudba
     */
    public void prehra() {
        this.aktualneOkno.vsetkoSkry();
        this.hrac.getPostava().vymazUtoky();
        this.hrac.manazerPrestanSpravovatObjekt();
        this.hrac.getAktualnyNepriatel().get().manazerPrestanSpravovatObjekt();
        this.vypniHudbu();
    }

    /**
     * Vytvorí novú postavu podľa toho aká postava sa hrala v prvom leveli.
     * Vytvorí nového hráča a nastaví mu postavu
     * Metóda povie oknu hry, aby načítalo ďalší level-
     * @param ktoryLevel Poradové čislo levela
     */
    public void nextLevel(int ktoryLevel) {
        Postava novaPostava = null;
        if (this.hrac.getPostava() instanceof PostavaGodoku) {
            novaPostava = new PostavaGodoku(SmerPostavy.VPRAVO, ktoryLevel);
        } else {
            novaPostava = new PostavaVampire(SmerPostavy.VPRAVO, ktoryLevel);
        }
        this.hrac.getPostava().vymazUtoky();
        this.hrac.getPostava().skryZivot();
        this.hrac.skryPostavuHrac();
        this.hrac = new Hrac(this);
        this.hrac.nastavHranuPostavu(novaPostava);
        this.aktualneOkno.vykonajZmenu(this.hrac);
    }

    /**
     * Metóda posiela správu na zapnutie hudby.
     */
    public void zapniHudbu() {
        this.hudba.hrajHudbu();
    }

    /**
     * Metóda posiela správu na vypnutie hudby.
     */
    public void vypniHudbu() {
        this.hudba.prestanHratHudbu();
    }
}
