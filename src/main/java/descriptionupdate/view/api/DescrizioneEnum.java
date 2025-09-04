package descriptionupdate.view.api;

public enum DescrizioneEnum {
    ITA("Descrizione Italiano"),
    ING("Descrizione Inglese"),
    GROUP("Gruppo Merceologico");

    private String description;

    DescrizioneEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
