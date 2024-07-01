import java.util.Scanner;

public class program_matriks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input baris kolom matriks: ");
        int baris = scanner.nextInt();
        int kolom = scanner.nextInt();
        int[][] matriks = new int[baris][kolom];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Input nilai " + (i + 1) + "," + (j + 1) + " = ");
                matriks[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Matriks asli:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + "\t");
            }
            System.out.println();
        }
        int[][] transpose = new int[kolom][baris];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                transpose[j][i] = matriks[i][j];
            }
        }

        System.out.println("Matriks transpose:");
        for (int i = 0; i < kolom; i++) {
            for (int j = 0; j < baris; j++) {
                System.out.print(transpose[i][j] + "\t");
            }
            System.out.println();
        }
        int[][] hasil = new int[baris][kolom];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                hasil[i][j] = matriks[i][j] + transpose[i][j];
            }
        }
        System.out.println("Jumlahkan matriks asli dengan transposenya:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(hasil[i][j] + "\t");
            }
            System.out.println();
        }
    }
}