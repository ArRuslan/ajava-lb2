package ua.nure.pv;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        HashTable hashtable = HashTable.getInstance();
        /*int[] elements = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        String[] expected = new String[]{
                //"[16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 0, 7, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 0, 0, 8, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 0, 0, 0, 9, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 13, 14, 15]",
                "[16, 0, 0, 0, 0, 0, 14, 15]",
                "[16, 0, 0, 15]",
                "[16, 0]",
                "[0, 0]",
        };*/
        int[] elements = new int[]{-74, 98, -44, 75, -27, 76, -33, 42, -71, 77};
        String[] expected = new String[]{
                //"[77, -33, 98, 0, 0, 0, 0, -71, 0, 0, 42, -74, 75, -44, -27, 76]",
                "[77, -33, 98, 0, 0, 0, 0, -71, 0, 0, 42, 0, 75, -44, -27, 76]",
                "[77, -33, 0, 0, 0, 0, 0, -71, 0, 0, 42, 0, 75, -44, -27, 76]",
                "[77, -33, 0, 0, 0, 0, 0, -71, 0, 0, 42, 0, 75, 0, -27, 76]",
                "[77, -33, 0, 0, 0, 0, 0, -71, 0, 0, 42, 0, 0, 0, -27, 76]",
                "[77, -33, 0, 0, 0, 0, 0, -71, 0, 0, 42, 0, 0, 0, 0, 76]",
                "[0, -33, 42, 0, 0, 77, 0, -71]",
                "[0, 0, 42, 0, 0, 77, 0, -71]",
                "[0, 77, 0, -71]",
                "[0, 77]",
                "[0, 0]",
        };

        for (int element : elements) {
            hashtable.insert(element, element);
        }

        System.out.printf("Initial state: %s%n", Arrays.toString(hashtable.keys()));
        System.out.println("-----------------------------------------");
        for (int i = 0; i < elements.length; i++) {
            System.out.printf("Removing %d...%n", elements[i]);
            hashtable.remove(elements[i]);

            String exp = expected[i];
            String act = Arrays.toString(hashtable.keys());
            System.out.printf("Expected: %s%n", exp);
            System.out.printf("Actual: %s%n", act);

            if(!exp.equals(act))
                System.out.println("exp != act !!!");
            System.out.println("-----------------------------------------");
        }
    }
}
