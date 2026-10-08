import java.util.Scanner;

public class Caesar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== CAESAR CIPHER =====");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1 || choice == 2) {
                System.out.print("Enter the text: ");
                String text = cleanText(sc.nextLine());
                System.out.print("Enter the key (a whole number): ");
                int key = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Encrypted text: " + caesarEncrypt(text, key));
                } else {
                    System.out.println("Decrypted text: " + caesarDecrypt(text, key));
                }
            } else if (choice != 3) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        sc.close();
    }
    public static String cleanText(String text) {
        String upper = text.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < upper.length(); i++) {
            char ch = upper.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public static String caesarEncrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int p = text.charAt(i) - 'A';
            int c = ((p + key) % 26 + 26) % 26;
            result.append((char) (c + 'A'));
        }
        return result.toString();
    }
    public static String caesarDecrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i) - 'A';
            int p = ((c - key) % 26 + 26) % 26;
            result.append((char) (p + 'A'));
        }
        return result.toString();
    }
}
