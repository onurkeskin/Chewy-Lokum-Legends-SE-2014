import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;

public class gameOverPop extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private boolean gameOverType;

	private double score;

	/**
	 * Create the frame.
	 */
	public gameOverPop(boolean gameOverType, double Score) {

		this.score = Score;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 533, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		if (!gameOverType) {
			if (scoreK()) {
				JLabel lblConguratilations = new JLabel(
						"Congratulations your Final Score: ");
				lblConguratilations.setBounds(12, 9, 208, 22);
				contentPane.add(lblConguratilations);

				JLabel scoreField = new JLabel("CurrentScore" + score);
				scoreField.setBounds(208, 12, 21, 16);
				contentPane.add(scoreField);

				textField = new JTextField();
				textField.setBounds(88, 41, 200, 22);
				contentPane.add(textField);
				textField.setColumns(10);

				JLabel lblNewLabel = new JLabel("Enter Name:");
				lblNewLabel.setBounds(12, 44, 81, 16);
				contentPane.add(lblNewLabel);

				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new highScoresAction("Action",
						KeyEvent.VK_A));
				btnSubmit.setBounds(208, 76, 97, 25);
				contentPane.add(btnSubmit);
			} else {
				JLabel lblConguratilations = new JLabel("FAIL");
				lblConguratilations.setBounds(12, 9, 208, 22);
				contentPane.add(lblConguratilations);

			}
		} else {
			JLabel lblConguratilations = new JLabel(
					"Congratulations Move On to the Next Level)");
			lblConguratilations.setBounds(12, 9, 208, 22);
			contentPane.add(lblConguratilations);
		}
	}

	private JFrame getOuter() {
		return this;
	}

	private class highScoresAction extends AbstractAction {

		public highScoresAction(String name, Integer mnemonic) {
			super(name);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			if (!gameOverType) {
				if (textField.getText() != "") {
					highScoreManip manip = new highScoreManip("scores.txt");
					try {
						manip.putScore(score, textField.getText());
						getOuter().dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Name must be given",
							"Name must be given", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else {
				getOuter().dispose();
			}
		}

	}

	private boolean scoreK() {
		highScoreManip scoreCheck = new highScoreManip("scores.txt");
		return (-1 != scoreCheck.checkScore(score));

	}

}
