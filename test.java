import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Chess chessBoard=new Chess();
        while(!chessBoard.checkmateChecker(false) && !chessBoard.checkmateChecker(true)){
            chessBoard.boardPrinter();
            Scanner s1=new Scanner(System.in);
            System.out.print(Board.ANSI_RED+"\n--RED PLAYER TURN---\n\n"+Board.ANSI_RESET+"Please enter the coordinates of your move in the following format:\nXY-XY\n(Initial-Final):");
            int[] tempInt=coordinateConverter(s1.nextLine());
            chessBoard.playPiece(tempInt[0], tempInt[1], tempInt[2], tempInt[3]);
            System.out.println();
            chessBoard.boardPrinter();
            if(chessBoard.checkmateChecker(false)){
                break;
            }
            System.out.print(Board.ANSI_GREEN+"\n--GREEN PLAYER TURN---\n\n"+Board.ANSI_RESET+"Please enter the coordinates of your move in the following format:\nXY-XY\n(Initial-Final):");
            tempInt=coordinateConverter(s1.nextLine());
            chessBoard.playPiece(tempInt[0], tempInt[1], tempInt[2], tempInt[3]);
            System.out.println();
            chessBoard.boardPrinter();
            if(chessBoard.checkmateChecker(true)){
                break;
            }

            s1.close();
        }
        if(chessBoard.checkmateChecker(false)){
            System.out.println("Green wins!");
        } else if(chessBoard.checkmateChecker(true)){
            System.out.println("Red wins!");
        } else {
            System.out.println("You either managed to draw or there is an error somewhere.\nMost likely the 2nd one.");
        }
    }

    public static int[] coordinateConverter (String input){
        //example format for entering coordinates:
        //C6-E3
        //(Initial-Final)

        //index 0 is xInitial
        //1 is yInitial
        //2 is xFinal
        //3 is yFinal

        String[] s=((input.trim()).toUpperCase()).split("-");
        
        int[] ret=new int[4];

        ret[1]=Integer.parseInt(s[0].substring(1,2))-1;
        ret[3]=Integer.parseInt(s[1].substring(1,2))-1;

        for (int i = 0; i < s.length; i++) {
            switch(s[i].substring(0, 1)){
                case "A":
                    ret[i*2]=0;
                    break;
                case "B":
                    ret[i*2]=1;
                    break;
                case "C":
                    ret[i*2]=2;
                    break;
                case "D":
                    ret[i*2]=3;
                    break;
                case "E":
                    ret[i*2]=4;
                    break;
                case "F":
                    ret[i*2]=5;
                    break;
                case "G":
                    ret[i*2]=6;
                    break;
                case "H":
                    ret[i*2]=7;
                    break;
                default:
                    System.out.println("Problem in the s[i].substring switch in the coordinateConverter function.");
                    break;
            }
        }
        return ret;
    }
}
