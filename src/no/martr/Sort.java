package no.martr;

public class Sort {

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min])
                    min = j;

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void quickSort(int[] A, int low, int high) {

        if (low < high) {
            int partitionIndex = partition(A, low, high);
            quickSort(A, low, partitionIndex - 1);
            quickSort(A, partitionIndex+1, high);
        }


    }

    public static int partition(int[] A, int low, int high) {
        int partitionElement = A[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (A[j] <= partitionElement) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i+1];
        A[i+1] = A[high];
        A[high] = temp;

        return i+1;
    }

    public static void mergeSort(int[] a) {
    }

    public static void radixSort(int[] a) {
    }
}
