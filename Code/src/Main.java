/**
 * Functionality - Actions
 * Make Transaction
 * take loan
 * pay loan
 */

/**
 * Functionality - Management
 * Manage App
 * take PIN to check all these
 * Account Check Up
 * Balance Check
 * Transaction check
 * Loans
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // clear terminal
    public static void cleanTerminal() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    //    Email should contain aA-zZ 0-9 . @ .com - Done
    public static boolean invalidateEMail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return !matcher.matches();
    }

    //    Sign up method - Done
    public static User SignUp() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        // takes username
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        // takes user's email
        System.out.print("Enter your email: ");
        String email = scan.nextLine().trim().toLowerCase();
        while (invalidateEMail(email)) {
            System.out.print("Enter a valid email: ");
            email = scan.nextLine().trim().toLowerCase();
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
        String subFolderName = name.toLowerCase().replace(" ", "").replace(".", "") + "@" + email.replace(".", "").split("@")[0];
        try {
            File mainFolder = new File("UserData/");
            if (!mainFolder.exists()) mainFolder.mkdir();
            File subFolder = new File(mainFolder, subFolderName);
            if (!subFolder.exists()) {
                subFolder.mkdir();
                File userData = new File(subFolder, "credentials.txt");
                if (!userData.exists()) {
                    FileWriter enterUserData = new FileWriter(userData);
                    enterUserData.write(name + "\n" + email + "\n" + password + "\n" + phoneNumber + "\n" + pin);
                    enterUserData.close();
                }
            } else System.out.println("A User already exists with this username/ email.");
        } catch (IOException e) {
            System.out.println("An error occurred while processing the user data.\n" + e.getMessage());
        }

        System.out.println("Successfully created your user account! \nProceed to Log In");
        Thread.sleep(3000);
        cleanTerminal();
        return SignIn();
    }

    //    Sign in method - Done
    public static User SignIn() {
        Scanner scan = new Scanner(System.in);

        //  Check if username and password exists
        System.out.print("Enter your name: ");
        String name = scan.nextLine();

        System.out.print("Enter your email: ");
        String email = scan.nextLine().trim().toLowerCase();
        while (invalidateEMail(email)) {
            System.out.println("Invalid email.");
            System.out.print("Enter your email: ");
            email = scan.nextLine().trim().toLowerCase();
        }

        //  Check if this password is associated with this username and email
        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        //  retrieve the other detail from the database
        File mainFolder = new File("UserData");
        String subFolderName = name.toLowerCase().replace(" ", "").replace(".", "") + "@" + email.replace(".", "").split("@")[0];
        File userFolder = new File(mainFolder, subFolderName);
        if (userFolder.exists() && userFolder.isDirectory()) {
           File credentials = new File(userFolder, "credentials.txt");
           if (credentials.exists()) {
               try {
                   List<String> userData = new ArrayList<>();
                   Scanner scanner = new Scanner(credentials);
                   while (scanner.hasNextLine()) {
                       userData.add(scanner.nextLine());
                   }
                   scanner.close();

                   if (!Objects.equals(password, userData.get(2))) {
                       System.out.println("Wrong Password");
                       return null;
                   } else {
                       System.out.println("Log In Successful!");
                       return new User(
                               userData.get(0),                  // name
                               userData.get(1),                  // email
                               userData.get(2),                  // password
                               Long.parseLong(userData.get(3)),  // phone
                               userData.get(4)                   // pin
                       );
                   }

               } catch (FileNotFoundException e) {
                   System.out.println("User not found: " + e.getMessage());
               }
           } else {
               System.out.println("User credentials not found.");
           }
        } else {
            System.out.println("User does not exists with username/ email.");
        }

        return null;
    }

    //    Main working method
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        Scanner scan = new Scanner(System.in);
        boolean wantToExit = false;

        while (!wantToExit) {
            System.out.println("Welcome to PayLynx - Your Net Banking Command Line!");
            System.out.println("-----------------------------------------");
            System.out.print("Choose one option: \n1. Sign Up \n2. Sign In \n3. Exit \n(Pick one number): ");
            int userInput = scan.nextInt();
            System.out.println("-----------------------------------------");
            System.out.print('\n');

            if (userInput == 1) {
                try {
                    user = SignUp();
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.out.println("An error occurred while processing the user data.\n" + e.getMessage());

                }
            } else if (userInput == 2) {
                user = SignIn();

            } else {
                System.out.println("See you soon!");
                Thread.sleep(3000);
                user = null;
                wantToExit = true;
            }

            System.out.println("-----------------------------------------");
            cleanTerminal();

            if (user != null) {
                System.out.println(user.name);
                System.out.println(user.email);
                System.out.println(user.password);
                System.out.println(user.phoneNumber);
                System.out.println(user.pin);
            }

            cleanTerminal();
        }
    }

}
