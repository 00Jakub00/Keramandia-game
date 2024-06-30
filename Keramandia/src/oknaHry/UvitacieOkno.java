package oknaHry;
import fri.shapesge.FontStyle;
import fri.shapesge.Obrazok;
import hlavnyBalikHry.Hra;
import hlavnyBalikHry.ZlozkaHry;
import menuHry.*;
import fri.shapesge.Text;
import java.util.ArrayList;

/**
 *Predstavuje privítacie okno, ktoré služi na privítanie hráča, je tu základne menu
 *
 * @author Jakub Gály
 */
public class UvitacieOkno extends Okno {
    public static final Text NAZOV_HRY = new Text("KERAMANDIA",  220,  130);
    private final ArrayList<Menu> menu;
    private Obrazok pozadie;

    /**
     *Nastaví atribúty, vytvorý pozadie
     *
     * @param poziciaMenuVHre Môže byť (Hra, Okno)
     */
    public UvitacieOkno(ZlozkaHry poziciaMenuVHre) {
        super();
        super.nastavPoziciuMenu(poziciaMenuVHre);
        this.pozadie = null;
        this.menu = super.podajMiMenu();
        this.pozadie = new Obrazok(Hra.CESTA_K_POZADIU, 0, 0);
        this.pozadie.zobraz();
    }
    /**
     * Správa polymorfizmu
     * Metóda pridá menu, nastaví text
     */
    @Override
    public void nastavOkno() {
        UvitacieOkno.NAZOV_HRY.zmenFont("Bold", FontStyle.ITALIC, 100);
        UvitacieOkno.NAZOV_HRY.zmenFarbu("#ebad02");
        UvitacieOkno.NAZOV_HRY.zobraz();

        this.menu.add(new MenuStart(440, 250, TypTextuMenu.START_GAME));
        this.menu.add(new MenuChooseCharacter(440, 330, TypTextuMenu.CHOOSE_CHARACTER));
        this.menu.add(new MenuSettings(440, 410, TypTextuMenu.SETTINGS));
        this.menu.add(new MenuQuit(440, 490, TypTextuMenu.QUIT));
    }

    /**
     * Správa polymorfizmu
     * Metóda skryje celé okno
     */
    @Override
    public void vsetkoSkry() {
        for (Menu zlozkaMenu : this.menu) {
            zlozkaMenu.skryMenu();
        }
        NAZOV_HRY.skry();
        super.prestanSparovatObjektManazer();
    }
}
