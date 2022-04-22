package edu.ufp.inf.en.siu;


public enum Tag {
    BUILDING(),
    ROAD(),
    LANES(),
    ACCESS(),
    SURFACE(),
    ADDRESS(),
    BIKELANE(),
    ONEWAY(),
    BUS(),
    MAXSPEED();

    private Boolean value;

    /**
     * sets all tags values as false
     */
    Tag () {
        for (Tag tag : Tag.values()) {
            tag.value = false;
        }
    }

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
