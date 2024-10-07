package Model;

public class Queen extends Piece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Di chuyển thẳng hàng hoặc chéo
        return new Rook(getColor()).isValidMove(startX, startY, endX, endY, board) ||
               new Bishop(getColor()).isValidMove(startX, startY, endX, endY, board);
    }
}
