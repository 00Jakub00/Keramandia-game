package postavy.ukonyPostav;

import fri.shapesge.Obrazok;
import postavy.Postava;
import postavy.TypPostavy;

/**
 * Trieda predstavujúca úkon chodenia dolava
 *
 * @author Jakub Gály
 */
public class ChodenieDolava implements UkonPostavy {
    /**
     * Správa polymorfizmu
     * Slúži na posunutie postavy do lava
     * @param rychlost Rýchlosť postavy
     * @param stavPostavy Obrázok postavy
     * @param typPostavy Typ postavy (Godotku, Onre...)
     * @param poradoveCislo Celočíselná hodnota
     * @param postava Inštancia triedy Postava
     * @param moreInfo Ďalšie dodatočné info
     * @param nepriatelAleboHrac Tento parameter určuje pre koho sa daný úkon vykoná, či pre hráča alebo nepriatela
     */
    @Override
    public void vykonajUkon(int rychlost, Obrazok stavPostavy, TypPostavy typPostavy, int poradoveCislo, Postava postava, String moreInfo, String nepriatelAleboHrac) {
        postava.posunVodorovne(-rychlost);
        stavPostavy.zmenObrazok("src/assets/" + nepriatelAleboHrac + "/" + typPostavy.toString() + "/walk" + moreInfo + "/walk" + poradoveCislo + ".png");
        if (poradoveCislo < typPostavy.getPocetObrazkovKracania()) {
            poradoveCislo++;
            postava.nastavPoradoveCislo(poradoveCislo);
        } else {
            postava.nastavPoradoveCislo(1);
        }
    }
}
