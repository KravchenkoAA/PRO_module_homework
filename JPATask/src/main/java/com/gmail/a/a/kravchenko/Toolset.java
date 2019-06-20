package com.gmail.a.a.kravchenko;

import com.gmail.a.a.kravchenko.entitys.Dish;

import java.util.Collection;
import java.util.Scanner;

public class Toolset {

    public static void menu() {
        System.out.println("1. Show menu");
        System.out.println("2. Find dish");
        System.out.println("3. Find dish for price");
        System.out.println("4. Find dish with discount");
        System.out.println("5. Find a set of dishes whose weight is not more than 1 kg");
        System.out.println("6. Add dish");
        System.out.println("7. Update data dishes");
        System.out.println("8. Delete dish");
        System.out.println("9. Exit");
        System.out.println("-------------");
        System.out.println("Please enter number menu");
        System.out.print("=>");
    }

    public static int getKey () {
        Scanner scanner = new Scanner(System.in);
        int key = 0;
        try {
            key = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
        }
        return key;
    }

    public static void clear() {
        for (int i=0;i<15;i++) {
            System.out.println('\n');
        }
    }

    public static void toPrintData (Dish dish) {
        System.out.print(dish.getId());
        if ((4-dish.getId().toString().length())>0) {
            for (int i=0;i<(4-dish.getId().toString().length());i++) {
                System.out.print(" ");
            }
        }
        System.out.print(dish.getName());
        if ((16-dish.getName().length())>0) {
            for (int i=0;i<(16-dish.getName().length());i++) {
                System.out.print(" ");
            }
        }
        System.out.print(dish.getWeight());
        if ((7-dish.getWeight().toString().length())>0) {
            for (int i=0;i<(7-dish.getWeight().toString().length());i++) {
                System.out.print(" ");
            }
        }
        System.out.print(dish.getPrice());
        if ((6-dish.getPrice().toString().length())>0) {
            for (int i=0;i<(6-dish.getPrice().toString().length());i++) {
                System.out.print(" ");
            }
        }
        System.out.print(dish.getDiscount());
        System.out.print('\n');
    }

    public static void toPrintData (Collection<Dish> menu) {
        System.out.println("id  name            weight price discount");
        for (Dish dish : menu) {
            toPrintData(dish);
        }
        System.out.print('\n');
    }

    public static Dish toCreateNewDish () {
        Scanner scanner = new Scanner(System.in);
        Dish newDish = new Dish();
        System.out.println("Enter dish name");
        newDish.setName(scanner.nextLine());
        System.out.println("Enter dish weight");
        newDish.setWeight(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter dish price");
        newDish.setPrice(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter discount for this dish, or set default value \"without discount\" (y). Or press Enter for skip this step");
        String discount = scanner.nextLine();
        if ("y".equals(discount.toLowerCase())) {
            newDish.setDiscount("without discount");
        } else if ("".equals(discount)) {
            newDish.setDiscount(null);
        } else {
            newDish.setDiscount(discount);
        }
        return newDish;
    }

    public static void toPressEnter () {
        Scanner scanner = new Scanner(System.in);
        System.out.print('\n' + "Press Enter to continue");
        for (;;) {
            if ("".equals(scanner.nextLine())) {
                break;
            }
        }
    }
}
