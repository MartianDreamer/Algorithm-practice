package leetcode.longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();
        Map<Character, Integer> map = new HashMap<>();
        char[] string = s.toCharArray();
        int count = 0;
        int longestSubstringLength = 0;
        for (int i = 0; i < string.length; i++) {
            if (!map.containsKey(string[i])) {
                map.put(string[i], i);
                count++;
            } else {
                longestSubstringLength = count > longestSubstringLength ? count : longestSubstringLength;
                int dupplicatedCharIndex = map.get(string[i]);
                count = i - dupplicatedCharIndex;
                map = new HashMap<>();
                for (int j = dupplicatedCharIndex + 1; j <= i; j++) {
                    map.put(string[j], j);
                }
            }
        }
        longestSubstringLength = count > longestSubstringLength ? count : longestSubstringLength;
        return longestSubstringLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.lengthOfLongestSubstring("abba");
        System.out.println(result);
    }
}