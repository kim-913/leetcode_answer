class Solution {
    public String removeDuplicateLetters(String s) {
        Set<Character> seen = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++)
            map.put(s.charAt(i), i);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && map.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack)
            sb.append(c.charValue());
        return sb.toString();
    }
}