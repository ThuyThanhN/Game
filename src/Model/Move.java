package Model;

public class Move {
	private int startX, startY, endX, endY;
	private int score; // Điểm số của nước đi

	public Move(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.score = 0; // Khởi tạo điểm số mặc định
	}

	public int getScore() {
		return score; // Getter cho điểm số
	}

	public void setScore(int score) {
		this.score = score; // Setter cho điểm số
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	public boolean isValidMove(Piece piece, Piece[][] board) {
		return piece.isValidMove(startX, startY, endX, endY, board);
	}
}
