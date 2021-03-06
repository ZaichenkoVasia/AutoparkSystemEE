package controller.exception;

public class WrongInputDataRuntimeException extends RuntimeException {

    public WrongInputDataRuntimeException() {
        super();
    }

    public WrongInputDataRuntimeException(String message) {
        super(message);
    }

    public WrongInputDataRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
