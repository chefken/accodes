import java.util.Scanner;

public class rc4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext string: ");
        String text = sc.nextLine();

        System.out.print("Enter key string: ");
        String keyStr = sc.nextLine();

        byte[] plaintext = text.getBytes();
        byte[] key = keyStr.getBytes();

        int[] S = new int[256];
        for (int i = 0; i < 256; i++) {
            S[i] = i;
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + (key[i % key.length] & 0xFF)) % 256;
            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }

        int i = 0;
        j = 0;
        byte[] ciphertext = new byte[plaintext.length];

        for (int k = 0; k < plaintext.length; k++) {
            i = (i + 1) % 256;
            j = (j + S[i]) % 256;

            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;

            int keystreamByte = S[(S[i] + S[j]) % 256];
            ciphertext[k] = (byte) (plaintext[k] ^ keystreamByte);
        }

        System.out.print("Encrypted Bytes: ");
        for (byte b : ciphertext) {
            System.out.printf("%02X ", b);
        }
        System.out.println();

        byte[] decryptedText = new byte[ciphertext.length];

        for (int k = 0; k < 256; k++) {
            S[k] = k;
        }
        j = 0;
        for (int k = 0; k < 256; k++) {
            j = (j + S[k] + (key[k % key.length] & 0xFF)) % 256;
            int temp = S[k];
            S[k] = S[j];
            S[j] = temp;
        }

        i = 0;
        j = 0;
        for (int k = 0; k < ciphertext.length; k++) {
            i = (i + 1) % 256;
            j = (j + S[i]) % 256;

            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;

            int keystreamByte = S[(S[i] + S[j]) % 256];
            decryptedText[k] = (byte) (ciphertext[k] ^ keystreamByte);
        }

        System.out.println("Decrypted Text: " + new String(decryptedText));

        sc.close();
    }
}