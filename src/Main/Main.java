package Main;

import java.util.*;
import Auxiliares.ClientRepositories;
import Auxiliares.UtilityMethods;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opt = -1, aux;
        Client testClient;
        BankAccount testAccount;

        while (opt != 0) {
            System.out.println("1. Register.");
            System.out.println("2. Log in.");
            System.out.println("3. Show all client's info");
            System.out.println("0. Go back.");
            System.out.print(">> ");
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case  1 -> {
                    System.out.println("1. Register client.\n2. Register client with bank account.\n3. Register client with bank account with funds.");
                    System.out.println("4. Show clients.\n0. Exit.");
                    System.out.print(">> ");
                    opt = sc.nextInt();
                    sc.nextLine();
                    switch (opt) {
                        case 1 -> {//solo crea cliente
                            testClient = UtilityMethods.createCLient();
                        }
                        case 2 -> { //crea cliente y una cuenta de banco
                            testClient = UtilityMethods.createCLient();
                            testAccount = UtilityMethods.createAccount();
                            testClient.getAccounts().add(testAccount);

                            if (!testAccount.isValidAcc())
                                testClient.getInvalidAccs().add(testAccount);
                        }
                        case 3 -> { //crea cliente y cuenta y da opcion a hacer el primer deposito
                            testClient = UtilityMethods.createCLient();
                            testAccount = UtilityMethods.createAccount();
                            testClient.getAccounts().add(testAccount);

                            if (testAccount.isValidAcc()) {
                                System.out.print("Initial balance: $");
                                testClient.showBalance(0);

                                System.out.print("How much do you want to deposit? ");
                                aux = sc.nextInt();
                                sc.nextLine();
                                testClient.getAccounts().getFirst().deposit(aux); //no se si se pueda añadir directamente al testAccount ;-;

                                System.out.print("New balance: $");
                                testClient.showBalance(0);

                                testClient.getAccounts().removeLast(); /*por algun motivo mete dos cuentas iguales, como si fuera el
                            mismo objeto duplucado, asi seria la solucion*/
                            } else {
                                testClient.getInvalidAccs().add(testAccount);
                            }

                            testClient.getAccounts().add(testAccount);
                        }
                        case 4 -> { //muestra los clientes que ya existen
                            int i = 1;
                            System.out.println("--------------------------------");
                            for (Client toShow : ClientRepositories.clients) {
                                System.out.println(i + ". " + toShow.getName() + " " + toShow.getLastName());
                                i ++;
                            }
                            System.out.println("--------------------------------\n");
                        }
                        case 0 -> { //vuelve
                            System.out.println("Going back...");
                            opt = -1;
                        }
                    }
                } case 2 -> { //para iniciar sesion y depositar y retirar libremente de tu cuenta
                    if (ClientRepositories.clients.isEmpty()) {
                        System.out.println("Theres no clients to sign in to, please register");
                    } else {
                        int tuki;
                        Client theClient;
                        ClientRepositories.showClients();
                        System.out.print("Who are you? ");
                        tuki = sc.nextInt();
                        sc.nextLine();

                        theClient = ClientRepositories.clients.get(tuki - 1);

                        byClient(theClient);
                    }
                } case 3 -> UtilityMethods.showEverything();
                case 0 -> System.out.println("Going back...");
                default -> System.out.println("Not an option");
            }
        }

        System.out.println("Bye bye!");

    }

    public static void byClient(Client theCLient) {
        int option, auxx;
        double aux;
        BankAccount theAccount;

        do {
            System.out.println("1. Deposit.\n2. Withdraw\n3. Manage accounts\n0. Go back.");
            System.out.print("What do you want to do? ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> {
                    theCLient.showAccounts();
                    System.out.print("To which account? ");
                    auxx = sc.nextInt();
                    sc.nextLine();
                    theAccount = theCLient.getAccounts().get(auxx - 1);

                    if (theAccount.isValidAcc()) {
                        System.out.print("How much do you want to deposit? ");
                        aux = sc.nextDouble();

                        theAccount.deposit(aux);
                    } else {
                        System.out.println("Cannot deposit to invalid accounts, must change its type before.");
                    }
                } case 2 -> {
                    theCLient.showAccounts();
                    System.out.print("To which account? ");
                    auxx = sc.nextInt();
                    sc.nextLine();
                    theAccount = theCLient.getAccounts().get(auxx - 1);

                    System.out.println("How much do you want to withdraw? ");
                    aux = sc.nextDouble();

                    theAccount.withdraw(aux);
                } case 3 -> {
                    System.out.println("1. Create account\n2. Activate existing account\n0. Go back...");
                    System.out.print(">> ");
                    auxx = sc.nextInt();
                    sc.nextLine();

                    switch (auxx) {
                        case 1 -> {
                            BankAccount newAcc = UtilityMethods.createAccount();
                            theCLient.getAccounts().add(newAcc);
                            if (!newAcc.isValidAcc())
                                theCLient.getInvalidAccs().add(newAcc);

                        } case 2 -> UtilityMethods.changeAccType(theCLient);
                        case 0 -> System.out.println("Going back...");
                        default -> System.out.println("Not an option.");
                    }
                } case 0 -> System.out.println("Going back...");
                default -> System.out.println("Not an option.");
            }
        } while (option != 0);
    }
}