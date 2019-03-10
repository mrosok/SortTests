package no.martr;

import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int
        MAX_SEQUENTIAL = 100000,
        MAX_N = 100000000;
    public static int[] A;
    public static int sortMethod, action;
    public static long time;

    public static void main(String[] args) {
        sortMethod = getSortMethod();
        A = getArray();
        action = chooseAction();

        if (action == 1) {
           time = System.currentTimeMillis();
           sort(sortMethod);
           time = System.currentTimeMillis() - time;
           System.out.println("Sort finished in  " + time + " ms");
       } else {
            time = System.currentTimeMillis();
            calculate(sortMethod, time);
       }
    }

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

    private static void calculate(int sortMethod, long time) {
        switch (sortMethod) {
            //dette virker fortsatt ikke....
            case 1:
                Sort.insertionSort(A);
                time = System.currentTimeMillis() - time;
                time *= 10000;
                float C = (float) (time/(A.length * A.length));
                System.out.println("time: " + time + " n^2: " + A.length * A.length);
                System.out.println("Constant: ~" + C);
                break;

            case 2:
                Sort.quickSort(A,0, A.length-1);
                time = System.currentTimeMillis() - time;
                time *= 10000;
                C = (float) (time/(A.length * Math.log(A.length)));
                System.out.println("Constant: ~" + C);
                break;
        }
    }

    private static void sort(int sortMethod) {
        switch (sortMethod) {
            case 1:
                Sort.insertionSort(A);
                break;
            case 2:
                Sort.quickSort(A, 0, A.length - 1);
                break;
            case 3:
                Sort.mergeSort(A, 0, A.length -1);
                break;
            case 4:
                int digits = Sort.countDigits(A.length);
                Sort.radixSort(A, digits);
                break;
        }
    }

    // kan trenge validering (input som ikke er av type int vil feile)
    public static int[] getArray() {
        int n = -1;
        int max;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        if (sortMethod == 1)
            max = MAX_SEQUENTIAL;
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

    // kan trenge validering (input som ikke er av type int vil feile)
    public static int chooseAction() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int choice = -1;
        while (choice < 0 || choice > 2) {
            System.out.print("Velg 1 for å sortere og se kjøretid, velg 2 for estimering av konstantverdi for sorteringsmetoden: ");
            choice = scanner.nextInt();
        }
        if (choice == 0)
            System.exit(0);
        return choice;
    }

    public static int[] makeArray(int n) {
        Random r = new Random();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = r.nextInt(n);
        }
        return A;
    }

}
