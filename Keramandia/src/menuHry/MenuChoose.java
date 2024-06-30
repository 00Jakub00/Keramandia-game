package menuHry;

import hlavnyBalikHry.ZlozkaHry;

/**
 *Trieda MenuChoose, ktorá vykonáva polymorfizmus
 *
 * @author Jakub Gály
 */
public class MenuChoose extends Menu {

    /**
     * Nastavenie menu na danú polohu s danými parametrami
     * @param x Predstavuje x-ovú súradnicu menu
     * @param y Predstavuje y-ovú súradcnicu menu
     * @param typTextu Predstavuje konkrétny typ menu
     */
    public MenuChoose(int x, int y, TypTextuMenu typTextu) {
        super(x, y, typTextu, 64, 218, "menu2");
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

