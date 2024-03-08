package Main;

import java.util.ArrayList;

public class Client {
    private String name;
    private String lastName;
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private ArrayList<BankAccount> invalidAccs = new ArrayList<>();

    public ArrayList<BankAccount> getInvalidAccs() {
        return invalidAccs;
    }

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

        System.out.printf("| %-3s | %-4s | %-6s | %-12s |%n", "No.", "Type", "Active", "Balance");
        for (BankAccount toShow : accounts) {
            System.out.printf("| %-3s | %-4s | %-6s | %-12s |%n", i, toShow.getAccType(), toShow.isValidAcc(), toShow.getBalance());
            System.out.println("--------------------");

            i ++;
        }
    }
}
