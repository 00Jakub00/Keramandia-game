package postavy.ukonyPostav;

import fri.shapesge.Obrazok;
import postavy.Postava;
import postavy.TypPostavy;
/**
 * Trieda predstavujúca úkon predstavenie sa
 *
 * @author Jakub Gály
 */
public class PredstavenieSa implements UkonPostavy {
    private int pocetVykonanychCyklov;
    private int dlzkaPosunuPostavy;

    /**
     * Nastavenie počiatočných hodnôt atribútov
     */
    public PredstavenieSa() {
        this.pocetVykonanychCyklov = 0;
        this.dlzkaPosunuPostavy = 0;
    }
    /**
     * Správa polymorfizmu
     * Slúži na to aby postava urobila pár krokov do prava a potom sa vrátila svoje počiatočné miesto
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

        postava.posunVodorovne(rychlost);
        this.dlzkaPosunuPostavy += rychlost;

        stavPostavy.zmenObrazok("src/assets/postavy/" + typPostavy.toString() + "/walk" + moreInfo + "/walk" + poradoveCislo + ".png");
        if (poradoveCislo < typPostavy.getPocetObrazkovKracania()) {
            poradoveCislo++;
            postava.nastavPoradoveCislo(poradoveCislo);
        } else if (this.pocetVykonanychCyklov < 3) {
            postava.nastavPoradoveCislo(1);
            this.pocetVykonanychCyklov++;
        } else {
            postava.posunVodorovne(this.dlzkaPosunuPostavy * (-1));
            System.out.println(postava.getSuradnicaX());
            postava.zmenAktualnyUkon(new StatieNaMieste());

        }
    }
}

