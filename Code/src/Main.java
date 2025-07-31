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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

//    Sign up method - Done
    public static User SignUp() {
        Scanner scan = new Scanner(System.in);
        // takes username
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        // takes user's email
        System.out.print("Enter your email: ");
        String email = scan.nextLine().trim().toLowerCase();
        while (!validateEMail(email)) {
            System.out.print("Enter a valid email: ");
            email = scan.nextLine();
        }
        // takes user's password
        System.out.print("Create a Password: ");
        String password = scan.nextLine();
        // takes user's phone number
        System.out.print("Enter your phone number: ");
        long phoneNumber = scan.nextLong();
        while (Long.toString(phoneNumber).length() != 10) {
            System.out.print("Enter a valid phone number: ");
            phoneNumber = scan.nextLong();
        }
        scan.nextLine();  // newLine bug of Scanner
        // takes use's pin
        System.out.print("Set a 4 digit PIN for your account: ");
        String pin = scan.nextLine();
        while (pin.length() != 4) {
            System.out.print("Set a valid 4 digit PIN for your account: ");
            pin = scan.nextLine();
        }
        // Saves all this data in File
        String fileName = name.toLowerCase().replace(" ", "").replace(".", "") + "@" + email.toLowerCase().split("@")[0] + ".txt";
        try {
            File userData = new File("UserData/", fileName);
            if (!userData.exists()) {
                FileWriter enterUserData = new FileWriter(userData
                );
                enterUserData.write(name + "\n" + email + "\n" + password + "\n" + phoneNumber + "\n" + pin);
                enterUserData.close();
            } else {
                System.out.println("A user already exists with this Username and Email.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the user data.\n" + e.getMessage());
        }
        // return a User object with these values initialised
        return new User(name, email, password, phoneNumber, pin);
    }

//    Sign in method
    public static User SignIn() {
        Scanner scan = new Scanner(System.in);

//        Check if username or password exists
        System.out.print("Username (or Email): ");
        String userNameOrEmail = scan.nextLine();

//        Check if this password is associated with this username or email
        System.out.print("Password: ");
        String password = scan.nextLine();

//        retrieve the other detail from the database
//        return new User(name, email, password, phoneNumber, pin);
        return null;
    }

    public static void main(String[] args) {
        User user = new User();
        Scanner scan = new Scanner(System.in);

        System.out.print("Choose one option: \n1. Sign Up \n2. Sign In \n3. Exit \n(Pick one number): ");
        int userInput = scan.nextInt();
        System.out.print('\n');

        if (userInput == 1) {
            try {
                user = SignUp();
            } catch (Exception e) {
                System.out.println("An error occurred while processing the user data.\n" + e.getMessage());

            }
        }
        else if (userInput == 2) user = SignIn();
        else System.out.println("See you soon!");

//        assert user != null;
//        System.out.println(user.name);
//        System.out.println(user.email);
//        System.out.println(user.password);
//        System.out.println(user.phoneNumber);
//        System.out.println(user.pin);
    }

}
