package edu.ccrm.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputValidator {

    public static int getInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.print("Invalid input. Please enter a number: ");
                    continue;
                }
                
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number: ");
                }
            } catch (NoSuchElementException e) {
                System.err.println("\nInput stream closed or unavailable. Exiting...");
                System.exit(0);
            }
        }
    }

    public static String getString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.err.println("\nInput stream closed or unavailable. Exiting...");
            System.exit(0);
            return "";
        }
    }
}