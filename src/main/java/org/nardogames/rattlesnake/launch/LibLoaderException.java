package org.nardogames.rattlesnake.launch;

public class LibLoaderException extends RuntimeException {
    public LibLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibLoaderException(String message) {
        super(message);
    }
}
