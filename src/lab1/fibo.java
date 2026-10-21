package lab1;

import java.util.Scanner;

public class fibo {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        System.out.println();

        int[] tab = new int[n];

        tab[0] = 1;
        tab[1] = 1;

        for (int i = 2; i < n; i++) {
            tab[i] = tab[i-1] + tab[i-2];
        }

        for (int i = 0; i < n; i++) {
            System.out.println(tab[i]);
        }

    }
}