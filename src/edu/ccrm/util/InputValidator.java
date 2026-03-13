package edu.ccrm.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputValidator {

    private static final String INVALID_NUMBER_MSG = "Invalid input. Please enter a number: ";
    private static final String STREAM_CLOSED_MSG = "\nInput stream closed or unavailable. Exiting...";

    public static int getInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = readInputSafely(scanner);
            if (isValidInput(input)) {
                Integer number = parseInteger(input);
                if (number != null) {
                    return number;
                }
            }
            System.out.print(INVALID_NUMBER_MSG);
        }
    }

    public static String getString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return readInputSafely(scanner);
    }

    private static String readInputSafely(Scanner scanner) {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            handleStreamClosed();
            return ""; // Never reached
        }
    }

    private static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    private static Integer parseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static void handleStreamClosed() {
        System.err.println(STREAM_CLOSED_MSG);
        System.exit(0);
    }
}