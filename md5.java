import java.security.MessageDigest;
import java.util.Scanner;

public class md5 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] hash = md.digest(message.getBytes());

        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            hexString.append(String.format("%02x", b & 0xff));
        }

        System.out.println("MD5 Hash: " + hexString);

        sc.close();
    }
}
