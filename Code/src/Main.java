/**
 * Functionality - Actions
 * Make Transaction
 * take loan
 * pay loan
 */

/** Functionality - Management
 * Manage App
 * take PIN to check all these
 * Account Check Up
 * Balance Check
 * Transaction check
 * Loans
 * */

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

    //    Email should contain a-z A-Z 0-9 . @ .com - Done
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
        String fileName = name.toLowerCase().replace(" ", "").replace(".", "") + "@" + email.replace(".", "").split("@")[0] + ".txt";
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

        System.out.println("Successfully created your user account! \nProceed to Log In");
        Thread.sleep(3000);
        return SignIn();
    }

    //    Sign in method - Done
    public static User SignIn() {
        Scanner scan = new Scanner(System.in);

        //  Check if username or password exists
        System.out.print("Enter your email: ");
        String email = scan.nextLine().trim().toLowerCase();
        while (invalidateEMail(email)) {
            System.out.print("Enter your email: ");
            System.out.println("Invalid email.");
            email = scan.nextLine().trim().toLowerCase();
        }

        //  Check if this password is associated with this username or email
        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        //  retrieve the other detail from the database
        File directory = new File("UserData");
        String searchTerm = email.replace(".", "").split("@")[0] + ".txt";
        String file = "";
        File[] matchingFiles = directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.contains(searchTerm);
            }
        });
        // uses email to find file in the Local file system
        if (matchingFiles != null) {
            for (File files : matchingFiles) {
                file = files.getName();
            }
            try {
                // when file is found it collects userdata
                List<String> data = new ArrayList<>();
                File userData = new File("UserData", file);
                Scanner scanner = new Scanner(userData);
                while (scanner.hasNextLine()) {
                    data.add(scanner.nextLine());
                }
                scanner.close();
                // if password matches with the one in the file it returns user else it returns null
                if (!Objects.equals(data.get(2), password)) {
                    System.out.println("User not found: UserData (Access Denied)");
                    return null;
                } else {
                    System.out.println("Log In Successful!");
                    return new User(data.get(0), data.get(1), data.get(2), Long.parseLong(data.get(3)), data.get(4));
                }

            } catch (FileNotFoundException e) {
                System.out.println("User not found: " + e.getMessage());
            }
        } else {
            System.out.println("Data not found!");
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
            }
            else if (userInput == 2) {
                user = SignIn();

            }
            else {
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
