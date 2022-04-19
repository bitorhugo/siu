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

    private String value;

    /**
     * sets all tags values as 'default'
     */
    Tag () {
        for (Tag tag : Tag.values()) {
            tag.value = "default";
        }
    }

    /**
     * sets a value for tag
     * @param value value of tag
     */
    Tag (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}
