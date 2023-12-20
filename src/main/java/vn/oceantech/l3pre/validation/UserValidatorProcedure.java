package vn.oceantech.l3pre.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.oceantech.l3pre.exception.DuplicateException;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class UserValidatorProcedure {
    private final EntityManager entityManager;

    public void existsUser(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("exists_user_by_id")
                .registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT)
                .setParameter("id", id);
        Integer result = (Integer) query.getOutputParameterValue("result");
        if (result != 1) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
    }

    public void checkDuplicateEmail(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("exists_user_by_email")
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT)
                .setParameter("email", email);
        Integer result = (Integer) query.getOutputParameterValue("result");
        if (result == 1) {
            throw new DuplicateException(ErrorMessage.DUPLICATE_EMAIL);
        }
    }
}
