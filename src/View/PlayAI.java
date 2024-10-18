package View;

import Model.Bishop;
import Model.King;
import Model.Knight;
import Model.Pawn;
import Model.Piece;
import Model.Queen;
import Model.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayAI extends JFrame {
    public static final int BOARD_SIZE = 8;
    public static final int SQ_SIZE = 80;
    private Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

    public PlayAI() {
        this.setSize(950, 680);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Ban co
        this.add(boardChess(), BorderLayout.CENTER);

        // Ten nguoi choi + time
        this.add(gameInfoPanel(), BorderLayout.EAST);

        // Khoi tao ban co
        setupBoard();
    }

    // Ban co
    private JPanel boardChess() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawPieces(g);
            }
        };

        // panel.addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseClicked(MouseEvent e) {
        // // Xử lý sự kiện click vào bàn cờ (bạn có thể thêm logic xử lý ở đây)
        // }
        // });
        return panel;
    }

    // Ve ban co
    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boolean isWhite = (row + col) % 2 == 0;
                g.setColor(isWhite ? Color.WHITE : Color.GRAY);
                g.fillRect(col * SQ_SIZE, row * SQ_SIZE, SQ_SIZE, SQ_SIZE);
            }
        }
    }

    // Bo tri quan co
    private void drawPieces(Graphics g) {
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

    // Khoi tao hinh anh quan co
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
        if (piece instanceof Rook) {
            return color.equals("white") ? whiteRook : blackRook;
        } else if (piece instanceof Knight) {
            return color.equals("white") ? whiteKnight : blackKnight;
        } else if (piece instanceof Bishop) {
            return color.equals("white") ? whiteBishop : blackBishop;
        } else if (piece instanceof Queen) {
            return color.equals("white") ? whiteQueen : blackQueen;
        } else if (piece instanceof King) {
            return color.equals("white") ? whiteKing : blackKing;
        } else if (piece instanceof Pawn) {
            return color.equals("white") ? whitePawn : blackPawn;
        }
        return null;
    }

    private void setupBoard() {
        // Quan den
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

        // Quan trang
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

    // Hien thi ten + time nguoi choi
    private JPanel gameInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayAI playAI = new PlayAI();
            playAI.setVisible(true);
        });
    }
}
