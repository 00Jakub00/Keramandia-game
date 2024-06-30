package postavy;

import utoky.utokyVAPMIRE.PadajuciKamen;
import utoky.utokyVAPMIRE.UtokMec;

/**
 * Táto trieda predstavuje hráčsku postavu Godoku
 *
 * @author Jakub Gály
 */
public class PostavaVampire extends Postava {
    public static final TypPostavy TYP_POSTAVY = TypPostavy.VAMPIRE;

    /**
     * Vytvorenie postavy
     * @param smerPostavy Počiatočný smer postavy
     * @param ktoryLevel Predstavuje hraný level
     */
    public PostavaVampire(SmerPostavy smerPostavy, int ktoryLevel) {
        super(TYP_POSTAVY, smerPostavy, 40, ktoryLevel);
        this.nastaveniePociatocnychUtokov(ktoryLevel);
        super.nastavPostavu(this);
        this.nastaveniePociatocnychUtokov(ktoryLevel);
    }

    /**
     * Počiatočné útoky pre hráčsku postavu pre daný level
     * @param ktoryLevel Poradové číslo levela
     */
    public void nastaveniePociatocnychUtokov(int ktoryLevel) {
        super.pridajUtok(new PadajuciKamen());
        super.pridajUtok(new UtokMec());
    }

    /**
     * Rozprávka o postave
     * @return Reťazec
     */
    @Override
    public String getInfoOPostave() {
        return """
                Ahoj, som Vampír Palanom.
                Som mimoriadne zdatný šermiar.
                Medzi moje špeciality patrí hlavne útok meča.
                Vyber si ma!""";
    }
}
