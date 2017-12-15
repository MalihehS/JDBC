package se.lexicon.jdbc.secondproject;

import java.util.Scanner;

public class Utilities {

    private static Scanner sc = new Scanner(System.in);

    public static int nextInt(String s) { // read an intiger from keyboard
        int num = 0;
        System.out.println(s);
        try {
            num = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            num = nextInt("Please Only Number");
        }
        return num;
    }

    public static String nextLine(String s) { // read a string 
        System.out.println(s);
        return sc.nextLine();
    }

    public static double nextDouble(String s) { // read a double
        double num = 0;
        System.out.println(s);
        try {
            num = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            num = nextInt("Please Only number ");
        }
        return num;
    }

}
