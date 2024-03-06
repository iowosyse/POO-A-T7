package Main;

import java.util.*;
import Repositories.ClientRepositories;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String aux;

    public static void main(String[] args) {
        int opt = -1, aux;
        Client test;
        BankAccount ciber;

        while (opt != 0) {
            System.out.println("1. Register.");
            System.out.println("2. Log in.");
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

                    Client menso = new Client();
                    switch (opt) {
                        case 1 -> {//solo crea cliente
                            test = createCLient();
                        }
                        case 2 -> { //crea cliente y una cuenta de banco
                            test = createCLient();
                            ciber = createAccount();
                        }
                        case 3 -> { //crea cliente y cuenta y da opcion a hacer el primer deposito
                            test = createCLient();
                            ciber = createAccount();
                            test.getAccounts().add(ciber);

                            System.out.print("Initial balance: $");
                            test.showBalance(0);

                            System.out.print("How much do you want to deposit? ");
                            aux = sc.nextInt();
                            sc.nextLine();
                            test.getAccounts().get(0).deposit(aux); //no se si se pueda añadir directamente al ciber ;-;

                            System.out.print("New balance: $");
                            test.showBalance(0);

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
                        }
                    }
                } case 2 -> { //para iniciar sesion y depositar y retirar libremente de tu cuenta
                    if (ClientRepositories.clients.isEmpty()) {
                        System.out.println("Theres no clients to sign in to");
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
                }
            }
        }

        System.out.println("Bye bye!");

    }

    public static void byClient(Client theCLient) {
        int option, auxx;
        double aux;
        BankAccount theAccount;

        do {
            System.out.println("1. Deposit.\n2. Withdraw\n0. Go back.");
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

                    System.out.print("How much do you want to deposit? ");
                    aux = sc.nextDouble();

                    theAccount.deposit(aux);
                } case 2 -> {
                    theCLient.showAccounts();
                    System.out.print("To which account? ");
                    auxx = sc.nextInt();
                    sc.nextLine();
                    theAccount = theCLient.getAccounts().get(auxx - 1);

                    System.out.println("How much do you want to withdraw? ");
                    aux = sc.nextDouble();

                    theAccount.withdraw(aux);
                }
            }
        } while (option != 0);

    }

    /**Crea cuentas en fa*/
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
}