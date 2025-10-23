package descriptionupdate.model.api;

public enum Reparti {
    OFFICINA("Officina"),
    PREASSEMBLAGGIO("Preassemblaggio"),
    SARTORIA("Sartoria"),
    PRODOTTO_FINITO("Prodotto Finito"),
    SPEDIZIONE("Spedizione"),
    MONTATORI("Montatori"),
    UFFICIO_ACQUISTI("Ufficio Acquisti");

    private final String repartoName;

    Reparti(String repartoName) {
        this.repartoName = repartoName;
    }

    /**
     * Returns the reparto name as a string.
     *
     * @return the reparto name
     */
    public String getRepartoName() {
        return repartoName;
    }
    
}
