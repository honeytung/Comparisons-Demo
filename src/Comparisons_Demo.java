import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Comparisons_Demo {
    static File quicktxt = new File("QuickSort.txt");
    static ArrayList<Integer> intArray = new ArrayList<Integer>();
    static long comparisons = 0;

    /*
     * pivotoptions: 0 - Use first element as pivot
     *               1 - Use last element as pivot
     *               2 - Use median element as pivot
     */
    static int pivotoptions = 2;

    public static void main (String[] args) throws IOException {
        if (quicktxt.exists()) {
            System.out.println("Quick Sort: \n");
            Scanner quickfile = new Scanner(quicktxt);

            while (quickfile.hasNextInt()) {
                intArray.add(quickfile.nextInt());
            }

            quickfile.close();

            int arraySize = intArray.size();
            int array[] = new int [arraySize];

            for (int i = 0; i < arraySize; i++) {
                array[i] = intArray.get(i);
            }

            quickSort(array, 0, array.length - 1);

            System.out.println("Number of Comparisons: " + comparisons);
        }
    }

    public static void quickSort (int arr[], int startIn, int endInt) {
        if ((endInt - startIn) > 0) {
            int pivot = ChoosePivot(arr, startIn, endInt);
            int swap = arr[pivot];

            // Swap pivot index to first element of array
            arr[pivot] = arr[startIn];
            arr[startIn] = swap;

            // New pivot location after Partition
            pivot = Partition(arr, startIn, endInt);

            // Increase number of comparisons
            comparisons += endInt - startIn;

            // Before pivot (Left side smaller than current chosen pivot)
            quickSort(arr, startIn, pivot - 1);

            // After pivot (Right side larger than current chosen pivot)
            quickSort(arr, pivot + 1, endInt);
        }
    }

    public static int ChoosePivot (int arr[], int startIn, int endInt) {

        // Choose pivot based on input via variable pivotoptions
        // Return index of pivot
        if (pivotoptions == 0)
            return startIn;
        else if (pivotoptions == 1)
            return endInt;
        else {
            // Use Median as Pivot
            int a = arr[startIn];
            int b = arr[(int)((endInt - startIn) / 2) + startIn];
            int c = arr[endInt];

            if (a > b) {
                if (b > c)
                    return ((int)((endInt - startIn) / 2) + startIn);
                else if (a > c)
                    return endInt;
                else
                    return startIn;
            } else {
                if (a > c)
                    return startIn;
                else if (b > c)
                    return endInt;
                else
                    return ((int)((endInt - startIn) / 2) + startIn);
            }
        }
    }

    public static int Partition (int arr[], int startIn, int endIn) {
        // Partition Algorithms
        int p = arr[startIn];
        int i = startIn + 1;
        int k;

        for (int j = startIn + 1; j < (endIn + 1); j++) {
            if (arr[j] < p) {
                k = arr[j];
                arr[j] = arr[i];
                arr[i] = k;
                i++;
            }
        }
        arr[startIn] = arr[i - 1];
        arr[i - 1] = p;

        // Return index of pivot after swap
        return (i - 1);
    }
}
