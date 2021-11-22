/**
 * solution_11_07
 */
public class solution_11_07 {

    public static void main(String[] args) {

    }

    Map<String, List<String>> prefixMap = new HashMap<>();

    public List<List<String>> wordSquares(String[] words) {
        /*
         * ball area lead lady
         */
        List<List<String>> res = new ArrayList<>();
        createPrefixMap(words);
        System.out.println(prefixMap);
        for (int i = 0; i < words.length; i++) {
            LinkedList<String> list = new LinkedList<>();
            list.add(words[i]);
            backTrack(1, list, res, words[i].length());
        }
        return res;
    }

    private void backTrack(int step, LinkedList<String> list, List<List<String>> res, int n) {
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            sb.append(word.charAt(step));
        }
        String prefix = sb.toString();
        List<String> wordList = prefixMap.getOrDefault(prefix, new ArrayList<>());
        for (String word : wordList) {
            list.add(word);
            backTrack(step + 1, list, res, n);
            list.removeLast();
        }
    }

    private void createPrefixMap(String[] words) {
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String prefix = word.substring(0, i);
                prefixMap.putIfAbsent(prefix, new ArrayList<>());
                List<String> list = prefixMap.get(prefix);
                list.add(word);
            }
        }
    }

    //
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        
        List<String> list = helper(n - 2, m);
        
        List<String> res = new ArrayList<String>();
        
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            
            if (n != m) res.add("0" + s + "0");
            
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        
        return res;
}