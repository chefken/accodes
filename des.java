import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class des {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text to encrypt: ");
        String plainText = sc.nextLine();

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();

        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = desCipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text: " + encryptedText);

        desCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = desCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted Text: " + decryptedText);

        sc.close();
    }
}