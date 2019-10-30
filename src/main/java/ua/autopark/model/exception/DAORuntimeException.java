package ua.autopark.model.exception;

public class DAORuntimeException extends RuntimeException{

    public DAORuntimeException() {
        super();
    }

    public DAORuntimeException(String message) {
        super(message);
    }

    public DAORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
