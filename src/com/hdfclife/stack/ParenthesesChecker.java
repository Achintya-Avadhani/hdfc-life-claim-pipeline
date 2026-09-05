package com.hdfclife.stack;

public class ParenthesesChecker {

    public static boolean isBalanced(String expression) {

        ArrayClaimStack stack = new ArrayClaimStack(32);

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {

                stack.push(ch);

            } else if (ch == ')' || ch == ']' || ch == '}') {

                if (stack.isEmpty()) {
                    return false;
                }

                char open = (char) stack.pop();

                if (!isMatching(open, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatching(char open, char close) {

        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }
}
