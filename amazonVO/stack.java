// LC 907. Sum of Subarray Minimums
class Solution {
    public int sumSubarrayMins(int[] A) {
        long res = 0, mod = (int) 1e9 + 7;
        int n = A.length, left[] = new int[n], right[] = new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i])
                count += s1.pop()[1];
            s1.push(new int[] { A[i], count });
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= A[i])
                count += s2.pop()[1];
            s2.push(new int[] { A[i], count });
            right[i] = count;
        }
        for (int i = 0; i < n; ++i)
            res = (res + (long) A[i] * left[i] * right[i]) % mod;
        return (int) res;
    }
}

// LC 735. Asteroid Collision
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int num : asteroids) {
            if (num > 0) {
                stack.push(num);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(num)) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(num);
                } else if (stack.peek() + num == 0) {
                    stack.pop();
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++)
            res[i] = stack.get(i);
        return res;
    }
}


// LC 503. Next Greater Element II
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[2 * nums.length];
        Stack<Integer> stack = new Stack<>();
        int[] extend = new int[2 * nums.length];
        for (int j = 0; j < nums.length; j++)
            extend[j] = nums[j];
        for (int j = nums.length; j < 2 * nums.length; j++) {
            extend[j] = nums[j - nums.length];
        }
        for (int i = 0; i < extend.length; i++) {
            while (!stack.isEmpty() && extend[stack.peek()] < extend[i]) {
                res[stack.pop()] = extend[i];
            }
            stack.push(i % nums.length);
            res[i] = -1;
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < res.length / 2; i++)
            result[i] = res[i];
        return result;
    }
}



// LC 1597. Build Binary Expression Tree From Infix Expression
class Solution {
    // stack to build tree?
    // operator are node
    int p = 0;

    public Node expTree(String s) {
        String operator = "+-*/";
        Deque<Node> operators = new ArrayDeque<>();
        Deque<Node> operands = new ArrayDeque<>();
        while (p < s.length()) {
            char cur = s.charAt(p++);
            if (Character.isDigit(cur)) {
                operands.push(new Node(cur));
            } else if (operator.contains(cur + "")) {
                while (!operators.isEmpty() && compareOperator(cur, operators.peek().val)) {
                    Node r = operands.pop(), l = operands.pop();
                    Node op = operators.pop();
                    op.left = l;
                    op.right = r;
                    operands.push(op);
                }
                operators.push(new Node(cur));
            } else if (cur == '(') {
                operands.push(expTree(s));
            } else if (cur == ')') {
                break;
            }
        }

        // handle rest numbers in stack
        // because we use loop inside, so directly use pop is fine
        // the only reason stack has two operator is that first priority low
        while (!operators.isEmpty()) {
            Node r = operands.pop(), l = operands.pop();
            Node op = operators.pop();
            op.left = l;
            op.right = r;
            operands.push(op);
        }

        return operands.pop();
    }

    // check l's priority is less or equal than r's priority
    private boolean compareOperator(char l, char r) {
        if (l == '*' || l == '/') {
            if (r == '*' || r == '/')
                return true; // true means can pop
            return false;
        }

        return true; // if l is + or -, all operator should pop
    }
}


// Find largest element on right
public static int[] findLarge(int[] nums) {
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    int[] res = new int[n];
    int max = nums[n - 1];
    stack.push(n - 1);
    for (int i = n - 2; i >= 0; i--) {
        max = Math.max(max, nums[i]);
        res[i] = max;
    }
    res[n - 1] = -1;
    return res;
}


// find next greater element on right
public static int[] nextGreater(int[] nums){
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    int[] res = new int[n];
    for(int i = 0; i < n; i++){
        while(!stack.isEmpty() && nums[i] > nums[stack.peek()]){
            res[stack.pop()] = nums[i];
        }
        stack.push(i);
        res[i] = -1;
    }
    return res;
}



// LC 227. Basic Calculator II
class Solution {
    public int calculate(String s) {
        // s.trim();
        int num = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                num = num * 10 + c - '0';
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = s.charAt(i);
            }
        }
        int res = 0;
        for (int n : stack) {
            res += n;
        }
        return res;
    }
}


// 901 Online stock span -- stack
class StockSpanner {
    Stack<int[]> stack = new Stack<>();
    public StockSpanner() {
        
    }
    
    public int next(int price) {
        int res = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            res += stack.pop()[1];
        }
        stack.push(new int[]{price, res});
        return res;
    }
}



// 496. Next Greater Element I
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }   
        for (int i = 0; i < nums1.length; i++)
            nums1[i] = map.getOrDefault(nums1[i], -1);
        return nums1;
    }
}



// 402. Remove K Digits

public class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        //corner case
        if(k == len) return "0";
            
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < num.length(); i++){
            while(k > 0 && !stack.isEmpty() && num.charAt(stack.peek()) > num.charAt(i)){
                stack.pop();
                k--;
            }
            stack.push(i);
        }
        while(k > 0){
            stack.pop();
            k--;            
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(num.charAt(stack.pop()));
        sb.reverse();
        while(sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.toString();
    }
}