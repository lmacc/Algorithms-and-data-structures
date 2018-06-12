/*
    Leslie McCarthy CS4
 */

public class Bishop {
    private static final int BISHOPS = 4;
    private int size;
    private int[][] chessBoard;
    private int numSol = 0;

    private Bishop(int size) {
        this.size = size;
        //initialise the chess board with the size being passed into constructor.
        this.chessBoard = new int[size][size];
    }

    private int getNumSol() {
        return numSol;
    }

    private boolean solve(int bishops, int rowIndex, int colIndex) {
        boolean res = false;
        //if we have tried all of the bishops, we have a solution!!
        if (bishops == 0) {
            //Increment the solution found and print solution
            this.numSol++;
            printBishop();
            return true;
        }

        for (; rowIndex < size; rowIndex++) {
            for(; colIndex < size; colIndex++){
                //if we can place a bishop @ this row and column, we mark this with a 1. We call isValid method to check for a valid move.
                if (isValid(rowIndex, colIndex)) {
                    chessBoard[rowIndex][colIndex] = 1;
                    //if res == true solution found, check recursively for all bishops
                    if(solve(bishops - 1, rowIndex, colIndex)){
                        res = true;
                    };
                    // Else remove bishop and Backtrack
                    chessBoard[rowIndex][colIndex] = 0;
                }
            }
            //Start at first column.
            colIndex = 0;
        }
        return res;
    }

    private boolean isValid(int rowIndex, int colIndex) {

        //check diagonals for bishop only
        for (int i = rowIndex, j = colIndex; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 1) {
                //There is a bishop on diagonal top left <-> bottom right, so return false
                return false;
            }
        }

        for (int i = rowIndex, j = colIndex; i < size && j >= 0; i++, j--) {
            //There is a bishop on diagonal bottom left <--> top right, so return false
            if (chessBoard[i][j] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = colIndex; j < size && i >= 0; j++, i--) {
            //There is a bishop on diagonal top right <--> bottom left, so return false
            if (chessBoard[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private void printBishop() {
        System.out.println("Solution " + getNumSol());
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (chessBoard[i][j] == 1) {
                    System.out.print(" B ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //I am setting size of board equal to bishops.
        Bishop bishop = new Bishop(BISHOPS);
        boolean result = bishop.solve(BISHOPS, 0, 0);

        //if we have a solution we print this for every solution found!
        if (result) {
            System.out.println("\nSolutions found " + bishop.getNumSol());
         /*
          * otherwise there is no solution for given size. This algorithm is exponential, as we increase size of board
          * the time to solve will also increase so it is not very efficient!
         */
        } else {
            System.out.println("There is no solution");
        }
    }
}

