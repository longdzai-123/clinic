package vn.oceantech.l3pre.exception;

import lombok.Getter;

@Getter
public class ProException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public ProException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
