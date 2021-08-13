package parenthesisvalidation;

import springcollection.LinkedStack;

public class ParenthesisValidation {

    public boolean isValid(String string) {
        LinkedStack<Character> chars = new LinkedStack<>();
        for (var i = 0; i < string.length(); i++) {
            var character = string.charAt(i);
            if (character == '(') {
                chars.push(character);
            }
            if (character == ')') {
                if (chars.size() > 0) {
                    chars.pop();
                    continue;
                }
                return false;
            }
        }
        return chars.size() == 0;
    }

    public int longestValidParenthesisSequence(String string) {
        int count = 0;
        LinkedStack<Character> chars = new LinkedStack<>();
        for (var i = 0; i < string.length(); i++) {
            var character = string.charAt(i);
            if (character == '(') {
                chars.push(character);
                count++;
            }
            if (character == ')' && chars.size() > 0) {
                chars.pop();
                count++;
            }
        }
        return count - chars.size();
    }
}
