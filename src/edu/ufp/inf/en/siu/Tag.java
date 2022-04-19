package edu.ufp.inf.en.siu;


public enum Tag {
    BUILDING("default"),
    ROAD("default"),
    LANES("default"),
    ACCESS("default"),
    SURFACE("default"),
    ADDRESS("default"),
    BIKELANE("default"),
    ONEWAY("default"),
    BUS("default"),
    MAXSPEED("default");

    private String value;

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
