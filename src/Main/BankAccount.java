package Main;

public class BankAccount {
    private double balance = 0;
    private int accNumber;
    private char accType;
    private double maxBalance;
    private boolean validAcc = true;

    public boolean isValidAcc() {
        return validAcc;
    }

    public void setValidAcc(boolean validAcc) {
        this.validAcc = validAcc;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public char getAccType() {
        return accType;
    }

    public void setAccType(char accType) {
        this.accType = accType;

        switch (accType) {
            case 'a' -> maxBalance = 50000;
            case 'b' -> maxBalance = 100000;
            case 'c' -> maxBalance = Double.MAX_VALUE;
            default -> {
                System.out.println("Could not create or modify that account type");
                validAcc = false;
                this.accType = '~';
            }
        }
    }

    public void deposit(double amount) {
        if (balance + amount > maxBalance || amount < 0) {
            System.out.println("Could not deposit that amount");
        } else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (balance - amount < 0 || amount < 0) {
            System.out.println("Could not withdraw that amount");
        } else {
            balance -= amount;
        }
    }
}
