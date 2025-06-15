package ED2.estruturas.hashTable;

public class HashTable<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] table; // Array de entradas
    private int size; // Número de entradas
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    // Construtor
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    // Calcula índice baseado no hash da chave
    private int getIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % table.length);
    }

    // Adiciona ou atualiza um par chave-valor
    public void put(K key, V value) {
        int index = getIndex(key);

        // Se a posição está vazia, cria nova entrada
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            size++;
        } else {
            // Percorre a lista encadeada na posição
            Entry<K, V> current = table[index];
            Entry<K, V> prev = null;

            while (current != null) {
                if ((current.key == null && key == null) || 
                    (current.key != null && current.key.equals(key))) {
                    current.value = value; // Atualiza valor existente
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = new Entry<>(key, value); // Adiciona nova entrada
            size++;
        }

        // Redimensiona se necessário
        if ((float) size / table.length >= LOAD_FACTOR) {
            resize();
        }
    }

    // Obtém o valor associado a uma chave
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if ((current.key == null && key == null) || 
                (current.key != null && current.key.equals(key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    // Remove uma chave
    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if ((current.key == null && key == null) || 
                (current.key != null && current.key.equals(key))) {
                V value = current.value;
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    // Retorna o tamanho atual
    public int size() {
        return size;
    }

    // Redimensiona a tabela
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }
}
