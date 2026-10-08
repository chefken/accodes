import java.util.Scanner;

public class Hillcipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== HILL CIPHER (3x3) =====");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1 || choice == 2) {
                System.out.print("Enter the text: ");
                String text = cleanText(sc.nextLine());
                System.out.println("Enter the 3 x 3 Key Matrix (values 0-25):");
                int[][] keyMatrix = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        keyMatrix[i][j] = sc.nextInt();
                    }
                }
                sc.nextLine();
                if (choice == 1) {
                    System.out.println("Encrypted text: " + hillEncrypt(text, keyMatrix));
                } else {
                    System.out.println("Decrypted text: " + hillDecrypt(text, keyMatrix));
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
    public static String hillEncrypt(String plain, int[][] keyMatrix) {
        while (plain.length() % 3 != 0) {
            plain = plain + "X";
        }
        StringBuilder cipher = new StringBuilder();
        for (int k = 0; k < plain.length(); k += 3) {
            int[] plainVector = createVector(plain.substring(k, k + 3));
            int[] cipherVector = new int[3];
            for (int i = 0; i < 3; i++) {
                cipherVector[i] = 0;
                for (int j = 0; j < 3; j++) {
                    cipherVector[i] += keyMatrix[i][j] * plainVector[j];
                }
                cipherVector[i] = ((cipherVector[i] % 26) + 26) % 26;
            }
            for (int value : cipherVector) {
                cipher.append((char) (value + 'A'));
            }
        }
        return cipher.toString();
    }
    public static String hillDecrypt(String cipher, int[][] keyMatrix) {
        int[][] inverseMatrix = inverseMatrix(keyMatrix);
        if (inverseMatrix == null) {
            return "Decryption failed. Invalid or non-invertible key matrix.";
        }
        while (cipher.length() % 3 != 0) {
            cipher = cipher + "X";
        }
        StringBuilder plain = new StringBuilder();
        for (int k = 0; k < cipher.length(); k += 3) {
            int[] cipherVector = createVector(cipher.substring(k, k + 3));
            int[] plainVector = new int[3];
            for (int i = 0; i < 3; i++) {
                plainVector[i] = 0;
                for (int j = 0; j < 3; j++) {
                    plainVector[i] += inverseMatrix[i][j] * cipherVector[j];
                }
                plainVector[i] = ((plainVector[i] % 26) + 26) % 26;
            }
            for (int value : plainVector) {
                plain.append((char) (value + 'A'));
            }
        }
        return plain.toString();
    }
    public static int[] createVector(String text) {
        int[] vector = new int[3];
        for (int i = 0; i < 3; i++) {
            vector[i] = text.charAt(i) - 'A';
        }
        return vector;
    }
    public static int[][] inverseMatrix(int[][] matrix) {
        int det = determinant(matrix);
        int detInverse = modInverse(det, 26);
        if (detInverse == -1) {
            return null;
        }
        int[][] adj = adjugate(matrix);
        int[][] inverse = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverse[i][j] = ((adj[i][j] * detInverse) % 26 + 26) % 26;
            }
        }
        return inverse;
    }
    public static int[][] adjugate(int[][] m) {
        int[][] adj = new int[3][3];
        adj[0][0] = m[1][1] * m[2][2] - m[1][2] * m[2][1];
        adj[1][0] = -(m[1][0] * m[2][2] - m[1][2] * m[2][0]);
        adj[2][0] = m[1][0] * m[2][1] - m[1][1] * m[2][0];
        adj[0][1] = -(m[0][1] * m[2][2] - m[0][2] * m[2][1]);
        adj[1][1] = m[0][0] * m[2][2] - m[0][2] * m[0][2];
        adj[2][1] = -(m[0][0] * m[2][1] - m[0][1] * m[2][0]);
        adj[0][2] = m[0][1] * m[1][2] - m[0][2] * m[1][1];
        adj[1][2] = -(m[0][0] * m[1][2] - m[0][2] * m[1][0]);
        adj[2][2] = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adj[i][j] = ((adj[i][j] % 26) + 26) % 26;
            }
        }
        return adj;
    }
    public static int determinant(int[][] matrix) {
        int det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        return ((det % 26) + 26) % 26;
    }
    public static int modInverse(int a, int mod) {
        a = ((a % mod) + mod) % mod;
        for (int x = 1; x < mod; x++) {
            if ((a * x) % mod == 1) {
                return x;
            }
        }
        return -1;
    }
}
