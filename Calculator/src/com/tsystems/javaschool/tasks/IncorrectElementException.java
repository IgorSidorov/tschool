package com.tsystems.javaschool.tasks;

/**
 * This class is used to inform that the original statement has errors.
 *
 * @author Igor
 */
public class IncorrectElementException extends Exception {

    /**
     * The constructor without parameters creates an exception with a ready
     * message
     */
    public IncorrectElementException() {
        super("Ошибка при анализе выражения");
    }

    /**
     * Costructor that can accept any message
     *
     * @param message - message
     */
    public IncorrectElementException(String message) {
        super(message);
    }
}
