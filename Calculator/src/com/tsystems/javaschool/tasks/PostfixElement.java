package com.tsystems.javaschool.tasks;

/**
 * This class is designed to store objects which make up the postfix notation
 * analyzed statement. It contains information about the type of the element and
 * its significance.
 *
 * @author Igor
 */
public class PostfixElement /*implements PostfixElementType*/ {

    private PostfixElementType elementType;
    private PostfixElementType operatorType;
    private NumberOperation number = null;
    private String value = "";
    private int index = 0; //index of the first symbol of element in the source string

    /**
     * Creates a new instance of the class PostfixElement
     *
     * @param value a string containing the new element (number or operator)
     * @param index index of the first symbol of element in the source string
     * @throws IllegalArgumentException if it is impossimble recognize the
     * element
     */
    public PostfixElement(String value, int index)
            throws IllegalArgumentException {
        parseElement(value);
        this.value = value;
        this.index = index;
    }

    /* Defines the type and parameters of the element specified by the string value */
    private void parseElement(String value)
            throws IllegalArgumentException {
        if (value.equals("(")) {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.LEFT_BRACKET;
            return;
        }
        if (value.equals(")")) {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.RIGHT_BRACKET;
            return;
        }
        if (value.equals("+")) {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.PLUS;
            return;
        }
        if (value.equals("-")) {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.MINUS;
            return;
        }
        if (value.equals("*")) {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.MULTIPLICATION;
            return;
        }
        if (value.equals("/")) {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.DIVISION;
            return;
        }

        try {
            number = new NumberOperation(value);
            elementType = PostfixElementType.NUMBER;
        } catch (NumberFormatException nf) {
            throw new IllegalArgumentException("ошибка при парсинге:\"" + value + "\"");
        }
    }

    /**
     * Returns a text description of this element
     */
    @Override
    public String toString() {
        String retValue = "";
        if (elementType == null) {
            retValue = "Значение не установлено";
        } else {
            if (elementType == PostfixElementType.OPERATOR) {
                retValue = this.value;
            }
            if (elementType == PostfixElementType.NUMBER) {
                retValue = number.toString();
            }
        }
        return retValue;
    }

    /**
     * Return true if this instance of the class  conform specified (param.
     * elem), false - otherwise.
     *
     * @param elem specified instance of the class
     */
    public boolean equals(PostfixElement elem) {
        boolean retValue = false;
        if ((elementType == elem.elementType)
                && (operatorType == elem.operatorType)
                && (number.equal(elem.number))) {
            retValue = true;
        }
        return retValue;
    }

    /**
     * return type of element
     */
    public PostfixElementType getElementType() {
        return elementType;
    }

    /**
     * If this element is opereator - return it's type.
     *
     * @throws IncorrectElementException - otherwise
     */
    public PostfixElementType getOperatorType() throws IncorrectElementException {
        if (elementType != PostfixElementType.OPERATOR) {
            throw new IncorrectElementException("Не правильный тип элемента");
        }
        return operatorType;
    }

    /**
     * If this element is opereator - return it's priority.
     *
     * @throws IncorrectElementException - otherwise
     */
    public int getOperatorPriority() throws IncorrectElementException {
        if (elementType != PostfixElementType.OPERATOR) {
            throw new IncorrectElementException("Не правильный тип элемента");
        }
        return operatorType.getPriority();
    }

    /**
     * If this element is number - return it.
     *
     * @throws IncorrectElementException - otherwise
     */
    public NumberOperation getNumber() throws IncorrectElementException {
        if (elementType != PostfixElementType.NUMBER) {
            throw new IncorrectElementException("Не правильный тип элемента");
        }
        return number;
    }

    /**
     * Return true if this element is operator, 
     * otherwise - false
     */
    public boolean isOperator() {
        if (elementType == PostfixElementType.OPERATOR) {
            return true;
        }
        return false;
    }

    /**
     * Return true if this element is number, 
     * otherwise - false
     */
    public boolean isNumber() {
        if (elementType == PostfixElementType.NUMBER) {
            return true;
        }
        return false;
    }
}
