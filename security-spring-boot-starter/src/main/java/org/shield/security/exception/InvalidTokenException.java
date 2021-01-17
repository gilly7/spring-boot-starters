package org.shield.security.exception;

/**
 * @author zacksleo@gmail.com
 */
public class InvalidTokenException extends Exception {

    private static final long serialVersionUID = 1194082261719439672L;

    public InvalidTokenException(String message) {
        super(message);
    }
}
