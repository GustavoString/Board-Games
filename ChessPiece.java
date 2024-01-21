public class ChessPiece extends Coordinate {

    int pieceType;

    public ChessPiece(int x, int y, int pieceType) {
        super(x, y);
        this.pieceType=pieceType;
    }
    
}
