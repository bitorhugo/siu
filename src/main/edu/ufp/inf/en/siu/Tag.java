package main.edu.ufp.inf.en.siu;


public enum Tag {
    BUILDING("default"),
    CROSSING("default"),
    CROSSING_ISLAND("default"),
    HIGHWAY("default"),
    ROAD("default"),
    LANES("default"),
    ACCESS("default"),
    SURFACE("default"),
    ADDRESS("default"),
    BIKELANE("default"),
    SIDEWALK("default"),
    ONEWAY("default"),
    BUS("default"),
    NAME("default"),
    ALT_NAME("default"),
    LOC_NAME("default"),
    ADDR_CITY("default"),
    ADDR_POSTCODE("default"),
    ADDR_STREET("default"),
    PUBLIC_TRANSPORT("default"),
    CONTACT_WEBSITE("default"),
    DOG("default"),
    LIT("default"),
    LEISURE("default"),
    OPENING_HOURS("default"),
    MAXSPEED("default");

    private String []value;
    
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
