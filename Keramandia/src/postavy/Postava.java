package postavy;

import fri.shapesge.Obrazok;
import hlavnyBalikHry.Hrac;
import zivot.Zivot;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.StatieNaMieste;
import postavy.ukonyPostav.UkonPostavy;

import utoky.Utok;
import utoky.UtokVykonavanyHracom;

import java.util.HashMap;
import java.util.Optional;

/**
 * Táto trieda predstavuje kontrétnu postavu v hre, má svoj aktuálnz úkon, počet životov, smer atď...
 *
 * @author Jakub Gály
 */
public class Postava {
    private final Obrazok stavPostavy;
    private final int cisloLevela;
    private UkonPostavy aktualnyUkonPostavy;
    private int cislo;
    private  TypPostavy typPostavy;
    private int suradnicaX;
    private int suradnicaY;
    private int pocetZivotov;
    private Zivot zivotPostavy;
    private SmerPostavy smerPostavy;
    private HashMap<String, Utok> utoky;
    private Postava postava;
    private boolean somMrtva;

    /**
     * Konštruktor ktorý sa vykoná, keď sa vytvára postava pre hráča
     * @param typ Predstavuje konkrétnu postavu
     * @param smerPostavy Predstavuje počiatočný smer postavy
     * @param zivoty Predstavuje počet životov postavy
     * @param ktoryLevel Predstavuje level, v ktorom sa nachádza daná postava
     */
    public Postava(TypPostavy typ, SmerPostavy smerPostavy, int zivoty, int ktoryLevel) {
        this.nastavUdaje(typ, smerPostavy, zivoty);
        this.suradnicaX = 100;
        this.suradnicaY = 100;
        this.stavPostavy = new Obrazok("src/assets/postavy/" + this.typPostavy.toString() + "/idle/idle" + this.cislo + ".png");
        this.somMrtva = false;
        this.cisloLevela = ktoryLevel;
    }

    /**
     * Konštruktor ktorý sa vykoná, keď sa vytvára postava pre nepriateľa
     * @param typ Predstavuje konkrétnu postavu
     * @param smerPostavy Predstavuje počiatočný smer postavy
     * @param suradnicaX x-ová súradnica nepriatela
     * @param suradnicaY y-ová súradnica nepriatela
     * @param zivoty Predstavuje počet životov postavy
     */
    public Postava(TypPostavy typ, SmerPostavy smerPostavy, int suradnicaX, int suradnicaY, int zivoty) {
        this.nastavUdaje(typ, smerPostavy, zivoty);
        this.stavPostavy = new Obrazok("src/assets/nepriatelia/" + this.typPostavy.toString() + "/idleVlavo/idle" + this.cislo + ".png", suradnicaX, suradnicaY);
        this.suradnicaX = suradnicaX;
        this.suradnicaY = suradnicaY;
        this.cisloLevela = -1;
    }

    private void nastavUdaje(TypPostavy typ, SmerPostavy smerPostavy, int zivoty) {
        this.smerPostavy = smerPostavy;
        this.aktualnyUkonPostavy = new StatieNaMieste();
        this.typPostavy = typ;
        this.cislo = 1;
        this.pocetZivotov = zivoty;
        this.zivotPostavy = null;
        this.utoky = new HashMap<>();
    }

    /**
     * Vráti čislo levela, ktorý sa akurát hrá
     * @return Celočíselná hodnota
     */
    public int getCisloLevela() {
        return this.cisloLevela;
    }

    /**
     * Správa polymorfizmu
     * Táto metóda vracia iba prázdny reťazec
     * @return Reťazec
     */
    public String getInfoOPostave() {
        return "";
    }

    /**
     * Vracia meno postavy
     * @return Reťazec
     */
    public String getMenoPostavy() {
        return this.typPostavy.toString();
    }

    /**
     * Vracia typ postavy
     * @return inštancia triedy TypPostavy
     */
    public TypPostavy getTypPostavy() {
        return this.typPostavy;
    }

    /**
     * Vracia aktuálny úkon postavy
     * @return Inštancia triedy UkonPostavy
     */
    public UkonPostavy getAktualnyUkonPostavy() {
        return this.aktualnyUkonPostavy;
    }

    /**
     * Vráti x-ová súradnicu postavy
     * @return Celočíselná hodnota
     */
    public int getSuradnicaX() {
        return this.suradnicaX;
    }

    /**
     * Vráti y-ová súradnicu postavy
     * @return Celočíselná hodnota
     */
    public int getSuradnicaY() {
        return this.suradnicaY;
    }

    /**
     * Hovorí nám, či je postava mŕtva
     * @return true/false
     */
    public boolean isSomMrtva() {
        return this.somMrtva;
    }

