package no.martr;

import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int
        MAX_SEQUENTIAL = 100000,
        MAX_N = 100000000;
    public static int[] A;

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            int choice;
            System.out.println("SORTERINGSTEST");
            int sortMethod = getSortMethod();

            A = getArray(sortMethod);
            do {
                System.out.println("Velg eksekvering:");
                System.out.println("\t1 - utfør sortering");
                System.out.println("\t2 - kalkuler konstant");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Not a valid choice, try again");
                }
                choice = scanner.nextInt();

            } while (choice < 0 || choice > 2);
            switch (choice) {
                case 0:
                    System.exit(0);

                case 1:
                    long time = System.currentTimeMillis();
                    sort(sortMethod);
                    time = System.currentTimeMillis() - time;
                    System.out.println("n: " + A.length + "\ttid: " + time + " ms");
                    System.out.println();

                    break;

                case 2:
                    calculate(sortMethod);
                    break;
            }
        }
    }

    private static void calculate(int sortMethod) {
        System.out.println("to be implemented");
    }

    private static void sort(int sortMethod) {
        switch (sortMethod) {
            case 1:
                Sort.selectionSort(A);
                break;
            case 2:
                Sort.quickSort(A, 0, A.length - 1);
                break;
            case 3:
                Sort.mergeSort(A);
                break;
            case 4:
                Sort.radixSort(A);

        }
    }

    private static int getSortMethod() {
        int sortMethod;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.println("\t1 - Insertion sort");
            System.out.println("\t2 - Quick sort");
            System.out.println("\t3 - Merge sort");
            System.out.println("\t4 - Radix sort");
            System.out.print("\nVelg en sorteringsmetode (0 for å avslutte): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ugyldig valg, prøv igjen!");
                scanner.next();
            }
            sortMethod = scanner.nextInt();
            if (sortMethod == 0) {
                System.exit(0);
            }
        } while (sortMethod < 1 || sortMethod > 4);
        return sortMethod;
    }

    public static int[] getArray(int sortMethod) {
        int n;
        int max;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        if (sortMethod == 1)
            max = MAX_SEQUENTIAL;
        else
            max = MAX_N;
        do {
            System.out.print("Velg antall tall som skal sorteres (1-" + max +", 0 for å avslutte): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ikke et tall, prøv igjen");
                scanner.next();
            }
            n = scanner.nextInt();
            if (n == 0)
                System.exit(0);
        } while (n < 1 || n > max);
        return makeArray(n);
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
