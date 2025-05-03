package doordash;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class FileSystem { // MUST BE public
    TrieNode root;

    public FileSystem() {
        root = new TrieNode("dummy");
    }

    public List<String> ls(String path) {
        return findOrCreatePath(path).listFile();
    }

    public void mkdir(String path) {
        findOrCreatePath(path);
    }

    public void addContentToFile(String filePath, String content) {
        findOrCreatePath(filePath).addContent(content);
    }

    public String readContentFromFile(String filePath) {
        return findOrCreatePath(filePath).getContent();
    }

    private TrieNode findOrCreatePath(String path) {
        TrieNode cur = root;
        String[] parts = path.trim().split("/");
        for (int i = 1; i < parts.length; i++) {
            String str = parts[i];
            if (cur.children.get(str) == null)
                cur.children.put(str, new TrieNode(str));
            cur = cur.children.get(str);
        }
        return cur;
    }

    static class TrieNode {
        TreeMap<String, TrieNode> children;
        StringBuilder fileContent;
        String name;

        public TrieNode(String name) {
            this.children = new TreeMap<>();
            this.fileContent = new StringBuilder();
            this.name = name;
        }

        public void addContent(String content) {
            fileContent.append(content);
        }

        public String getContent() {
            return fileContent.toString();
        }

        public boolean isFile() {
            return fileContent.length() > 0;
        }

        public String getName() {
            return name;
        }

        public List<String> listFile() {
            List<String> res = new ArrayList<>();
            if (isFile()) {
                res.add(getName());
            } else {
                res.addAll(children.keySet());
            }
            return res;
        }
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        // Test mkdir
        fs.mkdir("/a/b/c");
        fs.mkdir("/a/b/d");

        // Test ls
        System.out.println(fs.ls("/")); // [a]
        System.out.println(fs.ls("/a/b")); // [c, d]

        // Test addContentToFile
        fs.addContentToFile("/a/b/c/file.txt", "Hello ");
        fs.addContentToFile("/a/b/c/file.txt", "World");

        // Test readContentFromFile
        System.out.println(fs.readContentFromFile("/a/b/c/file.txt")); // Hello World

        // Test ls on file path
        System.out.println(fs.ls("/a/b/c/file.txt")); // [file.txt]
    }
}
