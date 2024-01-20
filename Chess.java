import java.util.Scanner;

public class Chess extends Board {
    public Chess() {
        super(8);
        for (int i = 0; i < board.length; i++) {
            this.board[i][1] = 11;
            this.board[i][6] = 21;
        }
        this.board[0][0] = 12;
        this.board[7][0] = 12;
        this.board[0][7] = 22;
        this.board[7][7] = 22;

        this.board[1][0] = 13;
        this.board[6][0] = 13;
        this.board[1][7] = 23;
        this.board[6][7] = 23;

        this.board[2][0] = 14;
        this.board[5][0] = 14;
        this.board[2][7] = 24;
        this.board[5][7] = 24;

        this.board[3][0] = 15;
        this.board[3][7] = 25;

        this.board[4][0] = 16;
        this.board[4][7] = 26;
    }

    public void movePiece(int xInitial, int yInitial, int xFinal, int yFinal) {
        this.board[xFinal][yFinal] = this.board[xInitial][yInitial];
        this.board[xInitial][yInitial] = 0;
    }

    public void playPiece(String xInitialLetter, int yInitial, String xFinalLetter, int yFinal) {
        int xInitial = letterToNumberCoordinateConverter(xInitialLetter);
        yInitial--;
        int xFinal = letterToNumberCoordinateConverter(xFinalLetter);
        yFinal--;
        if (this.board[xInitial][yInitial] == 0) {
            System.out.println("Invalid initial position:\nThere is nothing to play in that position.");
            return;
        } else if (this.board[xInitial][yInitial] / 10 == this.board[xFinal][yFinal] / 10) {
            System.out.println("Invalid final position:\nYou cannot capture your own pieces.");
            return;
        }
        switch (this.board[xInitial][yInitial] / 10) {
            case 1:
                // pawn gets played here
                if (this.board[xInitial][yInitial] % 10 == 1) {
                    if (yInitial == 1) {
                        boolean yFinalIsNotLegal = true;
                        for (int i = yInitial; i < yInitial + 2; i++) {
                            if (i == yFinal) {
                                yFinalIsNotLegal = false;
                            }
                        }
                        if (yFinalIsNotLegal) {
                            System.out.println("Illegal final position.");
                            return;
                        } else {
                            this.movePiece(xInitial, yInitial, xFinal, yFinal);
                        }
                    } else if (yFinal == 7 && yInitial + 1 == yFinal) {
                        // changing the piece type as it needs to be traded now
                        this.movePiece(xInitial, yInitial, xFinal, yFinal);
                        Scanner s1 = new Scanner(System.in);
                        System.out
                                .print("Enter the type of piece you would like to trade this pawn for\n(R, H, B, Q):");
                        String input = s1.nextLine();
                        switch (input) {
                            case "R":
                                this.board[xFinal][yFinal] = 12;
                                break;
                            case "H":
                                this.board[xFinal][yFinal] = 13;
                                break;
                            case "B":
                                this.board[xFinal][yFinal] = 14;
                                break;
                            case "Q":
                                this.board[xFinal][yFinal] = 15;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                s1.close();
                                return;
                        }
                        s1.close();
                    } else {
                        if (yInitial + 1 != yFinal) {
                            System.out.println("Illegal final position.");
                            return;
                        } else {
                            this.movePiece(xInitial, yInitial, xFinal, yFinal);
                        }
                    }
                } else {
                    if (yInitial == 6) {
                        boolean yFinalIsNotLegal = true;
                        for (int i = yInitial - 2; i < yInitial; i++) {
                            if (i == yFinal) {
                                yFinalIsNotLegal = false;
                            }
                        }
                        if (yFinalIsNotLegal) {
                            System.out.println("Illegal final position.");
                            return;
                        } else {
                            this.movePiece(xInitial, yInitial, xFinal, yFinal);
                        }
                    } else if (yFinal == 0 && yInitial - 1 == yFinal) {
                        // changing the piece type as it needs to be traded now
                        this.movePiece(xInitial, yInitial, xFinal, yFinal);
                        Scanner s1 = new Scanner(System.in);
                        System.out
                                .print("Enter the type of piece you would like to trade this pawn for\n(R, H, B, Q):");
                        String input = s1.nextLine();
                        switch (input) {
                            case "R":
                                this.board[xFinal][yFinal] = 12;
                                break;
                            case "H":
                                this.board[xFinal][yFinal] = 13;
                                break;
                            case "B":
                                this.board[xFinal][yFinal] = 14;
                                break;
                            case "Q":
                                this.board[xFinal][yFinal] = 15;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                s1.close();
                                return;
                        }
                        s1.close();
                    } else {
                        if (yInitial - 1 != yFinal) {
                            System.out.println("Illegal final position.");
                            return;
                        } else {
                            this.movePiece(xInitial, yInitial, xFinal, yFinal);
                        }
                    }
                }
                break;
            case 2:
                // rook here
                if(this.crossPatternChecker(xInitial, yInitial, xFinal, yFinal)) {
                    this.movePiece(xInitial, yInitial, xFinal, yFinal);
                } else {
                    System.out.println("Invalid move.");
                    return;
                }
                break;
            case 3:
                // knight (H/Horse) here

                break;
            case 4:
                // bishop here
                if(this.xPatternChecker(xInitial, yInitial, xFinal, yFinal)){
                    this.movePiece(xInitial, yInitial, xFinal, yFinal);
                } else{
                    System.out.println("Invalid move.");
                    return;
                }
                break;
            case 5:
                // queen here
                if(this.crossPatternChecker(xInitial, yInitial, xFinal, yFinal)||this.xPatternChecker(xInitial, yInitial, xFinal, yFinal)){
                    this.movePiece(xInitial, yInitial, xFinal, yFinal);
                } else{
                    System.out.println("Invalid move.");
                    return;
                }
                break;
            case 6:
                // king here
                
                break;
            default:
                System.out.println("Invalid chess piece type given at X: " + xInitial + "(" + xInitialLetter + ")"
                        + " Y: " + yInitial + "\nin the playPiece function.");
                return;
        }
    }

