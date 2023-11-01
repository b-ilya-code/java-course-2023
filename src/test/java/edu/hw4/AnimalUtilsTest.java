package edu.hw4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.*;
import static edu.hw4.Animal.Sex.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnimalUtilsTest {
    static class AnimalsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                Arguments.of(
                    List.of(
                        new Animal("Cat1", CAT, F, 105, 130, 40_000, true),
                        new Animal("Dog1", DOG, M, 2, 98, 20_000, true),
                        new Animal("Bird1", BIRD, M, 6, 60, 5_000, true),
                        new Animal("Fish1", FISH, M, 1, 15, 1_000, false),
                        new Animal("Spider1", SPIDER, M, 20, 7, 100, true),
                        new Animal("Dog2", DOG, F, 3, 80, 18_000, true),
                        new Animal("Fish2", FISH, M, 2, 20, 3_000, false),
                        new Animal("Cat2", CAT, F, 4, 70, 3_000, true),
                        new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true)
                    )
                )
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void sortByHeightTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Spider1", SPIDER, M, 20, 7, 100, true),
            new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true),
            new Animal("Fish1", FISH, M, 1, 15, 1_000, false),
            new Animal("Fish2", FISH, M, 2, 20, 3_000, false),
            new Animal("Bird1", BIRD, M, 6, 60, 5_000, true),
            new Animal("Cat2", CAT, F, 4, 70, 3_000, true),
            new Animal("Dog2", DOG, F, 3, 80, 18_000, true),
            new Animal("Dog1", DOG, M, 2, 98, 20_000, true),
            new Animal("Cat1", CAT, F, 105, 130, 40_000, true)
        );

        assertThat(AnimalUtils.sortByHeight(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void sortByWeightDescTest(List<Animal> animals) {
        int k = 3;
        var expectedOutput = List.of(
            new Animal("Cat1", CAT, F, 105, 130, 40_000, true),
            new Animal("Dog1", DOG, M, 2, 98, 20_000, true),
            new Animal("Dog2", DOG, F, 3, 80, 18_000, true)
        );

        assertThat(AnimalUtils.sortByWeightDesc(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void countPerTypeTest(List<Animal> animals) {
        var expectedOutput = Map.of(
            CAT, 2L,
            DOG, 2L,
            FISH, 2L,
            SPIDER, 2L,
            BIRD, 1L
        );

        assertThat(AnimalUtils.countPerType(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void longestNamedAnimalTest(List<Animal> animals) {
        var expectedOutput = new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true);

        assertThat(AnimalUtils.longestNamedAnimal(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void largestSexTest(List<Animal> animals) {
        assertThat(AnimalUtils.largestSex(animals)).isEqualTo(M);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void heaviestAnimalPerTypeTest(List<Animal> animals) {
        var expectedOutput = Map.of(
            CAT, new Animal("Cat1", CAT, F, 105, 130, 40_000, true),
            DOG, new Animal("Dog1", DOG, M, 2, 98, 20_000, true),
            BIRD, new Animal("Bird1", BIRD, M, 6, 60, 5_000, true),
            FISH, new Animal("Fish2", FISH, M, 2, 20, 3_000, false),
            SPIDER, new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true)
        );

        assertThat(AnimalUtils.heaviestAnimalPerType(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void kthOldestAnimalTest(List<Animal> animals) {
        int k = 3;
        var expectedOutput = new Animal("Bird1", BIRD, M, 6, 60, 5_000, true);

        assertThat(AnimalUtils.kthOldestAnimal(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void heaviestAnimalAmongThoseSmallerThanKTest(List<Animal> animals) {
        int k = 11;
        Optional<Animal> expectedOutput =
            Optional.of(new Animal("Spider1", SPIDER, M, 20, 7, 100, true));

        assertThat(AnimalUtils.heaviestAnimalAmongThoseSmallerThanK(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void countOfPawsTest(List<Animal> animals) {
        int expectedOutput = 34;

        assertThat(AnimalUtils.countOfPaws(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsWhoseAgeNotEqualCountOfPawsTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Cat1", CAT, F, 105, 130, 40_000, true),
            new Animal("Dog1", DOG, M, 2, 98, 20_000, true),
            new Animal("Bird1", BIRD, M, 6, 60, 5_000, true),
            new Animal("Fish1", FISH, M, 1, 15, 1_000, false),
            new Animal("Spider1", SPIDER, M, 20, 7, 100, true),
            new Animal("Dog2", DOG, F, 3, 80, 18_000, true),
            new Animal("Fish2", FISH, M, 2, 20, 3_000, false),
            new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true)
        );

        assertThat(AnimalUtils.animalsWhoseAgeNotEqualCountOfPaws(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsCanBiteAndBiggerThanMeterTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Cat1", CAT, F, 105, 130, 40_000, true)
        );

        assertThat(AnimalUtils.animalsCanBiteAndBiggerThanMeter(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void countOfAnimalsWhoseWeightBiggerThanHeightTest(List<Animal> animals) {
        var expectedOutput = 9;

        assertThat(AnimalUtils.countOfAnimalsWhoseWeightBiggerThanHeight(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsWhoseNamesLongerThanTwoWordsTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true));

        assertThat(AnimalUtils.animalsWhoseNamesLongerThanTwoWords(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void containsDogBiggerThanKTest(List<Animal> animals) {
        int k = 90;
        var expectedOutput = true;

        assertThat(AnimalUtils.containsDogBiggerThanK(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void totalWeightOfCertainAgeRangePerTypeTest(List<Animal> animals) {
        int k = 2;
        int l = 5;
        var expectedOutput = Map.of(
            CAT, 3_000,
            DOG, 38_000,
            BIRD, 0,
            FISH, 3_000,
            SPIDER, 120
        );

        assertThat(AnimalUtils.totalWeightOfCertainAgeRangePerType(animals, k, l)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void sortedByTypeSexNameTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Cat1", CAT, F, 105, 130, 40_000, true),
            new Animal("Cat2", CAT, F, 4, 70, 3_000, true),
            new Animal("Dog1", DOG, M, 2, 98, 20_000, true),
            new Animal("Dog2", DOG, F, 3, 80, 18_000, true),
            new Animal("Bird1", BIRD, M, 6, 60, 5_000, true),
            new Animal("Fish1", FISH, M, 1, 15, 1_000, false),
            new Animal("Fish2", FISH, M, 2, 20, 3_000, false),
            new Animal("Spider X Y Z", SPIDER, M, 2, 14, 120, true),
            new Animal("Spider1", SPIDER, M, 20, 7, 100, true)
        );

        assertThat(AnimalUtils.sortedByTypeSexName(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void doSpidersBiteMoreThanDogsTest(List<Animal> animals) {
        var expectedOutput = false;

        assertThat(AnimalUtils.doSpidersBiteMoreThanDogs(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void heaviestFishTest(List<Animal> animals) {
        var animals2 = List.of(
            new Animal("Lollipop", FISH, F, 1, 20, 1_234, false));
        var expectedOutput = new Animal("Fish2", FISH, M, 2, 20, 3_000, false);

        assertThat(AnimalUtils.heaviestFish(List.of(animals, animals2))).isEqualTo(expectedOutput);
    }

    @Test
    public void readableAnimalsEntriesWithMistakesTest() {
        List<Animal> animals = List.of(new Animal("", SPIDER, M, -2, 14, 120, true));

        var expectedOutput = Map.of(
            "", "Invalid name, Invalid age"
        );

        assertThat(AnimalUtils.readableAnimalsEntriesWithMistakes(animals)).isEqualTo(expectedOutput);
    }
}
