package descriptionupdate.model.api;

/**
 * Record representing print code values for various departments.
 *
 * @param codice            the code associated with the print values
 * @param officina         the value for the Officina department
 * @param preassemblaggio  the value for the Preassemblaggio department
 * @param sartoria         the value for the Sartoria department
 * @param prodottoFinito   the value for the Prodotto Finito department
 * @param spedizione       the value for the Spedizione department
 * @param montatori        the value for the Montatori department
 * @param ufficioAcquisti  the value for the Ufficio Acquisti department
 * @param dataValidita     the validity date of the print values
 */
public record PrintCodeValues(String codice, String officina, String preassemblaggio, String sartoria,
        String prodottoFinito, String spedizione, String montatori, String ufficioAcquisti, String dataValidita) {
}
    