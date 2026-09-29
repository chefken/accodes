import java.util.Scanner;

public class DiffieHellman {
    static int powerMod(int base, int exponent, int modulus) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result = (result * base) % modulus;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter shared prime number (p): ");
        int p = sc.nextInt();

        System.out.print("Enter primitive root (g): ");
        int g = sc.nextInt();

        System.out.print("Enter Alice's private key (a): ");
        int a = sc.nextInt();

        System.out.print("Enter Bob's private key (b): ");
        int b = sc.nextInt();

        int A = powerMod(g, a, p);
        int B = powerMod(g, b, p);

        System.out.println("Alice's Public Key (A): " + A);
        System.out.println("Bob's Public Key (B): " + B);

        int secretA = powerMod(B, a, p);
        int secretB = powerMod(A, b, p);

        System.out.println("Shared Secret Key computed by Alice: " + secretA);
        System.out.println("Shared Secret Key computed by Bob: " + secretB);

        sc.close();
    }
}
