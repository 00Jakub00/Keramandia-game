package oknaHry;

import fri.shapesge.FontStyle;
import fri.shapesge.Text;
import hlavnyBalikHry.Hra;
import hlavnyBalikHry.PosuvanaSprava;
import hlavnyBalikHry.ZlozkaHry;
import menuHry.*;

import java.util.ArrayList;

/**
 *Predstavuje okno nastavení, ktoré služi na výber hudby
 *
 * @author Jakub Gály
 */
public class OknoSettings extends Okno {
    public static final Text TEXT_HUDBA = new Text("NASTAV SI HUDBU!",  100,  130);
    private final ArrayList<Menu> menu;
    private final ZlozkaHry zlozkaHry;

    /**
     * Správa polymorfizmu
     * Metóda vytvorí ďalši level
     * @param zlozkaHry Môže byť (Hra, Okno)
     */
    public OknoSettings(ZlozkaHry zlozkaHry) {
        super();
        this.zlozkaHry = zlozkaHry;
        super.nastavPoziciuMenu(this);
        this.menu = super.podajMiMenu();
    }

    /**
     * Správa polymorfizmu
     * Metóda pridá menu nastaví text
     */
    @Override
    public void nastavOkno() {
        this.menu.add(new MenuBackToMenu(800, 200, TypTextuMenu.BACK_TO_MENU, this.zlozkaHry));
        this.menu.add(new MenuMusicOn(150, 400, TypTextuMenu.MUSIC_ON));
        this.menu.add(new MenuMusicOff(150, 480, TypTextuMenu.MUSIC_OFF));

        OknoSettings.TEXT_HUDBA.zmenFont("Bold", FontStyle.ITALIC, 65);
        OknoSettings.TEXT_HUDBA.zmenFarbu("#ebad02");
        OknoSettings.TEXT_HUDBA.zobraz();
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
        TEXT_HUDBA.skry();
        super.prestanSparovatObjektManazer();
    }

    /**
     * Správa polymorfizmu
     * Metóda zapne alebo vypne hudbu
     * @param sprava Môže byť (Okno, Hrac, Menu)
     */
    @Override
    public void vykonajZmenu(PosuvanaSprava sprava) {
        Menu typ = ( Menu )sprava;
        Hra hra = ( Hra )this.zlozkaHry;

        if (typ instanceof MenuMusicOn) {
            hra.zapniHudbu();
        } else {
            hra.vypniHudbu();
        }
    }
}
