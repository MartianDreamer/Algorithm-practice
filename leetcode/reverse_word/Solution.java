package leetcode.reverse_word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Solution
 */
public class Solution {

    public static String reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
        StringBuilder result = new StringBuilder();
        for (char character : s) {
            result.append(character);
        }
        return result.toString();
    }

    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        List<String> wordList = Arrays.stream(strings).map(string -> {
            return reverseString(string.toCharArray());
        }).collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        for (String word : wordList) {
            result.append(word + " ");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.reverseWords("null is llun");
        System.out.println(result);
    }
}