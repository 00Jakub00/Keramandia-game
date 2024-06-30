package hlavnyBalikHry;

import fri.shapesge.Manazer;
import postavy.Postava;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.*;
import postavy.SmerPostavy;
import utoky.UpravaUtoku;
import utoky.Utok;


import java.util.Optional;

/**
 *Predstavuje hráča, ktorým sme vlastne my, ktorého ovládame
 *
 * @author Jakub Gály
 */

public class Hrac implements PosuvanaSprava {
    private final Hra hra;
    private final int rychlost;
    private final KontrolaKolizieHraca kontrolaKolizie;
    private final int [] posunySuradnic;
    private final Manazer manazer;
    private Optional<Postava> hranaPostava;
    private String meno;
    private boolean jeZobrazeny;
    private boolean poholSaHrac;
    private boolean povolenaZmenaPohybDoprava;
    private boolean povolenaZmenaPohybDolava;
    private int casCakania;
    private Optional <Nepriatel> aktualnyNepriatel;
    private boolean utociNepriatel;
    private boolean zablokovanyPohyb;
    private UpravaUtoku upravaUtoku;

    /**
     * Konštruktor nastaví počiatočné stavy atribútov
     *
     * @param hra Predstavuje inštanciu triedy Hra, ktorú si hráč uloží do atribútu
     */
    public Hrac(Hra hra) {
        this.hra = hra;
        this.utociNepriatel = false;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.hranaPostava = Optional.empty();
        this.meno = "Zatial nezname meno";
        this.jeZobrazeny = false;
        this.poholSaHrac = false;
        this.povolenaZmenaPohybDolava = false;
        this.povolenaZmenaPohybDoprava = false;
        this.zablokovanyPohyb = false;
        this.casCakania = 0;
        this.kontrolaKolizie = new KontrolaKolizieHraca();
        this.posunySuradnic = new int[2];
        this.aktualnyNepriatel = Optional.empty();
        this.rychlost = 8;
        this.upravaUtoku = null;
    }

    /**
     * Vráti aktuálne nepriateľa
     * @return Inštancia triedy Nepriatel
     */
    public Optional<Nepriatel> getAktualnyNepriatel() {
        return this.aktualnyNepriatel;
    }

    /**
     * Vráti aktuálnu x-ovú pozíciu postavy hráča
     * @return Celočíslená hodnota
     */

    public int getSuradnicaXHraca() {
        return this.hranaPostava.get().getSuradnicaX();
    }

    /**
     * Vráti hranú postavu hráča
     * @return Inštancia triedy Postava
     */

    public Postava getPostava() {
        return this.hranaPostava.get();
    }

    /**
     * Povie nám čí je vybraná postava
     * @return true/false
     */
    public boolean jeVybranaPostava() {
        return this.hranaPostava.isPresent();
    }

    /**
     * Vráti rýchlost postavy (veľkosť kroku
     * @return Celočíselná hodnota
     */
    public int getRychlost() {
        return this.rychlost;
    }

    /**
     * Metóda hovorí o tom, či je postava zobrazená
     * @return true/false
     */
    public boolean jeZobrazeny() {
        return this.jeZobrazeny;
    }

    /**
     * Metóda z parametru uloží hranú postavu do atribútu
     * @param postava Parameter postavy, ktorá sa ide hrať
     */
    public void nastavHranuPostavu(Postava postava) {
        this.hranaPostava = Optional.of(postava);
        this.nastavMenoPostavy(this.hranaPostava.get().getMenoPostavy());
    }

    /**
     * Pošle správu hre, že sme prehrali.
     */
    public void prehra() {
        this.hra.prehra();
    }

    /**
     * Nastaví sa meno postavy do atribútu
     * @param meno Reťazec mena.
     */
    private void nastavMenoPostavy(String meno) {
        this.meno = meno;
        this.nastavPosunySuradnic(this.meno);
    }

    /**
     * Metóda urobí postavu viditeľnou
     */

    public void zobrazPostavu() {
        this.jeZobrazeny = true;
        this.hranaPostava.get().zobrazPostavu();
    }

    /**
     * Metóda nastaví počiatočnu polohu hráča
     */

    public void nastavVychodziuPoziciuPostavy() {
        this.hranaPostava.get().nastavPolohu(20, 480);
    }

    /**
     * Metóda nastaví počiatočný ukón hráča
     */
    public void nastavVychodziUkonHraca() {
        this.hranaPostava.get().zmenAktualnyUkon(new StatieNaMieste());
    }

    /**
     * Metóda vytvorí život hráča na danom mieste na základe vstupných parametrov
     * @param x x-ová pozícia života
     * @param y y-ová pozícia života
     */
    public void vytvorSiZivoty(int x, int y) {
        this.hranaPostava.get().vytvorSiZivoty(x, y);
    }

    /**
     * Metóda nastaví hráčovi zoznam útokov do ľavého rohu.
     */
    public void nastavSiUtoky() {
        this.hranaPostava.get().zobrazSiUtoky();
    }

