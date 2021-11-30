package problems;
import java.util.*;

// Stack
class Solution {
    public int evalRPN(String[] tokens) {
        int a,b;
        // always two numbers 运算
        Stack<Integer> S = new Stack<>();
        for(String s: tokens){
            if(s.equals("+")) {
				S.add(S.pop()+S.pop());
			}
			else if(s.equals("/")) {
				b = S.pop();
				a = S.pop();
				S.add(a / b);
			}
			else if(s.equals("*")) {
				S.add(S.pop() * S.pop());
			}
			else if(s.equals("-")) {
				b = S.pop();
				a = S.pop();
				S.add(a - b);
			}
			else {
				S.add(Integer.parseInt(s));
			}
		}	
		return S.pop();
    }
}

// recursion
class Solution1 {
    int seen = -1;
    public int evalRPN(String[] tokens) {
        return eval(tokens, tokens.length - 1);
    }
    
    private int eval(String[] tokens, int end){
        String op = tokens[end];
        if(!isOp(op)){
            seen = end;
            return Integer.parseInt(op);
        } 
        int b = eval(tokens, end-1);
        int a = eval(tokens, seen-1);
        int result;
        if(op.equals("*")) result = a * b;
        else if(op.equals("+")) result = a + b;
        else if(op.equals("-")) result = a - b;
        else result = a / b;
        if(seen > end) seen = end;
        return result;
    }
    
    private boolean isOp(String s){
        if(s.equals("*")) return true;
        else if(s.equals("/")) return true;
        else if(s.equals("+")) return true;
        else if(s.equals("-")) return true;
        else return false;
    }
}