import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESExample {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Enter key (8 characters): ");
        String key = sc.nextLine();

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(message.getBytes());

        String ciphertext = Base64.getEncoder().encodeToString(encrypted);

        System.out.println("Encrypted message: " + ciphertext);

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decrypted message: " + new String(decrypted));

        sc.close();
    }
}