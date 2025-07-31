import java.util.Scanner;

public class rough {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your phone number: ");
        long phoneNumber = scan.nextLong();
        while (Long.toString(phoneNumber).length() != 10) {
            System.out.print("Enter a valid phone number: ");
            phoneNumber = scan.nextLong();
        }
        scan.nextLine();

        System.out.print("Set a 4 digit PIN for your account: ");
        String pin = scan.nextLine();
        while (pin.length() != 4) {
            System.out.print("Set a valid 4 digit PIN for your account: ");
            pin = scan.nextLine();
        }
    }
}
