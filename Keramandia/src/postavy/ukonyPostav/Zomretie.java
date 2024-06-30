package postavy.ukonyPostav;

import fri.shapesge.Obrazok;
import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.TypPostavy;
import java.util.Optional;

/**
 * Trieda predstavujúca úkon zomretia
 *
 * @author Jakub Gály
 */
public class Zomretie implements UkonPostavy {
    private int casCakania;
    private final Optional <Hrac> hrac;

    /**
     * Nastavenie počiatočných atribútov objektu
     */
    public Zomretie() {
        this.casCakania = 0;
        this.hrac = Optional.empty();
    }

    /**
     * Nastavenie počiatočných atribútov objektu
     * @param hrac Inštancia triedy Hrac
     */
    public Zomretie(Hrac hrac) {
        this.casCakania = 0;
        this.hrac = Optional.of(hrac);
    }

    /**
     * Správa polymorfizmu
     * Slúži na to, aby postava pekne obrázok po obrázku zomrela
     * Ak je umieraná postava hráč tak sa posiela správa, že hráč zomrel
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
        stavPostavy.zmenObrazok("src/assets/" + nepriatelAleboHrac + "/" + typPostavy + "/dead" + moreInfo + "/" + poradoveCislo + ".png");
        poradoveCislo++;
        if (poradoveCislo >= typPostavy.getPocetObrazkovZomretia()) {
            poradoveCislo = typPostavy.getPocetObrazkovZomretia();
            if (this.casCakania < 8) {
                this.casCakania++;
            } else {
                postava.skryPostavu();
                postava.skryZivot();
                postava.postavaZomrela();
                if (this.hrac.isPresent()) {
                    this.hrac.get().prehra();
                }
            }
        }
        postava.nastavPoradoveCislo(poradoveCislo);
    }
}

