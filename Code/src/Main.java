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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
//    email should contain a-z A-Z 0-9 . @ .com
    public static boolean validateEMail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public static User SignUp() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scan.nextLine();

//        Validate this email
        System.out.print("Enter your email: ");
        String email = scan.nextLine().trim().toLowerCase();
        while (!validateEMail(email)) {
            System.out.print("Enter a valid email: ");
            email = scan.nextLine();
        }

        System.out.print("Create a Password: ");
        String password = scan.nextLine();

        System.out.print("Enter your phone number: ");
        long phoneNumber = scan.nextLong();
        while (Long.toString(phoneNumber).length() != 10) {
            System.out.print("Enter a valid phone number: ");
            phoneNumber = scan.nextLong();
        }

//        Validate the pin if longer than 4
        System.out.print("Set a 4 digit PIN for your account: ");
        String pin = scan.nextLine();
        while (pin.length() != 4) {
            System.out.print("Set a valid 4 digit PIN for your account: ");
            pin = scan.nextLine();
        }

//        Save all this data in JDBC Database

        return new User(name, email, password, phoneNumber, pin);
    }
    public static User SignIn() {
        Scanner scan = new Scanner(System.in);

//        Check if username or password exists
        System.out.print("Username (or Email): ");
        String userNameOrEmail = scan.nextLine();

//        Check if this password is associated with this username or email
        System.out.print("Password: ");
        String password = scan.nextLine();

//        retrieve the other detail from the database
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        User user;

        System.out.print("Choose one option: \n1. Sign Up \n2. Sign In \n3. Exit \n(Pick one number): ");
        int userInput = scan.nextInt();
        System.out.print('\n');

        if (userInput == 1) user = SignUp();
        else if (userInput == 2) user = SignIn();
        else System.out.println("See you soon!");

    }
}