    public boolean xPatternChecker(int xInitial, int yInitial, int xFinal, int yFinal) {
        boolean isValidFinalPosition=true;
        if(Math.abs(xInitial-xFinal) == Math.abs(yInitial-yFinal)) {
            //there are 4 diagonal directions the piece could move in the x pattern. You need to think of all 4 of them.
            if(xInitial<xFinal&&yInitial<yFinal){
                //the piece is moving diagonally right and down
                for(int i=xInitial+1, j=yInitial+1 ; i<xFinal && j<yFinal ; i++, j++){
                    if(this.board[i][j] != 0){
                        isValidFinalPosition=false;
                    }
                }
            } else if(xInitial<xFinal&&yInitial>yFinal){
                //the piece is moving diagonally right and up
                for(int i=xInitial+1, j=yFinal-1 ; i<xFinal && j>yInitial ; i++, j--){
                    if(this.board[i][j] != 0){
                        isValidFinalPosition=false;
                    }
                }
            } else if(xInitial>xFinal&&yInitial<yFinal){
                //the piece is moving diagonally left and down
                for(int i=xFinal-1, j=yInitial+1 ; i>xInitial && j<yFinal ; i--, j++){
                    if(this.board[i][j] != 0){
                        isValidFinalPosition=false;
                    }
                }
            } else if(xInitial>xFinal&&yInitial>yFinal){
                //the piece is moving diagonally left and up
                for(int i=xFinal-1, j=yFinal-1 ; i>xInitial && j>yInitial ; i--, j--){
                    if(this.board[i][j] != 0){
                        isValidFinalPosition=false;
                    }
                }
            }
        } else{
            isValidFinalPosition=false;
        }
        if(this.board[xFinal][yFinal]/10 == this.board[xInitial][yInitial]/10){
            isValidFinalPosition=false;
        }
        return isValidFinalPosition;
    }

