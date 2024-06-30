package menuHry;

import hlavnyBalikHry.ZlozkaHry;

/**
 *Trieda MenuBack, ktorá vykonáva polymorfizmus
 *
 * @author Jakub Gály
 */
public class MenuBack extends Menu {
    /**
     * Nastavenie menu na danú polohu s danými parametrami
     * @param suradnicaX Predstavuje x-ovú súradnicu menu
     * @param suradnicaY Predstavuje y-ovú súradcnicu menu
     * @param typTextuMenu Predstavuje konkrétny typ menu
     */
    public MenuBack(int suradnicaX, int suradnicaY, TypTextuMenu typTextuMenu) {
        super(suradnicaX, suradnicaY, typTextuMenu, 64,  125, "menu2");
    }

    /**
     * Správa polymorfizmu. Táto metóda, konkrétne posiela správu Oknu výber postavy
     * @param zlozkaHry Interface ZlozkaHry (Môže byť: Okno, Hra)
     */
    @Override
    public void kliknutie(ZlozkaHry zlozkaHry) {
        zlozkaHry.vykonajZmenu(this);
    }
}
