package com.gmail.sge.serejka;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            FlatDAOImpl flatDAO = new FlatDAOImpl(ConnectionFactory.getConnection());
            flatDAO.createTable();
            while (true) {
                System.out.println("Please, choose on of the following options");
                System.out.println("1 -- add flat to the table");
                System.out.println("2 -- get all flats");
                System.out.println("3 -- get flats from district");
                System.out.println("4 -- get flats by price");
                System.out.println("5 -- get flats by area");
                String s = scanner.next();
                int ch = 0;
                try {
                    ch = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                switch (ch) {
                    case 1:
                        addFlat(flatDAO);
                        break;
                    case 2:
                        System.out.println(flatDAO.getAll());
                        break;
                    case 3:
                        getByDistrict(flatDAO);
                        break;
                    case 4:
                        getByPrice(flatDAO);
                        break;
                    case 5:
                        getByArea(flatDAO);
                        break;
                    default:
                        System.out.println("Choose the correct number");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addFlat(FlatDAOImpl flatDAO) {
        Scanner scanner = new Scanner(System.in);
        Flat flat = new Flat();
        System.out.println("Input the district");
        flat.setDistrict(scanner.nextLine());
        System.out.println("Input the address");
        flat.setAddress(scanner.nextLine());
        try {
            System.out.println("Input the number of rooms");
            flat.setRooms(Integer.parseInt(scanner.nextLine()));
            System.out.println("Input the area");
            flat.setArea(Double.parseDouble(scanner.nextLine()));
            System.out.println("Input the price");
            flat.setPrice(Integer.parseInt(scanner.nextLine()));
            flatDAO.addFlat(flat);
            System.out.println("Flat was added to database");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void getByDistrict(FlatDAOImpl flatDAO){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the district");
        String district = scanner.nextLine();
        System.out.println(flatDAO.getByDistrict(district));
    }

    private static void getByPrice(FlatDAOImpl flatDAO) {
        Scanner scanner = new Scanner(System.in);
        int price = 0;
        int n = 0;
        try {
            System.out.println("1 -- choose maximum price");
            System.out.println("2 -- choose minimum price");
            n = Integer.parseInt(scanner.nextLine());
            switch (n){
                case 1 :
                    System.out.println("Input the maximum price");
                    price = Integer.parseInt(scanner.nextLine());
                    System.out.println(flatDAO.getByMaxPrice(price));
                    break;
                case 2:
                    System.out.println("Input the minimum price");
                    price = Integer.parseInt(scanner.nextLine());
                    System.out.println(flatDAO.getByMinPrice(price));
                    break;
                default:
                    System.out.println("Input the correct digit");
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    private static void getByArea(FlatDAOImpl flatDAO) {
        Scanner scanner = new Scanner(System.in);
        double area = 0;
        int n = 0;
        try {
            System.out.println("1 -- choose maximum area");
            System.out.println("2 -- choose minimum area");
            n = Integer.parseInt(scanner.nextLine());
            switch (n){
                case 1 :
                    System.out.println("Input the maximum area");
                    area = Double.parseDouble(scanner.nextLine());
                    System.out.println(flatDAO.getByMaxArea(area));
                    break;
                case 2:
                    System.out.println("Input the minimum area");
                    area = Double.parseDouble(scanner.nextLine());
                    System.out.println(flatDAO.getByMinArea(area));
                    break;
                default:
                    System.out.println("Input the correct digit");
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}