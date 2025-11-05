// Time Complexity : O(2^n) where n is the length of the source string
// Space Complexity : O(2^n *n) for storing the visited string of n length
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Using exhaustive BFS approach to check the validity of the string of current length and adding all valid strings of max length in the response.
 * If a match was not found for current length, then all combinations of (current length - 1) are checked exhaustively until match is found while ignoring the alphabets since those don't matter for validity.
 */
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new LinkedList<>();
        Queue<String> q = new LinkedList<>();
        q.add(s);
        Set<String> visited = new HashSet<>();
        visited.add(s);

        // flag to check if a match was found at current level
        boolean flag = false;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String str = q.poll();
                // match found at current level
                if (isValid(str)) {
                    res.add(str);
                    flag = true;
                } else {
                    // match was not found at current level. go one level deep
                    if (!flag) {
                        int strSize = str.length();
                        for (int j = 0; j < strSize; j++) {
                            // don't need to ignore the alphabet character when creating valid strings
                            if (Character.isAlphabetic(str.charAt(j))) continue;

                            // ignoring j-th character for creating new string
                            String newStr = str.substring(0, j) + str.substring(j + 1);
                            // add it to queue if it was already not created with other permutation
                            if (!visited.contains(newStr)) {
                                q.add(newStr);
                                visited.add(newStr);
                            }
                        }
                    }
                }
            }
        }

        if (res.isEmpty()) res.add("");

        return res;
    }

    private boolean isValid(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (cnt < 0) return false;
            if (str.charAt(i) == '(') cnt++;
            else if (str.charAt(i) == ')') cnt--;
        }

        return cnt == 0;
    }
}