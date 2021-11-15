public class Solution2 {

    boolean isLetter(char ch) {
        return (ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A');
    }

    String processString(String S) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (isLetter(S.charAt(i))) {
                for (int j = 0; j < count; j++) {
                    result.append('?');
                }
                count = 0;
                result.append(S.charAt(i));
            } else {
                count *= 10;
                count += S.charAt(i) - 48;
            }
        }
        for (int j = 0; j < count; j++) {
            result.append('?');
        }
        return result.toString();
    }

    public boolean solution(String S, String T) {
        String processedS = processString(S);
        String processedT = processString(T);
        if (processedS.length() != processedT.length()) {
            return false;
        }
        for (int i = 0; i < processedS.length(); i++) {
            if (processedS.charAt(i) != '?' && processedT.charAt(i) != '?'
                    && processedS.charAt(i) != processedT.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.solution("9a1", "9A1"));
    }
}