    public boolean crossPatternChecker(int xInitial, int yInitial, int xFinal, int yFinal) {
        boolean isValidFinalPosition = false;
        boolean isMovingOnX=false;
        boolean isMovingOnY=false;
        for (int i = Math.min(xInitial, xFinal); i <= Math.max(xInitial, xFinal); i++) {
            if (this.board[i][yInitial] == this.board[xFinal][yFinal] && this.board[xFinal][yFinal] / 10 != this.board[xInitial][yInitial] / 10) {
                isValidFinalPosition = true;
                isMovingOnX=true;
            }
        }
        for (int i = Math.min(yInitial, yFinal); i <= Math.max(yInitial, yFinal); i++) {
            if (this.board[xInitial][i] == this.board[xFinal][yFinal] && this.board[xFinal][yFinal] / 10 != this.board[xInitial][yInitial] / 10) {
                isValidFinalPosition = true;
                isMovingOnY=true;
            }
        }
        if(isMovingOnX){
            for (int i = Math.min(xInitial, xFinal) + 1; i < Math.max(xInitial, xFinal); i++) {
                if (this.board[i][yInitial] != 0) {
                    isValidFinalPosition = false;
                }
            }
        }
        if(isMovingOnY){
            for (int i = Math.min(yInitial, yFinal) + 1; i < Math.max(yInitial, yFinal); i++) {
                if (this.board[xInitial][i] != 0) {
                    isValidFinalPosition = false;
                }
            }
        }
        return isValidFinalPosition;
    }

    public static int letterToNumberCoordinateConverter(String letter) {
        String s = letter.toUpperCase();
        switch (s) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            default:
                System.out.println("Error: Invalid input at the letterToNumberCoordinateConverter function.");
                return 8;
        }
    }

    public void boardPrinter() {
        System.out.println("    A B C D E F G H\n");
        for (int i = 0; i < this.board.length; i++) {
            System.out.print(i + 1 + "   ");
            for (int j = 0; j < this.board[i].length; j++) {
                switch (this.board[j][i] / 10) {
                    case 0:
                        System.out.print("0 ");
                        break;
                    case 1:
                        switch (this.board[j][i] % 10) {
                            case 6:
                                System.out.print(ANSI_RED + "K " + ANSI_RESET);
                                break;
                            case 5:
                                System.out.print(ANSI_RED + "Q " + ANSI_RESET);
                                break;
                            case 4:
                                System.out.print(ANSI_RED + "B " + ANSI_RESET);
                                break;
                            case 3:
                                System.out.print(ANSI_RED + "H " + ANSI_RESET);
                                break;
                            case 2:
                                System.out.print(ANSI_RED + "R " + ANSI_RESET);
                                break;
                            case 1:
                                System.out.print(ANSI_RED + "P " + ANSI_RESET);
                                break;
                            case 0:
                                System.out.print("0 ");
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        switch (this.board[j][i] % 10) {
                            case 6:
                                System.out.print(ANSI_GREEN + "K " + ANSI_RESET);
                                break;
                            case 5:
                                System.out.print(ANSI_GREEN + "Q " + ANSI_RESET);
                                break;
                            case 4:
                                System.out.print(ANSI_GREEN + "B " + ANSI_RESET);
                                break;
                            case 3:
                                System.out.print(ANSI_GREEN + "H " + ANSI_RESET);
                                break;
                            case 2:
                                System.out.print(ANSI_GREEN + "R " + ANSI_RESET);
                                break;
                            case 1:
                                System.out.print(ANSI_GREEN + "P " + ANSI_RESET);
                                break;
                            case 0:
                                System.out.print("0 ");
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }
}