package edu.hw1;

public class Task8 {
    private Task8() {
    }

    private static final int CHESS_BOARD_SIZE = 8;

    public static boolean knightBoardCapture(int[][] board) {
        // go through all the squares occupied by a horse
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                if (board[i][j] == 1) {
                    // check whether other knight can capture this square
                    if (isAttackedByKnight(i, j, board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static final int[] KNIGHT_MOVE_UP_LEFT = {-1, 2};
    private static final int[] KNIGHT_MOVE_UP_RIGHT = {1, 2};
    private static final int[] KNIGHT_MOVE_DOWN_LEFT = {-1, -2};
    private static final int[] KNIGHT_MOVE_DOWN_RIGHT = {1, -2};
    private static final int[] KNIGHT_MOVE_LEFT_UP = {-2, 1};
    private static final int[] KNIGHT_MOVE_LEFT_DOWN = {-2, -1};
    private static final int[] KNIGHT_MOVE_RIGHT_UP = {2, 1};
    private static final int[] KNIGHT_MOVE_RIGHT_DOWN = {2, -1};

    private static boolean isAttackedByKnight(int x, int y, int[][] board) {
        // check all possible moves of the knight
        final int[][] KNIGHT_MOVES = {KNIGHT_MOVE_UP_LEFT,
                                    KNIGHT_MOVE_UP_RIGHT,
                                    KNIGHT_MOVE_DOWN_LEFT,
                                    KNIGHT_MOVE_DOWN_RIGHT,
                                    KNIGHT_MOVE_LEFT_UP,
                                    KNIGHT_MOVE_LEFT_DOWN,
                                    KNIGHT_MOVE_RIGHT_UP,
                                    KNIGHT_MOVE_RIGHT_DOWN};

        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            int nx = x + KNIGHT_MOVES[i][0];
            int ny = y + KNIGHT_MOVES[i][1];

            if (nx >= 0 && nx < CHESS_BOARD_SIZE && ny >= 0 && ny < CHESS_BOARD_SIZE) {
                // check if the square is occupied by other knight
                if (board[nx][ny] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