    /**
     * Vráti počet životov
     * @return Celočíselná hodnota
     */
    public int getPocetZivotov() {
        return this.pocetZivotov;
    }

    /**
     * Vráti aktuálny smer postavy
     * @return inštancia triedy SmerPostavy
     */
    public SmerPostavy getSmerPostavy() {
        return this.smerPostavy;
    }

    /**
     * Vráti aktuálny stav postavy
     * @return inštancia triedy Obrazok
     */
    public Obrazok getStavPostavy() {
        return this.stavPostavy;
    }

    /**
     * Vracia mapu útokov
     * @return inštancia triedy HashMap
     */
    protected HashMap dajMiUtoky() {
        return this.utoky;
    }

    /**
     * Zmení smer postavy na taký, aký dostane v parametri
     * @param smerPostavy Inštancia triedy SmerPostavy
     */
    public void zmenSmerPostavy(SmerPostavy smerPostavy) {
        this.smerPostavy = smerPostavy;
    }

    /**
     * Zobrazí postavu
     */
    public void zobrazPostavu() {
        this.stavPostavy.zobraz();
    }

    /**
     * Skryje postavu
     */
    public void skryPostavu() {
        this.stavPostavy.skry();
    }

    /**
     * Nastaví poradové číslo postavy na základe parametru
     * @param cislo Celočíselná hodnota
     */
    public void nastavPoradoveCislo(int cislo) {
        this.cislo = cislo;
    }

    /**
     * Vykoná aktuálny úkon, aký má postava nastavený
     * @param moreInfo Predstavuje dodatočné info
     * @param nepriatelAleboHrac Tento parameter určuje, či sa vykonáva úkon pre hráča alebo nepriateľa
     * @param rychlost Predstavuje rýchlosť pohybu
     */
    public void vykonajUkon(String moreInfo, String nepriatelAleboHrac, int rychlost) {
        this.aktualnyUkonPostavy.vykonajUkon(rychlost, this.stavPostavy, this.typPostavy, this.cislo, this, moreInfo, nepriatelAleboHrac);
    }

    /**
     * Zmení aktuálny úkon na nový ukon
     * @param ukon Inštantia triedy UkonPostavy
     */
    public void zmenAktualnyUkon(UkonPostavy ukon) {
        this.nastavPoradoveCislo(1);
        this.aktualnyUkonPostavy = ukon;
        this.zobrazPostavu();
    }

    /**
     * Posunie postavy po x-ovej osi
     * @param vzdialenost o koľko
     */
    public void posunVodorovne(int vzdialenost) {
        this.stavPostavy.posunVodorovne(vzdialenost);
        this.suradnicaX += vzdialenost;
    }

    /**
     * Posunie postavy po y-ovej osi
     * @param vzdialenost o koľko
     */
    public void posunZvisle(int vzdialenost) {
        this.stavPostavy.posunZvisle(vzdialenost);
        this.suradnicaY += vzdialenost;
    }

    /**
     *Nastaví novú polohu postavy
     * @param x nová x-ová súradnica
     * @param y nová y-ová súradnica
     */
    public void nastavPolohu(int x, int y) {
        this.posunVodorovne(-this.suradnicaX);
        this.posunZvisle(-this.suradnicaY);
        this.posunVodorovne(x);
        this.posunZvisle(y);
    }

    /**
     * Postava si vytvorí život na danej polohe pre hráča
     * @param x x-ová poloha života
     * @param y y-ová poloha života
     */
    public void vytvorSiZivoty(int x, int y) {
        this.zivotPostavy = new Zivot(this.pocetZivotov, x, y);
        this.zivotPostavy.zobrazZivot();
    }
    /**
     * Postava si vytvorí život na danej polohe pre nepriatela
     * @param x x-ová poloha života
     * @param y y-ová poloha života
     * @param zivotPreNepriatela hovorí nám o tom, že sa jedná život pre nepriateľa
     */
    public void vytvorSiZivoty(int x, int y, boolean zivotPreNepriatela) {
        this.zivotPostavy = new Zivot(this.pocetZivotov, x, y, zivotPreNepriatela);
        this.zivotPostavy.zobrazZivot();
    }


    /**
     * Metóda pridá útok pre postavá
     * @param utok Inštancia triedy Utok
     */
    public void pridajUtok(Utok utok) {
        this.utoky.put(utok.getNazovUtoku(), utok);
    }

    /**
     * Skryje všetky útoky pre postavu
     */
    public void vymazUtoky() {
        for (Utok utok : this.utoky.values()) {
            if (utok instanceof UtokVykonavanyHracom vykonavanyHracom) {
                vykonavanyHracom.skry();
            }
        }
    }

