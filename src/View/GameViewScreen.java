package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameViewScreen extends JFrame {
    private JButton playButton;
    private JButton exitButton;
    private JButton helpButton;
    private JLabel titleLabel;
    private Image backgroundImage;

    public GameViewScreen() {
        // Tải hình nền từ file
        backgroundImage = new ImageIcon("img/anh2.jpeg").getImage();

        // Cài đặt cửa sổ
        setTitle("Màn hình bắt đầu");
        setSize(900, 700);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Thêm panel với hình nền
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new GridBagLayout());
        add(panel);

        // Tiêu đề
        titleLabel = new JLabel("Chào mừng đến với Trò chơi!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(titleLabel, gbc);

        // Khởi tạo nút
        playButton = new JButton("Chơi");
        exitButton = new JButton("Thoát");
        helpButton = new JButton("Hướng dẫn");

        // Cải thiện kiểu dáng nút
        styleButton(playButton);
        styleButton(exitButton);
        styleButton(helpButton);

        // Thêm các nút vào panel
        gbc.gridy = 1;
        panel.add(playButton, gbc);
        gbc.gridy = 2;
        panel.add(helpButton, gbc);
        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        // Thêm hành động cho nút "Chơi"
        playButton.addActionListener(e -> openChessGame());

        // Thêm hành động cho nút "Thoát"
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 32));
        button.setPreferredSize(new Dimension(300, 60));
        button.setBackground(new Color(255, 69, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEtchedBorder());
    }

    private void openChessGame() {
        // Mở màn hình cờ vua
        JFrame chessFrame = new JFrame("Màn hình chơi cờ");
        ChessGameView chessGameView = new ChessGameView();
        chessFrame.add(chessGameView);
        chessFrame.setSize(ChessGameView.BOARD_SIZE * ChessGameView.SQ_SIZE + 18,
                          ChessGameView.BOARD_SIZE * ChessGameView.SQ_SIZE + 30);
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.setVisible(true);
        this.dispose(); // Đóng màn hình bắt đầu
    }

    public static void main(String[] args) {
        GameViewScreen view = new GameViewScreen();
        view.setVisible(true);
    }
}
