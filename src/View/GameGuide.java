package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Man hinh huong dan Play Chess
public class GameGuide extends JFrame {
	private Image backgroundImage;
	private JButton backBtn;
	private JPanel panel;

	public GameGuide() {
		this.setTitle("Game Guide");
		this.setSize(950, 680);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		backgroundImage = new ImageIcon("img/rule.png").getImage();

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel.setLayout(new BorderLayout());

		// Btn Back
		backBtn = new JButton(new ImageIcon("img/back.png"));
		backBtn.setBorderPainted(false);
		backBtn.setFocusPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Btn Back => Quay lai man hinh chinh (GameViewScreen)
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameViewScreen gameViewScreen = new GameViewScreen();
				gameViewScreen.setVisible(true); // Hiện GameViewScreen
				dispose(); // Đóng
			}
		});

		panel.add(backBtn, BorderLayout.SOUTH);

		this.setContentPane(panel);
	}

	public static void main(String[] args) {
		GameGuide gameGuide = new GameGuide();
		gameGuide.setVisible(true);
	}
}
