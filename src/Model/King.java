package Model;

public class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Di chuyển một ô bất kỳ
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx <= 1 && dy <= 1);
    }
}
