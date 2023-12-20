package vn.oceantech.l3pre.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.exception.DuplicateException;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.repository.UserRepo;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepo userRepo;

    public void checkDuplicateEmail(String email) {
        boolean result = userRepo.existsByEmail(email);
        if (result) {
            throw new DuplicateException(ErrorMessage.DUPLICATE_EMAIL);
        }
    }
}
