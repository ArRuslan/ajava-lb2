package ua.nure.pv;

public class Demo {
    public static void main(String[] args) {
        HashTable table = HashTable.getInstance();
        table.insert(1, 2);
        System.out.println(table.search(1));
        table.insert(2, "test");
        System.out.println(table.search(2));
        table.insert(3, "qwe");
        System.out.println(table.search(3));
        table.insert(4, "yep");
        System.out.println(table.search(4));
        table.insert(4, "asdasd");
        System.out.println(table.search(4));
        table.insert(5, "asdasdqwe");
        System.out.println(table.search(5));
    }
}
