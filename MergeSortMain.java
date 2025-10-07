// Grant Wehrli
// mergesrort implementation

import java.io.*;


public class MergeSortMain {

    public static void main(String[] args) throws IOException{
        System.out.println("Grant Wehrli");
        String filename = "10k.txt";
        // get arrays from getArray
        System.out.println("Getting Arrays from file...");
        int[] numbers2 = getArray(filename);

        // call the sorting algo
        System.out.println("Merge sorting...");
        double elapsedMs_merge = mergeSort(numbers2); // keep call/signature the same; just don't print time

        // print the sorted array
        printArray(numbers2);
    }

    /**
     * Reads integers from a text file and returns them as an array.
     * <p>
     * Each line in the file is expected to contain one integer. The method
     * first counts the number of lines to determine the array size, then
     * reads and parses each line into the array.
     * </p>
     *
     * @param filename the path to the text file containing integers, one per line
     * @return an array of integers read from the file
     * @throws IOException if the file cannot be read
     */
    public static int[] getArray(String filename) throws IOException {
        // first pass counts the lines to create the array
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.readLine() != null) {
                count++;
            }
        }

        // allocate array based on count
        int[] numbers = new int[count];

        // read through the file, adding integers to the array
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < count; i++) {
                numbers[i] = Integer.parseInt(br.readLine().trim());
            }
        }
        // return the array
        return numbers;
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
     * Sorts the given integer array using the Merge Sort algorithm and measures the time taken.
     * <p>
     * Merge Sort is a divide-and-conquer algorithm that recursively splits the array into halves,
     * sorts each half, and then merges them back together. The sorting is done in place (modifies the input array).
     * </p>
     *
     * @param a the integer array to be sorted
     * @return the time taken to perform the sort, in milliseconds
     */
    public static double mergeSort(int[] a) {
        long start = System.nanoTime();
        int n = a.length;

        mergeRecursive(a, 0, (n - 1));
       
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
        
    }

    /**
     * Recursively divides the array into halves until subarrays of size 1 are reached,
     * then calls the {@code merge} method to combine them in sorted order.
     *
     * @param arr   the array being sorted
     * @param left  the starting index of the subarray
     * @param right the ending index of the subarray
     */
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

    /**
     * Merges two sorted subarrays of {@code arr} into a single sorted subarray.
     * <p>
     * The left subarray is defined as {@code arr[left .. mid]} and the right subarray
     * is {@code arr[mid+1 .. right]}. This method assumes both subarrays are already sorted
     * and combines them into a single sorted sequence in {@code arr}.
     * </p>
     * 
     * @param arr   the array containing the subarrays to merge
     * @param left  the starting index of the left subarray
     * @param right the ending index of the right subarray
     * @param mid   the index that splits the left and right subarrays
     */
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
        while (j < r_length) {
            arr[k] = r_arr[j];
            j++;
            k++;
        }
        // merge complete
    }
}
