package Design_algos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashSHA256<T> {

    private final int numberOfReplicas;
    private final SortedMap<Integer, T> ring = new TreeMap<>();
    private final MessageDigest sha256;

    public ConsistentHashSHA256(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        try {
            this.sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }

        for (T node : nodes) {
            add(node);
        }
    }

    private int hash(String key) {
        byte[] digest = sha256.digest(key.getBytes(StandardCharsets.UTF_8));
        // Use first 4 bytes of SHA-256 digest to make a 32-bit integer
        return ((digest[0] & 0xFF) << 24)
                | ((digest[1] & 0xFF) << 16)
                | ((digest[2] & 0xFF) << 8)
                | (digest[3] & 0xFF);
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hash(node.toString() + i);
            ring.put(hash, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hash(node.toString() + i);
            ring.remove(hash);
        }
    }

    public T get(Object key) {
        if (ring.isEmpty())
            return null;
        int hash = hash(key.toString());
        SortedMap<Integer, T> tailMap = ring.tailMap(hash);
        int nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(nodeHash);
    }

    public void printRing() {
        for (Map.Entry<Integer, T> entry : ring.entrySet()) {
            System.out.println("Hash: " + entry.getKey() + " -> Node: " + entry.getValue());
        }
    }

    // Example usage
    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("NodeA", "NodeB", "NodeC");
        ConsistentHashSHA256<String> ch = new ConsistentHashSHA256<>(100, nodes);

        System.out.println("Key 'apple' maps to " + ch.get("apple"));
        System.out.println("Key 'banana' maps to " + ch.get("banana"));
        System.out.println("Key 'cherry' maps to " + ch.get("cherry"));

        System.out.println("\n--- After adding NodeD ---");
        ch.add("NodeD");
        System.out.println("Key 'apple' maps to " + ch.get("apple"));
        System.out.println("Key 'banana' maps to " + ch.get("banana"));
        System.out.println("Key 'cherry' maps to " + ch.get("cherry"));
    }
}
