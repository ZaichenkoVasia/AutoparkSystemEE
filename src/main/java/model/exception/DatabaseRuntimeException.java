package model.exception;

public class DatabaseRuntimeException extends RuntimeException {

    public DatabaseRuntimeException() {
        super();
    }

    public DatabaseRuntimeException(String message) {
        super(message);
    }

    public DatabaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
