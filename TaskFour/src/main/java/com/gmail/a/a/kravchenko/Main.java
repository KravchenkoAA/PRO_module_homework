package com.gmail.a.a.kravchenko;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientsDAO clientsDAO = null;
        try {
            clientsDAO = new ReleaseClientsDAO();
            for (; ; ) {
                toCLS();
                toPrintMenu();
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        toCLS();
                        ArrayList<Clients> list = (ArrayList<Clients>) clientsDAO.findAll();
                        toPrintData(list);
                        System.out.print('\n' + "Press Enter to continue");
                        if ("".equals(scanner.nextLine())) {
                            break;
                        }
                    case 2:
                        toCLS();
                        toFindClient(clientsDAO);
                        System.out.print('\n' + "Press Enter to continue");
                        if ("".equals(scanner.nextLine())) {
                            break;
                        }
                    case 3:
                        toCLS();
                        toAddClient(clientsDAO);
                        System.out.print('\n' + "Press Enter to continue");
                        if ("".equals(scanner.nextLine())) {
                            break;
                        }
                    case 4:
                        toCLS();
                        toUpdateClient(clientsDAO);
                        System.out.print('\n' + "Press Enter to continue");
                        if ("".equals(scanner.nextLine())) {
                            break;
                        }
                    case 5:
                        toCLS();
                        toDeleteClient(clientsDAO);
                        System.out.print('\n' + "Press Enter to continue");
                        if ("".equals(scanner.nextLine())) {
                            break;
                        }
                    case 6:
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            ReleaseClientsDAO releaseClientsDAO = (ReleaseClientsDAO) clientsDAO;
            ConnectionUtils.toCloseConnection(releaseClientsDAO.getConnection());
        }
    }

    public static void toPrintMenu() {
        System.out.println("1. Find all clients");
        System.out.println("2. Find client");
        System.out.println("3. Add client");
        System.out.println("4. Edit data about client");
        System.out.println("5. Delete client");
        System.out.println("6. Exit" + '\n');
        System.out.print("=>");
    }

    public static void toPrintData(ArrayList<Clients> list) {
        System.out.println("id   name       age");
        System.out.println("-------------------");
        for (Clients element : list) {
            System.out.print(element.getId());
            int n = 4 - Integer.toString(element.getId()).length();
            if (n > 0) {
                for (int i = 0; i <= n; i++) {
                    System.out.print(" ");
                }
            }
            System.out.print(element.getName());
            n = 10 - element.getName().length();
            if (n > 0) {
                for (int i = 0; i <= n; i++) {
                    System.out.print(" ");
                }
            }
            System.out.println(element.getAge());
        }
    }

    public static void toPrintData(Clients client) {
        System.out.println("id   name       age");
        System.out.println("-------------------");
        System.out.print(client.getId());
        int n = 4 - Integer.toString(client.getId()).length();
        if (n > 0) {
            for (int i = 0; i <= n; i++) {
                System.out.print(" ");
            }
        }
        System.out.print(client.getName());
        n = 10 - client.getName().length();
        if (n > 0) {
            for (int i = 0; i <= n; i++) {
                System.out.print(" ");
            }
        }
        System.out.println(client.getAge());
    }

    public static void toFindClient(ClientsDAO clientsDAO) {
        Scanner scanner = new Scanner(System.in);
        Clients clientForFind = null;
        System.out.println("Enter parameter for search");
        String parameter = scanner.nextLine();
        System.out.println("Enter value for search");
        String value = scanner.nextLine();
        try {
            clientForFind = clientsDAO.find(parameter, value);
            toCLS();
            System.out.println("Results:");
            toPrintData(clientForFind);
        } catch (SQLException e) {
            System.out.println("Input parameter or value is false. Search cannot be performed");
        }
    }

    public static void toAddClient(ClientsDAO clientsDAO) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Clients newClient = new Clients();
        System.out.println("Input client name");
        newClient.setName(scanner.nextLine());
        System.out.println("Input client age");
        newClient.setAge(Integer.parseInt(scanner.nextLine()));
        if (clientsDAO.create(newClient)) {
            System.out.println("New client add successful");
        }
    }

    public static void toUpdateClient(ClientsDAO clientsDAO) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Clients> clients = (ArrayList<Clients>) clientsDAO.findAll();
        toPrintData(clients);
        Clients tempClient = new Clients();
        System.out.println("Enter the line number to be changed");
        tempClient.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter new client name");
        tempClient.setName(scanner.nextLine());
        System.out.println("Enter new client age");
        tempClient.setAge(Integer.parseInt(scanner.nextLine()));
        if (clientsDAO.update(tempClient)) {
            toCLS();
            System.out.println("Update client successful");
        }
        tempClient = null;
    }

    public static void toDeleteClient(ClientsDAO clientsDAO) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Clients clientForDelete = null;
        try {
            ArrayList<Clients> clients = (ArrayList<Clients>) clientsDAO.findAll();
            toPrintData(clients);
            System.out.println("Enter id for delete data client");
            String idValue = scanner.nextLine();
            clientForDelete = clientsDAO.find("id", idValue);
            toCLS();
            toPrintData(clientForDelete);
        } catch (SQLException e) {
            System.out.println("Input parameter or value is false. Search cannot be performed");
        }
        System.out.println("Delete data of this client? (y/n)");
        if ("y".equals(scanner.nextLine().toLowerCase())) {
            if (clientsDAO.delete(clientForDelete.getId())) {
                System.out.println("Is client deleted  successful");
            }
        } else {
            System.out.println("Data was not deleted");
        }
        clientForDelete = null;
    }

    public static void toCLS() {
        for (int i = 0; i < 50; i++) {
            System.out.println('\n');
        }
    }
}