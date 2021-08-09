package parenthesisvalidation;

public class Demo {
    public static void main(String[] args) {
        var checker = new ParenthesisValidation();
        if (checker.isValid("(sang(truc(thuong)haha)sa)")) {
            System.out.println("valid");
            return;
        }
        System.out.println("invalid");
    }
}
