package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DFSSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (!isValidValues(maze, start, end)) {
            throw new IllegalArgumentException();
        }
        Coordinate indexStart = new Coordinate(start.row() - 1, start.col() - 1);
        Coordinate indexEnd = new Coordinate(end.row() - 1, end.col() - 1);
        if (maze.getCell(indexStart.row(), indexStart.col()).type() == Cell.Type.WALL
            || maze.getCell(indexEnd.row(), indexEnd.col()).type() == Cell.Type.WALL) {
            return new ArrayList<>();
        }

        Coordinate[][] pathArr = new Coordinate[maze.height()][maze.width()];
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        Deque<Coordinate> cells = new ArrayDeque<>();

        visited[indexStart.row()][indexStart.col()] = true;
        cells.addLast(indexStart);
        while (cells.peek() != null) {
            Coordinate curr = cells.pop();
            List<Coordinate> neighbors = getNeighbors(maze, curr, visited);
            for (Coordinate neighbor : neighbors) {
                pathArr[neighbor.row()][neighbor.col()] = curr;
                visited[neighbor.row()][neighbor.col()] = true;
                cells.addLast(neighbor);
            }
        }
        Coordinate pathCord = indexEnd;
        List<Coordinate> path = new ArrayList<>();
        while (pathCord != null) {
            path.add(pathCord);
            pathCord = pathArr[pathCord.row()][pathCord.col()];
        }
        return path;
    }

    private boolean isValidValues(Maze maze, Coordinate start, Coordinate end) {
        if (maze == null || start == null || end == null) {
            return false;
        }
        if (start.col() < 1 || start.row() < 1 || end.row() < 1 || end.col() < 1
            || start.col() > maze.width() || start.row() > maze.height()
            || end.row() > maze.height() || end.col() > maze.width()) {
            return false;
        }
        return true;
    }

    private List<Coordinate> getNeighbors(Maze maze, Coordinate coordinate, boolean[][] visited) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] move : moves) {
            int newRow = coordinate.row() + move[0];
            int newCol = coordinate.col() + move[1];

            if (newRow >= 0 && newRow < maze.height() && newCol >= 0 && newCol < maze.width()
                && maze.getCell(newRow, newCol).type() == Cell.Type.PASSAGE && !visited[newRow][newCol]) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }
}
