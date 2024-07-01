import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arraylist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input baris kolom matriks: ");
        int baris = scanner.nextInt();
        int kolom = scanner.nextInt();

        List<List<Integer>> matriks = new ArrayList<>();

        for (int i = 0; i < baris; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < kolom; j++) {
                System.out.print("Input nilai " + (i + 1) + "," + (j + 1) + " = ");
                row.add(scanner.nextInt());
            }
            matriks.add(row);
        }

        System.out.println("Matriks asli:");
        for (List<Integer> row : matriks) {
            for (Integer val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }

        List<List<Integer>> transpose = new ArrayList<>();

        for (int j = 0; j < kolom; j++) {
            List<Integer> col = new ArrayList<>();
            for (int i = 0; i < baris; i++) {
                col.add(matriks.get(i).get(j));
            }
            transpose.add(col);
        }

        System.out.println("Matriks transpose:");
        for (List<Integer> row : transpose) {
            for (Integer val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }

        List<List<Integer>> hasil = new ArrayList<>();

        for (int i = 0; i < baris; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < kolom; j++) {
                row.add(matriks.get(i).get(j) + transpose.get(j).get(i));
            }
            hasil.add(row);
        }

        System.out.println("Jumlahkan matriks asli dengan transposenya:");
        for (List<Integer> row : hasil) {
            for (Integer val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}