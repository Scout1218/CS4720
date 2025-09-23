// Grant Wehrli
// bubblesort implementation


import java.io.*;
import java.util.*;

public class BubbleSortMain {

    public static void main(String[] args) {
        System.out.println("Grant Wehrli");
        String filename = "100k.txt";
        // get arrays from getArray
        System.out.println("Getting Arrays from file...");
        int[] numbers = getArray(filename);

        // call the sorting algo
        System.out.println("Bubble sorting...");
        double elapsedMs_bubble = bubbleSort(numbers); // keep call/signature the same; just don't print time

        // print the sorted array
        printArray(numbers);
    }

    /**
     * Reads integers from a text file and returns them as an array.
     * <p>
     * Each line in the file is expected to contain one integer.
     * The method attempts to parse each line into an integer and collects
     * them into a list, which is then converted to an integer array.
     * </p>
     * <p>
     * If the file cannot be read or a line contains an invalid number format,
     * an error message is printed, and only successfully parsed integers are included
     * in the returned array.
     * </p>
     *
     * @param filename the path to the text file containing integers, one per line
     * @return an array of integers read from the file
     */
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

    /**
     * Prints the given integer array to the console.
     * 
     * @param a the integer array to be sorted
     */
    public static void printArray(int[] a) {
        for (int value : a) {
            System.out.println(value);
        }
    }
    
    /**
     * Sorts the given integer array using the Bubble Sort algorithm and measures the time taken.
     * <p>
     * The method repeatedly steps through the array, compares adjacent elements,
     * and swaps them if they are in the wrong order. This process continues until
     * the array is sorted. The sorting is done in place (modifies the input array).
     * </p>
     * 
     * @param a the integer array to be sorted
     * @return the time taken to perform the sort, in milliseconds
     */
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
