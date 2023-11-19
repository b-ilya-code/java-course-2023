package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(final int height, final int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
    }

    public void setCell(int height, int width, Cell.Type type) {
        grid[height][width] = new Cell(height, width, type);
    }

    public Cell getCell(int height, int width) {
        return grid[height][width];
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Cell[][] grid() {
        return grid;
    }
}
