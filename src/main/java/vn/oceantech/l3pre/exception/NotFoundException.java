package vn.oceantech.l3pre.exception;

public class NotFoundException extends ProException{
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
