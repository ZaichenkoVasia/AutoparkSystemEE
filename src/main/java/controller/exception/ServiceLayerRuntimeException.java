package controller.exception;

public class ServiceLayerRuntimeException extends RuntimeException {

    public ServiceLayerRuntimeException() {
        super();
    }

    public ServiceLayerRuntimeException(String message) {
        super(message);
    }

    public ServiceLayerRuntimeException(Throwable cause) {
        super(cause);
    }

    public ServiceLayerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
