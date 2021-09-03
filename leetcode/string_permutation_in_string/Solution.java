package leetcode.string_permutation_in_string;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int s1Int = convertToInt(s1);
        int s1Lenth = s1.length();
        for (int i = 0; i <= s2.length() - s1Lenth; i++) {
            String subString = s2.substring(i, i + s1Lenth);
            if (convertToInt(subString) == s1Int) {
                boolean check = true;
                for (char character : s1.toCharArray()) {
                    check &= subString.contains("" + character);
                }
                if (check)
                    return check;
            }
        }
        return false;
    }

    public int convertToInt(String string) {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            result += string.charAt(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean result = solution.checkInclusion("adc", "dcda");
        System.out.println(result);
    }
}