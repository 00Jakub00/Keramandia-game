package oknaHry;
import fri.shapesge.FontStyle;
import fri.shapesge.Text;
import hlavnyBalikHry.Hra;
import hlavnyBalikHry.PosuvanaSprava;
import hlavnyBalikHry.ZlozkaHry;
import menuHry.*;
import postavy.*;
import postavy.ukonyPostav.PredstavenieSa;
import postavy.SmerPostavy;


import java.util.ArrayList;
import java.util.Optional;

/**
 *Predstavuje okno hry, v ktorom si hráč vybere postavu, s ktorou bude hrať
 *
 * @author Jakub Gály
 */
public class VyberPostavyOkno extends Okno {
    private final ArrayList<Menu> menu;
    private final ArrayList<Postava> postavy;
    private int poradoveCisloPostavy;
    private Optional<Postava> vybranaPostava;
    private Text infoOPostave;
    private final ZlozkaHry zlozkaHry;

    /**
     * Konštruktor nastaví menu okna, pridá postavy, z ktorých si hráč bude môcť vybrať
     * @param zlozkaHry
     */
    public VyberPostavyOkno(ZlozkaHry zlozkaHry) {
        super();
        this.zlozkaHry = zlozkaHry;
        super.nastavPoziciuMenu(this);
        this.poradoveCisloPostavy = 0;
        this.menu = super.podajMiMenu();
        this.postavy = new ArrayList<>();
        this.postavy.add(new PostavaGodoku(SmerPostavy.VPRAVO, 1));
        this.postavy.add(new PostavaVampire(SmerPostavy.VPRAVO, 1));
        this.postavy.get(this.poradoveCisloPostavy).zobrazPostavu();
        this.vybranaPostava = Optional.empty();
        this.vypisInfoOPostave();
    }

    /**
     * Metóda vykonáva daný úkon postavy, ktorý je práve nastavený
     */
    public void tik() {
        Postava postava  = this.postavy.get(this.poradoveCisloPostavy);
        postava.vykonajUkon("", "postavy",  10);
    }

    /**
     * Správa polymorfizmu
     * Metóda pridá menu
     */
    @Override
    public void nastavOkno() {
        this.menu.add(new MenuNext(167, 380, TypTextuMenu.NEXT));
        this.menu.add(new MenuBack(33, 380, TypTextuMenu.BACK));
        this.menu.add(new MenuChoose(100, 452, TypTextuMenu.CHOOSE));
        this.menu.add(new MenuBackToMenu(800, 200, TypTextuMenu.BACK_TO_MENU, this.zlozkaHry));
    }

    /**
     * Správa polymorfizmu
     * Metóda skryje celé okno
     */
    @Override
    public void vsetkoSkry() {
        for (Menu menucko : this.menu) {
            menucko.skryMenu();
        }
        for (Postava postava : this.postavy) {
            postava.skryPostavu();
        }
        this.infoOPostave.skry();
        super.prestanSparovatObjektManazer();
    }

    /**
     * Metóda vypíše text ku postave
     */
    private void vypisInfoOPostave() {
        this.infoOPostave = new Text(this.postavy.get(this.poradoveCisloPostavy).getInfoOPostave(), 500, 500);
        this.infoOPostave.zmenFont("Plain", FontStyle.PLAIN, 24);
        this.infoOPostave.zmenFarbu("#f7c00a");
        this.infoOPostave.zobraz();
    }

    /**
     * Správa polymorfizmu
     * Metóda na základe parametru sprava prepne buď na ďalšiu postavu alebo zvolí danú
     * postavu, s ktorou bude hráč hrať
     * @param sprava Môže byť (Hrac, Menu, Okno)
     */

    @Override
    public void vykonajZmenu(PosuvanaSprava sprava) {
        Menu typ = ( Menu )sprava;
        TypTextuMenu typMenu = typ.getTypTextuMenu();

        this.postavy.get(this.poradoveCisloPostavy).skryPostavu();
        switch (typMenu) {
            case NEXT -> {
                this.infoOPostave.skry();
                if (this.poradoveCisloPostavy + 1 < 2) {
                    this.poradoveCisloPostavy++;
                } else {
                    this.poradoveCisloPostavy = 0;
                }
                this.postavy.get(this.poradoveCisloPostavy).zobrazPostavu();
                this.vypisInfoOPostave();
            }
            case BACK -> {
                this.infoOPostave.skry();
                if (this.poradoveCisloPostavy - 1 >= 0) {
                    this.poradoveCisloPostavy--;
                } else {
                    this.poradoveCisloPostavy = 1;
                }
                this.postavy.get(this.poradoveCisloPostavy).zobrazPostavu();
                this.vypisInfoOPostave();
            }
            case CHOOSE -> {
                this.vybranaPostava = Optional.ofNullable(this.postavy.get(this.poradoveCisloPostavy));
                this.vybranaPostava.get().nastavPoradoveCislo(1);
                Hra hra = (Hra)this.zlozkaHry;
                hra.getHrac().nastavHranuPostavu(this.vybranaPostava.get());

                this.vybranaPostava.get().zmenAktualnyUkon(new PredstavenieSa());
            }
        }
    }
}

