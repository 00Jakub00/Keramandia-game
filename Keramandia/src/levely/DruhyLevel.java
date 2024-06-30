package levely;

import hlavnyBalikHry.Hrac;
import postavy.PostavaGodoku;
import postavy.nepriatelia.Onre;
import postavy.SmerPostavy;
import postavy.TypPostavy;
import utoky.utokyGODOKU.FackaZObochStran;

/**
 * Predstavuje druhý level hry
 *
 * @author Jakub Gály
 */
public class DruhyLevel extends Level {

    /**
     * Vytvorenie prvého levela, s cestou ku pozadiu
     * @param hrac Inštancia triedy Hrac
     */
    public DruhyLevel(Hrac hrac) {
        super(hrac, "src/assets/levely/2.png"  , "LEVEL 2");
        super.nastavHranyLevel(this);
    }

    /**
     * Správa polymorfizmu
     * Vytvorenie nepriatela a vykonanie nastavení pre hraca pre druhý level
     */
    @Override
    public void vytvorNepriatelaANastavHraca() {
        super.nastavNepriatela(new Onre(TypPostavy.ONRE, SmerPostavy.VLAVO,  950,  485));
        if (super.getHrac().getPostava() instanceof PostavaGodoku) {
            super.getHrac().zvysSiZivoty(10);
            super.getHrac().pridajUtok(new FackaZObochStran());
            super.getHrac().zobrazPostavu();
        }
        super.getHrac().vytvorSiZivoty(5, 5);
        super.getHrac().nastavSiUtoky();
    }

}
