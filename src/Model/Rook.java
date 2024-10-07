package Model;

public class Rook extends Piece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Di chuyển thẳng hàng (hàng hoặc cột)
        if (startX == endX || startY == endY) {
            return isPathClear(startX, startY, endX, endY, board);
        }
        return false;
    }

    private boolean isPathClear(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Integer.signum(endX - startX);
        int dy = Integer.signum(endY - startY);
        int x = startX + dx;
        int y = startY + dy;

        while (x != endX || y != endY) {
            if (board[y][x] != null) {
                return false; // Có quân cờ cản trở
            }
            x += dx;
            y += dy;
        }
        return true;
    }
}
