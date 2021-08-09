package parenthesisvalidation;

public class ParenthesisValidation {

    public boolean isValid(String string) {
        Stack<Character> chars = new Stack<>();
        for (var i = 0; i < string.length(); i++) {
            var character = string.charAt(i);
            if (character == '(') {
                chars.push(character);
            }
            if (character == ')') {
                if (chars.getSize() > 0) {
                    chars.pop();
                }
                return false;
            }
        }
        if (chars.getSize() == 0)
            return true;
        return false;
    }
}
