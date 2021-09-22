import java.util.*;
public class solution_9_22 {
    
    public static void main(String[] args) {
        
    }

    public int maxLength(List<String> arr) {
        if(arr == null || arr.size() == 0) return 0;
        return concatenation(arr, 0, "");
    }
    public static int concatenation(List<String> arr, int index, String cur){
        Set<Character> unique = new HashSet<>();
        for(char c: cur.toCharArray()){
            unique.add(c);
        }
        // important, check the end of the recursion
        if(unique.size() != cur.length()) return 0;
        int res = unique.size();
        for(int i = index; i < arr.size(); i++){
            res = Math.max(res, concatenation(arr, i + 1, cur + arr.get(i)));
        }
        return res;
    }
}
