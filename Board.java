public class Board{

    int[][] board;

    Board(int boardSize){
        this.board=new int[boardSize][boardSize];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[j][i]=0;
            }
        }
    }

    public void boardPrinter(){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[j][i]+" ");
            }
            System.out.println();
        }
    }
}