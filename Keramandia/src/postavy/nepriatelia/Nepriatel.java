package postavy.nepriatelia;

import fri.shapesge.Manazer;
import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.TypPostavy;
import utoky.Utok;

import java.util.HashMap;
import java.util.Random;

/**
 * Trieda reprezentuje nepriateľskú postavu v Keramandii
 *
 * @author Jakub Gály
 */

public class Nepriatel extends Postava {
    private final Manazer manazer;

    /**
     * Vytvorenie nepriatelskej postavy
     * @param typ Konkrétny typ postavy
     * @param smerPostavy Začiatočný smer postavy
     * @param suradnicaX Počiatočná x-ová pozícia
     * @param suradnicaY Počiatočná y-ová pozícia
     * @param pocetZivotov Počet životov nepriatela
     */

    public Nepriatel(TypPostavy typ, SmerPostavy smerPostavy, int suradnicaX, int suradnicaY, int pocetZivotov) {
        super(typ, smerPostavy, suradnicaX, suradnicaY, pocetZivotov);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        super.vytvorSiZivoty(1110, 5, true);
    }

    /**
     * Správa pre manazera, aby sa už nestral o objekt
     */
    public void manazerPrestanSpravovatObjekt() {
        this.manazer.prestanSpravovatObjekt(this);
    }

    /**
     * Správa pre manazera, aby sa začal starať o objekt
     */
    public void manazerSPravujObjekt() {
        this.manazer.spravujObjekt(this);
    }

    /**
     * V tejto metóde útoči nepriateľ, náhodné si zvolí útok s útokov ktoré má k dispozícii
     * @param hrac Inštacia triedy Hrac
     */
    public void zautoc(Hrac hrac) {
        if (!this.isSomMrtva()) {
            Random randomnyUtok = new Random();
            HashMap<String, Utok> utoky = super.dajMiUtoky();
            Object[] utokyVPoli = utoky.values().toArray();
            int nahodnyIndex = randomnyUtok.nextInt(utokyVPoli.length);
            Utok utok = ( Utok )utokyVPoli[nahodnyIndex];
            utok.zautoc(hrac, this, 1);
        }
    }

    /**
     * Metóda spravovaná manazerom
     * Vykonáva daný úkon postavy v danom časovom období
     */
    public void tik() {
        super.vykonajUkon(super.getSmerPostavy().toString(), "nepriatelia", 0);
    }
}
