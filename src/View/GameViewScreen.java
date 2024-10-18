package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Man hinh chinh cua Play Chess
public class GameViewScreen extends JFrame {
	private Image backgroundImage;
	private JButton helpBtn, exitBtn, playBtn;
	private JPanel panel;

	public GameViewScreen() {
		this.setTitle("Play Chess");
		this.setSize(950, 680);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		backgroundImage = new ImageIcon("img/main.png").getImage();

		// Tao Panel de ve background
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel.setLayout(new GridBagLayout());
		this.setContentPane(panel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 10, 20, 10); // Khoang trong giua cac nut
		gbc.anchor = GridBagConstraints.CENTER; // Bo tri cac nut nam giua

		helpBtn = createButton("img/help.png");
		playBtn = createButton("img/play.png");
		exitBtn = createButton("img/exit.png");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 1.0;
		this.add(new JPanel(), gbc);

		// Btn Help
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0;
		this.add(helpBtn, gbc);

		// Btn Play
		gbc.gridx = 1;
		this.add(playBtn, gbc);

		// Btn Exit
		gbc.gridx = 2;
		this.add(exitBtn, gbc);

		// Nhiem vu cua Button
		// Btn Play => Chuyen sang ModelView de chon che do va dien ten
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeView modeView = new ModeView();
				modeView.setVisible(true);
				dispose(); // Đóng
			}
		});

		// Btn Help => Chuyen sang GameGuide nguoi choi doc huong dan choi Play Chess
		helpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameGuide gameGuide = new GameGuide();
				gameGuide.setVisible(true);
				dispose(); // Đóng
			}
		});

		// Btn Exit => Thoat Game
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Thoat
			}
		});
	}

	// Tao giao dien cho Button
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
		GameViewScreen gameViewScreen = new GameViewScreen();
		gameViewScreen.setVisible(true);
	}
}
