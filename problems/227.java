package problems;
import java.util.*;

// stack
class Solution {
    public int calculate(String s) {
        int n = s.length();
        if(s == null || n == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for(int i = 0; i < n; i++){
            // if the current char is digit
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                if(sign == '-') {
                    stack.push(-num);
                } else if(sign == '+') {
                    stack.push(num);
                } else if(sign == '*'){
                    stack.push(stack.pop() * num);
                } else if(sign == '/'){
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for(int i : stack){
            res += i;
        }
        return res;
    } 
}