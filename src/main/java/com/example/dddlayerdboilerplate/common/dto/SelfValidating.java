package com.example.dddlayerdboilerplate.common.dto;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;
import com.example.dddlayerdboilerplate.common.exceptions.type.BaseException;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * SelfValidating 을 상속받아서 사용하는 클래스는
 * 해당 클래스가 만들어질 때 Validation 을 수행한다.
 * @param <T>
 */
@Slf4j
public abstract class SelfValidating<T> {

    private final Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            log.error("Validation error occurred: {}", violations);
            String errorMessage = violations.stream()
                    .map(violation -> String.format("%s : %s", violation.getPropertyPath(), violation.getMessage()))
                    .reduce((msg1, msg2) -> msg1 + "; " +msg2)
                    .orElse("Validation error occurred");
            throw new IllegalArgumentException(errorMessage);
        }
    }


}
