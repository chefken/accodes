import java.util.Scanner;

public class Substitution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String plainAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int choice;
        do {
            System.out.println("\n===== SUBSTITUTION CIPHER =====");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1 || choice == 2) {
                System.out.print("Enter the 26-letter key alphabet: ");
                String keyAlphabet = sc.nextLine().toUpperCase();
                if (keyAlphabet.length() != 26) {
                    System.out.println("Error: The key must contain exactly 26 letters.");
                    continue;
                }
                System.out.print("Enter the text: ");
                String text = cleanText(sc.nextLine());
                StringBuilder result = new StringBuilder();
                if (choice == 1) {
                    for (int i = 0; i < text.length(); i++) {
                        int position = plainAlphabet.indexOf(text.charAt(i));
                        result.append(keyAlphabet.charAt(position));
                    }
                    System.out.println("Encrypted text: " + result.toString());
                } else {
                    for (int i = 0; i < text.length(); i++) {
                        int position = keyAlphabet.indexOf(text.charAt(i));
                        result.append(plainAlphabet.charAt(position));
                    }
                    System.out.println("Decrypted text: " + result.toString());
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
}
