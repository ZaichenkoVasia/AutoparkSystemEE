package project.model.exception;

public class UserIsRegisteredRuntimeException extends RuntimeException {
    public UserIsRegisteredRuntimeException() {
    }

    public UserIsRegisteredRuntimeException(String s) {
        super(s);
    }

    public UserIsRegisteredRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
