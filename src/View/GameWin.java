package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Man hinh khi nguoi choi thang
public class GameWin extends JFrame {
    private Image backgroundImage;
    private JButton homeBtn, exitBtn;
    private JLabel winLabel;

    public GameWin() {
        this.setTitle("Game Win");
        this.setSize(950, 680);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        backgroundImage = new ImageIcon("img/win.png").getImage();

        // Tao JPanel de ve nen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10); // Khoang trong giua cac nut
        gbc.anchor = GridBagConstraints.CENTER; // Bo tri cac nut nam giua

        // Tao JLabel de hien thi thong bao chien thang
        winLabel = new JLabel("WIN", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 36));
        winLabel.setForeground(Color.DARK_GRAY);

        // Them JLabel vao layout
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 3; 
        panel.add(winLabel, gbc);

        homeBtn = createButton("img/home.png");
        exitBtn = createButton("img/exit.png");

        gbc.gridwidth = 1; 

        // Nut Home
        gbc.gridx = 0;
        gbc.gridy = 1; 
        panel.add(homeBtn, gbc);

        // Nut Exit
        gbc.gridx = 1;
        panel.add(exitBtn, gbc);

        // Them ActionListener cho nut Exit
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Thoat ung dung
            }
        });

        // Them ActionListener cho nut Home
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameViewScreen gameViewScreen = new GameViewScreen();
                gameViewScreen.setVisible(true); // Hien GameViewScreen
                dispose(); // Dong GameWin
            }
        });

        setContentPane(panel); // Thiet lap panel lam noi dung cua JFrame
    }

    private JButton createButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.setVisible(true);
    }
}
