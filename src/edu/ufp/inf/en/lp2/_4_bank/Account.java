package edu.ufp.inf.en.lp2._4_bank;

/*
    Cannot instantiate abstract classes
    Object not finished
*/


public abstract class Account implements AccountMoneyI, AccountOwnershipI {
    private Client owner;
    private String accountNumber;
    private double bankNum;

    public Account (String accountNumber, Client owner, double bankNum) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.bankNum = bankNum;
    }


    

    public Client getOwner() {
        return owner;
    }




    public void setOwner(Client owner) {
        this.owner = owner;
    }




    public String getAccountNumber() {
        return accountNumber;
    }




    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }




    public double getBankNum() {
        return bankNum;
    }




    public void setBankNum(double bankNum) {
        this.bankNum = bankNum;
    }




    @Override
    public double withdraw(double amount) {
        return 0;
    }
    
    @Override
    public double deposit(double amount) {
        return 0;
    }

    @Override
    public double transfer(double amount, AccountMoneyI dest) {
        return 0;
    }

    @Override
    public double balance() {
        return 0;
    }

}
