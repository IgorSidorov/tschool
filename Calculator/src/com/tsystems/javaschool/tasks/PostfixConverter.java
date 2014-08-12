package com.tsystems.javaschool.tasks;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * This class converts a string written in infix form to a collection of objects
 * of type PostfixElement
 *
 * @author Igor
 */
public class PostfixConverter {

    private ArrayList postfixList = null;
    private ArrayList infixList = null;
    String infixStr;

    /**
     * Creates a new instance of the class PostfixConverter
     *
     * @param str a string containing the expression in infix form
     */
    public PostfixConverter(String str) {

        infixStr = str;
    }

    /**
     * converts a string written in infix form of a collection of objects of
     * type PostfixElement.
     *
     * @return link to the object of class <code>java.util.ArrayList</code>,
     * containing an array of objects PostfixElement in postfix form.
     *
     * @throws IllegalArgumentException if there was an attempt to create an
     * collection with negative length or if it is impossimble recognize the
     * element
     *
     * @throws IncorrectElementException if there was an error in the analysis
     * of expression or if there was an attempt to call a method of a class
     * PostfixElement, which is not allowed for this instance. For example, an
     * instance of the class PostfixElement no method can be used getNumber (),
     * if it contains a operator.
     */
    public ArrayList convertToPostfix() throws IllegalArgumentException,
            IncorrectElementException {
        infixList = new ArrayList(10);

        parseStr(infixStr);
        InfixChecker checker = new InfixChecker(infixList);
        checker.check();
        convert();
        return postfixList;
    }

    /* Converts a string written in infix form 
     * to a collection of objects PostfixElement in infix form */
    private void parseStr(String str) throws IllegalArgumentException,
            IncorrectElementException {
        infixStr = removeSpaces(str);
        StringTokenizer st = new StringTokenizer(infixStr, "()+-*/", true);
        PostfixElement currentElement = null;
        PostfixElement previousElement = null;
        int tokenIndex = 0;
        int curIndex = 0;
        boolean firstElement = true;
        while (st.hasMoreTokens()) {
            String currentToken = st.nextToken();
            tokenIndex = str.indexOf(currentToken, curIndex);
            currentElement = new PostfixElement(currentToken, tokenIndex);
            if (previousElement != null) {
                if (previousElement.isOperator()
                        && (previousElement.getOperatorType()
                        == PostfixElementType.LEFT_BRACKET)
                        && currentElement.isOperator()) {
                    if ((currentElement.getOperatorType()
                            == PostfixElementType.MINUS)
                            || (currentElement.getOperatorType()
                            == PostfixElementType.PLUS)) {
                        currentToken += st.nextToken();
                        tokenIndex = str.indexOf(currentToken, curIndex);
                        currentElement = new PostfixElement(currentToken,
                                tokenIndex);
                    }
                }
            }
            if (firstElement) {
                if (currentElement.isOperator()
                        && ((currentElement.getOperatorType() == PostfixElementType.MINUS)
                        || (currentElement.getOperatorType() == PostfixElementType.PLUS))) {
                    currentToken += st.nextToken();
                    tokenIndex = str.indexOf(currentToken, curIndex);
                    currentElement = new PostfixElement(currentToken, tokenIndex);
                }
                firstElement = false;
            }

            infixList.add(currentElement);
            curIndex = tokenIndex + currentToken.length();
            previousElement = currentElement;
        }
    }

    /* Converts a collection in infix form 
     * to a collection of objects PostfixElement in postfix form.*/
    private void convert() throws IllegalArgumentException,
            IncorrectElementException {
        postfixList = new ArrayList(infixList.size());
        int curIndex = 0;
        PostfixElement curElement = null;
        Stack s = new Stack();
        s.push(new PostfixElement("(", 0));
        infixList.add(new PostfixElement(")", 0));
        while (s.isEmpty() == false) {
            curElement = (PostfixElement) infixList.get(curIndex);
            curIndex++;
            if (curElement.isNumber()) {
                postfixList.add(curElement);
                continue;
            }
            if (curElement.isOperator()) {
                if (curElement.getOperatorType()
                        == PostfixElementType.LEFT_BRACKET) {
                    s.push(curElement);
                    continue;
                }
            }
            if (curElement.isOperator()) {
                if (curElement.getOperatorType()
                        != PostfixElementType.LEFT_BRACKET
                        && curElement.getOperatorType()
                        != PostfixElementType.RIGHT_BRACKET) {
                    while (true) {
                        PostfixElement peekOperator = (PostfixElement) s.peek();
                        if (peekOperator.getOperatorType()
                                == PostfixElementType.LEFT_BRACKET) {
                            break;
                        }
                        if (peekOperator.getOperatorPriority()
                                >= curElement.getOperatorPriority()) {
                            postfixList.add(s.pop());
                        } else {
                            break;
                        }
                    }
                    s.push(curElement);
                    continue;
                }
            }
            if (curElement.isOperator()) {
                if (curElement.getOperatorType()
                        == PostfixElementType.RIGHT_BRACKET) {
                    while (true) {
                        PostfixElement peekOperator = (PostfixElement) s.peek();
                        if (peekOperator.getOperatorType()
                                == PostfixElementType.LEFT_BRACKET) {
                            s.pop();
                            break;
                        }
                        postfixList.add(s.pop());
                    }
                }
            }
        }
    }

    /* Remove spaces from string str. Return string without spaces */
    private String removeSpaces(String str) {
        StringTokenizer st = new StringTokenizer(str);
        StringBuilder temp = new StringBuilder(str.length());
        while (st.hasMoreTokens()) {
            temp.append(st.nextToken());
        }
        return temp.toString();
    }
}
