package hlavnyBalikHry;

import levely.Level;
import postavy.Postava;
import postavy.SmerPostavy;

/**
 * Táto trieda slúži na to, aby sa hráč pohyboval len po tých miestach, po ktorých
 * má povolené sa hýbať.
 *
 * @author Jakub Gály
 */
public class KontrolaKolizieHraca {

    /**
     * Metóda zistí smer, ktorým sa hráč môže pohnúť na základe vstupných parametrov.
     * @param postava Postava hráča
     * @param prvyPosun Tetno posun je tu kvôli peknému zladeniu hráča s okrajom, aby reálne z nášho pohľadu nemohol odísť za okraj (nie z pohľadu súradníc súradníc).
     * @param druhyPosun Tento istý princíp aký pri prvom posune.
     * @return Vracia povolený smer pohybu.
     */
    public SmerPostavy maHracPovolenyPohyb(Postava postava, int prvyPosun, int druhyPosun) {
        if (postava.getSuradnicaX() > Level.SURADNICA_X_LEVELOV - prvyPosun  &&
                postava.getSuradnicaX() + 70 < Level.MAX_SURADNICA_X_LEVELOV + druhyPosun) {
            return SmerPostavy.OBESTRANY;
        } else if (postava.getSuradnicaX() > Level.SURADNICA_X_LEVELOV - prvyPosun) {
            return SmerPostavy.VLAVO;
        } else {
            return SmerPostavy.VPRAVO;
        }
    }
}
