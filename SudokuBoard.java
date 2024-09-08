package Main;

public class SudokuBoard {
    int[][] board;
    final int INNER_DIMENSION = 3;

    public SudokuBoard(int[][] board) 
    {
        this.board = board;
    }

    public void print() 
    {
        for (int row = 0; row < board.length; row++) {
            // Empty space for 3x3 row
            if (row % INNER_DIMENSION == 0) 
            {
            	System.out.println();
            }            	
            for (int column = 0; column < board.length; column++) {
                // Empty space for 3x3 column
                if (column % INNER_DIMENSION == 0) 
                	{
                		System.out.print("  ");
                	}
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public boolean solve(int row, int column) {
        //Check do we reached bottom of the board
        if (row == board.length) 
        {
            column++;
            
            if (column == board.length) 
            	return true;
               
            else row = 0; // for next column first index 0
        }

        if (board[row][column] != 0) 
        	return solve(row + 1, column);

        for (int num = 1; num <= board.length; num++) 
        {
            //Check is it a valid cell // 3,0,1
            if (isValid(row, column, num)) 
            {
                board[row][column] = num;
                //check if the next sub problem is solved
                if (solve(row + 1, column)) return true;
                //if the above sub problem is not solved BACKTRACK
                board[row][column] = 0;
            }
        }
        return false;
    }

    public boolean isValid(int row, int column, int num) // 3,0,1
    {
        //Does num already in row
        for (int i = 0; i < board.length; i++) 
        {
            if (board[row][i] == num) 
            	return false;
        }

        //Does num already in column
        for (int i = 0; i < board.length; i++) 
        {
            if (board[i][column] == num) return false;
        }

        // Check if num is in the 3x3 subgrid
        int startRow = (row / INNER_DIMENSION) * INNER_DIMENSION; // 3/3*3 = 3
        int startCol = (column / INNER_DIMENSION) * INNER_DIMENSION; // 0/3*3 =0

        for (int i = startRow; i < startRow + INNER_DIMENSION; i++) // 3 to 9
        {
            for (int j = startCol; j < startCol + INNER_DIMENSION; j++) // o to 3
            {
                if (board[i][j] == num) return false;
            }
        }

        // If num is not found in the row, column, or 3x3 subgrid, it is valid
        return true;
    }
}
