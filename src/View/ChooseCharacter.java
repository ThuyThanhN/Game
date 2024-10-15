package View;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ChooseCharacter extends JFrame {
	private static final int BOARD_SIZE = 8;
	private static final int SQ_SIZE = 80;
	private String whiteName;
	private String blackName;

	public ChooseCharacter() {
		// Thiết lập tiêu đề cho cửa sổ
		setTitle("Chọn nhân vật");

		// Thiết lập kích thước cửa sổ
		setSize(BOARD_SIZE * SQ_SIZE + 18, BOARD_SIZE * SQ_SIZE + 30);

		// Thiết lập hành động đóng cửa sổ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Tạo một panel để chứa hình ảnh nền
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				// Tải hình ảnh từ đường dẫn
				ImageIcon icon = new ImageIcon("img/view_choose.jpg");
				Image img = icon.getImage();

				// Vẽ hình ảnh lên panel làm background
				g.drawImage(img, 0, 0, getWidth() / 2, getHeight() / 2, this);
			}
		};

		// Thêm panel vào JFrame
		setContentPane(panel); // Đặt panel làm nền
		panel.setLayout(new BorderLayout()); // Thiết lập layout để có thể điều chỉnh vị trí các nút

		// Thiết lập hiển thị cửa sổ ở giữa màn hình
		setLocationRelativeTo(null);

		// Tạo panel chứa các nút "Select a Side" và "Mode" và đặt nó ở phía giữa
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false); // Làm trong suốt panel chứa các nút
		buttonPanel.setLayout(new GridLayout(1, 2, 10, 10)); // Dùng GridLayout 1 hàng và 2 cột với khoảng cách 10px

		// Tạo các nút "Select a Side" và "Mode" với hình ảnh và làm chúng trong suốt
		ImageIcon sideIcon = new ImageIcon("img/select.png"); // Đường dẫn đến hình ảnh
		JButton selectSideButton = new JButton(sideIcon); // Nút "Select a Side" với hình ảnh
		selectSideButton.setContentAreaFilled(false); // Làm cho nút trong suốt
		selectSideButton.setBorderPainted(false); // Tắt viền nút

		ImageIcon modeIcon = new ImageIcon("img/mode.png"); // Đường dẫn đến hình ảnh
		JButton modeButton = new JButton(modeIcon); // Nút "Mode" với hình ảnh
		modeButton.setContentAreaFilled(false); // Làm cho nút trong suốt
		modeButton.setBorderPainted(false); // Tắt viền nút

		selectSideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Tạo hộp thoại "Select a Side"
				JDialog selectSideDialog = new JDialog(ChooseCharacter.this, "Chọn phe", true);
				selectSideDialog.setSize(400, 200);
				selectSideDialog.setLayout(new GridLayout(5, 2, 10, 10)); // 5 hàng, 2 cột

				// Tạo JComboBox cho phe
				String[] sides = { "White", "Black" };
				JComboBox<String> sideComboBox = new JComboBox<>(sides);

				// JLabel cho lưu tên
				JLabel saveNameLabel = new JLabel("Lưu tên:");
				saveNameLabel.setHorizontalAlignment(JLabel.CENTER); // Căn giữa chữ

				// JLabel cho tên quân trắng
				JLabel whiteLabel = new JLabel("Tên quân trắng:");
				JTextField whiteNameField = new JTextField(10);
				whiteNameField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa chữ

				// JLabel cho tên quân đen
				JLabel blackLabel = new JLabel("Tên quân đen:");
				JTextField blackNameField = new JTextField(10);
				blackNameField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa chữ

				// Nút OK
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Lưu tên vào biến
						whiteName = whiteNameField.getText();
						blackName = blackNameField.getText();
						selectSideDialog.dispose(); // Đóng hộp thoại
					}
				});

				// Thêm các thành phần vào hộp thoại
				selectSideDialog.add(new JLabel("Chọn phe:")); // JLabel cho chọn phe
				selectSideDialog.add(sideComboBox); // JComboBox cho phe
				selectSideDialog.add(saveNameLabel);
				selectSideDialog.add(new JLabel("")); // Ô trống để căn giữa
				selectSideDialog.add(whiteLabel);
				selectSideDialog.add(whiteNameField);
				selectSideDialog.add(blackLabel);
				selectSideDialog.add(blackNameField);
				selectSideDialog.add(new JLabel("")); // Ô trống để căn giữa
				selectSideDialog.add(okButton);

				selectSideDialog.setLocationRelativeTo(null);
				selectSideDialog.setVisible(true);
			}
		});

		// Thêm ActionListener cho nút "Mode"
		modeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Tạo hộp thoại "Mode"
				JDialog modeDialog = new JDialog(ChooseCharacter.this, "Chọn chế độ chơi", true);
				modeDialog.setSize(300, 300);
				modeDialog.setLayout(new GridLayout(6, 2, 10, 10)); // 6 hàng, 2 cột

				// JLabel cho chế độ Easy
				JLabel easyLabel = new JLabel("Easy:");
				modeDialog.add(easyLabel);
				// JRadioButton cho chế độ Easy
				JRadioButton easyRadioButton = new JRadioButton("Player vs Player");
				modeDialog.add(easyRadioButton);

				// JLabel cho chế độ Hard
				JLabel hardLabel = new JLabel("Hard:");
				modeDialog.add(hardLabel);
				// JRadioButton cho chế độ Hard
				JRadioButton hardRadioButton1 = new JRadioButton("Player vs Player (Thời gian)");
				modeDialog.add(hardRadioButton1);
				JRadioButton hardRadioButton2 = new JRadioButton("Player vs Computer");
				modeDialog.add(hardRadioButton2);

				// Tạo ButtonGroup để nhóm các JRadioButton
				ButtonGroup hardGroup = new ButtonGroup();
				hardGroup.add(hardRadioButton1);
				hardGroup.add(hardRadioButton2);

				// Nút OK
				JButton okModeButton = new JButton("OK");
				okModeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Chế độ đã chọn:");
						if (easyRadioButton.isSelected()) {
							System.out.println("- Easy: Player vs Player");
						}
						if (hardRadioButton1.isSelected()) {
							System.out.println("- Hard: Player vs Player (Thời gian)");
						}
						if (hardRadioButton2.isSelected()) {
							System.out.println("- Hard: Player vs Computer");
						}
						modeDialog.dispose(); // Đóng hộp thoại
					}
				});

				// Đặt nút OK ở dưới cùng
				modeDialog.add(okModeButton);

				modeDialog.setLocationRelativeTo(null);
				modeDialog.setVisible(true);
			}
		});

		// Thêm các nút "Select a Side" và "Mode" vào panel
		buttonPanel.add(selectSideButton);
		buttonPanel.add(modeButton);

		// Thêm panel chứa các nút vào giữa của JFrame
		panel.add(buttonPanel, BorderLayout.CENTER);

		// Tạo panel chứa nút "Next" và đặt nó ở dưới cùng
		JPanel nextButtonPanel = new JPanel();
		nextButtonPanel.setOpaque(false); // Làm trong suốt panel chứa nút
		nextButtonPanel.setLayout(new BorderLayout()); // Đặt layout BorderLayout để căn giữa nút

		// Thêm nút "Next" với hình ảnh và làm trong suốt
		ImageIcon nextIcon = new ImageIcon("img/next.png");
		JButton nextButton = new JButton(nextIcon);
		nextButton.setContentAreaFilled(false); // Làm cho nút trong suốt
		nextButton.setBorderPainted(false); // Tắt viền nút

		// Thêm ActionListener cho nút "Next"
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Hành động khi nhấn nút "Next"
				System.out.println("Next button clicked!");
			}
		});

		// Thêm nút "Next" vào panel chứa nút
		nextButtonPanel.add(nextButton, BorderLayout.CENTER);

		// Thêm panel chứa nút "Next" vào phía dưới của JFrame
		panel.add(nextButtonPanel, BorderLayout.SOUTH);

		// Hiển thị cửa sổ
		setVisible(true);
	}

	public static void main(String[] args) {
		// Khởi tạo và hiển thị cửa sổ ChooseCharacter
		ChooseCharacter frame = new ChooseCharacter();
		frame.setVisible(true);
	}
}