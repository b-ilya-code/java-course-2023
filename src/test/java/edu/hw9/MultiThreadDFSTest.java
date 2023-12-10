package edu.hw9;

import edu.hw9.task3.MultiThreadDFS;
import org.junit.jupiter.api.Test;
import java.awt.Point;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadDFSTest {

    @Test
    void multiThreadDFSTest() {
        var grid = new int[][] {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        var finder = new MultiThreadDFS(grid, new Point(0,0),
            new Point(3, 3), 4, 4);
        assertTrue(finder.findPathMultiThread());
    }

    @Test
    void noPathMultiThreadDFSTest() {
        var grid = new int[][] {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 0},
        };
        var finder = new MultiThreadDFS(grid, new Point(0,0),
            new Point(3, 3), 4, 4);
        assertFalse(finder.findPathMultiThread());
    }
}
