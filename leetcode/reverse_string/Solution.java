package leetcode.reverse_string;

/**
 * Solution
 */
public class Solution {

    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        char[] s = { 'a', 'b', 'c', 'd' };
        new Solution().reverseString(s);
        System.out.println(s);
    }
}