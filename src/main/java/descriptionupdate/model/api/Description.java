package descriptionupdate.model.api;


/**
 * Record representing descriptions in Italian and English along with their group.
 * @param itaDescription the Italian description
 * @param engDescription the English description
 * @param group          the group associated with the descriptions
 */
public record Description(String itaDescription, String engDescription, String group) {
}