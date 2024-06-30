package menuHry;

import hlavnyBalikHry.ZlozkaHry;

/**
 *Trieda MenuMusicOff, ktorá vykonáva polymorfizmus
 *
 * @author Jakub Gály
 */
public class MenuMusicOff extends Menu {

    /**
     * Nastavenie menu na danú polohu s danými parametrami
     * @param suradnicaX Predstavuje x-ovú súradnicu menu
     * @param suradnicaY Predstavuje y-ovú súradcnicu menu
     * @param typTextuMenu Predstavuje konkrétny typ menu
     */
    public MenuMusicOff(int suradnicaX, int suradnicaY, TypTextuMenu typTextuMenu) {
        super(suradnicaX, suradnicaY, typTextuMenu, 64,  218, "menu1");
    }

    /**
     * Správa polymorfizmu. Táto metóda, konkrétne posiela správu Oknu Settings
     * @param zlozkaHry Interface ZlozkaHry (Môže byť: Okno, Hra)
     */
    @Override
    public void kliknutie(ZlozkaHry zlozkaHry) {
        zlozkaHry.vykonajZmenu(this);
    }
}
