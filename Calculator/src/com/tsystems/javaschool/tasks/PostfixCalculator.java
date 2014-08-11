package com.tsystems.javaschool.tasks;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class calculates the value of the expression written in postfix form.
 * This expression can be obtained from a normal statement (written in infix
 * form) with the help of a class PostfixConverter.
 *
 * @author Igor
 */
public class PostfixCalculator {

    private ArrayList postfixList = null;
    private NumberOperation result = null;

    /**
     * Creates a new instance of the class PostfixCalculator
     *
     * @param postfixList instance of the      * class <code>java.util.ArrayList</code>, containing an array of raw
     * data in postfix form.
     */
    public PostfixCalculator(ArrayList postfixList) {
        this.postfixList = postfixList;
    }

    /**
     * Calculates the value of the expression.
     *
     * @return result of the calculation
     * 
     * @throws java.text.ParseException Signals that an error has been reached
     * unexpectedly while parsing.
     * 
     * @throws IncorrectElementException if there was an error in the analysis
     * of expression or if there was an attempt to call a method of a class
     * PostfixElement, which is not allowed for this instance. For example, an
     * instance of the class PostfixElement no method can be used getNumber (),
     * if it contains a operator.
     * 
     * @throws IllegalArgumentException if there was an error in recognizing the
     * new element
     */
    public NumberOperation calculate() throws java.text.ParseException,
            IncorrectElementException, IllegalArgumentException {
        result = new NumberOperation("0");
        Stack stack = new Stack();
        PostfixElement temp = null;
        for (int i = 0; i < postfixList.size(); i++) {
            temp = (PostfixElement) postfixList.get(i);
            if (temp.isNumber()) {
                stack.push(temp);
            }
            if (temp.isOperator()) {
                NumberOperation y = ((PostfixElement) stack.pop()).getNumber();
                NumberOperation x = ((PostfixElement) stack.pop()).getNumber();
                NumberOperation res = solveOperation(x, y, temp.getOperatorType());
                stack.push(new PostfixElement(res.toString(), 0));
            }
        }
        result = ((PostfixElement) stack.pop()).getNumber();
        return result;
    }

    /*Calculates the result of each operation */
    private NumberOperation solveOperation(NumberOperation x, NumberOperation y,
            PostfixElementType operatorType) throws java.text.ParseException {
        NumberOperation res = new NumberOperation("0");
        res = x;
        switch (operatorType) {
            case PLUS:
                res.add(y);
                break;
            case MINUS:
                res.sub(y);
                break;
            case MULTIPLICATION:
                res.mult(y);
                break;
            case DIVISION:
                res.div(y);
                break;
        }
        return res;
    }
}
