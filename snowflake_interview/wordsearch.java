/*
 * Click `Run` to execute the snippet below!

 * Auto-correct suggestion
 * Dict -> a set of "correct words"
 * word -> user input
 *
 * Find all the words in dict that are at most 1 chars different than the input word
 - same length
 *
 *
 * dict: ["base", "bass"]
 * word: "basi" => [base, bass]
 * word: "xyzw" => []
 *
 * word length N
 * dict size M
 * O(N^2)

 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("base");
        dict.add("bass");
        dict.add("aasi");
        dict.add("bwsi");
        dict.add("bwyi");
        dict.add("bas");
        dict.add("basiww");
        FindAtMostK object = new FindAtMostK(dict);
        System.out.println(object.findAtMostKdiff(dict, "basbwy"));
        System.out.println(object.findAtMostKdiff(dict, "xyzw"));
    }

    // defining trie structure
    static class TrieNode {
        private HashMap<Character, TrieNode> child;
        private boolean isEnd;

        public TrieNode() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }

        public HashMap<Character, TrieNode> getChild() {
            return child;
        }
    }

    static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            TrieNode cur = root;
            for (Character c : s.toCharArray()) {
                if (cur.child.get(c) == null) {
                    cur.child.put(c, new TrieNode());

                }
                cur = cur.getChild().get(c);
            }
            cur.isEnd = true;
        }

        public TrieNode getRoot() {
            return root;
        }
    }

    static class FindAtMostK {
        private Trie trie;

        public FindAtMostK(Set<String> dictionary) {
            this.trie = new Trie();
            for (String word : dictionary) {
                trie.insert(word);
            }
        }

        public Set<String> findAtMostKdiff(Set<String> dictionary, String word) {
            Set<String> res = new HashSet<>();
            // dfs
            StringBuilder sb = new StringBuilder();
            dfs(trie.getRoot(), 0, word, 0, sb, res);
            return res;
        }

        private void dfs(TrieNode cur, int diffCnt, String word, int index, StringBuilder sb, Set<String> res) {
            if (diffCnt > 2)
                return;
            if (index == word.length()) {
                if (cur.isEnd) {
                    res.add(sb.toString());
                }
                return;
            }
            for (char c : cur.getChild().keySet()) {
                if (c == word.charAt(index)) {
                    sb.append(c);
                    dfs(cur.getChild().get(c), diffCnt, word, index + 1, sb, res);
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(c);
                    dfs(cur.getChild().get(c), diffCnt + 1, word, index + 1, sb, res);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}

// Your previous Plain Text content is preserved below:

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you'd like to use for your interview,
// simply choose it from the dots menu on the tab, or add a new language
// tab using the Languages button on the left.

// You can also change the default language your pads are created with
// in your account settings: https://app.coderpad.io/settings

// Enjoy your interview!