import java.util.HashMap;
import java.util.Stack;

public class Stacks {
    public boolean is_valid(String s) {
        var valid_closers = new HashMap<Character, Character>();
        valid_closers.put(')', '(');
        valid_closers.put('}', '{');
        valid_closers.put(']', '[');
        var seen = new Stack<Character>();

        for (char c : s.toCharArray()) {
            if (valid_closers.containsKey(c)) {
                if (seen.empty()) {
                    return false;
                }
                if (seen.peek() != valid_closers.get(c)) {
                    return false;
                }
                seen.pop();
                continue;
            }
            seen.push(c);
        }

        if (!seen.empty()) {
            return false;
        }

        return true;
    }

    public int eval_RPN(String[] tokens) {
        var state = new Stack<Integer>();

        for (String token : tokens) {
            if (!"+-*/".contains(token)) {
                state.push(Integer.parseInt(token));
                continue;
            }

            int value_2 = state.pop();
            int value_1 = state.pop();

            state.push(evaluate(value_1, value_2, token));
        }

        return state.pop();
    }

    public int evaluate(int value_1, int value_2, String operator) {
        return switch (operator) {
            case "+" -> value_1 + value_2;
            case "-" -> value_1 - value_2;
            case "*" -> value_1 * value_2;
            case "/" -> value_1 / value_2;
            default -> 0;
        };
    }
}

class Min_Stack {
    private Stack<Integer> value_stack;
    private Stack<Integer> min_stack;

    public Min_Stack() {
        value_stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int val) {
        value_stack.push(val);
        if (min_stack.isEmpty()) {
            min_stack.push(val);
            return;
        }
        int current_min = min_stack.peek();
        if (current_min > val) {
            min_stack.push(val);
            return;
        }
        min_stack.push(current_min);
    }

    public void pop() {
        if (value_stack.isEmpty()) {
            return;
        }
        min_stack.pop();
        value_stack.pop();
    }

    public int top() {
        return value_stack.peek();
    }

    public int get_min() {
        return min_stack.peek();
    }
}
