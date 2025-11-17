package descriptionupdate.model.api;

/**
 * Record representing a single print value with its associated details.
 *
 * @param codice      the code of the print value
 * @param nomeCampo   the name of the field
 * @param valoreCampo the value of the field
 * @param endValue    the end value associated with the print value
 */
public record SinglePrintvalue(String codice, String nomeCampo, String valoreCampo, String endValue) {

}