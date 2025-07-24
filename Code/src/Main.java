/**
 * Functionality - Actions
 * Make Transaction
 * take loan
 * pay loan
 * */

/** Functionality - Management
 * Manage App
 * take PIN to check all these
 * Account Check Up
 * Balance Check
 * Transaction check
 * Loans
 * */

import java.util.Scanner;

public class Main {
    public static void SignUp() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scan.nextLine();

//        Validate this email
        System.out.print("Enter your email: ");
        String email = scan.nextLine();

        System.out.print("Create a Password: ");
        String password = scan.nextLine();

        System.out.print("Enter your phone number: ");
        long phoneNumber = scan.nextLong();

//        Validate the pin if longer than 4
        System.out.print("Set a 4 digit PIN for your account: ");
        int pin = scan.nextInt();

    }
    public static void SignIn() {
        Scanner scan = new Scanner(System.in);

//        Check if username or password exists
        System.out.print("Username (or Email): ");
        String userNameOrEmail = scan.nextLine();

//        Check if this password is associated with this username or email
        System.out.print("Password: ");
        String password = scan.nextLine();

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Choose one option: \n1. Sign Up \n2. Sign In \n3. Exit \n(Pick one number): ");
        int userInput = scan.nextInt();
        System.out.print('\n');

        if (userInput == 1) SignUp();
        else if (userInput == 2) SignIn();
        else System.out.println("See you soon!");

    }
}
