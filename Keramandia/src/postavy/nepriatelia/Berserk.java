package postavy.nepriatelia;

import postavy.SmerPostavy;
import postavy.TypPostavy;
import utoky.utokyBERSERK.Prenasledovanie;
import utoky.utokyBERSERK.SekSablou;
import utoky.utokyBERSERK.UtokBalvan;
import utoky.utokyBERSERK.VymenaStranUtok;

/**
 * Nepritel Bersker a jeho
 *
 * @author Jakub Gály
 */
public class Berserk extends Nepriatel {

    /**
     * Vytvorenie Berskerka
     * @param typ Konkrétny typ postavy, v tomto prídae Berserk
     * @param smerPostavy Počiatočný smer postavy
     * @param suradnicaX Počiatočná x-ová súradnica
     * @param suradnicaY Počiatočná y-ová súradnica
     */
    public Berserk(TypPostavy typ, SmerPostavy smerPostavy, int suradnicaX, int suradnicaY) {
        super(typ, smerPostavy, suradnicaX, suradnicaY, 65);
        super.nastavPostavu(this);
        this.nastaveniePociatocnychUtokov(-1);
    }

    /**
     * Vytvorenie útokov Berskerka
     * @param ktoryLevel Poradové číslo levela
     */
    @Override
    public void nastaveniePociatocnychUtokov(int ktoryLevel) {
        super.pridajUtok(new SekSablou());
        super.pridajUtok(new Prenasledovanie());
        super.pridajUtok(new UtokBalvan());
        super.pridajUtok(new VymenaStranUtok());
    }
}
