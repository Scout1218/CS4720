import java.io.*;
import java.util.*;

public class FileToArray {

    public static void main(String[] args) {
        String filename = "100k.txt";
        // get array from getArray
        int[] numbers = getArray(filename);
        double elapsedMs = bubbleSort(numbers);
        //printArray(numbers);

        System.out.printf("Bubble sort took %.3f ms%n", elapsedMs);
    }

    public static int[] getArray(String filename) {
        List<Integer> numbersList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbersList.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + e.getMessage());
        }

        return numbersList.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void printArray(int[] a) {
        for (int value : a) {
            System.out.println(value);
        }
    }
    
public static double bubbleSort(int[] a) {
    long start = System.nanoTime();

    int n = a.length;
    for (int i = n - 1; i >= 1; i--) {
        for (int j = 0; j < i; j++) {
            if (a[j] > a[j + 1]) {
                // swap a[j] and a[j+1]
                int t = a[j];
                a[j] = a[j + 1];
                a[j + 1] = t;
            }
        }
    }

    long end = System.nanoTime();
    return (end - start) / 1_000_000.0; // return ms
}
}