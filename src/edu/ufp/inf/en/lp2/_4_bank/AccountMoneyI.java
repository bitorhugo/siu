package edu.ufp.inf.en.lp2._4_bank;

public interface AccountMoneyI {
    
    public double withdraw (double amount);

    public double deposit (double amount);

    public double transfer (double amount, AccountMoneyI dest);

    public double balance();
}
