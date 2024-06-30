package utoky.utokyVAPMIRE;

import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.SmerPostavy;
import postavy.nepriatelia.Nepriatel;
import postavy.ukonyPostav.ChodenieDolava;
import postavy.ukonyPostav.ChodenieDoprava;
import utoky.UtokVykonavanyHracom;

import java.util.Random;

/**
 * Trieda prestavujuca utok meča
 * Tento utok funguje tak, že hráč sa rozbehne k nepriatelovi, keď ku nemu dorazi
 * tak ho sekne mecom, následne sa s nepriatelom este niekolkokrat premiesti a
 * pri kazdom premiesteni ho zas sekne mecom
 *
 * @author Jakub Gály
 */
public class UtokMec extends UtokVykonavanyHracom {
    private int vychodziaXPoziciaNepriatel;
    private int poradoveCisloObrazkaUtoku;
    private int pocetTeleportovani;
    public UtokMec() {
        super("mec");
        this.vychodziaXPoziciaNepriatel = 0;
        this.poradoveCisloObrazkaUtoku = 1;
        this.pocetTeleportovani = 0;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku meča
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        super.pripravSaNaUtok(hrac, nepriatel);
        int silaUtoku = ( int )sila * 2;
        super.nastavSiluUtoku(silaUtoku);
        this.vychodziaXPoziciaNepriatel = nepriatel.getSuradnicaX();
        if (super.getSmerUtoku() == SmerPostavy.VPRAVO) {
            hrac.getPostava().zmenAktualnyUkon(new ChodenieDoprava());
        } else {
            hrac.getPostava().zmenAktualnyUkon(new ChodenieDolava());
        }
        hrac.manazerPrestanSpravovatObjekt();
        super.manazerSpravujObjekt();
    }

    /**
     * Metóda spravovaná manazerom.
     * Vykonáva tento útok
     */

    public void vykonavajUtok() {
        Hrac hrac = super.getHrac();
        Postava nepriatel = super.getNepriatel();
        Postava postavaHraca = hrac.getPostava();
        int vzdialenostMedzi = 0;

        switch (super.getSmerUtoku()) {
            case VPRAVO -> vzdialenostMedzi = Math.abs((hrac.getSuradnicaXHraca() + 70) - (nepriatel.getSuradnicaX() + 30));
            case VLAVO -> vzdialenostMedzi = Math.abs(hrac.getSuradnicaXHraca() - (nepriatel.getSuradnicaX() + nepriatel.getTypPostavy().getSirkaPostavy() - 20));
        }

        if (vzdialenostMedzi >= 8) {
            hrac.getPostava().vykonajUkon("Hrac" + hrac.getPostava().getSmerPostavy().toString(), "postavy", 10);
        } else {
            if (this.poradoveCisloObrazkaUtoku < 5) {
                postavaHraca.getStavPostavy().zmenObrazok("src/assets/postavy/" + postavaHraca.getTypPostavy().toString() + "/utoky/mec" + super.getSmerUtoku().toString() + "/" + this.poradoveCisloObrazkaUtoku + ".png");
                this.poradoveCisloObrazkaUtoku++;
            } else {
                if (this.pocetTeleportovani <= 5) {
                    super.getNepriatel().uberZivot(super.getSilaUtoku());
                    int suradnicaXTeleportacie = 0;
                    Random r = new Random();
                    suradnicaXTeleportacie = r.nextInt(855) + 15;
                    super.getHrac().getPostava().nastavPolohu(suradnicaXTeleportacie, super.getHrac().getPostava().getSuradnicaY());
                    super.getNepriatel().nastavPolohu(suradnicaXTeleportacie + 70, super.getNepriatel().getSuradnicaY());
                    this.poradoveCisloObrazkaUtoku = 1;
                    this.pocetTeleportovani++;
                } else {
                    super.getHrac().getPostava().nastavPolohu(super.getVychodziaXovaPoziciaHraca(), super.getHrac().getPostava().getSuradnicaY());
                    super.getNepriatel().nastavPolohu(this.vychodziaXPoziciaNepriatel, super.getNepriatel().getSuradnicaY());
                    super.ukonciUtok();
                }
            }
        }
    }
}


