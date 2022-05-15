package main.edu.ufp.inf.en.siu.database.transport;

public enum Transport {
    WALKING(80),
    CYCLING(308.33),
    CITYVEHICLE(833.333),
    HIGHWAYVEHICLE(1833.33), 
    BUS(750);

    public final double speed;
    private Transport (double speed){
      this.speed = speed;
    }  
}
