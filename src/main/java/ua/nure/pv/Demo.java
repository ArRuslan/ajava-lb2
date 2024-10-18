package ua.nure.pv;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        HashTable table = HashTable.getInstance();

        int[] elements = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        Random prng = new Random(elements[0] + elements[0]);
        List<Integer> elementsInCertainOrder = Arrays.stream(elements).boxed().collect(Collectors.toList());

        Collections.shuffle(elementsInCertainOrder, prng);
        for (int element : elementsInCertainOrder) {
            table.insert(element, element);
            System.out.println(Arrays.toString(table.keys()));
        }

        Collections.shuffle(elementsInCertainOrder, prng);
        for (int element : elementsInCertainOrder) {
            table.remove(element);
            System.out.println(Arrays.toString(table.keys()));
        }

        Collections.shuffle(elementsInCertainOrder, prng);
        for (int element : elementsInCertainOrder) {
            table.insert(element, element);
            System.out.println(Arrays.toString(table.keys()));
        }
    }
}
