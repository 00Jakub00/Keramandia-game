package menuHry;


import fri.shapesge.FontStyle;
import fri.shapesge.Obrazok;
import fri.shapesge.Text;
import hlavnyBalikHry.PosuvanaSprava;
import hlavnyBalikHry.ZlozkaHry;

/**
 *Abstraktná trieda, ktorá predstavuje základ pre každé vytvorené menu, obsahuje text, pozadie, rozmery...
 *
 * @author Jakub Gály
 */

public abstract class Menu implements PosuvanaSprava {
    private final int suradnicaX;
    private final int suradnicaY;
    private final Obrazok pozadieMenu;
    private final Text textMenu;
    private final TypTextuMenu typTextuMenu;
    private final int[] rozmeryMenu;

    /**
     * Konštruktor ktorý, vytvorí menu, nastaví ho na určitú polohu, napíšeho na neho text...
     * @param suradnicaX Predstavuje x-ovú polohu menu
     * @param suradnicaY Predstavuje y-ovú polohu menu
     * @param typTextuMenu Tento parameter určuje aký kontkrétny typ menu to je, (next, back, start...)
     * @param sirka Predstavuje šírku menu
     * @param dlzka Predstavuje dĺžku menu
     * @param typ Predstavuje typ menu, máme v typ typ1 a typ2
     */

    public Menu(int suradnicaX, int suradnicaY, TypTextuMenu typTextuMenu, int sirka, int dlzka, String typ) {
        this.rozmeryMenu = new int[]{sirka, dlzka};
        this.typTextuMenu = typTextuMenu;
        this.suradnicaX = suradnicaX;
        this.suradnicaY = suradnicaY;
        this.pozadieMenu = new Obrazok("src/assets/menu/" + typ + ".png", suradnicaX, suradnicaY);
        this.pozadieMenu.zobraz();

        int posunPoOsiX = switch (typTextuMenu) {
            case START_GAME -> 48;
            case CHOOSE_CHARACTER -> 12;
            case SETTINGS -> 63;
            case NEXT, BACK -> 35;
            case CHOOSE -> 22;
            case BACK_TO_MENU -> 32;
            case QUIT -> 55;
            case MUSIC_ON, MUSIC_OFF -> 60;
        };

        this.textMenu = new Text(typTextuMenu.getRetazec(), this.suradnicaX + posunPoOsiX, this.suradnicaY + 40);
        this.textMenu.zmenFont("Plain", FontStyle.PLAIN, 24);
        this.textMenu.zmenFarbu("white");
        this.textMenu.zobraz();
    }

    /**
     * Vráti x-ovú súradnicu menu
     * @return celočíselná hodnota
     */
    public int getSuradnicaX() {
        return this.suradnicaX;
    }

    /**
     * Vráti y-ovú súradnicu menu
     * @return celočíselná hodnota
     */
    public int getSuradnicaY() {
        return this.suradnicaY;
    }

    /**
     * Vráti čoho menu to je toto menu
     * @return Inštancia triedy TypTextuMenu
     */
    public TypTextuMenu getTypTextuMenu() {
        return this.typTextuMenu;
    }

    /**
     * Vráti rozomer menu na základe paramteru
     * @param sirka0dlzka1 Parameter určuje, čo sa vráti šírka/dĺžka
     * @return Celočíslená hodnota
     */
    public int getRozmerMenu(int sirka0dlzka1) {
        return this.rozmeryMenu[sirka0dlzka1];
    }

    /**
     * Metóda zmení uhol menu na základe parametru
     * @param dotknutieMysou Hodnota parametru true/false
     */
    public void zvirazniOkno(boolean dotknutieMysou) {
        if (dotknutieMysou) {
            this.pozadieMenu.zmenUhol(3);
            return;
        }
        this.pozadieMenu.zmenUhol(0);
    }

    /**
     * Správa ktorá vykonáva polymorfizmus, reálne sa táto metóda v tejto triede ani nevykoná
     * @param zlozkaHry Interface ZlozkaHry (Môže byť: Okno, Hra)
     */
    public abstract void kliknutie(ZlozkaHry zlozkaHry);

    /**
     * Metóda skryje celé menu aj s textom
     */
    public void skryMenu() {
        this.pozadieMenu.skry();
        this.textMenu.skry();
    }

}
