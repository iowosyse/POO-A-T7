package Auxiliares;

import Main.BankAccount;
import Main.Client;

import java.util.*;

public class UtilityMethods {
    static Scanner sc = new Scanner(System.in);
    static String aux;

    public static BankAccount createAccount() {
        char type;
        BankAccount accForMenso = new BankAccount();

        System.out.println("What type of account do you want?");
        System.out.println("A type: MAX $50 000\nB type: MAX $100 000\nC type: unlimited");
        System.out.print(">> ");
        aux = sc.nextLine();
        aux = aux.toLowerCase();
        type = aux.charAt(0);

        accForMenso.setAccType(type);

        return accForMenso;
    }

    /**Crea clientes en fa*/
    public static Client createCLient() {
        Client menso = new Client();

        System.out.print("Whats your name? ");
        aux = sc.nextLine();
        menso.setName(aux);

        System.out.print("Whats your last name? ");
        aux = sc.nextLine();
        menso.setLastName(aux);

        ClientRepositories.clients.add(menso); //para mostrar los clientes luego

        return menso;
    }

    public static void showInvalidAccs(Client theOne) {
        int i = 1;

        System.out.printf("| %-3s | %-4s | %-6s |%n", "No.", "Type", "Active");
        for (BankAccount toShow : theOne.getInvalidAccs()) {
            if (!toShow.isValidAcc()) {
                System.out.printf("| %-3s | %-4s | %-6s |%n", i, toShow.getAccType(), toShow.isValidAcc());
                System.out.println("--------------------");
                i ++;
            }
        }
    }

    public static void changeAccType(Client theCLient) {
        BankAccount toChange;
        int auxx;

        if (theCLient.getInvalidAccs().isEmpty()) {
            System.out.println("Theres no accounts to change.");
        } else {
            UtilityMethods.showInvalidAccs(theCLient);
            System.out.print("What account do you want to change? ");
            auxx = sc.nextInt();
            sc.nextLine();

            toChange = theCLient.getInvalidAccs().get(auxx - 1);

            System.out.print("Change the account type: ");
            char type = sc.nextLine().charAt(0);
            toChange.setAccType(type);

            if (type == 'a' || type == 'b' || type == 'c') {
                toChange.setValidAcc(true);
                theCLient.getInvalidAccs().remove(toChange);
            }
        }
    }

    public static void showEverything() {
        if (!ClientRepositories.clients.isEmpty()) {
            for (Client theClient : ClientRepositories.clients) {
                if (!theClient.getAccounts().isEmpty()) {
                    System.out.println("Accounts owned by " + theClient.getName() + " " + theClient.getLastName() + ": ");
                    theClient.showAccounts();
                } else
                    System.out.println(theClient.getName() + " " + theClient.getLastName() + " has no accounts");
            }
        } else
            System.out.println("There's no clients");

        System.out.println();
    }

}
