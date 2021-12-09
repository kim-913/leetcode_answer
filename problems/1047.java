class Solution {
    public String removeDuplicates(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        int index = 0;
        for (char ch : s.toCharArray()) {
            if (res.length() > 0 && ch == res.charAt(res.length() - 1)) {
                res.deleteCharAt(res.length() - 1);
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}