package edu.ufp.inf.en.lp2._4_bank;

import java.util.ArrayList;

import edu.ufp.inf.en.lp2._1_intro.date.Date;
import edu.ufp.inf.en.lp2._1_intro.person.Person;


public class Client extends Person implements ClientRelationshipsI {

    private String vat;
    private ArrayList<Account> accounts;

    public Client(String name, String address, String idNumber, String vat, Date birth) {
        super(name, address, idNumber, birth);
        this.vat = vat;
    }

    

    public String getVat() {
        return vat;
    }



    public void setVat(String vat) {
        this.vat = vat;
    }



    public ArrayList<Account> getAccounts() {
        return accounts;
    }



    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }



    @Override
    public void addAccount(Account a) {
        if (!this.contains(a)) return;
        a.setOwner(this);
        this.accounts.add(a);
    }

    @Override
    public Account getAccount(String owner) {
        for (Account account : accounts) {
            if (account.getAccountOwner().getName() == owner) {
                return account;
            }
        }
        return null;
    }
    
    public boolean contains (Account a) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == a.getAccountNumber()) return false;
        }
        return true;
    }
}
