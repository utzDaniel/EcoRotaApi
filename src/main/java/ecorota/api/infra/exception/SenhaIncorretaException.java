package ecorota.api.infra.exception;

public class SenhaIncorretaException extends RuntimeException {

    public SenhaIncorretaException(String message) {
        super(message);
    }
}
