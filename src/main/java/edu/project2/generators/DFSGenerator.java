package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class DFSGenerator implements Generator {
    private final static Random RANDOM = new Random();

    private Maze maze;

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        maze = new Maze(height, width);
        initializeMazeWithWalls(height, width);

        Deque<Cell> cells = new ArrayDeque<>();

        maze.setCell(0, 0, Cell.Type.PASSAGE);
        cells.push(maze.getCell(0, 0));

        while (cells.peek() != null) {
            Cell currCell = cells.peek();
            List<Cell> currCellNeighbors = getNeighbors(currCell);
            if (!currCellNeighbors.isEmpty()) {
                Cell nextCell = selectRandomCell(currCellNeighbors);

                int rowBetweenCell = (currCell.row() + nextCell.row()) / 2;
                int colBetweenCell = (currCell.col() + nextCell.col()) / 2;
                removeWall(rowBetweenCell, colBetweenCell);

                maze.setCell(nextCell.row(), nextCell.col(), nextCell.type());
                cells.push(nextCell);
            } else {
                cells.pop();
            }
        }
        return maze;
    }

    private Cell selectRandomCell(List<Cell> cells) {
        return cells.get(RANDOM.nextInt(cells.size()));
    }

    private void initializeMazeWithWalls(int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze.setCell(i, j, Cell.Type.WALL);
            }
        }
    }

    private void removeWall(int row, int col) {
        maze.setCell(row, col, Cell.Type.PASSAGE);
    }

    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] move : moves) {
            int newRow = cell.row() + 2 * move[0];
            int newCol = cell.col() + 2 * move[1];

            if (newRow >= 0 && newRow < maze.height() && newCol >= 0 && newCol < maze.width()) {
                boolean isUnvisitedCell = maze.getCell(newRow, newCol).type() == Cell.Type.WALL;
                if (isUnvisitedCell) {
                    neighbors.add(new Cell(newRow, newCol, Cell.Type.PASSAGE));
                }
            }
        }
        return neighbors;
    }
}
