package softwire.sudoku;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Sudoku {

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        int[][] goodInitialBoard = {
                {0, 0, 0, 0, 0, 2, 1, 0, 0},
                {0, 0, 4, 0, 0, 8, 7, 0, 0},
                {0, 2, 0, 3, 0, 0, 9, 0, 0},
                {6, 0, 2, 0, 0, 3, 0, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 0, 6, 0, 0, 3, 0, 1},
                {0, 0, 3, 0, 0, 5, 0, 8, 0},
                {0, 0, 8, 2, 0, 0, 5, 0, 0},
                {0, 0, 9, 7, 0, 0, 0, 0, 0}
        };
        int[][] badInitialBoard = {
                {0, 8, 5, 9, 6, 2, 1, 3, 4},
                {0, 0, 4, 0, 0, 8, 7, 0, 0},
                {0, 2, 0, 3, 0, 0, 9, 0, 0},
                {6, 0, 2, 0, 0, 3, 0, 4, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 0, 6, 0, 0, 3, 0, 1},
                {0, 0, 3, 0, 0, 5, 0, 8, 0},
                {0, 0, 8, 2, 0, 0, 5, 0, 0},
                {0, 0, 9, 7, 0, 0, 0, 0, 0}
        };

        int[][] emptyInitialBoard = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        // Initialising
        int[][] initialBoard = goodInitialBoard;
        System.out.println("Solving board:");
        printBoard(initialBoard);

        // Set up the stack
        Deque<int[][]> stack = new ArrayDeque<>();
        stack.push(initialBoard);

        int visitCount = 0;
        while (!stack.isEmpty()) {
            int[][] board = stack.pop();
            Slot slot = originalGetEmptySlot(board);

            if (slot == null) {
                System.out.printf("Solved! We visited %d nodes%n", visitCount);
                printBoard(board);
                return;
            }

            for (int guess = 1; guess <= 9; guess++) {
                if (isValidInSlot(guess, slot, board)) {
                    stack.push(updateBoard(guess, slot, board));
                    visitCount++;
                }
            }
        }
        System.out.printf("No Solution! We visited %d nodes%n", visitCount);
    }



    private static Slot newGetEmptySlot(int[][] board) throws ExecutionControl.NotImplementedException {

        int bestEmptyCount = 0;
        Slot selectedSlot = null;

        for (int squareX = 0; squareX < 3; squareX++) {
            for (int squareY = 0; squareY < 3; squareY++) {
                int emptyCount = 0;
                Slot availableSlot = null;
                for (int row = squareX * 3; row < (squareX + 1) * 3; row++) {
                    for (int col = squareY * 3; col < (squareY + 1) * 3; col++) {
                        if (board[row][col] == 0) {
                            emptyCount++;
                            availableSlot = new Slot(row, col);
                        }
                    }
                }
                if ( emptyCount > bestEmptyCount){
                    selectedSlot = availableSlot;
                }
            }
        }

        return selectedSlot;
    }

    private static Slot originalGetEmptySlot(int[][] board) throws ExecutionControl.NotImplementedException {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    return new Slot(row, col);

                }
            }
        }
        return null;
    }
    private static boolean isValidInSlot(int guess, Slot slot, int[][] board) throws ExecutionControl.NotImplementedException {
        return isValidInRow(slot.row, guess, board) &&
                isValidInCol(slot.col, guess, board) &&
                isValidInSquare(slot, guess, board);
    }

    private static boolean isValidInRow(int row, int guess, int[][] board) throws ExecutionControl.NotImplementedException {
        for (int col = 0; col < board[row].length; col++){
            if ( board[row][col] == guess){
                return false;
            }
        }
        return true;
    }

    private static boolean isValidInCol(int col, int guess, int[][] board) throws ExecutionControl.NotImplementedException {
        for (int row = 0; row < board.length; row++){
           if ( board[row][col] == guess){
                return false;
            }
        }
        return true;
    }

    private static boolean isValidInSquare(Slot slot, int guess, int[][] board) throws ExecutionControl.NotImplementedException {
        int squareX = slot.row / 3;
        int squareY = slot.col / 3;

        for (int row = squareX * 3; row < (squareX + 1) * 3; row++) {
            for (int col = squareY * 3; col < (squareY + 1) * 3; col++) {
                if (board[row][col] == guess) {
                    return false;
                }
            }
        }
        return true;
    }



    private static int[][] updateBoard(int guess, Slot slot, int[][] board) {
        int[][] clonedBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        if ( clonedBoard[slot.row][slot.col] != 0){
            throw new RuntimeException("already occupied " + slot);
        }
        clonedBoard[slot.row][slot.col] =  guess;
        return clonedBoard;
    }

    private static void printBoard(int[][] board)
    {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 0 ? "  " : cell + " ");
            }
            System.out.println();
        }
    }

    private static class Slot {
        int row;
        int col;

        Slot(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
