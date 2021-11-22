package problems;

class Trie {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        public TrieNode (){
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode p = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(p.children[index] == null) p.children[index] = new TrieNode();
            p = p.children[index];
        }
        p.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }
    
    public TrieNode find(String word){
        TrieNode p = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(p.children[index] == null) return null;
            p = p.children[index];
        }
        return p;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */