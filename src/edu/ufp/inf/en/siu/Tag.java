package edu.ufp.inf.en.siu;


public enum Tag {
    BUILDING(false),
    ROAD(false),
    LANES(false),
    ACCESS(false),
    SURFACE(false),
    ADDRESS(false),
    BIKELANE(false),
    ONEWAY(false),
    BUS(false),
    MAXSPEED(false);

    private Boolean value;

    /**
     * sets a value for tag
     * @param value value of tag
     */
    Tag (Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    
}
