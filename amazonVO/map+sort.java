// LC 1152. Analyze User Website Visit Pattern
class Pair {
    int time;
    String web;

    public Pair(int time, String web) {
        this.time = time;
        this.web = web;
    }
}

class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();
        int n = username.length;
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }
        // count map to record every 3 combination occuring time for the different user.
        Map<String, Integer> count = new HashMap<>();
        String res = "";
        for (String key : map.keySet()) {
            Set<String> set = new HashSet<>();
            // this set is to avoid visit the same 3-seq in one user
            List<Pair> list = map.get(key);
            Collections.sort(list, (a, b) -> (a.time - b.time)); // sort by time
            // brutal force O(N ^ 3)
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        String str = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                        if (!set.contains(str)) {
                            count.put(str, count.getOrDefault(str, 0) + 1);
                            set.add(str);
                        }
                        if (res.equals("") || count.get(res) < count.get(str)
                                || (count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                            // make sure the right lexi order
                            res = str;
                        }
                    }
                }
            }
        }
        // grab the right answer
        String[] r = res.split(" ");
        List<String> result = new ArrayList<>();
        for (String str : r) {
            result.add(str);
        }
        return result;
    }
}



// LC 1366. Rank Teams by Votes
class Solution {
    public String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        int l = votes[0].length();
        for (String vote : votes) {
            for (int i = 0; i < l; i++) {
                char c = vote.charAt(i);
                map.putIfAbsent(c, new int[l]);
                map.get(c)[i]++;
            }
        }

        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> {
            for (int i = 0; i < l; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a - b;
        });

        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}


// LC 2021. Brightest Position on Street
class Solution {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> line = new TreeMap<>();
        int bright = 0, max_bright = 0, res = 0;
        for (int[] l : lights) {
            line.put(l[0] - l[1], line.getOrDefault(l[0] - l[1], 0) + 1);
            line.put(l[0] + l[1] + 1, line.getOrDefault(l[0] + l[1] + 1, 0) - 1);
        }
        for (Integer light : line.keySet()) {
            bright += line.get(light);
            if (bright > max_bright) {
                max_bright = bright;
                res = light;
            }
        }
        return res;
    }
}

// LC 937. Reorder Data in Log Files
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Map<String, String> map = new HashMap<>();
        List<String> digits = new ArrayList<>();
        List<String> letters = new ArrayList<>();
        List<String> identifies = new ArrayList<>();
        for(String log: logs){
            String[] parts = log.split(" ");
            if (Character.isDigit(parts[1].charAt(0))) {
                digits.add(log);
            } else {
                if(map.containsKey(log.substring(parts[0].length() + 1))) {
                    identifies.add(map.get(log.substring(parts[0].length() + 1)));
                    identifies.add(log);
                } 
                map.put(log.substring(parts[0].length() + 1), log);
                letters.add(log.substring(parts[0].length() + 1));
            }
        }
        Collections.sort(letters);
        Collections.sort(identifies);
                   
        String[] res = new String[logs.length];
        int i;
        for(i = 0; i < letters.size(); i++){
            for(int j = 0; j < identifies.size(); j++){
                if(letters.get(i).equals(identifies.get(j))) {
                    String i1 = identifies.get(j).split(" ")[0];
                    String i2 = map.get(letters.get(i)).split(" ")[0];
                    if()
                } else {
                    res[i] = map.get(letters.get(i));
                }
            }
        }
        for(int j = 0; j < digits.size(); j++){
            res[i++] = digits.get(j);
        }
        return res;
    }
}