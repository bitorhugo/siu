package edu.ufp.inf.en.lp2._4_bank;

public class AccountUnsafe extends Account {

    public AccountUnsafe(String accountNumber, Client owner, double bankNum) {
        super(accountNumber, owner, bankNum);
    }

    @Override
    public double withdraw(double amount) {
        super.setBankNum(super.getBankNum() - amount);
        return super.getBankNum();
    }

    @Override
    public double deposit(double amount) {
        super.setBankNum(super.getBankNum() + amount);
        return super.getBankNum();
    }

    @Override
    public double transfer(double amount, AccountMoneyI dest) {
        double b = this.withdraw(amount);
        dest.deposit(amount);
        return b; 
    }

    @Override
    public Client getAccountOwner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAccountOwner() {
        // TODO Auto-generated method stub
        
    }
    
}
