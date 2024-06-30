package postavy;

/**
 * Táto trieda nám hovorí o základných informácia o postavách (o počte obrázkov) + o šírke postavy
 *
 * @author Jakub Gály
 */

public enum TypPostavy {
    ONRE(6, 7, 70, 0),
    VAMPIRE(5, 7, 80, 8),
    GODOKU(5, 6,  70, 5),
    BERSERK(5, 5, 168, 4);

    private final int pocetObrazkovStatia;
    private final int pocetObrazkovKracania;
    private final int sirkaPostavy;
    private final int pocetObrazkovZomretia;

    /**
     * Nastavenie počtu obrázkov pre daná postavu
     * @param pocetObrazkovStatia Počet obrázkov státia
     * @param pocetObrazkovKracania Počet obrázkov kráčania
     * @param sirkaPostavy Šírka postavy v pixeloch
     * @param pocetObrazkovZomretia Počet obrázkov státia
     */
    TypPostavy(int pocetObrazkovStatia, int pocetObrazkovKracania, int sirkaPostavy, int pocetObrazkovZomretia) {
        this.pocetObrazkovStatia = pocetObrazkovStatia;
        this.pocetObrazkovKracania = pocetObrazkovKracania;
        this.sirkaPostavy = sirkaPostavy;
        this.pocetObrazkovZomretia = pocetObrazkovZomretia;
    }

    /**
     * Vráti počet obrázkov státia
     * @return Celočíselná hodnota
     */
    public int getPocetObrazkovStatia() {
        return this.pocetObrazkovStatia;
    }

    /**
     * Vráti počet obrázkov kráčania
     * @return Celočíselná hodnota
     */
    public int getPocetObrazkovKracania() {
        return this.pocetObrazkovKracania;
    }

    /**
     * Vráti šírku postavu
     * @return Celočíselná hodnota
     */
    public int getSirkaPostavy() {
        return this.sirkaPostavy;
    }

    /**
     * Vráti počet obrázkov zomretia
     * @return Celočíselná hodnota
     */
    public int getPocetObrazkovZomretia() {
        return this.pocetObrazkovZomretia;
    }

}
