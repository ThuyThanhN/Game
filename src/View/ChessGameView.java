package View;

import Controller.ChessGameController;
import Model.Piece;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ChessGameView extends JPanel {
    public static final int BOARD_SIZE = 8;
    public static final int SQ_SIZE = 80;

    private ChessGameController controller; // Thêm controller
    private List<Point> validMoves = new ArrayList<>();

    public ChessGameView() {
        controller = new ChessGameController(this); // Khởi tạo controller
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / SQ_SIZE;
                int y = e.getY() / SQ_SIZE;
                controller.handleMouseClick(x, y); // Gọi phương thức trong controller
            }
        });
    }

    public void highlightValidMoves(List<Point> validMoves) {
        this.validMoves = validMoves; // Cập nhật validMoves
        repaint(); // Vẽ lại bàn cờ
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
        drawHighlights(g);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boolean isWhite = (row + col) % 2 == 0;
                g.setColor(isWhite ? Color.WHITE : Color.GRAY);
                g.fillRect(col * SQ_SIZE, row * SQ_SIZE, SQ_SIZE, SQ_SIZE);
            }
        }
    }

    private void drawPieces(Graphics g) {
        Piece[][] board = controller.getBoard(); // Lấy bàn cờ từ controller
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    ImageIcon pieceImage = getPieceImage(piece);
                    if (pieceImage != null) {
                        g.drawImage(pieceImage.getImage(), col * SQ_SIZE, row * SQ_SIZE, SQ_SIZE, SQ_SIZE, this);
                    }
                }
            }
        }
    }

    // Định nghĩa hình ảnh cho các quân cờ
    private ImageIcon whiteRook = new ImageIcon("img/wR.png");
    private ImageIcon whiteKnight = new ImageIcon("img/wN.png");
    private ImageIcon whiteBishop = new ImageIcon("img/wB.png");
    private ImageIcon whiteQueen = new ImageIcon("img/wQ.png");
    private ImageIcon whiteKing = new ImageIcon("img/wK.png");
    private ImageIcon whitePawn = new ImageIcon("img/wp.png");

    private ImageIcon blackRook = new ImageIcon("img/bR.png");
    private ImageIcon blackKnight = new ImageIcon("img/bN.png");
    private ImageIcon blackBishop = new ImageIcon("img/bB.png");
    private ImageIcon blackQueen = new ImageIcon("img/bQ.png");
    private ImageIcon blackKing = new ImageIcon("img/bK.png");
    private ImageIcon blackPawn = new ImageIcon("img/bp.png");

    private ImageIcon getPieceImage(Piece piece) {
        String color = piece.getColor();
        if (piece instanceof Model.Rook) {
            return color.equals("white") ? whiteRook : blackRook;
        } else if (piece instanceof Model.Knight) {
            return color.equals("white") ? whiteKnight : blackKnight;
        } else if (piece instanceof Model.Bishop) {
            return color.equals("white") ? whiteBishop : blackBishop;
        } else if (piece instanceof Model.Queen) {
            return color.equals("white") ? whiteQueen : blackQueen;
        } else if (piece instanceof Model.King) {
            return color.equals("white") ? whiteKing : blackKing;
        } else if (piece instanceof Model.Pawn) {
            return color.equals("white") ? whitePawn : blackPawn;
        }
        return null;
    }

    private void drawHighlights(Graphics g) {
        g.setColor(new Color(255, 255, 0, 128)); // Màu vàng nhạt cho highlight
        for (Point move : validMoves) {
            g.fillRect(move.x * SQ_SIZE, move.y * SQ_SIZE, SQ_SIZE, SQ_SIZE);
        }
    }
}
