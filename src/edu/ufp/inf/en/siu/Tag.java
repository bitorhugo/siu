package edu.ufp.inf.en.siu;


public enum Tag {
    BUILDING("default"),
    CROSSING("default"),
    HIGHWAY("default"),
    ROAD("default"),
    LANES("default"),
    ACCESS("default"),
    SURFACE("default"),
    ADDRESS("default"),
    BIKELANE("default"),
    ONEWAY("default"),
    BUS("default"),
    NAME("default"),
    ADDR_CITY("default"),
    ADDR_POSTCODE("default"),
    MAXSPEED("default");

    private String []value;
    private String volume;
    /**
     * sets a value for tag
     * @param value value of tag
     */
    Tag (String... value) {
        this.value = value;
    }
    Tag (String value, String volume) {

    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String... value) {
        this.value = value;
    }    
    
}
