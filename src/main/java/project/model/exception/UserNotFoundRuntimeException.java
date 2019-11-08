package project.model.exception;

public class UserNotFoundRuntimeException extends RuntimeException {
    public UserNotFoundRuntimeException() {
    }

    public UserNotFoundRuntimeException(String s) {
        super(s);
    }

    public UserNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
