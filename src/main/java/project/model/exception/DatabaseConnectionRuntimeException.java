package project.model.exception;

public class DatabaseConnectionRuntimeException extends RuntimeException {
    public DatabaseConnectionRuntimeException() {
    }

    public DatabaseConnectionRuntimeException(String message) {
        super(message);
    }

    public DatabaseConnectionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
