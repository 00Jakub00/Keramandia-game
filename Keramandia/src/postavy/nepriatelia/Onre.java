package postavy.nepriatelia;

import postavy.SmerPostavy;
import postavy.TypPostavy;
import utoky.utokyBERSERK.UtokBalvan;

public class Onre extends Nepriatel {

    /**
     * Vytvorenie nepriatelskej postavy
     * @param typ Konkrétny typ postavy
     * @param smerPostavy Začiatočný smer postavy
     * @param suradnicaX Počiatočná x-ová pozícia
     * @param suradnicaY Počiatočná y-ová pozícia
     */
    public Onre (TypPostavy typ, SmerPostavy smerPostavy, int suradnicaX, int suradnicaY) {
        super(typ, smerPostavy, suradnicaX, suradnicaY, 75);
        super.nastavPostavu(this);
        this.nastaveniePociatocnychUtokov(-1);
    }
    /**
     * Vytvorenie útokov Onre
     * @param ktoryLevel Poradové číslo levela
     */
    @Override
    public void nastaveniePociatocnychUtokov(int ktoryLevel) {
        super.pridajUtok(new UtokBalvan());
    }
}

