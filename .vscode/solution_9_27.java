import java.util.*;

public class solution_9_27 {
    
    public static void main(String[] args) {
        
    }

    // LC 929. Unique Email Addresses
    public int numUniqueEmails(String[] emails) {
        if(emails.length == 1) return 1;
        Set<String> s = new HashSet<>();
        for(String email: emails){
            String[] each = email.split("@");
            String[] local = each[0].split("\\+");
            s.add(local[0].replace(".", "") + "@" + each[1]);
        }
        return s.size();
    }

    // LC 735. Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        for(int i = asteroids.length - 1; i >= 0; i--){
            s1.push(asteroids[i]);
        }
        //[5,10,-5]
        s2.push(s1.pop());
        // [-5]
        while(!s1.isEmpty()){
            if(s2.isEmpty()) {
                s2.push(s1.pop());
                continue;
            }
            /*
            if(s1.peek() * s2.peek() < 0) {
                if(s1.peek() < 0) {
                    s2.add(s1.pop());
                }else {
                    if(Math.abs(s1.peek()) < Math.abs(s2.peek())) {
                    s1.pop();
                    }else if(Math.abs(s1.peek()) > Math.abs(s2.peek())){
                        s2.pop();
                        s2.add(s1.pop());
                    } else{
                        s2.pop();
                        s1.pop();
                    }
                }
            } else{
                s2.add(s1.pop());
            }
            */
            int left = s2.pop();
            int right = s1.pop();
            // no collision
            if(right > 0 || left < 0) {
                s2.push(left);
                s2.push(right);
            } else if(left + right > 0){
                s2.push(left);
            } else if(left + right < 0){
                s1.push(right);
            }
        }
        int[] res = new int[s2.size()];
        for(int i = res.length - 1; i >= 0; i--){
            res[i] = s2.pop();
        }
        return res;
        /*
        Stack<Integer> s = new Stack<>();
        for(int i: asteroids){
            if(i > 0){
                s.push(i);
            }else{// i is negative
                while(!s.isEmpty() && s.peek() > 0 && s.peek() < Math.abs(i)){
                    s.pop();
                }
                if(s.isEmpty() || s.peek() < 0){
                    s.push(i);
                }else if(i + s.peek() == 0){
                    s.pop(); //equal
                }
            }
        }
        int[] res = new int[s.size()];   
        for(int i = res.length - 1; i >= 0; i--){
            res[i] = s.pop();
        }
        return res;
        */
    }

    // LC 1048. Longest String Chain
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a ,b) -> (a.length() - b.length()));
        Map<String, Integer> map = new HashMap<>();
        int res = 1;
        for(String word: words){
            // current here is to record the best string chain length and store it inside the map
            int cur = 1;
            int n = word.length();
            // this string builder takes care of each word and deleting each character inside word
            StringBuilder sb = new StringBuilder(word);
            for(int i = 0; i < n; i++){
                char ch = word.charAt(i);
                sb.deleteCharAt(i);
                String possible = sb.toString();
                if(map.containsKey(possible)) {
                    cur = Math.max(cur, map.get(possible) + 1);
                }
                // here, we need to restore the stringbuilder to deleted character
                if(i < n -1){
                    sb.insert(i, ch);
                }
            } 
            // put the current prefix word and the its length that satisfy the string chain inside the map
            map.put(word, cur);
            res = Math.max(res, cur);
        }
        return res;
    }


    //
    public int numSplits(String s) {
        /*
        if(s.length() <= 1) return 0;
        // brute force worst case O(n^2)
        Set<Character> left = new HashSet();
        Set<Character> right = new HashSet();
        // int split_index = 0;
        int res = 0;
        // i represents the split index
        for(int i = 1; i < s.length(); i++){
            String l = s.substring(0, i);
            String r = s.substring(i, s.length());
            for(char c: l.toCharArray()){
                left.add(c);
            }
            for(char c: r.toCharArray()){
                right.add(c);
            }
            int left_length = left.size();
            int right_length = right.size();
            if(left_length == right_length) res++;
            left.clear();
            right.clear();
        }
        return res;
        */
        int rc[] = new int[26], lc[] = new int[26], l = 0, r = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (rc[c - 'a']++ == 0) r++;
        }
        for (char c : s.toCharArray()) {
            if (lc[c - 'a']++ == 0) l++;
            if (--rc[c - 'a'] == 0) r--;
            if (l == r) res++;
        }
        return res;
    }
}
