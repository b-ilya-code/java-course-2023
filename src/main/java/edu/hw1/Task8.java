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

    @SuppressWarnings("MagicNumber")
    private static boolean isAttackedByKnight(int x, int y, int[][] board) {
        // check all possible moves of the knight
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

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
