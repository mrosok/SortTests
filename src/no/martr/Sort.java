package no.martr;
/*
 * Sorteringsalgoritmer i stor grad basert på eksempler fra bok og foreleser
 */
public class Sort {

    public static void insertionSort(int A[])
    {
        int n = A.length;
        int key;

        for (int i = 1; i < n; i++)
        {
            // A er sortert t.o.m. indeks i-1
            key = A[i];
            int j = i;
            // Setter element nummer i pÃ¥ riktig plass
            // blant de i-1 fÃ¸rste elementene
            while (j > 0 && A[j-1] > key)
            {
                A[j] = A[j-1];
                j--;
            }
            A[j] = key;
        }
    }

    public static void quickSort(int[] A, int low, int high) {

        if (low < high) {
            // Partisjonerer array
            int partitionIndex = partition(A, low, high);
            // Sorterer venstre del
            quickSort(A, low, partitionIndex - 1);
            // Sorterer høyre del
            quickSort(A, partitionIndex + 1, high);
        }
    }

    public static int partition(int[] A, int low, int high) {

        //bruker siste element til å dele opp
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
        int temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;

        return i + 1;
    }

    public static void mergeSort(int[] A, int min, int max) {

        if (min == max)
            return;

        int[] temp;
        int index1, left, right;
        int size = max - min + 1;
        int mid = (min + max) / 2;

        temp = new int[size];

        mergeSort(A, min, mid);
        mergeSort(A, mid + 1, max);

        // Kopierer array over i temp.array
        for (index1 = 0; index1 < size; index1++)
            temp[index1] = A[min + index1];

        // Fletter sammen de to sorterte halvdelene over i A
        left = 0;
        right = mid - min + 1;
        for (index1 = 0; index1 < size; index1++) {
            if (right <= max - min)
                if (left <= mid - min)
                    if (temp[left] > temp[right])
                        A[index1 + min] = temp[right++];
                    else
                        A[index1 + min] = temp[left++];
                else
                    A[index1 + min] = temp[right++];
            else
                A[index1 + min] = temp[left++];
        }
    }

    public static void radixSort(int A[], int maksAntSiffer) {

        // Radixsortering av en array a med desimale heltall
        // maksAntSiffer: Maksimalt antall siffer i tallene

        int ti_i_m = 1; // Lagrer 10^m
        int n = A.length;

        // Oppretter 10 tomme køer
        CircularArrayQueue<Integer>[] Q =
                (CircularArrayQueue<Integer>[]) (new CircularArrayQueue[10]);

        for (int i = 0; i < 10; i++)
            Q[i] = new CircularArrayQueue<Integer>();

        // Sorterer på et og et siffer, fra høyre mot venstre

        for (int m = 0; m < maksAntSiffer; m++) {
            // Fordeler tallene i 10 køer
            for (int i = 0; i < n; i++) {
                int siffer = (A[i] / ti_i_m) % 10;
                Q[siffer].enqueue(new Integer(A[i]));
            }

            // Tæmmer kæene og legger tallene fortlæpende tilbake i A
            int j = 0;
            for (int i = 0; i < 10; i++)
                while (!Q[i].isEmpty())
                    A[j++] = Q[i].dequeue();

            // Beregner 10^m for neste iterasjon
            ti_i_m *= 10;
        }
    }

    // Hjelpefunksjon som teller antall siffer i et tall
    public static int countDigits(int n) {
        int count = 0;
        while (n != 0) {
            count ++;
            n /= 10;
        }
        return count;
    }
}
