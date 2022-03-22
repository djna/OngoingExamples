package org.djna.eg;

import java.util.ArrayList;
import java.util.Scanner;

public class Pizza {

    private static final String TOMATO_BASE = "Tomato Base";
    private static final String BARBECUE_BASE = "Barbecue Base";

    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        int userInput;
        String base = null;
        ArrayList<String> toppings = new ArrayList<>();

        System.out.println("Hi, Welcome to Naruke Pizza!");

        while (base == null ) {
            System.out.println("What base do you want? 1 - Tomato Base, 2 - Barbecue Base");
            userInput = console.nextInt();
            switch (userInput) {
                case 1:
                    base = TOMATO_BASE;

                    break;
                case 2:
                    base = BARBECUE_BASE;

                    break;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }


    }

}
