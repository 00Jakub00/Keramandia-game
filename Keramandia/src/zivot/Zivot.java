package zivot;

import fri.shapesge.Obdlznik;
import postavy.Postava;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.Zomretie;

import java.util.ArrayList;

/**
 * Trieda predstavuje život postavy
 * Je tvorený z obdlžnikov.
 *
 * @author Jakub Gály
 */
public class Zivot {
    private final Obdlznik okrajZivota;
    private final Obdlznik zivot;
    private final int stranaAZivota;
    private final int stranaBZivota;
    private int pocetZivotov;
    private int poziciaX;
    private int poziciaY;
    private ArrayList<OdobranyZivot> odobraneZivoty;

    /**
     * Vytvorenie života pre hráča na daných súradniciach
     * @param pocetZivotov Počet životo
     * @param suradnicaX x-ová poloha života
     * @param suradnicaY y-ová poloha života
     */
    public Zivot(int pocetZivotov, int suradnicaX, int suradnicaY) {
        this.okrajZivota = new Obdlznik(suradnicaX - 5,  suradnicaY - 5);
        this.okrajZivota.zmenStrany( pocetZivotov * 7, 20);
        this.zivot = new Obdlznik(suradnicaX,  suradnicaY);
        this.stranaAZivota = (pocetZivotov * 7) - 10;
        this.stranaBZivota = 10;
        this.zivot.zmenStrany(this.stranaAZivota, this.stranaBZivota);
        this.vykonajUpravy(pocetZivotov, suradnicaX, suradnicaY);
    }

    /**
     * Vytvorenie života pre hráča na daných súradniciach
     * @param pocetZivotov Počet životo
     * @param suradnicaX x-ová poloha života
     * @param suradnicaY y-ová poloha života
     * @param zivotPreNepriatela Informácia, že vytváraný život je pre nepriatela
     */
    public Zivot(int pocetZivotov, int suradnicaX, int suradnicaY, boolean zivotPreNepriatela) {
        this.okrajZivota = new Obdlznik(suradnicaX - (pocetZivotov * 7),  suradnicaY - 5);
        this.zivot = new Obdlznik(suradnicaX - (pocetZivotov * 7) + 5,  suradnicaY);
        this.stranaAZivota = (pocetZivotov * 7) - 10;
        this.stranaBZivota = 10;
        this.zivot.zmenStrany(this.stranaAZivota, this.stranaBZivota);
        this.okrajZivota.zmenStrany( pocetZivotov * 7, 20);
        this.vykonajUpravy(pocetZivotov, suradnicaX - (pocetZivotov * 7) + 5, suradnicaY);
    }
    private void vykonajUpravy(int pocetZivotov, int suradnicaX, int suradnicay) {
        this.pocetZivotov = (pocetZivotov * 7) - 10;
        this.okrajZivota.zmenFarbu("black");
        this.zivot.zmenFarbu("red");
        this.poziciaX = suradnicaX;
        this.poziciaY = suradnicay;
        this.odobraneZivoty = new ArrayList<>();
    }

    /**
     * Vráti x-ovú pozíciu života
     * @return Celočíselná hodnota
     */
    public int getPoziciaX() {
        return this.poziciaX;
    }

    /**
     * Zobrazí životy
     */
    public void zobrazZivot() {
        this.okrajZivota.zobraz();
        this.zivot.zobraz();
    }

    /**
     * Metóda danej postavy ubere daný počet život, ak už postava nemá žiadne život tak sa jej nastaví nový úkon Zomretia.
     * @param pocetZivotov Počet život
     * @param postava Inštancia triedy Postava
     */
    public void uberZoZivota(int pocetZivotov, Postava postava) {
        if (pocetZivotov * 7 >= this.pocetZivotov) {
            pocetZivotov = this.pocetZivotov;
            if (postava instanceof Nepriatel) {
                this.postavaZomrela(postava);
            }
        } else {
            pocetZivotov = pocetZivotov * 7;
        }
        this.pocetZivotov -= pocetZivotov;
        if (this.odobraneZivoty.size() == 0) {
            OdobranyZivot odobranyZivot = new OdobranyZivot(this.poziciaX + this.stranaAZivota - pocetZivotov, this.poziciaY, pocetZivotov);
            this.odobraneZivoty.add(odobranyZivot);
        } else {
            int suradnicaX = this.odobraneZivoty.get(this.odobraneZivoty.size() - 1).getSuradnicaX() - pocetZivotov;
            OdobranyZivot odobranyZivot = new OdobranyZivot(suradnicaX, this.poziciaY, pocetZivotov);
            this.odobraneZivoty.add(odobranyZivot);
        }
    }

    /**
     * Nastavenie úkonu zomretia pre zomretú postavu
     * @param postava Inštancia triedy Postava
     */
    private void postavaZomrela(Postava postava) {
        postava.zmenAktualnyUkon(new Zomretie());
    }

    /**
     * Skryje celý život
     */
    public void vymazZivot() {
        for (OdobranyZivot odobranyZivot : this.odobraneZivoty) {
            odobranyZivot.vymaz();
        }
        this.okrajZivota.skry();
        this.zivot.skry();
    }
}
