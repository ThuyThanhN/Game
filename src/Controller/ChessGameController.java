package Controller;

import Model.Move;
import Model.Piece;
import Model.Rook;
import Model.Knight;
import Model.Queen;
import Model.King;
import Model.Bishop;
import Model.Pawn;
import View.ChessGameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGameController {
	private static final int BOARD_SIZE = 8;
	private Piece[][] board;
	private Piece selectedPiece;
	private int selectedX, selectedY;
	private List<Point> validMoves = new ArrayList<>();
	private String currentPlayer; // Lượt chơi hiện tại
	private ChessGameView view;

	public ChessGameController(ChessGameView view) {
		this.view = view;
		this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
		setupBoard();
		currentPlayer = "white"; // Bắt đầu với người chơi trắng
	}

	private void setupBoard() {
		// Khởi tạo quân đen
		board[0][0] = new Rook("black");
		board[0][1] = new Knight("black");
		board[0][2] = new Bishop("black");
		board[0][3] = new Queen("black");
		board[0][4] = new King("black");
		board[0][5] = new Bishop("black");
		board[0][6] = new Knight("black");
		board[0][7] = new Rook("black");

		for (int i = 0; i < BOARD_SIZE; i++) {
			board[1][i] = new Pawn("black");
		}

		// Khởi tạo quân trắng
		board[7][0] = new Rook("white");
		board[7][1] = new Knight("white");
		board[7][2] = new Bishop("white");
		board[7][3] = new Queen("white");
		board[7][4] = new King("white");
		board[7][5] = new Bishop("white");
		board[7][6] = new Knight("white");
		board[7][7] = new Rook("white");

		for (int i = 0; i < BOARD_SIZE; i++) {
			board[6][i] = new Pawn("white");
		}
	}

	public void handleMouseClick(int x, int y) {
		if (selectedPiece == null) {
			selectPiece(x, y);
		} else {
			executeMove(x, y);
		}
	}

	private void selectPiece(int x, int y) {
		if (isValidSelection(x, y)) {
			selectedPiece = board[y][x];
			selectedX = x;
			selectedY = y;
			highlightValidMoves(selectedPiece, selectedX, selectedY);
			view.highlightValidMoves(validMoves);
		}
	}

	private boolean isValidSelection(int x, int y) {
		return board[y][x] != null && board[y][x].getColor().equals(currentPlayer);
	}

	private void executeMove(int x, int y) {
		Move move = new Move(selectedX, selectedY, x, y);
		// Kiểm tra xem nước đi có hợp lệ và không ăn quân của chính mình
		if (move.isValidMove(selectedPiece, board)
				&& (board[y][x] == null || !board[y][x].getColor().equals(currentPlayer))) {
			makeMove(move, x, y);
			switchPlayer();
		} else {
			resetSelection();
		}
	}

	private void makeMove(Move move, int x, int y) {
		if (board[y][x] != null) {
			// Xóa quân cờ đối thủ
			board[y][x] = selectedPiece;
		}
		board[move.getEndY()][move.getEndX()] = selectedPiece;
		board[selectedY][selectedX] = null; // Xóa quân cũ
		resetSelection();
		view.repaint();
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer.equals("white") ? "black" : "white";
	}

	private void resetSelection() {
		selectedPiece = null; // Reset quân đã chọn
		validMoves.clear(); // Xóa highlight
		view.repaint();
	}

	private void highlightValidMoves(Piece piece, int x, int y) {
		validMoves.clear();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece targetPiece = board[i][j];
				if (piece.isValidMove(x, y, j, i, board)
						&& (targetPiece == null || !targetPiece.getColor().equals(piece.getColor()))) {
					validMoves.add(new Point(j, i));
				}
			}
		}
	}

	public Piece[][] getBoard() {
		return board;
	}
}
