package edu.project2;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(final Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }
        return getStringMaze(maze);
    }

    @Override
    public String render(final Maze maze, final List<Coordinate> path) {
        if (maze == null || path == null) {
            throw new IllegalArgumentException();
        }
        Maze mazeWithPath = new Maze(maze.height(), maze.width());
        for (int i = 0; i < mazeWithPath.height(); i++) {
            for (int j = 0; j < mazeWithPath.width(); j++) {
                mazeWithPath.setCell(i, j, maze.grid()[i][j].type());
            }
        }
        for (Coordinate coordinate : path) {
            mazeWithPath.setCell(coordinate.row(), coordinate.col(), Cell.Type.PATH);
        }
        return getStringMaze(mazeWithPath);
    }

    @NotNull private static String getStringMaze(Maze maze) {
        String wallSymb = "⬜";
        String passageSymb = "⬛";
        String pathSymb = "\uD83D\uDFE5";

        StringBuilder mazeStr = new StringBuilder();
        mazeStr.append(wallSymb.repeat(Math.max(0, maze.height() + 2)));
        mazeStr.append('\n');
        for (Cell[] row : maze.grid()) {
            mazeStr.append(wallSymb);
            for (Cell cell : row) {
                if (cell.type() == Cell.Type.WALL) {
                    mazeStr.append(wallSymb);
                } else {
                    mazeStr.append(cell.type() == Cell.Type.PASSAGE ? passageSymb : pathSymb);
                }
            }
            mazeStr.append(wallSymb);
            mazeStr.append('\n');
        }
        mazeStr.append(wallSymb.repeat(Math.max(0, maze.height() + 2)));
        return mazeStr.toString();
    }
}
