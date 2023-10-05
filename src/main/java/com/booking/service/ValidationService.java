package com.booking.service;

import java.util.Scanner;

public class ValidationService {
    public static String regexNumber = "^[0-9]+$";
    public static String regexHuruf = "^[a-zA-Z ]+$";
    public static String regexID = "^[A-Za-z0-9\\-]+$";
    private static Scanner input = new Scanner(System.in);

    public static String validateInput(String question, String errorMessage, String regex) {
        String result;
        boolean isLooping = true;
        do {
            System.out.print(question);
            result = input.nextLine();

            if (result.matches(regex)) {
                isLooping = false;
            }else {
                System.out.println(errorMessage);
            }
        } while (isLooping);

        return result;
    }

    public static int validateNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
        int result;
        boolean isLooping = true;

        do {
            result = Integer.valueOf(validateInput(question, errorMessage, regex));
            if (result >= min && result <= max) {
                isLooping = false;
            }else {
                System.out.println(errorMessage);
            }
        } while (isLooping);

        return result;
    }


    public static void validateCustomerId(){

    }

    public static boolean validateMenu(String question) {
        boolean isLooping = false;

        int pilihan = validateNumberWithRange(question, "Hanya menerima inputan angka 0!", regexNumber, 0, 0);
        if (pilihan == 0) {
            isLooping = true;
        }

        return isLooping;
    }

    public static boolean validateService(String question) {
        boolean isLooping = false;

        String pilihan = validateInput(question, "Input Tidak Dimengerti, Pastikan Employee ID valid!", regexHuruf);
        if (pilihan.equalsIgnoreCase("Y")) {
            isLooping = true;
        }

        return isLooping;
    }
}
