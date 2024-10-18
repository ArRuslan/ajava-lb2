package ua.nure.pv;

public class HashTableImpl implements HashTable {
	
	private static class Entry {
		int key;
		Object value;
	}

	private static final int INITIAL_SIZE = 4;

	private static final int MIN_SIZE = 2;
	private static final int MAX_SIZE = 16;
	
	private Entry[] table;
	private int current_size = 0;

	public HashTableImpl() {
		table = new Entry[INITIAL_SIZE];
	}

	private static Integer find_slot(int key, Entry[] table) {
		int idx = hash(key, table.length) % table.length;
		int checked = 0;
		while(table[idx] != null && table[idx].key != key) {
			if(checked++ > table.length) {
				return null;
			}
			idx++;
			idx %= table.length;
		}

		return idx;
	}

	private int find_slot_resize(int key) {
		Integer idx = find_slot(key, table);
		if(idx != null) {
			return idx;
		}

		rebuild(table.length * 2);
		idx = find_slot(key, table);
		assert idx != null;
		return idx;
	}

	private void rebuild(int new_size) {
		if(new_size > MAX_SIZE) {
			throw new IllegalStateException("Reached MAX_SIZE of the table.");
		}
		Entry[] newTable = new Entry[new_size];
		for(Entry entry : table) {
			if(entry == null) {
				continue;
			}
			Integer idx = find_slot(entry.key, newTable);
			assert idx != null;
			newTable[idx] = entry;
		}

		table = newTable;
	}

	@Override
	public void insert(int key, Object value) {
		int idx = find_slot_resize(key);
		if(table[idx] != null) {
			table[idx].value = value;
			return;
		}

		if(table[idx] == null) {
			table[idx] = new Entry();
		}

		Entry entry = table[idx];
		entry.key = key;
		entry.value = value;

		current_size++;
	}

	@Override
	public Object search(int key) {
		int idx = find_slot_resize(key);
		if(table[idx] != null) {
			return table[idx].value;
		}
		return null;
	}

	@Override
	public void remove(int key) {
		int i = find_slot_resize(key);
		if(table[i] == null) {
			return;
		}

		table[i] = null;
		int j = i;
		while(true) {
			j++;
			j %= table.length;

			if(table[j] == null)
				break;

			int k = hash(table[j].key) % table.length;

			if(i <= j) {
				if(i < k && k <= j)
					continue;
			} else {
				if(k <= j || i < k)
					continue;
			}

			table[i] = table[j];
			table[j] = null;
			i = j;
		}

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
			result[i] = entry == null ? 0 : entry.key;
		}

		return result;
	}

	private static int hash(int key, int tableLen) {
		return Math.abs(key % tableLen);
	}
	
	// you have to use this function to obtain a hash of a key
	private int hash(int key) {
		return hash(key, table.length);
	}

}
