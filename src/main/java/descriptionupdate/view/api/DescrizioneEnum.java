package descriptionupdate.view.api;

/**
 * Enum representing description types for Italian, English, and group.
 */
public enum DescrizioneEnum {
    ITA("Descrizione Italiano"),
    ING("Descrizione Inglese"),
    GROUP("Gruppo Merceologico");

    private final String description;

    /**
     * Constructs a DescrizioneEnum with the specified description.
     *
     * @param description the description string
     */
    DescrizioneEnum(final String description) {
        this.description = description;
    }

    /**
     * Returns the description string associated with the enum constant.
     *
     * @return the description string
     */
    public String getDescription() {
        return description;
    }
}
