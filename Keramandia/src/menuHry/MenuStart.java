package menuHry;

import hlavnyBalikHry.Hra;
import hlavnyBalikHry.ZlozkaHry;
import oknaHry.OknoHry;
import postavy.PostavaGodoku;
import postavy.PostavaVampire;

import javax.swing.*;
import java.awt.*;

/**
 *Trieda MenuStart, ktorá vykonáva polymorfizmus
 *
 * @author Jakub Gály
 */
public class MenuStart extends Menu {

    /**
     * Nastavenie menu na danú polohu s danými parametrami
     * @param x Predstavuje x-ovú súradnicu menu
     * @param y Predstavuje y-ovú súradcnicu menu
     * @param typTextu Predstavuje konkrétny typ menu
     */
    public MenuStart(int x, int y, TypTextuMenu typTextu) {
        super(x, y, typTextu, 64, 218, "menu1");
    }

    /**
     * Správa polymorfizmu. Táto metóda, konkrétne posiela správu Hre
     * @param zlozkaHry Interface ZlozkaHry (Môže byť: Okno, Hra)
     */

    @Override
    public void kliknutie(ZlozkaHry zlozkaHry) {
        Hra hra = ( Hra )zlozkaHry;
        if (hra.getHrac().jeVybranaPostava()) {
            zlozkaHry.vykonajZmenu(new OknoHry(hra.getHrac()));
        } else {
            JOptionPane.showMessageDialog(null, "Najpr si vyber postavu, potom začni hrať!");
        }
    }
}
