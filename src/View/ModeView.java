package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ModeView extends JFrame {
	public ModeView() {
		// background
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// background
				ImageIcon icon = new ImageIcon("img/selectView.png");
				Image img = icon.getImage();

				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};

		setContentPane(panel);
		panel.setLayout(new BorderLayout());

		setTitle("Select and Mode");
		setSize(950, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// 2 button mode and next
		JPanel button = new JPanel();
		button.setOpaque(false);// lam trong suot
		button.setLayout(new FlowLayout(FlowLayout.CENTER, 20, -80)); // khoang cach

		// create button mode
		ImageIcon mode = new ImageIcon("img/mode.png");
		JButton btMode = new JButton(mode);
		btMode.setOpaque(false);// lam trong suot button
		btMode.setBorderPainted(false);
		btMode.setPreferredSize(new Dimension(300, 300));
		btMode.setFocusPainted(false);
		btMode.setContentAreaFilled(false);
		btMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// event for mode
		btMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog di = new JDialog(ModeView.this, "Choose Mode");
				JPanel panel = new JPanel(new GridLayout(4, 1));
				panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
				JLabel label = new JLabel("Mode");
				label.setFont(new Font("Tahoma", Font.BOLD, 15));
				label.setForeground(Color.DARK_GRAY);
				panel.add(label);
				// player vs player or computer vs player
				JRadioButton pvp = new JRadioButton("Player vs Player");
				panel.add(pvp);
				JRadioButton cvp = new JRadioButton("Computer vs Player");
				panel.add(cvp);

				ButtonGroup gr = new ButtonGroup();
				gr.add(pvp);
				gr.add(cvp);

				// create button ok
				JButton btOk = new JButton("OK");
				panel.add(btOk, BorderLayout.SOUTH);

				// event button OK
				btOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (pvp.isSelected() || cvp.isSelected()) {
							JDialog nameDialog = new JDialog(di, "Choose Side", true);
							JPanel panel = new JPanel(new BorderLayout());
							panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
							JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));

							// choose side
							JLabel sideLabel = new JLabel("Choose your side:");
							String[] sides = { "White", "Black" };
							JComboBox<String> sideComboBox = new JComboBox<>(sides);
							inputPanel.add(sideLabel);
							inputPanel.add(sideComboBox);

							JLabel whiteLabel = new JLabel("Enter White name:");
							JTextField whiteNameField = new JTextField();
							inputPanel.add(whiteLabel);
							inputPanel.add(whiteNameField);

							JLabel blackLabel = new JLabel("Enter Black name:");
							JTextField blackNameField = new JTextField();
							inputPanel.add(blackLabel);
							inputPanel.add(blackNameField);

							panel.add(inputPanel, BorderLayout.CENTER);

							// create button Save
							JButton btSave = new JButton("Save");
							panel.add(btSave, BorderLayout.SOUTH);
							nameDialog.add(panel);

							// click save
							btSave.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									String whiteName = whiteNameField.getText();
									String blackName = blackNameField.getText();

									if (!whiteName.isEmpty() && !blackName.isEmpty()) {
										// name player
										System.out.println("White Name: " + whiteName);
										System.out.println("Black Name: " + blackName);
										nameDialog.dispose();
									} else {
										JOptionPane.showMessageDialog(nameDialog,
												"Please enter names for both players.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							});

							nameDialog.setSize(400, 200);
							nameDialog.setLocationRelativeTo(di);
							nameDialog.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(di, "Please choose a mode.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				di.add(panel);
				di.setSize(400, 200);
				di.setVisible(true);
				di.setLocationRelativeTo(null);
			}

		});
		// create button next
		ImageIcon next = new ImageIcon("img/next.png");
		JButton btNext = new JButton(next);
		btNext.setOpaque(false);// lam trong suot button
		btNext.setBorderPainted(false);
		btNext.setPreferredSize(new Dimension(300, 300));
		btNext.setFocusPainted(false);
		btNext.setContentAreaFilled(false);
		btNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// event for next
		btNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChessGameView chess = new ChessGameView();
				chess.setVisible(true);
				dispose();
			}
		});

		// add panel
		button.add(btMode);
		button.add(btNext);
		panel.add(button, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModeView view = new ModeView();
		view.setVisible(true);
	}

}
