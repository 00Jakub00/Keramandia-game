package postavy;

import utoky.utokyGODOKU.Facka;
import utoky.utokyGODOKU.FackaZLava;
import utoky.utokyGODOKU.FackaZObochStran;

/**
 * Táto trieda predstavuje hráčsku postavu Godoku
 *
 * @author Jakub Gály
 */
public class PostavaGodoku extends Postava {
    public static final TypPostavy TYP_POSTAVY = TypPostavy.GODOKU;

    /**
     * Vytvorenie postavy
     * @param smerPostavy Počiatočný smer postavy
     * @param ktoryLevel Predstavuje hraný level
     */
    public PostavaGodoku(SmerPostavy smerPostavy, int ktoryLevel) {
        super(TYP_POSTAVY, smerPostavy, 35, ktoryLevel);
        this.nastaveniePociatocnychUtokov(ktoryLevel);
        super.nastavPostavu(this);
    }

    /**
     * Rozprávka o postave
     * @return Reťazec
     */
    @Override
    public String getInfoOPostave() {
        return """
                Ahoj, ja som slávny Godoku.\s
                Som veľmi nemilosrdný a obávaný.
                Medzi moje špeciality patrí hlavne útok facky.
                Vyber si ma!""";
    }

    /**
     * Počiatočné útoky pre hráčsku postavu pre daný level
     * @param ktoryLevel Poradové číslo levela
     */
    @Override
    public void nastaveniePociatocnychUtokov(int ktoryLevel) {
        super.pridajUtok(new Facka());
        super.pridajUtok(new FackaZLava());
       // if (ktoryLevel == 2) {
            super.pridajUtok(new FackaZObochStran());
        //}
    }
}
