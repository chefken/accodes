import java.util.Scanner;

public class rsa {

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int powerMod(int base, int exponent, int modulus) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result = (result * base) % modulus;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first prime number p: ");
        int p = sc.nextInt();

        System.out.print("Enter second prime number q: ");
        int q = sc.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1) {
                break;
            }
            e++;
        }

        int d = 1;
        while ((d * e) % phi != 1) {
            d++;
        }

        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        System.out.print("Enter message (integer format): ");
        int msg = sc.nextInt();

        int cipherText = powerMod(msg, e, n);
        System.out.println("Encrypted Message: " + cipherText);

        int decryptedMsg = powerMod(cipherText, d, n);
        System.out.println("Decrypted Message: " + decryptedMsg);

        sc.close();
    }
}