    /**
     * Vytvorí si útoky na základe útokov v HashMap
     */
    public void zobrazSiUtoky() {
        int y = 50;
        for (Utok utok : this.utoky.values()) {
            if (utok instanceof UtokVykonavanyHracom vykonavanyHracom) {
                vykonavanyHracom.vytvorIkonuUtoku(this.zivotPostavy.getPoziciaX(), y);
                y += 70;
            }
        }
    }

    /**
     * Metóda zisťuje či myška ukazuje na kontrétny útok z kontajneea
     * @param utok Inštancia triedy Utok
     * @param x x-ová súradnica myšky
     * @param y y-ová súradnica myšky
     * @return Vráti buď útok vykonavany hracom alebo prázdno
     */
    public Optional <UtokVykonavanyHracom> ukazujeMysNaUtok(Utok utok, int x, int y) {
        if (utok instanceof UtokVykonavanyHracom vykonavanyHracom) {
            if (x >= vykonavanyHracom.getSuradnicaXIkony() && x <= vykonavanyHracom.getSuradnicaXIkony() + UtokVykonavanyHracom.ROZMER_IKONY) {
                if (y >= vykonavanyHracom.getSuradnicaYIkony() && y <= vykonavanyHracom.getSuradnicaYIkony() + UtokVykonavanyHracom.ROZMER_IKONY) {
                    return Optional.of(vykonavanyHracom);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Táto metóda zabezpečuje zmenu farbu ikony útoku, keď na neho hráč ukáže
     * @param x x-ová súradnica myšky
     * @param y y-ová súradnica myšky
     */
    public void prejdenieMysouNaIkonuUtoku(int x, int y) {
        for (Utok utok : this.utoky.values()) {
            var najdenyUtok = this.ukazujeMysNaUtok(utok, x, y);
            if (najdenyUtok.isPresent()) {
                najdenyUtok.get().zmenFarbuIkony("green");
                return;
            }
        }
        for (Utok utok : this.utoky.values()) {
            if (utok instanceof UtokVykonavanyHracom vykonavanyHracom) {
                vykonavanyHracom.zmenFarbuIkony("red");
            }
        }
    }

    /**
     * Metóda útočí daným útokom ak na neho ukazuje myška
     * @param x x-ová súradnica myšky
     * @param y y-ová súradnica myšky
     * @param hrac inštancia triedy Hrac
     * @param nepriatel inštancia triedy Nepriatel
     * @param sila Celočiselná hodnota
     */
    public void zautocAkJeZvolenyUtok(int x, int y, Hrac hrac, Nepriatel nepriatel, double sila) {
        for (Utok utok : this.utoky.values()) {
            var najdenyUtok = this.ukazujeMysNaUtok(utok, x, y);
            if (najdenyUtok.isPresent()) {
                najdenyUtok.get().zautoc(hrac, nepriatel, sila);
                this.resetujUtoky();
                return;
            }
        }
    }

    /**
     * Metóda vymaže a znova načíta všetky útoky kvôli znovupoužitiu
     */
    public void resetujUtoky() {
        for (Utok utok : this.utoky.values()) {
            if (utok instanceof UtokVykonavanyHracom utokVykonavanyHracom) {
                utokVykonavanyHracom.skry();
            }
        }
        this.utoky = new HashMap<>();
        this.postava.nastaveniePociatocnychUtokov(this.cisloLevela);
        this.zobrazSiUtoky();
    }

    /**
     * Správa polymorfizmu
     * Každá postava si načíta svoje počiatočné útoky, záleží to aj od levela
     * @param ktoryLevel Poradové číslo levela
     */
    public void nastaveniePociatocnychUtokov(int ktoryLevel) {
    }

    /**
     * Nastavenie postavy to atribútu postava
     * @param postava Inštancia triedy Postava
     */
    public void nastavPostavu(Postava postava) {
        this.postava = postava;
    }

    /**
     * Metóda slúžiaca na odobratie života postave
     * @param kolko Počet životov
     */
    public void uberZivot(int kolko) {
        this.zivotPostavy.uberZoZivota(kolko, this);
    }

    /**
     * Nataví postave opačný smer aký mala predtým
     */
    public void nastavOpacnySmer() {
        if (this.smerPostavy == SmerPostavy.VPRAVO) {
            this.smerPostavy = SmerPostavy.VLAVO;
        } else {
            this.smerPostavy = SmerPostavy.VPRAVO;
        }
    }

    /**
     * Metóda vymaže život postavy
     */
    public void skryZivot() {
        this.zivotPostavy.vymazZivot();
    }

    /**
     * Nastavenie, že postava zomrela
     */
    public void postavaZomrela() {
        this.somMrtva = true;
    }

    /**
     * Metóda slúžiaca na zvýšnie života postay
     * @param pocetZivotov Celočíselná hodnota
     */
    public void zvysZivoty(int pocetZivotov) {
        this.pocetZivotov += pocetZivotov;
    }
}

