package edu.hw4;

import edu.hw4.errors.ValidationError;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
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

    public static List<Animal> sortByWeightDesc(List<Animal> animals, int k) {

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
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
        return animals.stream()
            .collect(groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.<Animal.Sex, Long>comparingByValue())
            .orElseThrow()
            .getKey();
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
            .orElseThrow();
    }

    public static Optional<Animal> heaviestAnimalAmongThoseSmallerThanK(List<Animal> animals, int maxHeight) {
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

    public static Boolean containsDogBiggerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> totalWeightOfCertainAgeRangePerType(List<Animal> animals, int k, int l) {
        return Arrays.stream(Animal.Type.values())
            .map(type ->
                new AbstractMap.SimpleEntry<>(
                    type,
                    animals.stream()
                        .filter(animal -> animal.type() == type)
                        .filter(animal -> animal.age() >= k && animal.age() < l)
                        .map(Animal::weight)
                        .reduce(0, Integer::sum)
                ))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
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
        Map<String, List<ValidationError>> errors = animalsEntriesWithMistakes(animals);

        return errors.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(Throwable::getMessage)
                    .collect(Collectors.joining(", "))
            ));
    }
}
