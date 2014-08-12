package com.tsystems.javaschool.tasks;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class looks for errors in the infix expression. For example, the lack of
 * brackets "(2 +3) - (5", several operators consecutive record "3 / * + 2",
 * etc.
 *
 * @author Igor
 */
public class InfixChecker {

    ArrayList elements;

    /**
     * Creates a new instance of the class InfixChecker
     *
     * @param infixList a pointer to an object of class <code>ArrayList</code>,
     * Containing the analyzed sequence.
     */
    public InfixChecker(ArrayList infixList) {
        elements = infixList;
    }

    /**
     * performs checking.
     *
     * @exception IncorrectElementException if an error was found No brackets or
     * write multiple statements consecutive Or if the processing occurred Bug
     * when working with objects such as PostfixElement
     */
    public void check() throws IncorrectElementException {
        if (elements == null) {
            return;
        }
        int rightBracketsCount = 0;
        int leftBracketsCount = 0;
        int operatorsCount = 0;
        int numbersCount = 0;
        PostfixElement curElem = null;
        PostfixElement prevElem = null;
        boolean firstElem = true;

        Iterator iterator = elements.iterator();
        if (iterator == null) {
            return;
        }

        while (iterator.hasNext()) {
            curElem = (PostfixElement) iterator.next();

//            the first element may be a number or a left parenthesis
            if (firstElem) {
                if (curElem.isOperator() && (curElem.getOperatorType()
                        != PostfixElementType.LEFT_BRACKET)) {
                    throw new IncorrectElementException();
                }
                firstElem = false;
            }

//            count the number of parentheses and the number of consecutive
//            recorded operators (parentheses are not taken into account) and the numbers
            if (curElem.getElementType() == PostfixElementType.OPERATOR) {
                numbersCount = 0;
                if (curElem.getOperatorType() == PostfixElementType.RIGHT_BRACKET) {
                    rightBracketsCount++;
                } else if (curElem.getOperatorType()
                        == PostfixElementType.LEFT_BRACKET) {
                    leftBracketsCount++;
                } else {
                    operatorsCount++;
                }
            }
            if (curElem.getElementType() == PostfixElementType.NUMBER) {
                numbersCount++;
                operatorsCount = 0;
            }

//            numbers and operators must alternate
            if (numbersCount > 1 || operatorsCount > 1) {
                throw new IncorrectElementException();
            }

            if (prevElem != null) {
                //after the opening parenthesis can be a number, or other open-parenthesis
                if (prevElem.isOperator() && (prevElem.getOperatorType()
                        == PostfixElementType.LEFT_BRACKET)) {
                    if (curElem.isOperator()) {
                        if ((curElem.getOperatorType()
                                != PostfixElementType.LEFT_BRACKET)) {
                            throw new IncorrectElementException();
                        }
                    }
                }
//                before opening parenthesis can be any operator, 
//                but can not be a number
                if (prevElem.isNumber() && (curElem.getOperatorType()
                        == PostfixElementType.LEFT_BRACKET)) {
                    throw new IncorrectElementException();
                }

//                after the closing parenthesis can be any operator,
//                but can not be a number and the opening parenthesis
                if (prevElem.isOperator() && (prevElem.getOperatorType()
                        == PostfixElementType.RIGHT_BRACKET)) {
                    if (curElem.isNumber()) {
                        throw new IncorrectElementException();
                    } else if (curElem.getOperatorType()
                            == PostfixElementType.LEFT_BRACKET) {
                        throw new IncorrectElementException();
                    }
                }
            }

            prevElem = curElem;
        }

        //the last element can be a number or a closing parenthesis
        if (curElem.isOperator() && (curElem.getOperatorType()
                != PostfixElementType.RIGHT_BRACKET)) {
            throw new IncorrectElementException();
        }

        //number of opening parentheses must be equal to the number of closing parentheses
        if (leftBracketsCount > rightBracketsCount) {
            throw new IncorrectElementException("Отсутствует \")\"");
        }
        if (leftBracketsCount < rightBracketsCount) {
            throw new IncorrectElementException("Отсутствует \"(\"");
        }
    }
}
