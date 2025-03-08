import java.util.HashMap;
import java.util.Map;

// Definition for a doubly linked list node
class Node {
    int key, value;
    Node prev, next;
    
    // Constructor to initialize a node with key-value pair
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    // Dummy head and tail nodes to facilitate easy removal and insertion
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int capacity; // Maximum capacity of the cache
    Map<Integer, Node> map = new HashMap<>(); // HashMap to store key-node mapping

    public LRUCache(int capacity) {
        this.capacity = capacity;

        // Initialize the doubly linked list with head and tail pointing to each other
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }

    // Retrieves a value from the cache if it exists
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            
            // Move the accessed node to the front (most recently used position)
            remove(node);
            insert(node);
            return node.value;
        } else {
            return -1; // Return -1 if the key is not found
        }
    }

    // Inserts or updates a key-value pair in the cache
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // If key already exists, remove the old node
            remove(map.get(key));
        }
        if (map.size() == capacity) {
            // Remove the least recently used node (node before tail)
            remove(tail.prev);
        }
        // Insert the new node at the front (most recently used position)
        insert(new Node(key, value));
    }

    // Removes a node from the doubly linked list
    private void remove(Node node) {
        map.remove(node.key); // Remove from the hashmap
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Inserts a node at the front of the doubly linked list (most recently used)
    private void insert(Node node) {
        map.put(node.key, node); // Add to hashmap
        Node headNext = head.next;

        // Insert node right after head
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
*/
