import java.util.ArrayList;
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

    public void playPiece(int xInitial, int yInitial, int xFinal, int yFinal) {
        if (this.board[xInitial][yInitial] == 0) {
            System.out.println("Invalid initial position:\nThere is nothing to play in that position.");
            return;
        } else if (this.board[xInitial][yInitial] / 10 == this.board[xFinal][yFinal] / 10) {
            System.out.println("Invalid final position:\nYou cannot capture your own pieces.");
            return;
        }
        switch (this.board[xInitial][yInitial] % 10) {
            case 1:
                // pawn gets played here
                if (this.board[xInitial][yInitial] / 10 == 1) {
                    if(xFinal == xInitial-1 || xFinal == xInitial+1) {
                        if(xInitial+1 < 8 && xInitial-1 >= 0){
                            if((this.board[xInitial+1][yInitial+1] / 10 == 2 && xInitial+1 == xFinal && yInitial+1 == yFinal) || (this.board[xInitial-1][yInitial+1] / 10 == 2 && xInitial-1 == xFinal && yInitial+1 == yFinal)){
                                this.movePiece(xInitial, yInitial, xFinal, yFinal);
                            }
                        } else if (xInitial+1 < 8){
                            if(this.board[xInitial+1][yInitial+1] / 10 == 2 && xInitial+1 == xFinal && yInitial+1 == yFinal){
                                this.movePiece(xInitial, yInitial, xFinal, yFinal);
                            }
                        } else if (xInitial-1>=0){
                            if(this.board[xInitial-1][yInitial+1] / 10 == 2 && xInitial-1 == xFinal && yInitial+1 == yFinal){
                                this.movePiece(xInitial, yInitial, xFinal, yFinal);
                            }
                        } else {
                            System.out.println("Illegal final position. 123315");
                            return;
                        }
                    } else if (this.board[xFinal][yFinal]!=0){
                        System.out.println("Illegal final position.");
                        return;
                    } else if (yInitial == 1) {
                        boolean yFinalIsNotLegal = true;
                        for (int i = yInitial+1; i <= yInitial + 2; i++) {
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
                        System.out.print("Enter the type of piece you would like to trade this pawn for\n(R, H, B, Q):");
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
                    if(xFinal == xInitial-1 || xFinal == xInitial +1) {
                        if(xInitial+1 < 8 && xInitial-1 >= 0){
                            if((this.board[xInitial+1][yInitial-1] / 10 == 1 && xInitial+1 == xFinal && yInitial-1 == yFinal) || (this.board[xInitial-1][yInitial-1] / 10 == 1 && xInitial-1 == xFinal && yInitial-1 == yFinal)){
                                this.movePiece(xInitial, yInitial, xFinal, yFinal);
                            }
                        } else if (xInitial+1 < 8){
                            if(this.board[xInitial+1][yInitial-1] / 10 == 1 && xInitial+1 == xFinal && yInitial-1 == yFinal){
                                this.movePiece(xInitial, yInitial, xFinal, yFinal);
                            }
                        } else if (xInitial-1>=0){
                            if(this.board[xInitial-1][yInitial-1] / 10 == 1 && xInitial-1 == xFinal && yInitial-1 == yFinal){
                                this.movePiece(xInitial, yInitial, xFinal, yFinal);
                            }
                        }
                    } else if (this.board[xFinal][yFinal]!=0) {
                        System.out.println("Illegal final position.");
                        return;
                    } else if (yInitial == 6) {
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
                if(this.horsePatternChecker(xInitial, yInitial, xFinal, yFinal)){
                    this.movePiece(xInitial, yInitial, xFinal, yFinal);
                } else{
                    System.out.println("Invalid move.");
                    return;
                }
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
                if(this.kingPatternChecker(xInitial, yInitial, xFinal, yFinal)){
                    this.movePiece(xInitial, yInitial, xFinal, yFinal);
                } else{
                    System.out.println("Invalid move.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid chess piece type given at X: " + xInitial  + " Y: " + yInitial + "\nin the playPiece function.");
                return;
        }
    }

    public boolean kingPatternChecker(int xInitial, int yInitial, int xFinal, int yFinal){
        boolean isValidFinalPosition=false;
        for (int i = xInitial-1; i <= xInitial+1 ; i++) {
            if(yInitial == yFinal && xFinal == i){
                isValidFinalPosition=true;
            }
        }
        for(int i = yInitial-1 ; i <= yInitial+1 ; i++){
            if(xInitial == xFinal && yFinal == i){
                isValidFinalPosition=true;
            }
        }
        for (int i = xInitial-1, j = yInitial-1; i <= xInitial+1 && j <= yInitial+1 ; i++, j++) {
            if(xFinal == i && yFinal == j){
                isValidFinalPosition = true;
            }
        }
        for (int i = xInitial-1, j = yInitial+1; i <= xInitial+1 && j >= yInitial-1 ; i++, j--) {
            if(xFinal == i && yFinal == j){
                isValidFinalPosition = true;
            }
        }
        if(this.board[xInitial][yInitial]/10 == this.board[xFinal][yFinal]/10){
            isValidFinalPosition=false;
        }
        if(xInitial == xFinal && yInitial == yFinal){
            isValidFinalPosition=false;
        }
        return isValidFinalPosition;
    }

    public boolean horsePatternChecker(int xInitial, int yInitial, int xFinal, int yFinal){
        boolean isValidFinalPosition=false;
        //top
        if((xInitial+1 == xFinal && yInitial-2 == yFinal) || (xInitial-1 == xFinal && yInitial-2 == yFinal)){
            isValidFinalPosition=true;
        }
        //right
        if((xInitial+2 == xFinal && yInitial-1 == yFinal) || (xInitial+2 == xFinal && yInitial+1 == yFinal)){
            isValidFinalPosition=true;
        }
        //bottom
        if((yInitial+2 == yFinal && xInitial+1 == xFinal) || (yInitial+2 == yFinal && xInitial-1 == xFinal)){
            isValidFinalPosition=true;
        }
        //left
        if((xInitial-2 == xFinal && yInitial+1 == yFinal) || (xInitial-2 == xFinal && yInitial-1 == yFinal)){
            isValidFinalPosition=true;
        }
        if(this.board[xFinal][yFinal]/10 == this.board[xInitial][yInitial]){
            isValidFinalPosition=false;
        }
        if(xInitial == xFinal && yInitial == yFinal){
            isValidFinalPosition=false;
        }
        return isValidFinalPosition;
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
        if(xInitial == xFinal && yInitial == yFinal){
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
        if(xInitial == xFinal && yInitial == yFinal){
            isValidFinalPosition=false;
        }
        return isValidFinalPosition;
    }

    public boolean checkmateChecker(boolean isRed){
        //isRed should be true if we are checking if the red king is in checkmate.
        //otherwise should be false

        Coordinate king=new Coordinate(0, 0);
        if(isRed){
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if(this.board[j][i] == 16){
                        king.setCoordinate(j, i);
                    }
                }
            }
        } else{
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if(this.board[j][i] == 26){
                        king.setCoordinate(j, i);
                    }
                }
            }
        }

        Coordinate[] possibleKingMoves=Chess.possibleKingMovesFinder(king);
        Coordinate[] possibleEnemyMoves=this.possibleEnemyMovesFinder(isRed);

        int amountOfOverlaps=0;

        for (int i = 0; i < possibleKingMoves.length; i++) {
            for (int j = 0; j < possibleEnemyMoves.length; j++) {
                if(possibleKingMoves[i].equals(possibleEnemyMoves[j])){
                    amountOfOverlaps++;
                }
            }
        }

        boolean kingIsSurroundedByOtherPieces=true;

        for (int i = 0; i < possibleKingMoves.length; i++) {
            if(this.board[possibleKingMoves[i].x][possibleKingMoves[i].y] == 0){
                kingIsSurroundedByOtherPieces=false;
            }
        }

        if(amountOfOverlaps == possibleKingMoves.length && !kingIsSurroundedByOtherPieces){
            return true;
        } else{
            return false;
        }
    }

    public Coordinate[] possibleEnemyMovesFinder(boolean isRed){
        //check if the coordinates on the move pattern other than the piece in question's own coordinate are empty and if they are add those coordinates to possibleMoves
        int teamType;
        if(isRed){
            teamType=2;
        } else{
            teamType=1;
        }
        ArrayList<ChessPiece> pieces=new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(this.board[i][j]/10 == teamType){
                    ChessPiece temp=new ChessPiece(i, j, this.board[i][j]%10);
                    pieces.add(temp);
                }
            }
        }
        ArrayList<Coordinate> possibleMoves=new ArrayList<>();
        for (int i = 0; i < pieces.size(); i++) {
            boolean terminateX;
            boolean terminateY;
            switch(pieces.get(i).pieceType){
                case 1:
                    //pawn
                    if(teamType==1){
                        if(pieces.get(i).y == 1){
                            Coordinate temp=new Coordinate(pieces.get(i).x, pieces.get(i).y+1);
                            possibleMoves.add(temp);
                            temp.setCoordinate(pieces.get(i).x, pieces.get(i).y+2);
                            possibleMoves.add(temp);
                        } else{
                            Coordinate temp=new Coordinate(pieces.get(i).x, pieces.get(i).y+1);
                            possibleMoves.add(temp);
                        }
                    } else{
                        if(pieces.get(i).y == 6){
                            Coordinate temp=new Coordinate(pieces.get(i).x, pieces.get(i).y-1);
                            possibleMoves.add(temp);
                            temp.setCoordinate(pieces.get(i).x, pieces.get(i).y-2);
                            possibleMoves.add(temp);
                        } else{
                            Coordinate temp=new Coordinate(pieces.get(i).x, pieces.get(i).y-1);
                            possibleMoves.add(temp);
                        }
                    }
                    break;
                case 2:
                    //rook
                    //start of cross pattern checker
                    terminateX=false;
                    if(pieces.get(i).x-1>=0){
                        for (int j = pieces.get(i).x-1 ; j >= 0 && !terminateX; j--) {
                            if(this.board[j][pieces.get(i).y] != 0){
                                terminateX=true;
                            } else{
                                Coordinate temp=new Coordinate(j, pieces.get(i).y);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    terminateX=false;
                    if(pieces.get(i).x+1<8){
                        for (int j = pieces.get(i).x+1 ; j < 8 && !terminateX; j++) {
                            if(this.board[j][pieces.get(i).y] != 0){
                                terminateX=true;
                            } else{
                                Coordinate temp=new Coordinate(j, pieces.get(i).y);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    terminateY=false;
                    if(pieces.get(i).y-1>=0){
                        for (int j = pieces.get(i).y-1 ; j >= 0 && !terminateY; j--) {
                            if(this.board[pieces.get(i).x][j] != 0){
                                terminateY=true;
                            } else{
                                Coordinate temp=new Coordinate(pieces.get(i).x, j);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    terminateY=false;
                    if(pieces.get(i).y+1<8){
                        for (int j = pieces.get(i).y+1 ; j < 8 && !terminateY; j++) {
                            if(this.board[pieces.get(i).x][j] != 0){
                                terminateY=true;
                            } else{
                                Coordinate temp=new Coordinate(pieces.get(i).x, j);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    //end of cross pattern checker
                    break;
                case 3:
                    //knight
                    Coordinate temp1=new Coordinate(0, 0);

                    try{
                        if(this.board[pieces.get(i).x+1][pieces.get(i).y-2] == 0){
                            temp1.setCoordinate(pieces.get(i).x+1, pieces.get(i).y-2);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }

                    try{
                        if(this.board[pieces.get(i).x+2][pieces.get(i).y-1] == 0){
                            temp1.setCoordinate(pieces.get(i).x+2, pieces.get(i).y-1);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }

                    try{
                        if(this.board[pieces.get(i).x+2][pieces.get(i).y+1] == 0){
                            temp1.setCoordinate(pieces.get(i).x+2, pieces.get(i).y+1);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try{
                        if(this.board[pieces.get(i).x+1][pieces.get(i).y+2] == 0){
                            temp1.setCoordinate(pieces.get(i).x+1, pieces.get(i).y+2);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try{
                        if(this.board[pieces.get(i).x-1][pieces.get(i).y+2] == 0){
                            temp1.setCoordinate(pieces.get(i).x-1, pieces.get(i).y+2);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try{
                        if(this.board[pieces.get(i).x-2][pieces.get(i).y+1] == 0){
                            temp1.setCoordinate(pieces.get(i).x-2, pieces.get(i).y+1);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try{
                        if(this.board[pieces.get(i).x-2][pieces.get(i).y-1] == 0){
                            temp1.setCoordinate(pieces.get(i).x-2, pieces.get(i).y-1);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try{
                        if(this.board[pieces.get(i).x-1][pieces.get(i).y-2] == 0){
                            temp1.setCoordinate(pieces.get(i).x-1, pieces.get(i).y-2);
                            possibleMoves.add(temp1);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    break;
                case 4:
                    //bishop
                    //start of x pattern checker
                    //make sure you include terminate booleans to terminate the checker if it comes across a piece (enemy of friendly) in that direction.
                    //diagonally right-down
                    if(pieces.get(i).x+1 < 8 && pieces.get(i).y+1 < 8){
                        for (int j = pieces.get(i).x+1, k=pieces.get(i).y+1 ; j < 8 && k < 8 ; j++, k++) {
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            }
                        }
                    }
                    //diagonally left-down
                    if(pieces.get(i).x-1 >= 0 && pieces.get(i).y+1 < 8){
                        for(int j = pieces.get(i).x-1, k=pieces.get(i).y+1 ; j >= 0 && k < 8 ; j--, k++){
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            }
                        }
                    }
                    //diagonally left-up
                    if(pieces.get(i).x-1 >= 0 && pieces.get(i).y-1 >= 0){
                        for(int j = pieces.get(i).x-1, k=pieces.get(i).y-1 ; j >= 0 && k >= 0 ; j--, k--){
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            } 
                        }
                    }
                    //diagonally right-up
                    if(pieces.get(i).x+1 < 8 && pieces.get(i).y-1 >= 0){
                        for(int j = pieces.get(i).x+1, k=pieces.get(i).y-1 ; j < 8 && k >= 0 ; j++, k--){
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            } 
                        }
                    }
                    //end of x pattern checker
                    break;
                case 5:
                    //queen
                    //copy paste cross pattern checker from rook and x pattern checker from bishop below

                    //start of x pattern checker
                    //make sure you include terminate booleans to terminate the checker if it comes across a piece (enemy of friendly) in that direction.
                    //diagonally right-down
                    if(pieces.get(i).x+1 < 8 && pieces.get(i).y+1 < 8){
                        for (int j = pieces.get(i).x+1, k=pieces.get(i).y+1 ; j < 8 && k < 8 ; j++, k++) {
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            }
                        }
                    }
                    //diagonally left-down
                    if(pieces.get(i).x-1 >= 0 && pieces.get(i).y+1 < 8){
                        for(int j = pieces.get(i).x-1, k=pieces.get(i).y+1 ; j >= 0 && k < 8 ; j--, k++){
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            }
                        }
                    }
                    //diagonally left-up
                    if(pieces.get(i).x-1 >= 0 && pieces.get(i).y-1 >= 0){
                        for(int j = pieces.get(i).x-1, k=pieces.get(i).y-1 ; j >= 0 && k >= 0 ; j--, k--){
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            } 
                        }
                    }
                    //diagonally right-up
                    if(pieces.get(i).x+1 < 8 && pieces.get(i).y-1 >= 0){
                        for(int j = pieces.get(i).x+1, k=pieces.get(i).y-1 ; j < 8 && k >= 0 ; j++, k--){
                            if(this.board[j][k] == 0){
                                Coordinate temp=new Coordinate(j, k);
                                possibleMoves.add(temp);
                            } else{
                                break;
                            } 
                        }
                    }
                    //end of x pattern checker

                    //start of cross pattern checker
                    terminateX=false;
                    if(pieces.get(i).x-1>=0){
                        for (int j = pieces.get(i).x-1 ; j >= 0 && !terminateX; j--) {
                            if(this.board[j][pieces.get(i).y] != 0){
                                terminateX=true;
                            } else{
                                Coordinate temp=new Coordinate(j, pieces.get(i).y);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    terminateX=false;
                    if(pieces.get(i).x+1<8){
                        for (int j = pieces.get(i).x+1 ; j < 8 && !terminateX; j++) {
                            if(this.board[j][pieces.get(i).y] != 0){
                                terminateX=true;
                            } else{
                                Coordinate temp=new Coordinate(j, pieces.get(i).y);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    terminateY=false;
                    if(pieces.get(i).y-1>=0){
                        for (int j = pieces.get(i).y-1 ; j >= 0 && !terminateY; j--) {
                            if(this.board[pieces.get(i).x][j] != 0){
                                terminateY=true;
                            } else{
                                Coordinate temp=new Coordinate(pieces.get(i).x, j);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    terminateY=false;
                    if(pieces.get(i).y+1<8){
                        for (int j = pieces.get(i).y+1 ; j < 8 && !terminateY; j++) {
                            if(this.board[pieces.get(i).x][j] != 0){
                                terminateY=true;
                            } else{
                                Coordinate temp=new Coordinate(pieces.get(i).x, j);
                                possibleMoves.add(temp);
                            }
                        }
                    }
                    //end of cross pattern checker
                    break;
                case 6:
                    //king
                    Coordinate temp2=new Coordinate(0, 0);

                    try {
                        if(this.board[pieces.get(i).x][pieces.get(i).y-1] == 0){
                            temp2.setCoordinate(pieces.get(i).x, pieces.get(i).y-1);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }

                    try {
                        if(this.board[pieces.get(i).x+1][pieces.get(i).y-1] == 0){
                            temp2.setCoordinate(pieces.get(i).x+1, pieces.get(i).y-1);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }

                    try {
                        if(this.board[pieces.get(i).x+1][pieces.get(i).y] == 0){
                            temp2.setCoordinate(pieces.get(i).x+1, pieces.get(i).y);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try {
                        if(this.board[pieces.get(i).x+1][pieces.get(i).y+1] == 0){
                            temp2.setCoordinate(pieces.get(i).x+1, pieces.get(i).y+1);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try {
                        if(this.board[pieces.get(i).x][pieces.get(i).y+1] == 0){
                            temp2.setCoordinate(pieces.get(i).x, pieces.get(i).y+1);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }

                    try {
                        if(this.board[pieces.get(i).x-1][pieces.get(i).y+1] == 0){
                            temp2.setCoordinate(pieces.get(i).x-1, pieces.get(i).y+1);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try {
                        if(this.board[pieces.get(i).x-1][pieces.get(i).y] == 0){
                            temp2.setCoordinate(pieces.get(i).x-1, pieces.get(i).y);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    try {
                        if(this.board[pieces.get(i).x-1][pieces.get(i).y-1] == 0){
                            temp2.setCoordinate(pieces.get(i).x-1, pieces.get(i).y-1);
                            possibleMoves.add(temp2);
                        }
                    } catch (Exception e) {
                        //does nothing here
                    }
                    
                    break;
                default:
                    break;
            }
            //end of switch-case here
        }
        //end of for-loop here
        Coordinate[] ret=new Coordinate[possibleMoves.size()];
        for (int j = 0; j < ret.length; j++) {
            ret[j]=possibleMoves.get(j);
        }
        return ret;
    }

    public static Coordinate[] possibleKingMovesFinder(Coordinate king){
        ArrayList <Coordinate> kingMoves=new ArrayList<>();

        Coordinate temp=new Coordinate(0,0);

        temp.setCoordinate(king.x-1, king.y);
        kingMoves.add(temp);

        temp.setCoordinate(king.x-1, king.y-1);
        kingMoves.add(temp);

        temp.setCoordinate(king.x, king.y-1);
        kingMoves.add(temp);

        temp.setCoordinate(king.x+1, king.y-1);
        kingMoves.add(temp);

        temp.setCoordinate(king.x+1, king.y);
        kingMoves.add(temp);

        temp.setCoordinate(king.x+1, king.y+1);
        kingMoves.add(temp);

        temp.setCoordinate(king.x, king.y+1);
        kingMoves.add(temp);

        temp.setCoordinate(king.x-1, king.y+1);
        kingMoves.add(temp);

        ArrayList<Coordinate> kingMovesRet=new ArrayList<>();

        for (int i = 0; i < kingMoves.size(); i++) {
            if(kingMoves.get(i).x<0 || kingMoves.get(i).x>7 || kingMoves.get(i).y<0 || kingMoves.get(i).y>7){

            } else{
                kingMovesRet.add(kingMoves.get(i));
            }
        }
        
        Coordinate[] ret=new Coordinate[kingMovesRet.size()];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = kingMovesRet.get(i);
        }

        return ret;
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
            System.out.println("  " + (i+1));
        }
        System.out.println("\n    A B C D E F G H");
    }
}