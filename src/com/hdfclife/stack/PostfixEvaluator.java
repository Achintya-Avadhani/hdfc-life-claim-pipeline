package com.hdfclife.stack;

public class PostfixEvaluator {

    public static int evaluate(String expression) {

        LinkedClaimStack stack = new LinkedClaimStack();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {

            if (token.equals("+")
                    || token.equals("-")
                    || token.equals("*")
                    || token.equals("/")) {

                int second = stack.pop();
                int first = stack.pop();

                int result = 0;

                if (token.equals("+")) {
                    result = first + second;
                } else if (token.equals("-")) {
                    result = first - second;
                } else if (token.equals("*")) {
                    result = first * second;
                } else if (token.equals("/")) {
                    result = first / second;
                }

                stack.push(result);

            } else {

                int number = Integer.parseInt(token);
                stack.push(number);
            }
        }

        return stack.pop();
    }
}