import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}

class ReversePolishNotationCalculatorTest{

    ReversePolishNotationCalculator check=new ReversePolishNotationCalculator();
    @Test
    public void ifPlus() {
        int i = check.calculatePolishNotation("1 3 +");
        Assertions.assertEquals(4, i);
    }
    @Test
    public void ifMinus() {
        int i = check.calculatePolishNotation("1 3 -");
        Assertions.assertEquals(-2, i);
    }
    @Test
    public void ifMulti() {
        int i = check.calculatePolishNotation("1 3 *");
        Assertions.assertEquals(3, i);
    }
    @Test
    public void ifSpaces() {
        int i = check.calculatePolishNotation("1 3   *");
        Assertions.assertEquals(3, i);
    }
    @Test
    public void ifNegative() {
        int i = check.calculatePolishNotation("1 -3 *");
        Assertions.assertEquals(-3, i);
    }
}