package roguezero.exception;

public class HttpException extends Exception {

    private final int code;

    public HttpException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
