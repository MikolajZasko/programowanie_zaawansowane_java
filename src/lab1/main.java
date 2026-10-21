package lab1;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("HelloMain");
        System.out.print("...");
        System.out.println("...");

        // input liczb z kropką zamiast przecinka
        // Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f",s,i,d);

        // String s = "sas";
        // int d = 2;
        // Float f = 5.5f;

        // System.out.printf("String %s int %d double %f",s,d,f);
    }
}