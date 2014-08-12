package com.tsystems.javaschool.tasks;

/**
 * This class contains a enumeration of element types and their priorities.
 *
 * @author Igor
 */
public enum PostfixElementType {

    NUMBER, OPERATOR, PLUS(0), MINUS(0), DIVISION(1),
    MULTIPLICATION(1), LEFT_BRACKET(2), RIGHT_BRACKET(2);
    private int priority; //priorities of operators

    private PostfixElementType(int p) {
        priority = p;
    }

    /*overloaded constructor */
    private PostfixElementType() {
        priority = -1; // priority is not specified
    }

    /*return priority of operator */
    int getPriority() {
        return priority;
    }
}