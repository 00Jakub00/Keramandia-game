package menuHry;

/**
 * Tento enum predstavuje reťazce textu pre dané menu
 *
 * @author Jakub Gály
 */

public enum TypTextuMenu {
    START_GAME("Start Game"),
    CHOOSE_CHARACTER("Choose Character"),
    SETTINGS("Settings"),
    NEXT("Next"),
    BACK("Back"),
    BACK_TO_MENU("Back to menu"),
    QUIT("Quit game"),
    CHOOSE("Choose"),
    MUSIC_ON("Music on"),
    MUSIC_OFF("Music off");

    private final String retazec;

    /**
     * Konštruktor nastaví daný reťazec pre dané menu
     * @param retazec Text menu
     */
    TypTextuMenu(String retazec) {
        this.retazec = retazec;
    }

    /**
     * Vráti nám text menu
     * @return Reťazec
     */
    public String getRetazec() {
        return this.retazec;
    }
}
