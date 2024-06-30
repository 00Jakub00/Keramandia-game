package menuHry;
import hlavnyBalikHry.ZlozkaHry;
import oknaHry.VyberPostavyOkno;

/**
 *Trieda MenuChooseCharacter, ktorá vykonáva polymorfizmus
 *
 * @author Jakub Gály
 */
public class MenuChooseCharacter extends Menu {

    /**
     * Nastavenie menu na danú polohu s danými parametrami
     * @param x Predstavuje x-ovú súradnicu menu
     * @param y Predstavuje y-ovú súradcnicu menu
     * @param typTextu Predstavuje konkrétny typ menu
     */
    public MenuChooseCharacter(int x, int y, TypTextuMenu typTextu) {
        super(x, y, typTextu, 64, 218, "menu1");
    }

    /**
     * Správa polymorfizmu. Táto metóda, konkrétne posiela správu Hre
     * @param zlozkaHry Interface ZlozkaHry (Môže byť: Okno, Hra)
     */

    @Override
    public void kliknutie(ZlozkaHry zlozkaHry) {
        zlozkaHry.vykonajZmenu(new VyberPostavyOkno(zlozkaHry));
    }
}
