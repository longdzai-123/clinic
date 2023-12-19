package vn.oceantech.l3pre.exception;
public class DuplicateException extends ProException {
    public DuplicateException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
