package ua.nure.pv;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        HashTable hashtable = HashTable.getInstance();

        hashtable.insert(10,10);
        hashtable.insert(18,18);
        hashtable.insert(34,34);

        assert 10 == (int)hashtable.search(10);
        assert 18 == (int)hashtable.search(18);
        assert 34 == (int)hashtable.search(34);

        hashtable.remove(18);

        assert 10 == (int)hashtable.search(10);
        assert null == hashtable.search(18);
        assert 34 == (int)hashtable.search(34);

        hashtable.remove(10);

        assert null == hashtable.search(10);
        assert null == hashtable.search(18);
        assert 34 == (int)hashtable.search(34);

        hashtable.insert(10,10);
        hashtable.insert(18,18);
        hashtable.insert(34,34);
        hashtable.insert(42,42);
        hashtable.insert(50,50);
        hashtable.insert(58,58);
        hashtable.insert(66,66);
        hashtable.insert(74,74);

        hashtable.remove(2);
        assert 10 == (int)hashtable.search(10);

        hashtable.remove(10);

        assert null == hashtable.search(10);
        assert 18 == (int)hashtable.search(18);
        assert 34 == (int)hashtable.search(34);
        assert 42 == (int)hashtable.search(42);
        assert 50 == (int)hashtable.search(50);
        assert 58 == (int)hashtable.search(58);
        assert 66 == (int)hashtable.search(66);
        assert 74 == (int)hashtable.search(74);

        hashtable.remove(50);

        assert null == hashtable.search(10);
        assert 18 == (int)hashtable.search(18);
        assert 34 == (int)hashtable.search(34);
        assert 42 == (int)hashtable.search(42);
        assert null == hashtable.search(50);
        assert 58 == (int)hashtable.search(58);
        assert 66 == (int)hashtable.search(66);
        assert 74 == (int)hashtable.search(74);
    }
}
