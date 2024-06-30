package hlavnyBalikHry;

/**
 * Tento interface sa používa v polymorfizme
 * Obsahuje jednu správu
 * Ide o to, že chceme cez premmenú zložka hry posielať správu vykonajZmenu pre viacej objektov rôžneho typu z
 * praktických dôvodov, aby nám to zjednodušilo kód
 *
 * @author Jakub Gály
 */
public interface ZlozkaHry {
    /**
     * Správa polymorfizmu
     * Metóda nerobí nič
     * @param sprava (Môže byť: Okno, Menu, Hrac)
     */
    void vykonajZmenu(PosuvanaSprava sprava);

}
