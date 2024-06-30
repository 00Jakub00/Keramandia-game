package zivot;

import fri.shapesge.Obdlznik;

/**
 * Táto trieda predstavuje časť odobraného života
 *
 * @author Jakub Gály
 */
public class OdobranyZivot {
    private final int suradnicaX;
    private final Obdlznik zivot;

    /**
     * Nastaví sa koľko života sa odobralo
     * @param suradnicaX x-ová súradnica odobraného života
     * @param suradnicaY y-ová súradnica odobraného života
     * @param dlzkaOdobranehoZivota Počet odobraných životov
     */
    public OdobranyZivot(int suradnicaX, int suradnicaY, int dlzkaOdobranehoZivota) {
        this.suradnicaX = suradnicaX;
        this.zivot = new Obdlznik(suradnicaX, suradnicaY);
        this.zivot.zmenStrany(dlzkaOdobranehoZivota, 10);
        this.zivot.zmenFarbu("black");
        this.zivot.zobraz();
    }

    /**
     * Vráti x-ovú súradnicu odobraného života
     * @return
     */

    public int getSuradnicaX() {
        return this.suradnicaX;
    }

    /**
     * Skryje odobraný život
     */
    public void vymaz() {
        this.zivot.skry();
    }
}
