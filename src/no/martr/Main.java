package no.martr;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int
        MAX_SEQUENTIAL = 100000,
        MAX_N = 100000000;
    public static int[] A;


    public static void main(String[] args) throws IOException{
        System.out.println("SORTERINGSTEST");
        int sortMethod = getSortMethod();
        A = getArray(sortMethod);
        System.out.println("Array length: " + A.length);
    }

    private static int getSortMethod() {
        int sortMethod;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.println("\t1 - Insertion sort");
            System.out.println("\t2 - Quick sort");
            System.out.println("\t3 - Merge sort");
            System.out.println("\t4 - Radix sort");
            System.out.print("Velg en sorteringsmetode: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ugyldig valg, prøv igjen!");
                scanner.next();
            }
            sortMethod = scanner.nextInt();
        } while (sortMethod < 1 || sortMethod > 4);
        return sortMethod;
    }

    public static int[] getArray(int sortMethod) {
        int n = 0;
        int max;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        if (sortMethod == 1)
            max = MAX_SEQUENTIAL;
        else
            max = MAX_N;
        do {
            System.out.println("Velg antall tall som skal sorteres. (1-" + max +")");
            while (!scanner.hasNextInt()) {
                System.out.println("Ikke et tall, prøv igjen");
                scanner.next();
            }
            n = scanner.nextInt();
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
