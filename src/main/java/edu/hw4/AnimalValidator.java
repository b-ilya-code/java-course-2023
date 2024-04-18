package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class AnimalValidator {
    private AnimalValidator() {
    }

    @NotNull
    public static List<ValidationError> getErrors(Animal animal) {
        List<ValidationError> errors = new ArrayList<>();

        if (animal.type() == null) {
            errors.add(new ValidationError(ValidationError.ErrorType.TYPE_IS_NULL));
        }

        if (animal.name() == null) {
            errors.add(new ValidationError(ValidationError.ErrorType.NAME_IS_NULL));
        } else if (animal.name().isBlank()) {
            errors.add(new ValidationError(ValidationError.ErrorType.NAME_IS_EMPTY));
        }

        if (animal.sex() == null) {
            errors.add(new ValidationError(ValidationError.ErrorType.SEX_IS_NULL));
        }
        if (animal.age() < 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.AGE_IS_NEGATIVE));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.HEIGHT_IS_NEGATIVE));
        }
        if (animal.weight() < 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.WEIGHT_IS_NEGATIVE));
        }

        return errors;
    }
}
