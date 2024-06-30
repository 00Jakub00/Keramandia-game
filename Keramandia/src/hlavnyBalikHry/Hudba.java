package hlavnyBalikHry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Trieda Hudba, ktorá ponúka muziku do pozadia hrania.
 * Táto časť kódu v tejto triede je prevzaná z internetu.
 *
 * @author Jakub Gály
 */
public class Hudba {

    private final File suborSHudbou;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private boolean jeUzHudbaPustena;

    /**
     * Konštruktor, inicializuje atribúty na počiatočný stav + nastaví cestu k hudbe.
     */
    public Hudba() {
        this.suborSHudbou = new File("src/music.wav");
        this.clip = null;
        this.audioInputStream = null;
        this.jeUzHudbaPustena = false;
    }

    /**
     * Metóda spustí muziku ak už predtým nebola spustená
     */
    public void hrajHudbu() {
        if (!this.jeUzHudbaPustena) {
            try {
                if (this.suborSHudbou.exists()) {
                    this.audioInputStream = AudioSystem.getAudioInputStream(this.suborSHudbou);
                    this.clip = AudioSystem.getClip();
                    this.clip.open(this.audioInputStream);
                    this.clip.start();
                    this.jeUzHudbaPustena = true;
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Metóda vypne muziku ak už predtým bola spustená.
     */
    public void prestanHratHudbu() {
        if (this.jeUzHudbaPustena) {
            this.clip.stop();
            this.jeUzHudbaPustena = false;
        }
    }
}
