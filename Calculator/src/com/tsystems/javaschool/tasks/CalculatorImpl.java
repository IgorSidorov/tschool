package com.tsystems.javaschool.tasks;

/**
 * the main class of Calculator task
 *
 * @author Igor
 */
public class CalculatorImpl implements Calculator {

    private PostfixConverter converter = null;
    private PostfixCalculator calc = null;
    private NumberOperation result = null;
    private String res;

    /**
     * the main method in which written statement
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        System.out.println(c.evaluate("((-12+21*3)/2+4.567893/3)"));
    }

    @Override
    public String evaluate(String statement) {

        try {
            converter = new PostfixConverter(statement);
            calc = new PostfixCalculator(converter.convertToPostfix());
            result = new NumberOperation(calc.calculate());
            res = result.rounding();
        } catch (Exception err) {
            System.out.println(err);
        }
        return res;
    }
}
