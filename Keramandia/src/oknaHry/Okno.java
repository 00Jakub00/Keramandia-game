package oknaHry;

import fri.shapesge.Manazer;
import hlavnyBalikHry.PosuvanaSprava;
import hlavnyBalikHry.ZlozkaHry;
import menuHry.Menu;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Predstavuje základ pre každé okno hry, obsauje menu, manazera atď.. Avšak každé okno si vytvorí pridá svoje menu po ä
 * svojom
 *
 * @author Jakub Gály
 */
public abstract class Okno implements ZlozkaHry, PosuvanaSprava {
    private final Manazer manazer;
    private final ArrayList<Menu> menu;
    private Optional <Menu> zvyrazneneMenu;
    private ZlozkaHry poziciaMenuVHre;

    /**
     * Konštruktor nastaví atribúty na počiatočný stav
     * Zatial nemá žiadne menu
     */

    public Okno() {
        this.poziciaMenuVHre = null;
        this.menu = new ArrayList<>();
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.zvyrazneneMenu = Optional.empty();
    }

    /**
     * Metóda vráti kontajter menu
     * @return Inštancia triedy Arraylist.
     */
    protected ArrayList<Menu> podajMiMenu() {
        return this.menu;
    }

    /**
     * Metóda spravovaná manazerom, ktorá zvýrazňuje dané menu, na ktoré akurát ukazuje myška
     * @param x x-ová súradnica myšky
     * @param y y-ová súradnica myšky
     */
    public void mouseMove(int x, int y) {
        var zlozkaMenu  = this.jeTamNejakeMenu(x, y);

        if (zlozkaMenu.isPresent()) {
            zlozkaMenu.get().zvirazniOkno(true);
            this.zvyrazneneMenu = zlozkaMenu;
            return;
        }

        if (this.zvyrazneneMenu.isPresent()) {
            this.zvyrazneneMenu.get().zvirazniOkno(false);
            this.zvyrazneneMenu = Optional.empty();
        }
    }

    /**
     * Metóda posiela správu daneému menu na, ktoré hráč klikol
     * @param x x-ová súradnica myšky
     * @param y y-ová súradnica myšky
     */
    public void zvolKliknutim(int x, int y) {
        var zlozkaMenu  = this.jeTamNejakeMenu(x, y);
        if (zlozkaMenu.isPresent()) {
            zlozkaMenu.get().kliknutie(this.poziciaMenuVHre);
        }
    }

    /**
     * Metóda zisťuje, či tam kde sa akurát nachádza myška, či sa tam nadháza aj nejaké menu
     * @param x x-ová súradnica myšky
     * @param y y-ová súradnica myšky
     * @return Vrati danú inštanciu menu alebo vráti prázdno
     */
    private Optional<Menu> jeTamNejakeMenu(int x, int y) {
        for (Menu zlozkaMenu : this.menu) {
            if (x >= zlozkaMenu.getSuradnicaX() && x <= zlozkaMenu.getSuradnicaX() + zlozkaMenu.getRozmerMenu(1)) {
                if (y >= zlozkaMenu.getSuradnicaY() && y <= zlozkaMenu.getSuradnicaY() + zlozkaMenu.getRozmerMenu(0)) {
                    return Optional.of(zlozkaMenu);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Metóda posiela správu manazerovi nech sa už nestará o objekt
     */
    protected void prestanSparovatObjektManazer() {
        this.manazer.prestanSpravovatObjekt(this);
    }

    /**
     * Metóda polymorfizmu, ktorá nastavuje danéOkno
     */
    public void nastavOkno() {

    }

    /**
     * Metóda polymorfizmu, ktorá v danom okne všetko skryje
     */
    public abstract void vsetkoSkry();

    /**
     * Metóda polymorfizmu s interface zlozkaHry
     * @param sprava Môže byť (Menu, Hrac, Okno)
     */
    @Override
    public void vykonajZmenu(PosuvanaSprava sprava) {

    }

    /**
     * Nastaví sa pozicia menu v hre, to znamená, že keď sa klikne na menu
     * tak na tu pozicu bude menu posielať správu
     * @param poziciaMenuVHre
     */
    protected void nastavPoziciuMenu(ZlozkaHry poziciaMenuVHre) {
        this.poziciaMenuVHre = poziciaMenuVHre;
    }
}
