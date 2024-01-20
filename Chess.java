public class Chess extends Board{
    public Chess(){
        super(8);
        for (int i = 0; i < board.length; i++) {
            this.board[i][1]=11;
            this.board[i][6]=21;
        }
        this.board[0][0]=12;
        this.board[7][0]=12;
        this.board[0][7]=22;
        this.board[7][7]=22;

        this.board[1][0]=13;
        this.board[6][0]=13;
        this.board[1][7]=23;
        this.board[6][7]=23;

        this.board[2][0]=14;
        this.board[5][0]=14;
        this.board[2][7]=24;
        this.board[5][7]=24;

        this.board[3][0]=15;
        this.board[3][7]=25;

        this.board[4][0]=16;
        this.board[4][7]=26;
    }

    public void boardPrinter(){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                switch(this.board[j][i]%10){
                    case 6:
                        System.out.print("K ");
                        break;
                    case 5:
                        System.out.print("Q ");
                        break;
                    case 4:
                        System.out.print("B ");
                        break;
                    case 3:
                        System.out.print("H ");
                        break;
                    case 2:
                        System.out.print("R ");
                        break;
                    case 1:
                        System.out.print("P ");
                        break;
                    case 0:
                        System.out.print("0 ");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }
}