package Auxiliares;

import Main.BankAccount;
import Main.Client;
import Repositories.ClientRepositories;
import java.util.*;

public class AuxiliarMethods {
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
            }
        }
    }

}
