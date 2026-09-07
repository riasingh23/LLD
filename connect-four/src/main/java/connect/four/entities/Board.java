package connect.four.entities;

import connect.four.enums.Color;
import java.util.Arrays;

public class Board {
    private Color[][] board;
    private int rows;
    private int columns;
    private int numOfMoves;
    private int[] bottomMostUnfilledRowForColumn;

    public Board(int rows, int columns) {
        this.board = new Color[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.numOfMoves = 0;
        this.bottomMostUnfilledRowForColumn = new int[columns];
        Arrays.fill(this.bottomMostUnfilledRowForColumn, rows - 1);
    }

    public boolean isFull(){
        return numOfMoves == (rows * columns);
    }

    public int placeDisc(int col, Color discColour) {
        if(!canPlace(col)) return -1;
        int row = bottomMostUnfilledRowForColumn[col];
        bottomMostUnfilledRowForColumn[col]--;
        board[row][col] = discColour;
        numOfMoves++;
        return row;

    }

    private boolean canPlace(int col){
        return col<columns && col>=0 && bottomMostUnfilledRowForColumn[col]>=0;
    }

    public boolean checkWinner (int row, int col, Color discColour) {

        int[][] directions = {{0,1}, {1,0}, {1,1}, {-1,1}};
        for(int[] dir : directions) {
            int count = countContinousColor(row, col, discColour, dir[0], dir[1]) + countContinousColor(row, col, discColour, -dir[0], -dir[1]) -1;
            if(count >=4 ) return true;
        }

        return false;

    }

    private int countContinousColor(int row, int col, Color discColour, int rowAdd, int colAdd) {
        int countOfContinousColor = 0;
        for (int r=row, c=col; r>=0 && c>=0 && r<rows && c<columns; r=r+rowAdd, c=c+colAdd) {
            if(board[r][c] == discColour) countOfContinousColor++;
            else break;
        }
        return countOfContinousColor;
    }

}
