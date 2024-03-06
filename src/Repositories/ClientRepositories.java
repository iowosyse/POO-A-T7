package Repositories;

import Main.Client;

import java.util.ArrayList;

public class ClientRepositories {
    public static ArrayList<Client> clients = new ArrayList<>();

    public static void showClients() {
        int i = 1;

        System.out.printf("| %-3s | %-15s |%n", "No.", "Name");
        for (Client pinga : clients) {
            System.out.printf("| %-3s | %-15s |%n", i, pinga.getName() + " " + pinga.getLastName());
            System.out.println("---------------------------------------------");
        }
    }
}
