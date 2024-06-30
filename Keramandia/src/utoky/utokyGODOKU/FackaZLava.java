package utoky.utokyGODOKU;

import hlavnyBalikHry.Hrac;
import postavy.Postava;
import postavy.nepriatelia.Nepriatel;
import utoky.UtokVykonavanyHracom;

/**
 * Trieda prestavujuca utok PadajuciKamen
 * Tento utok funguje tak, že sa hrac teleportne k nepraitelovi a da mu facku
 *
 * @author Jakub Gály
 */
public class FackaZLava extends UtokVykonavanyHracom {
    private int poradoveCisloObrazkaUtoku;
    private int cakaciaDoba;
    private int pocetCyklovUtoku;

    public FackaZLava() {
        super("fackaZLava");
        this.poradoveCisloObrazkaUtoku = 1;
        this.cakaciaDoba = 0;
        this.pocetCyklovUtoku = 0;
    }

    /**
     * Správa polymorfizmu
     * Metóda vykonávajuca započatie útoku facky zlava
     * @param hrac Inštancia triedy Hrac
     * @param nepriatel Inštancia triedy Nepriatel
     * @param sila Celočíselná hodnota
     */

    @Override
    public void zautoc(Hrac hrac, Nepriatel nepriatel, double sila) {
        super.pripravSaNaUtok(hrac, nepriatel);
        int silaUtoku = ( int )sila * 7;
        super.nastavSiluUtoku(silaUtoku);
        hrac.skryPostavuHrac();
        hrac.zmenPolohuHrac(nepriatel.getSuradnicaX(), nepriatel.getSuradnicaY());

        hrac.manazerPrestanSpravovatObjekt();
        super.manazerSpravujObjekt();
    }

    /**
     * Metóda spravovaná manazerom.
     * Vykonáva tento útok
     */

    public void vykonavajUtok() {
        if (this.cakaciaDoba == 7) {
            Hrac hrac = super.getHrac();
            hrac.zobrazPostavu();
            Nepriatel nepriatel = super.getNepriatel();
            Postava postavaHraca = hrac.getPostava();
            if (this.poradoveCisloObrazkaUtoku < 5) {
                postavaHraca.getStavPostavy().zmenObrazok("src/assets/postavy/" + postavaHraca.getTypPostavy().toString() + "/utoky/fackaZLava" + super.getSmerUtoku().toString() + "/" + this.poradoveCisloObrazkaUtoku + ".png");
                this.poradoveCisloObrazkaUtoku++;
            } else if (this.pocetCyklovUtoku < 1) {
                this.pocetCyklovUtoku++;
                this.poradoveCisloObrazkaUtoku = 1;
            } else  {
                hrac.skryPostavuHrac();
                super.ukonciUtok();
                hrac.zobrazPostavu();
                super.getNepriatel().uberZivot(super.getSilaUtoku());
            }
        } else {
            this.cakaciaDoba++;
        }
    }
}
