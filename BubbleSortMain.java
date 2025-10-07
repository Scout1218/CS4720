// Grant Wehrli
// bubblesort implementation


import java.io.*;


public class BubbleSortMain {

    public static void main(String[] args) throws IOException {
        System.out.println("Grant Wehrli");
        String filename = "random_numbers.txt";
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
        // loop through every element
        for (int i = n - 1; i >= 1; i--) {
            // we have to compare every element every iteration
            for (int j = 0; j < i; j++) {
                // if the current element is bigger than the next we swap
                if (a[j] > a[j + 1]) {
                    // swap a[j] and a[j+1] using temp var t
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