    /**
     * Vloží aktuálneho nepriateľa do atribútu.
     * @param nepriatel Inštancia triedy Nepriateľ
     */
    public void nastavAktualnehoNepriatela(Nepriatel nepriatel) {
        this.aktualnyNepriatel = Optional.of(nepriatel);
        this.upravaUtoku = new UpravaUtoku(this,  this.aktualnyNepriatel.get());
    }

    /**
     * Metóda povie manažérovi aby sa už nestaral o objekt hráča
     */

    public void manazerPrestanSpravovatObjekt() {
        this.manazer.prestanSpravovatObjekt(this);
    }


    /**
     * Metóda povie manažérovi, aby sa staral o objekt hráča
     */

    public void manazerSpravujObjekt() {
        this.manazer.spravujObjekt(this);
    }

    /**
     * Metóda posunie hráča na novú x-ovú poziciu
     * @param x nová poloha x
     */
    public void zmenXovuSuracnicuHrac(int x) {
        this.hranaPostava.get().nastavPolohu(x, this.hranaPostava.get().getSuradnicaY());
    }

    /**
     * Metóda skryje postavu z plátna
     */
    public void skryPostavuHrac() {
        this.hranaPostava.get().skryPostavu();
    }

    /**
     * Metóda zmení polohu hráča na základe vstupných parametrov
     * @param x nová x-ová pozícia
     * @param y nová y-ová pozícia
     */
    public void zmenPolohuHrac(int x, int y) {
        this.hranaPostava.get().nastavPolohu(x, y);
    }

    /**
     * tik kontroluje keď sme už porazili nepriateľa, vtedy volá posiela správu dajDalsiLevel()
     * Ďalej zabezpečuje, aby hráč správne na mieste stepoval
     */
    public void tik() {
        if (this.aktualnyNepriatel.isPresent() && this.aktualnyNepriatel.get().isSomMrtva()) {
            this.dajDalsiLevel();
        }
        if (this.jeZobrazeny) {
            if (!this.poholSaHrac) {
                this.hranaPostava.get().vykonajUkon("Hrac" + this.hranaPostava.get().getSmerPostavy().toString(), "postavy", 0);
            } else {
                this.casCakania++;
                if (this.casCakania == 3) {
                    this.hranaPostava.get().zmenAktualnyUkon(new StatieNaMieste());
                    this.poholSaHrac = false;
                    this.casCakania = 0;
                }
            }
        }
    }

    /**
     * Metóda spravovaná manazerom, slúžiaca na pohyb hráča dolava
     * Tento pohyb je však obmedzený urítými prvkami, ako napríklad, či má hráč povolený pohyb alebo, či okrajom...
     * Plus pri každom pohybe smerom k nepriateľovi sa zvyšuje sila útoku, ktorá sa použije pri útočení
     */
    public void doprava() {
        if (!this.zablokovanyPohyb) {
            SmerPostavy smer = this.kontrolaKolizie.maHracPovolenyPohyb(this.hranaPostava.get(), this.posunySuradnic[0], this.posunySuradnic[1]);
            if (this.jeZobrazeny && (smer.equals(SmerPostavy.OBESTRANY) || smer.equals(SmerPostavy.VPRAVO))) {
                if (!this.utociNepriatel) {
                    this.upravaUtoku.vypocitajSiluUtoku(SmerPostavy.VPRAVO);
                }
                this.hranaPostava.get().zmenSmerPostavy(SmerPostavy.VPRAVO);
                this.casCakania = 0;
                if (!this.poholSaHrac || this.povolenaZmenaPohybDoprava) {
                    this.hranaPostava.get().zmenAktualnyUkon(new ChodenieDoprava());
                    this.povolenaZmenaPohybDolava = true;
                    this.povolenaZmenaPohybDoprava = false;
                }
                this.dokonciPohyb();
            }
        }
    }

    /**
     * Metóda spravovaná manazerom, slúžiaca na pohyb hráča dolava
     * Tento pohyb je však obmedzený urítými prvkami, ako napríklad, či má hráč povolený pohyb alebo, či okrajom...
     * Plus pri každom pohybe smerom k nepriateľovi sa zvyšuje sila útoku, ktorá sa použije pri útočení
     */
    public void dolava() {
        if (!this.zablokovanyPohyb) {
            SmerPostavy smer = this.kontrolaKolizie.maHracPovolenyPohyb(this.hranaPostava.get(), this.posunySuradnic[0], this.posunySuradnic[1]);
            if (this.jeZobrazeny && (smer.equals(SmerPostavy.OBESTRANY) || smer.equals(SmerPostavy.VLAVO))) {
                if (!this.utociNepriatel) {
                    this.upravaUtoku.vypocitajSiluUtoku(SmerPostavy.VLAVO);
                }
                this.hranaPostava.get().zmenSmerPostavy(SmerPostavy.VLAVO);
                this.casCakania = 0;
                if (!this.poholSaHrac || this.povolenaZmenaPohybDolava) {
                    this.hranaPostava.get().zmenAktualnyUkon(new ChodenieDolava());
                    this.povolenaZmenaPohybDoprava = true;
                    this.povolenaZmenaPohybDolava = false;
                }
                this.dokonciPohyb();
            }
        }
    }

