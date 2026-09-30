import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class sign {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Signature signature = Signature.getInstance("SHA256withDSA");

        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();

        String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);

        System.out.println("\nOriginal Message: " + message);
        System.out.println("Digital Signature: " + encodedSignature);

        signature.initVerify(publicKey);
        signature.update(message.getBytes());

        boolean verified = signature.verify(digitalSignature);

        System.out.println("Signature Verified: " + verified);

        sc.close();
    }
}
