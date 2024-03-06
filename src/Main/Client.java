package Main;

import java.util.ArrayList;

public class Client {
    private String name;
    private String lastName;
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public void showBalance(int i) {
        System.out.println(accounts.get(i).getBalance());
    }

    public void showAccounts() {
        int i = 1;

        System.out.printf("| %-3s | %-4s |%n", "No.", "Type");
        for (BankAccount toShow : accounts) {
            System.out.printf("| %-3s | %-4s |%n", i, toShow.getAccType());
            System.out.println("--------------------");
        }
    }
}
