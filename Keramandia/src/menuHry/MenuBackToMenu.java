package menuHry;
import hlavnyBalikHry.ZlozkaHry;
import oknaHry.UvitacieOkno;

/**
 *Trieda MenuBackToMenu, ktorá vykonáva polymorfizmus
 *
 * @author Jakub Gály
 */
public class MenuBackToMenu extends Menu {
    private final ZlozkaHry poziciaMenuVHre;

    /**
     * Nastavenie menu na danú polohu s danými parametrami
     * @param suradnicaX Predstavuje x-ovú súradnicu menu
     * @param suradnicaY Predstavuje y-ovú súradcnicu menu
     * @param typTextuMenu Predstavuje konkrétny typ menu
     * @param poziciaMenuVHRe Predstavuje objekt, ktorému sa pošle správa keď sa zavolá metóda kliknutie
     */
    public MenuBackToMenu(int suradnicaX, int suradnicaY, TypTextuMenu typTextuMenu, ZlozkaHry poziciaMenuVHRe) {
        super(suradnicaX, suradnicaY, typTextuMenu, 64,  218, "menu1");
        this.poziciaMenuVHre = poziciaMenuVHRe;
    }
    /**
     * Správa polymorfizmu. Táto metóda, konkrétne posiela správu Hre
     * @param zlozkaHry Interface ZlozkaHry (Môže byť: Okno, Hra)
     */
    @Override
    public void kliknutie(ZlozkaHry zlozkaHry) {
        this.poziciaMenuVHre.vykonajZmenu(new UvitacieOkno(this.poziciaMenuVHre));
    }
}

