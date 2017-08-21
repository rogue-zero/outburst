package roguezero.exception;

import org.eclipse.jetty.http.HttpStatus;

public class ValidationException extends HttpException {

    private static final long serialVersionUID = 4250392937919283127L;

    public ValidationException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST_400, message, cause);
    }

    public ValidationException(String message) {
        this(message, null);
    }

}
