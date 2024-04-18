package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

public class AnimalUtils {
    private AnimalUtils() {
    }

    public static List<Animal> sortByHeight(List<Animal> animals) {

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortByWeightDesc(List<Animal> animals, int limit) {

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(limit)
            .toList();
    }

    public static Map<Animal.Type, Long> countPerType(List<Animal> animals) {
        return animals.stream()
            .collect(groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal longestNamedAnimal(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex largestSex(List<Animal> animals) {
        Map<Animal.Sex, Long> genderCounts = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        long maleCount = genderCounts.getOrDefault(Animal.Sex.M, 0L);
        long femaleCount = genderCounts.getOrDefault(Animal.Sex.F, 0L);

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        } else if (femaleCount > maleCount) {
            return Animal.Sex.F;
        } else {
            return null;
        }
    }

    public static Map<Animal.Type, Animal> heaviestAnimalPerType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                    optional -> optional.orElse(null)
                )
            ));
    }

    public static Animal kthOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public static Optional<Animal> heaviestAnimalAmongThoseSmallerThanMaxHeight(List<Animal> animals, int maxHeight) {
        return animals.stream()
            .filter(animal -> (animal.height() < maxHeight))
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countOfPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> animalsWhoseAgeNotEqualCountOfPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    public static List<Animal> animalsCanBiteAndBiggerThanMeter(List<Animal> animals) {
        final int centimetersInMeter = 100;
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > centimetersInMeter)
            .toList();
    }

    public static Integer countOfAnimalsWhoseWeightBiggerThanHeight(List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    public static List<Animal> animalsWhoseNamesLongerThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean containsDogBiggerThanMaxHeight(List<Animal> animals, int maxHeight) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > maxHeight);
    }

    public static Map<Animal.Type, Integer> totalWeightOfCertainAgeRangePerType(
        List<Animal> animals,
        int minAge,
        int maxAge
    ) {
        Map<Animal.Type, Integer> result = animals.stream()
            .filter(animal -> animal.age() >= minAge && animal.age() <= maxAge)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));

        for (Animal.Type value : Animal.Type.values()) {
            result.putIfAbsent(value, 0);
        }
        return result;
    }

    public static List<Animal> sortedByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator
                    .comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean doSpidersBiteMoreThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count()
            >
            animals.stream()
                .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
                .count();
    }

    public static Animal heaviestFish(List<List<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, List<ValidationError>> animalsEntriesWithMistakes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidator::getErrors
            ));
    }

    public static Map<String, String> readableAnimalsEntriesWithMistakes(List<Animal> animals) {
        Map<String, List<ValidationError>> entries = animalsEntriesWithMistakes(animals);
        Map<String, String> result = new HashMap<>();
        entries.forEach(
            (name, errorSet) ->
                result.put(
                    name,
                    errorSet.stream()
                        .map(ValidationError::toString)
                        .collect(Collectors.joining(", "))
                ));

        return result;
    }
}
