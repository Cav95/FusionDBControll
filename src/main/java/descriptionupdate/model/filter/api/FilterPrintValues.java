    package descriptionupdate.model.filter.api;
    public record FilterPrintValues(String code, String dateValue) {

        public FilterPrintValues() {
            this("%", "%");
        }
    }