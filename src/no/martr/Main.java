package no.martr;

import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //grenser for størrelse på arrayer, basert på sorteringsmetode
    private static final int
        MAX_SEQUENTIAL = 100000,
        MAX_RADIX = 10000000,
        MAX_N = 100000000;

    //tar vare på array, størrelsen n, sorteringsmetoden og tid.
    private static int[] A;
    private static int n;
    private static int sortMethod;
    private static long time;

    public static void main(String[] args) {
        sortMethod = getSortMethod();
        A = getArray();
        int action = chooseAction();

        if (action == 1) {
           time = System.currentTimeMillis();
           sort(sortMethod);
           time = System.currentTimeMillis() - time;
           System.out.println("Sortering ferdig på  " + time + " ms");
       } else {
            time = System.currentTimeMillis();
            calculate(sortMethod);
       }
    }

    // Lar bruker velge sorteringsmetode
    private static int getSortMethod() {
        int option = -1;
        Scanner scanner = new Scanner(System.in);
        while (option < 0 || option > 4) {
            System.out.println("Velg sorteringsmetode: ");
            System.out.println("1 - Insertion sort ");
            System.out.println("2 - Quick sort ");
            System.out.println("3 - Merge sort");
            System.out.println("4 - Radix sort");
            System.out.println("0 - Slutt programmet");
            System.out.print("Velg 0-4: ");
            option = scanner.nextInt();
        }
        if (option == 0)
            System.exit(0);
        return option;
    }

    // Lar bruker velge størrelse på array
    private static int[] getArray() {
        n = -1;
        int max;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        if (sortMethod == 1)
            max = MAX_SEQUENTIAL;
        else if (sortMethod == 4)
            max = MAX_RADIX;
        else
            max = MAX_N;

        while (n < 0 || n > max) {
            System.out.print("Velg antall tall som skal sorteres (1-" + max + ", 0 for å avslutte): ");
            n = scanner.nextInt();
        }
        if (n == 0)
            System.exit(0);
        return makeArray(n);
    }

    //Lar bruker velge mellom de to testene
    private static int chooseAction() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int choice = -1;
        while (choice < 0 || choice > 2) {
            System.out.println("1 for å sortere og se kjøretid");
            System.out.println("2 for estimering av konstantverdi for sorteringsmetoden");
            System.out.println("0 for å avslutte");
            System.out.print("Velg 0-2: ");
            choice = scanner.nextInt();
        }
        if (choice == 0)
            System.exit(0);
        return choice;
    }

    //henter riktig metode for sorteringen
    private static void sort(int sortMethod) {
        switch (sortMethod) {
            case 1:
                Sort.insertionSort(A);
                break;
            case 2:
                Sort.quickSort(A, 0, n - 1);
                break;
            case 3:
                Sort.mergeSort(A, 0, n -1);
                break;
            case 4:
                int digits = Sort.countDigits(n);
                Sort.radixSort(A, digits);
                break;
        }
    }

    //henter riktig metode for estimeringen
    private static void calculate(int sortMethod) {
        switch (sortMethod) {
            case 1:
                calculateEstimateInsertion();
                break;

            case 2:
                calculateEstimateQuickSort();
                break;

            case 3:
                calculateEstimateMergeSort();
                break;

            case 4:
                calculateEstimateRadixSort();
                break;
        }
    }

    private static void calculateEstimateRadixSort() {
        float C = 0;
        int digits = Sort.countDigits(n);

        for (int i = 0; i < 10; i++) {
            time = System.currentTimeMillis();
            Sort.radixSort(A,digits);
            time = System.currentTimeMillis() - time;
            time *= 1000;
            C += (float) time / ( n * digits);
        }
        C /= 10;
        System.out.println("Konstant: ~ " + C);

    }

    private static void calculateEstimateMergeSort() {
        float C = 0;
        for (int i = 0; i < 10; i++) {
            time = System.currentTimeMillis();
            Sort.mergeSort(A,0, n-1);
            time = System.currentTimeMillis() - time;
            time *= 1000;
            C += (float) (time/(n * Math.log(n)));
        }
        C /= 10;
        System.out.println("Konstant: ~ " + C);
    }

    private static void calculateEstimateInsertion() {
        float C = 0;
        for (int i = 0; i < 10; i++) {
            time = System.currentTimeMillis();
            Sort.insertionSort(A);
            time = System.currentTimeMillis() - time;
            time *= 1000;
            C += (float) time/ (n * n);
        }
        C /= 10;
        System.out.println("Konstant: ~ " + C);
    }

    private static void calculateEstimateQuickSort() {
        float C = 0;
        for (int i = 0; i < 10; i++) {
            time = System.currentTimeMillis();
            Sort.quickSort(A,0, n-1);
            time = System.currentTimeMillis() - time;
            time *= 1000;
            C += (float) (time/(n * Math.log(n)));
        }
        C /= 10;
        System.out.println("Konstant: ~ " + C);
    }

    private static int[] makeArray(int n) {
        Random r = new Random();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = r.nextInt(n);
        }
        return A;
    }
}
