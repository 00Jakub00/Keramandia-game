package utoky;

import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import java.util.Random;

/**
 * Trieda predstavuje vylepšienie útoku pre hráča alebo nepriateľa
 *
 * @author Jakub Gály
 */
public class UpravaUtoku {
    private final Hrac hrac;
    private final Postava nepriatel;
    private final int rychlostPosunu;
    private double zvysenaSilaUtoku;

    /**
     * V konštruktore sa nastaví hrač, nepriatel, ryhclostPosunu, a zvysenaSilaUtoku
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     */
    public UpravaUtoku(Hrac hrac, Postava nepriatel) {
        this.hrac = hrac;
        this.nepriatel = nepriatel;
        this.rychlostPosunu = this.hrac.getRychlost();
        this.zvysenaSilaUtoku = 1;
    }

    /**
     * Vráti upravenú silu útoku
     * @return hodnota typu double
     */
    public double getZvysenaSilaUtoku() {
        return this.zvysenaSilaUtoku;
    }

    /**
     * Na základe smeru hráča buď zvýši silu útoku alebo ju nezvýši
     * @param smerPostavy
     */
    public void vypocitajSiluUtoku(SmerPostavy smerPostavy) {
        int vziadelnostMedziHracomAnepriatelom = Math.abs(this.hrac.getSuradnicaXHraca() - this.nepriatel.getSuradnicaX());
        int druhaVzdialenost = 0;
        switch (smerPostavy) {
            case VPRAVO -> druhaVzdialenost = Math.abs(this.hrac.getSuradnicaXHraca() + this.rychlostPosunu - this.nepriatel.getSuradnicaX());
            case VLAVO -> druhaVzdialenost = Math.abs(this.hrac.getSuradnicaXHraca() - this.rychlostPosunu - this.nepriatel.getSuradnicaX());
        }
        if (druhaVzdialenost < vziadelnostMedziHracomAnepriatelom) {
            this.zvysenaSilaUtoku += 0.02;
        }
    }

    /**
     * Dáva nám 5% šancu na to, že zaútočí nepriateľ
     * @return true/false
     */
    public boolean zautociNepriatel() {
        Random r = new Random();
        int a = r.nextInt(20) + 1;
        return a == 5;
    }

    /**
     * Resetuje nam zvysenu silu utoku na hodnotu 1
     */
    public void resetujSiluUtoku() {
        this.zvysenaSilaUtoku = 1;
    }
}
