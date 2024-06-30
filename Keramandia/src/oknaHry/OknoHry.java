package oknaHry;
import fri.shapesge.FontStyle;
import fri.shapesge.Text;
import hlavnyBalikHry.Hrac;
import hlavnyBalikHry.PosuvanaSprava;
import levely.DruhyLevel;
import levely.Level;
import levely.PrvyLevel;

import java.util.ArrayList;

/**
 *Predstavuje okno hry, ktoré služi na načitavanie levelov
 *
 * @author Jakub Gály
 */
public class OknoHry extends Okno {
    private final ArrayList<Level> levely;
    private Level aktualnyLevel;
    private Hrac hrac;

    /**
     *Nastaví atribúty, vytvorý prvý level
     *
     * @param hrac Inštania triedy Hráč
     */
    public OknoHry(Hrac hrac) {
        this.levely = new ArrayList<>();
        this.hrac = hrac;
        this.aktualnyLevel = new PrvyLevel(this.hrac);
        this.levely.add(this.aktualnyLevel);
    }

    /**
     * Správa polymorfizmu
     * Metóda vytvorí ďalši level
     * @param sprava Môže byť (Menu, Hrac, Okno)
     */
    @Override
    public void vykonajZmenu(PosuvanaSprava sprava) {
        this.hrac = ( Hrac )sprava;
        this.vsetkoSkry();
        switch (this.levely.size()) {
            case 1 -> this.aktualnyLevel = new DruhyLevel(this.hrac);
            case 2 -> System.out.println("Treti level este nie je");
            case 3 -> System.out.println("Stvrty level este nie je "); //Stvrty level (rozsirenie do buducna)
        }
        this.levely.add(this.aktualnyLevel);
    }

    /**
     * Správa polymorfizmu
     * Nerobí nič, je tu len kvôli tomu, že sa musela Overridnúť
     */
    @Override
    public void nastavOkno() {

    }

    /**
     * Správa polymorfizmu
     * Metóda skryje level a vypíše Game over screen
     */
    @Override
    public void vsetkoSkry() {
        this.aktualnyLevel.skryLevel();
        Text gameOver = new Text("GAME OVER", 280, 300);
        gameOver.zmenFont("Bold", FontStyle.BOLD, 90);
        gameOver.zmenFarbu("red");
        gameOver.zobraz();
    }
}
