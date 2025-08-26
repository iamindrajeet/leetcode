class Node {
    int key, value, freq;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1; // Every new node starts with a frequency of 1
    }
}

class LFUCache {

    private final int capacity; // Maximum capacity of the LFU cache
    private final Map<Integer, Node> cache; // Maps key to the Node (for O(1) access)
    private final Map<Integer, LinkedHashSet<Node>> freqMap; // Maps frequency to all nodes with that frequency
    private int minFreq; // Keeps track of the minimum frequency in the cache

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFreq = 0; // No elements initially, so minFreq is set to 0
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        increaseFreq(node); // Update the frequency due to access
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return; // Can't put anything in a 0-capacity cache

        // If key already exists, update the value and frequency
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            increaseFreq(node);
            return;
        }

        // If cache is at capacity, evict the least frequently used item
        if (cache.size() >= capacity) {
            evict();
        }

        // Create a new node and add it to the cache and frequency map
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        if (!freqMap.containsKey(1)) {
            freqMap.put(1, new LinkedHashSet<>());
        }
        freqMap.get(1).add(newNode);
        minFreq = 1; // Reset minFreq to 1 for the newly added node
    }

    private void increaseFreq(Node node) {
        int oldFreq = node.freq;
        freqMap.get(oldFreq).remove(node); // Remove from the current frequency list

        node.freq++; // Increase frequency

        // Add to the list of the new frequency
        if (!freqMap.containsKey(node.freq)) {
            freqMap.put(node.freq, new LinkedHashSet<>());
        }
        freqMap.get(node.freq).add(node);

        // If the old frequency list becomes empty and was the minFreq, update minFreq
        if (freqMap.get(oldFreq).isEmpty()) {
            freqMap.remove(oldFreq); // Clean up empty sets to save memory
            if (oldFreq == minFreq) {
                minFreq++;
            }
        }
    }

    private void evict() {
        // Get the set of nodes with the minimum frequency
        LinkedHashSet<Node> minFreqSet = freqMap.get(minFreq);

        // Remove the least recently used node from this frequency list
        Node nodeToEvict = minFreqSet.iterator().next();
        minFreqSet.remove(nodeToEvict);

        // Also remove it from the main cache
        cache.remove(nodeToEvict.key);

        // If the frequency set is empty after removal, clean it up
        if (minFreqSet.isEmpty()) {
            freqMap.remove(minFreq);
        }
    }
}

/**
 * Example usage:
 * LFUCache obj = new LFUCache(capacity);
 * int value = obj.get(key); // retrieves value if key exists
 * obj.put(key, value);      // adds or updates the value for key
 */
