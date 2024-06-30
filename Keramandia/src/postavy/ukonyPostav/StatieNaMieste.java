package postavy.ukonyPostav;

import fri.shapesge.Obrazok;

import postavy.Postava;
import postavy.TypPostavy;

/**
 * Trieda predstavujúca úkon státia na mieste
 *
 * @author Jakub Gály
 */
public class StatieNaMieste implements UkonPostavy {

    /**
     * Správa polymorfizmu
     * Slúži na to, aby postava na mieste stepovala
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
        stavPostavy.zmenObrazok("src/assets/" + nepriatelAleboHrac + "/" + typPostavy + "/idle" + moreInfo + "/idle" + poradoveCislo + ".png");
        poradoveCislo++;
        if (poradoveCislo >= typPostavy.getPocetObrazkovStatia()) {
            poradoveCislo = 1;
        }
        postava.nastavPoradoveCislo(poradoveCislo);
    }
}
