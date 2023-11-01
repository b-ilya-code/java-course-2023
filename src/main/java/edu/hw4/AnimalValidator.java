package edu.hw4;

import edu.hw4.errors.AgeError;
import edu.hw4.errors.HeightError;
import edu.hw4.errors.IsNullError;
import edu.hw4.errors.NameError;
import edu.hw4.errors.SexError;
import edu.hw4.errors.TypeError;
import edu.hw4.errors.ValidationError;
import edu.hw4.errors.WeightError;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class AnimalValidator {
    private AnimalValidator() {
    }

    @NotNull
    public static List<ValidationError> getErrors(Animal animal) {
        List<ValidationError> errors = new ArrayList<>();
        if (animal == null) {
            return List.of(new IsNullError("No animal provided"));
        }

        if (!nameIsValid(animal.name())) {
            errors.add(new NameError("Invalid name"));
        }
        if (!typeIsValid(animal.type())) {
            errors.add(new TypeError("Invalid type"));
        }
        if (!sexIsValid(animal.sex())) {
            errors.add(new SexError("Invalid sex"));
        }
        if (!ageIsValid(animal.age())) {
            errors.add(new AgeError("Invalid age"));
        }
        if (!heightIsValid(animal.height())) {
            errors.add(new HeightError("Invalid height"));
        }
        if (!weightIsValid(animal.weight())) {
            errors.add(new WeightError("Invalid weight"));
        }

        return errors;
    }

    private static boolean nameIsValid(String name) {
        return name != null && !name.isEmpty();
    }

    private static boolean typeIsValid(Animal.Type type) {
        return type != null;
    }

    private static boolean sexIsValid(Animal.Sex sex) {
        return sex != null;
    }

    private static boolean ageIsValid(int age) {
        return age > 0;
    }

    private static boolean heightIsValid(int height) {
        return height > 0;
    }

    private static boolean weightIsValid(int weight) {
        return weight > 0;
    }
}
