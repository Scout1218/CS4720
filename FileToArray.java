import java.io.*;
import java.util.*;

public class FileToArray {

    public static void main(String[] args) {
        String filename = "100k.txt";
        // get array from getArray
        System.out.println("Getting Arrays from file...");
        int[] numbers = getArray(filename);
        int[] numbers2 = getArray(filename);
        System.out.println("Bubble sorting...");
        double elapsedMs_bubble = bubbleSort(numbers);
        System.out.println("Merge sorting...");
        double elapsedMs_merge = mergeSort(numbers2);
        System.out.println("Time Comparisons:");
        System.out.printf("bubble sort took %.3f ms%n", elapsedMs_bubble);
        System.out.printf("merge sort took %.3f ms%n", elapsedMs_merge);
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

    public static double mergeSort(int[] a) {
        long start = System.nanoTime();
        int n = a.length;

        mergeRecursive(a, 0, (n - 1));
       
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
        
    }

    private static void mergeRecursive(int[] arr, int left, int right) {
        // base case if the left index is greater than or equal to the right index (implies length of 1 or 0)
        if (left >= right) return;

        // if we dont hit base case
        // find middle, and call mergeRecursive() recursivley which breaks into arrays of 1/2 length
        int mid = (left + (right - left) / 2);

        // call for left array
        mergeRecursive(arr, left, mid);

        // call for right array (must be from mid + 1 isnce we used up to mid in the left
        mergeRecursive(arr, (mid + 1), right);

        // call merge function (where the sorting happens)
        merge(arr, left, right, mid);

    }
    private static void merge(int[] arr, int left, int right, int mid) {
        // get the lengths of the 2 subarrays
        int l_length = mid - left + 1;
        int r_length = right - mid;

        // declare the new subarrays
        int[] l_arr = new int[l_length];
        int[] r_arr = new int[r_length];

        // initialize values in left array [left .. mid]
        for (int i = 0; i < l_length; i++) {
            l_arr[i] = arr[left + i];
        }

        // initialize values in right array [mid+1 .. right]
        for (int j = 0; j < r_length; j++) {
            r_arr[j] = arr[mid + 1 + j];
        }
        
        /* now since by definition we are sorting with already
         * sorted arrays, as the smallest arrays have length 1
         * which is automatically sorted, we have to comnine them
         * now.
         * 
         * The 2 arrays to merge are l_arr and r_arr, with length
         * l_length and r_length respectivley.
         * 
         * There are 3 pointers to keep track of, i, j, k
         * 
         * i and j are the indiceis for l and r_arr respectivley
         * 
         * k is the index in arr which is where the next smallest 
         * element gets inserted.
         */

        //first step: merge while both arrays still have elements
        // (bc one of them will run out first)
        // pointers
        int i = 0, j = 0, k = left;
        while (i < l_length && j < r_length) {
            if (l_arr[i] <= r_arr[j]) {
                arr[k] = l_arr[i];
                i++;
            }
            else {
                arr[k] = r_arr[j];
                j++;
            }
            k++;
        }
        // now one of the arrays should be empty:
        // so we put the remaining ones into the array directly
        while (i < l_length) {
            arr[k] = l_arr[i];
            i++;
            k++;
        }
        while (i < r_length) {
            arr[k] = r_arr[j];
            j++;
            k++;
        }
        // merge complete
    }
}