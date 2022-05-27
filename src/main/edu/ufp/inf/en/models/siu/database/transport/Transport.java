package main.edu.ufp.inf.en.models.siu.database.transport;

public enum Transport {
  // measured in meters per second
  WALKING(1.333),
  CYCLING(5.133),
  CITYVEHICLE(13.8333),
  BUS(12.5);

  public final double speed;
  private Transport (double speed){
    this.speed = speed;
  }  
  
}
