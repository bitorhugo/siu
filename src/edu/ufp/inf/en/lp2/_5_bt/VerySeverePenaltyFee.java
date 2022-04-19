package edu.ufp.inf.en.lp2._5_bt;

import edu.ufp.inf.en.lp2._1_intro.date.Date;

public class VerySeverePenaltyFee extends PenaltyFee {

  public VerySeverePenaltyFee(Date date, String motive, String local) {
    super(date, motive, local);
    //TODO Auto-generated constructor stub
  }

  public static String SPEEDING = "EXCESS_SPEED";

  public static String DRIVING_UNDER_INFLUENCE = "EXCESS_ALCOOL";

  @Override
  public int compareTo(PenaltyFee o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void value() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void punishment() {
    // TODO Auto-generated method stub
    
  }

}