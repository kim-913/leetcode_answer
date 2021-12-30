class Solution {
    public String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<StringBuilder> build_String = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            } else if (c == '[') {
                count.push(k);
                build_String.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                int countK = count.pop();
                StringBuilder temp = build_String.pop();
                while (countK-- > 0) {
                    temp.append(cur);
                }
                cur = temp;
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
