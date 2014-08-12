package com.tsystems.javaschool.tasks;

import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.Locale;

/**
 * This class contains a number and allows you to perform mathematical
 * operations on it.
 *
 * @author Igor
 */
public class NumberOperation {

    private double N = 0; //

    /**
     * Creates a new instance of the class NumberOperation
     *
     * @param value a string containing a number. For example, "-9.867";
     * @throws NumberFormatException if the string does not contain a parsable
     * {@code double}.
     */
    public NumberOperation(String value) throws NumberFormatException {

        String val = removeSpaces(value);
        parseNumber(val);
    }

    /**
     * Creates a new instance of the class NumberOperation
     *
     * @param value instance of the class NumberOperation.
     */
    public NumberOperation(NumberOperation value) {
        N = value.getN();
    }

    /**
     * returns the number
     *
     * @return number
     */
    public double getN() {
        return N;
    }

    /**
     * Converts a number to a string
     *
     * @return string
     */
    @Override
    public String toString() {
        String retValue;
        retValue = String.valueOf(N);
        return retValue;
    }

    /**
     * Rounds the result and convert to string
     *
     * @return result with 4 fraction Digits
     */
    public String rounding() {
        String retValue;
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(4);
        retValue = nf.format(N);
        return retValue;
    }

    /*Remove spaces from string*/
    private String removeSpaces(String str) {
        StringTokenizer st = new StringTokenizer(str);
        StringBuilder temp = new StringBuilder(str.length());
        while (st.hasMoreTokens()) {
            temp.append(st.nextToken());
        }
        return temp.toString();
    }

    /*Converts the string in the number (string does not contain spaces)*/
    private void parseNumber(String str)
            throws NumberFormatException {
        StringTokenizer st = new StringTokenizer(str, "+-", true);
        String previousToken = "";
        String currentToken = "";
        String numb = "";
        while (st.hasMoreTokens()) {
            currentToken = st.nextToken();
            if (previousToken.equals("-")) {
                numb = previousToken + currentToken;
            } else {
                numb = currentToken;
            }
            if (numb.equals("+") == false && numb.equals("-") == false) {
                N = Double.parseDouble(numb);
            }
            previousToken = currentToken;
        }
    }

    /**
     * Checks conform the this number of a specified number
     *
     * @param value specified number
     * @return boolean result
     */
    public boolean equal(NumberOperation value) {
        if (N == value.N) {
            return true;
        }
        return false;
    }

    /**
     * Adds the this number of a specified number
     *
     * @param value given number
     */
    public void add(NumberOperation value) {
        N += value.N;
    }

    /**
     * subtracts from this number of a specified number
     *
     * @param value specified number
     */
    public void sub(NumberOperation value) {
        N -= value.N;
    }

    /**
     * multiplies this number by the specified number
     *
     * @param value specified number
     */
    public void mult(NumberOperation value) {
        double tempN = N * value.N;
        N = tempN;
    }

    /**
     * divides this number by the specified number
     *
     * @param value specified number
     */
    public void div(NumberOperation value) {
        if (value.N == 0) {
            throw new ArithmeticException("на ноль делить нельзя");
        }
        double tempN = N / value.N;
        N = tempN;

    }
}
