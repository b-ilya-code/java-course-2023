package edu.hw5;

import edu.hw5.task3.ParseUtils;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task3Test {
    @Test
    public void testParseDate() {
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), ParseUtils.parseDate("2020-10-10"));
        assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), ParseUtils.parseDate("2020-12-2"));
        assertEquals(Optional.of(LocalDate.of(1976, 3, 1)), ParseUtils.parseDate("1/3/1976"));
        assertEquals(Optional.of(LocalDate.of(2020, 3, 1)), ParseUtils.parseDate("1/3/20"));
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), ParseUtils.parseDate("tomorrow"));
        assertEquals(Optional.of(LocalDate.now()), ParseUtils.parseDate("today"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), ParseUtils.parseDate("yesterday"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), ParseUtils.parseDate("1 day ago"));
        assertEquals(Optional.of(LocalDate.now().minusDays(2234)), ParseUtils.parseDate("2234 days ago"));
        assertFalse(ParseUtils.parseDate("invalid").isPresent());
        assertFalse(ParseUtils.parseDate("2020-32-01").isPresent());
        assertFalse(ParseUtils.parseDate("2020-01-32").isPresent());
        assertFalse(ParseUtils.parseDate("2020/01/01").isPresent());
        assertFalse(ParseUtils.parseDate("2020-01-01T12:00:00").isPresent());
    }
}
