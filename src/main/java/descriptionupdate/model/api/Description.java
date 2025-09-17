package descriptionupdate.model.api;

/**
 * Represents a description with its Italian and English translations, along
 * with a group type.
 * This record is used to encapsulate the data for descriptions in the
 * application.
 * It provides a structured way to store and retrieve descriptions
 * in both languages, along with the associated group type.
 * * @param itaDescription the Italian description
 * 
 * @param engDescription the English description
 * @param group          the group type
 */
public record Description(String itaDescription, String engDescription, String group) {
}