    /**
     * Metóda, ktorá doplňa metódy doprava a dolava už reálne pohne hráča, to znamená, že sa zmení súradnica a aj obrázok
     * Plus pri každom pohybe je šanca, že začne útočiť nepriateľ (to je tá správa zautociNepriatel().
     */
    private void dokonciPohyb() {
        this.poholSaHrac = true;
        this.hranaPostava.get().vykonajUkon("Hrac" + this.hranaPostava.get().getSmerPostavy().toString(), "postavy", this.rychlost);
        if (this.upravaUtoku.zautociNepriatel() && !this.utociNepriatel) {
            this.utociNepriatel = true;
            this.upravaUtoku.resetujSiluUtoku();
            this.aktualnyNepriatel.get().zautoc(this);
        }
    }

    /**
     * Metóda spravovaná manazerom reagujuca na kliknutie myšky
     * Volá ďalšiu metódu v hranej postave, ktorá slúži na volanie útokov.
     * @param x Predstavuje x-ovú súradnicu myšky
     * @param y Predstavuje y-ovú súradnicu myšky
     */
    public void zvolKliknutim(int x, int y) {
        if (this.jeZobrazeny && !this.utociNepriatel) {
            double utok = this.upravaUtoku.getZvysenaSilaUtoku();
            this.hranaPostava.get().zautocAkJeZvolenyUtok(x, y, this, this.aktualnyNepriatel.get(), utok);
        }
    }

    /**
     * Metóda spravovaná manazerom reagujuca na posun myšky
     * Volá ďalšiu metódu v hranej postave slúžiacu na zvýrazenie útokov
     * @param x Predstavuje x-ovú súradnicu myšky
     * @param y Predstavuje y-ovú súradnicu myšky
     */
    public void mouseMove(int x, int y) {
        if (this.jeZobrazeny) {
            this.hranaPostava.get().prejdenieMysouNaIkonuUtoku(x, y);
        }
    }

    /**
     * Táto metódá slúži na to, aby sa nastavili dané posuny súradníc z dôvodu kolízie hráča s okrajom mapy
     * Tieto posuny sú indivitúalne pre rôzne hrané postavy
     * @param menoPostavy Predstavuje meno hranej postavy na základe, ktorého sa nastaví posun
     */
    private void nastavPosunySuradnic(String menoPostavy) {
        switch (menoPostavy) {
            case "GODOKU" -> {
                this.posunySuradnic[0] = 8;
                this.posunySuradnic[1] = 5;
            }

            case "VAMPIRE" -> {
                this.posunySuradnic[0] = 10;
                this.posunySuradnic[1] = 5;
            }

        }
    }

    /**
     * Pri volaní tejto metódy začne útočiť nepriateľ
     */
    public void nechZautociNepriatel() {
        this.utociNepriatel = true;
        this.aktualnyNepriatel.get().zautoc(this);
    }

    /**
     * Volanie tejto metódy umožňuje hráčovi znova útočiť
     */
    public void mozesUtocic() {
        this.utociNepriatel = false;
    }

    /**
     * Keď sa táto metóda zavolá, tak sa hráč nebue môcť hýbať
     */
    public void nastavZablokovanyPohyb() {
        this.zablokovanyPohyb = !this.zablokovanyPohyb;
    }

    /**
     * Ubere zo života hráča daný počet životov, ak už hráč zomrel, tak sa nastaví nový úkon zomretia
     * @param kolko Predstavuje počet život, ktoré sa odoberú.
     */

    public void uberZivot(int kolko) {
        this.hranaPostava.get().uberZivot(kolko);
        if (this.hranaPostava.get().getPocetZivotov() == 0 ) {
            this.hranaPostava.get().zmenAktualnyUkon(new Zomretie(this));
        }
    }

    /**
     * Ak sme už porazili neoriateľa tak sa zavolá táto metóda, ktorá
     * požaduje od hry ďalší level.
     */
    private void dajDalsiLevel() {
        int cisloDalsiehoLvla = this.hranaPostava.get().getCisloLevela();
        this.manazerPrestanSpravovatObjekt();
        this.aktualnyNepriatel.get().manazerPrestanSpravovatObjekt();
        this.aktualnyNepriatel.get().skryPostavu();
        this.aktualnyNepriatel = Optional.empty();
        this.hra.nextLevel(cisloDalsiehoLvla);
    }

    /**
     * Pridá hráčskej postave útok
     * @param utok Predstavuje nový pridávaný útok
     */
    public void pridajUtok(Utok utok) {
        this.hranaPostava.get().pridajUtok(utok);
    }

    /**
     * Metóda zvýši hranej postavy životy
     * @param kolko Parameter určuje koľko života  sa pridá
     */
    public void zvysSiZivoty(int kolko) {
        this.hranaPostava.get().zvysZivoty(kolko);
    }
}
