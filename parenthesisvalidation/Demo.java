package parenthesisvalidation;

public class Demo {
    public static void main(String[] args) {
        var checker = new ParenthesisValidation();
        if (checker.isValid("(()(()()))")) {
            System.out.println("valid");
            return;
        }
        System.out.println("invalid");
        System.out.println(checker.longestValidParenthesisSequence(")()(())()()))())))("));
    }
}
