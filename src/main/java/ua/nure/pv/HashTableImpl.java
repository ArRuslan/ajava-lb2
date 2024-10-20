package ua.nure.pv;

import java.util.Arrays;

public class HashTableImpl implements HashTable {

	private static class Entry {
		int key;
		Object value;
		boolean occupied;
	}

	private static final int INITIAL_SIZE = 4;

	private static final int MIN_SIZE = 2;
	private static final int MAX_SIZE = 16;

	private Entry[] table;
	private int current_size = 0;

	public HashTableImpl() {
		table = new Entry[INITIAL_SIZE];
	}

	private Integer find_slot(int key, boolean for_search) {
		int idx = hash(key) % table.length;
		int checked = 0;
		while(table[idx] != null && (table[idx].occupied || for_search) && table[idx].key != key) {
			if(checked++ > table.length) {
				return null;
			}
			idx++;
			idx %= table.length;
		}

		return idx;
	}

	private int find_slot_resize(int key, boolean for_search) {
		Integer idx = find_slot(key, for_search);
		if(idx != null) {
			return idx;
		}

		rebuild(table.length * 2);
		idx = find_slot(key, false);
		assert idx != null;
		return idx;
	}

	private void rebuild(int new_size) {
		if(new_size > MAX_SIZE) {
			throw new IllegalStateException("Reached MAX_SIZE of the table.");
		}
		Entry[] oldTable = table;
		table = new Entry[new_size];
		for(Entry entry : oldTable) {
			if(entry == null || !entry.occupied) {
				continue;
			}
			Integer idx = find_slot(entry.key, false);
			assert idx != null;
			table[idx] = entry;
		}
	}

	@Override
	public void insert(int key, Object value) {
		int idx = find_slot_resize(key, false);
		if(table[idx] != null && table[idx].occupied) {
			table[idx].value = value;
			return;
		}

		if(table[idx] == null) {
			table[idx] = new Entry();
		}

		Entry entry = table[idx];
		entry.key = key;
		entry.value = value;
		entry.occupied = true;

		current_size++;
	}

	@Override
	public Object search(int key) {
		int idx = find_slot_resize(key, true);
		if(table[idx] != null && table[idx].occupied) {
			return table[idx].value;
		}
		return null;
	}

	@Override
	public void remove(int key) {
		int i = find_slot_resize(key, true);
		if(table[i] == null || !table[i].occupied) {
			System.out.printf("Not found: %d%n", key);
			return;
		}

		//int old_i = i;
		table[i].occupied = false;
		table[i].value = null;

		current_size--;

		if(current_size <= (Math.sqrt(table.length)) && table.length > MIN_SIZE) {
			rebuild(table.length / 2);
		}
	}

	@Override
	public int size() {
		return current_size;
	}

	@Override
	public int[] keys() {
		int[] result = new int[table.length];
		for (int i = 0; i < table.length; i++) {
			Entry entry = table[i];
			result[i] = entry == null || !entry.occupied ? 0 : entry.key;
		}

		return result;
	}

	private int hash(int key) {
		return Math.abs(key % table.length);
	}
}
