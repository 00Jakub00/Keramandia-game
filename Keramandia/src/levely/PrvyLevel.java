package levely;

import hlavnyBalikHry.Hrac;
import postavy.SmerPostavy;
import postavy.TypPostavy;
import postavy.nepriatelia.Berserk;

/**
 * Predstavuje prvý level hry
 *
 * @author Jakub Gály
 */
public class PrvyLevel extends Level {

    /**
     * Vytvorenie prvého levela, s cestou ku pozadiu
     * @param hrac Inštancia triedy Hrac
     */
    public PrvyLevel(Hrac hrac) {
        super(hrac, "src/assets/levely/1.png", "LEVEL 1");
        super.nastavHranyLevel(this);
    }

    /**
     * Správa polymorfizmu
     * Vytvorenie nepriatela a vykonanie nastavení pre hraca pre prvý level
     */
    public void vytvorNepriatelaANastavHraca() {
        super.nastavNepriatela(new Berserk(TypPostavy.BERSERK, SmerPostavy.VLAVO, 950, 485));
        super.getHrac().zobrazPostavu();
        super.getHrac().vytvorSiZivoty(5, 5);
        super.getHrac().nastavSiUtoky();

    }
}
