package levely;

import fri.shapesge.FontStyle;
import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import fri.shapesge.Text;
import hlavnyBalikHry.Hrac;
import postavy.nepriatelia.Nepriatel;

/**
 * Trieda Level je abstraktná trieda na vytváranie levelov
 *
 * @author Jakub Gály
 */
public abstract class Level {
    private final Text uvodnyNapis;
    private int suradnicaYNapisu;
    private final Obrazok pozadie;
    private final Hrac hrac;
    private final Manazer manazer;
    private Level level;
    private Nepriatel nepriatel;
    private boolean skoncilaAnimacia;
    public static final int SURADNICA_X_LEVELOV = 0;
    public static final int MAX_SURADNICA_X_LEVELOV = 1100;

    /**
     * Vytvorenie levela, začatie animácie
     * @param hrac Inštancia triedy HRac
     * @param cestaKObrazku Cesta k pozadiu, ktoré bude na pozadí
     * @param textLevel Reťazec (Môže byť: LEVEL 1, LEVEL 2...)
     */
    public Level(Hrac hrac, String cestaKObrazku, String textLevel) {
        this.nepriatel = null;
        this.skoncilaAnimacia = false;
        this.level = null;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.hrac = hrac;
        this.pozadie = new Obrazok(cestaKObrazku, 0, 0);
        this.pozadie.zobraz();

        this.uvodnyNapis = new Text(textLevel, 450, 740);
        this.uvodnyNapis.zmenFont("Bold", FontStyle.BOLD, 50);
        this.uvodnyNapis.zmenFarbu("#db9f07");
        this.uvodnyNapis.zobraz();
        this.suradnicaYNapisu = 740;
    }

    /**
     * Vráti hráča
     * @return Inštancia triedy Hrac
     */
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Metóda spravovaná manazerom
     * Text levelu sa preanimuje zdola plátna až hore, následne sa začne level
     */
    public void animacia() {
        if (!this.skoncilaAnimacia) {
            if (this.suradnicaYNapisu > 0) {
                this.uvodnyNapis.posunZvisle(-4);
                this.suradnicaYNapisu -= 4;
                this.hrac.skryPostavuHrac();
            } else if (!this.hrac.jeZobrazeny()) {
                this.hrac.nastavVychodziUkonHraca();
                this.hrac.nastavVychodziuPoziciuPostavy();
                this.level.vytvorNepriatelaANastavHraca();
                this.hrac.nastavAktualnehoNepriatela(this.nepriatel);
                this.skoncilaAnimacia = true;
                this.manazer.prestanSpravovatObjekt(this);
            }
        }
    }

    /**
     * Nastavenie aktualneho hraneho levela
     * @param level Inštancia triedy Level
     */
    public void nastavHranyLevel(Level level) {
        this.level = level;
    }

    /**
     * Správa polymorfizmu
     * Táto meóda nerobí nič
     */
    public abstract void vytvorNepriatelaANastavHraca();

    /**
     * Metóda nastaví nepriateľa pre daný level
     * @param nepriatel Inštancia triedy Nepriatel
     */
    public void nastavNepriatela(Nepriatel nepriatel) {
        this.nepriatel = nepriatel;
        this.nepriatel.zobrazPostavu();
    }

    /**
     * Skryje celý level
     */
    public void skryLevel() {
        this.pozadie.skry();
        this.nepriatel.skryZivot();
        this.nepriatel.skryPostavu();
    }
}
