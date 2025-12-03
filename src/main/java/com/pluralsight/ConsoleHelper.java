package com.pluralsight;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleHelper {

     private static Scanner scanner = new Scanner(System.in);

        public static String promptForString(String prompt) {
            System.out.print(prompt + ": ");
            return scanner.nextLine().trim();
        }

        public static int promptForInt(String prompt) {


            boolean isValid = false;
            int result = 0;
            do {
                try {
                    System.out.print(prompt + ": ");
                    result = scanner.nextInt();
                    scanner.nextLine();
                    isValid = true;
                } catch (Exception ex) {
                    scanner.nextLine();
                    System.out.println("Invalid Entry, please enter a whole number");
                    //ex.printStackTrace();
                }
            } while (!isValid);


            //after the catch
            return result;

        }


        public static int promptForIntAlt(String prompt) {

            int result = 0;

            try {
                System.out.print(prompt + ": ");
                result = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid Entry, please enter a whole number");
            }

            //after the catch
            return result;

        }

        public static float promptForFloat(String prompt) {
            System.out.print(prompt + ": ");
            float result = scanner.nextFloat();
            scanner.nextLine();
            return result;
        }

        public static long promptForLong(String prompt) {
            System.out.print(prompt + ": ");
            long result = scanner.nextLong();
            scanner.nextLine();
            return result;


        }
        public static LocalDate promptForDate(String prompt){
            while(true){
                try{
                    System.out.print(prompt + ":");
                    String dateAsString = scanner.nextLine().trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return LocalDate.parse(dateAsString, formatter);
                }
                catch (DateTimeException e){
                    System.out.println("Invalid date please enter using format yyyy-MM-dd ");
                }
            }
        }
        public static LocalTime promptForTime(String prompt){
            while(true){
                try{
                    System.out.println(prompt + ":");
                    String timeAsString = scanner.nextLine().trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    return LocalTime.parse(timeAsString, formatter);
                } catch (Exception e){
                    System.out.println("Invalid time please enter using format HH:mm:ss");
                }
            }
        }
        public static double promptForDouble(String prompt) {
while(true) {
    try {
        System.out.println(prompt + ": ");
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please try again:");
        scanner.nextLine();
    }
}
        }
    }





