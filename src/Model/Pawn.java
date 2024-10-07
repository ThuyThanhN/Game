package Model;

public class Pawn extends Piece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int direction = getColor().equals("white") ? -1 : 1; // Quân trắng di chuyển lên, quân đen di chuyển xuống
        if (startX == endX) {
            // Di chuyển thẳng
            if (endY - startY == direction && board[endY][endX] == null) {
                return true; // Di chuyển một ô
            }
            if (endY - startY == direction * 2 && startY == (getColor().equals("white") ? 6 : 1) && board[endY][endX] == null) {
                return true; // Di chuyển hai ô từ vị trí xuất phát
            }
        } else if (Math.abs(endX - startX) == 1 && endY - startY == direction) {
            // Ăn quân
            return board[endY][endX] != null && !board[endY][endX].getColor().equals(getColor());
        }
        return false;
    }
}
