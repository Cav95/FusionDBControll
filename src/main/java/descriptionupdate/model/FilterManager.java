package descriptionupdate.model;

public class FilterManager {

    private String itaFilterTemp = "%";
    private String engFilterTemp = "%";
    private String groupFilterTemp = "%";

    public String getItaFilterTemp() {
        return itaFilterTemp;
    }
    private void setItaFilterTemp(String itaFilterTemp) {
        this.itaFilterTemp = itaFilterTemp;
    }

    public String getEngFilterTemp() {
        return engFilterTemp;
    }

    private void setEngFilterTemp(String engFilterTemp) {
        this.engFilterTemp = engFilterTemp;
    }

    public String getGroupFilterTemp() {
        return groupFilterTemp;
    }

    private void setGroupFilterTemp(String groupFilterTemp) {
        this.groupFilterTemp = groupFilterTemp;
    }

    public void setAllFilterTemp(String ita, String eng, String group) {
        setItaFilterTemp(ita);
        setEngFilterTemp(eng);
        setGroupFilterTemp(group);
    }

    public void resetFilterTemp() {
        setItaFilterTemp("%");
        setEngFilterTemp("%");
        setGroupFilterTemp("%");
    }

    
}
