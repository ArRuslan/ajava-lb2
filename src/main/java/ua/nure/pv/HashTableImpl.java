package ua.nure.pv;

public class HashTableImpl implements HashTable {
	
	private static class Entry {
		int key;
		Object value;
		boolean occupied;
	}

	private static class NeedResizeException extends Exception {}

	private static final int INITIAL_SIZE = 4;

	private static final int MAX_SIZE = 16;
	
	private Entry[] table;
	private int current_size = 0;

	public HashTableImpl() {
		table = new Entry[INITIAL_SIZE];
	}

	private static Integer find_slot(int key, Entry[] table) throws NeedResizeException {
		/*
		i := hash(key) modulo num_slots
		// search until we either find the key, or find an empty slot.
		while (slot[i] is occupied) and (slot[i].key ≠ key)
			i := (i + 1) modulo num_slots
		return i
		*/

		int idx = hash(key, table.length) % table.length;
		int checked = 0;
		while(table[idx] != null && table[idx].occupied && table[idx].key != key) {
			if(checked++ >= table.length) {
				throw new NeedResizeException();
			}
			idx++;
			idx %= table.length;
		}

		return idx;
	}

	private int find_slot_resize(int key) {
		int idx = 0;
		try {
			idx = find_slot(key, table);
		} catch (NeedResizeException e) {
			rebuild();
			try {
				idx = find_slot(key, table);
			} catch (NeedResizeException ignored) {
				assert false; // unreachable
			}
		}

		return idx;
	}

	private void rebuild() {
		Entry[] newTable = new Entry[table.length * 2];
		for(Entry entry : table) {
			if(entry == null || !entry.occupied) {
				continue;
			}
			try {
				int idx = find_slot(entry.key, newTable);
				newTable[idx] = entry;
			} catch (NeedResizeException ignored) {
				assert false; // unreachable
			}

		}

		table = newTable;
	}

	@Override
	public void insert(int key, Object value) {
		/*
		i := find_slot(key)
		if slot[i] is occupied   // we found our key
			slot[i].value := value
			return
		if the table is almost full
			rebuild the table larger (note 1)  // TODO: rebuild
			i := find_slot(key)
		mark slot[i] as occupied
		slot[i].key := key
		slot[i].value := value
		*/

		int idx = find_slot_resize(key);
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
		int idx = find_slot_resize(key);
		if(table[idx] != null && table[idx].occupied) {
			return table[idx].value;
		}
		return null;
	}

	@Override
	public void remove(int key) {
		int idx = find_slot_resize(key);
		if(table[idx] == null || !table[idx].occupied) {
			return;
		}

		Entry entry = table[idx];
		entry.key = 0;
		entry.value = null;
		entry.occupied = false;

		current_size--;
	}

	@Override
	public int size() {
		return current_size;
	}

	@Override
	public int[] keys() {
		int[] result = new int[table.length];
		for(int i = 0; i < table.length; i++) {
			Entry entry = table[i];
			if(entry != null && entry.occupied) {
				result[i] = entry.key;
			} else {
				result[i] = 0;
			}
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
