package postavy.ukonyPostav;

import fri.shapesge.Obrazok;
import hlavnyBalikHry.PosuvanaSprava;
import postavy.Postava;
import postavy.TypPostavy;

/**
 * Tento interface predstavuje rôzny typy výkonu útokov
 *
 * @author Jakub Gály
 */
public interface UkonPostavy {

    /**
     * Správa polymorfizmu
     * Táto metóda sa nikdy nevykoná
     * @param rychlost Rýchlosť postavy
     * @param stavPostavy Obrázok postavy
     * @param typPostavy Typ postavy (Godotku, Onre...)
     * @param poradoveCislo Celočíselná hodnota
     * @param postava Inštancia triedy Postava
     * @param moreInfo Ďalšie dodatočné info
     * @param nepriatelAleboHrac Tento parameter určuje pre koho sa daný úkon vykoná, či pre hráča alebo nepriatela
     */
    void vykonajUkon(int rychlost, Obrazok stavPostavy, TypPostavy typPostavy, int poradoveCislo, Postava postava, String moreInfo, String nepriatelAleboHrac);
}
