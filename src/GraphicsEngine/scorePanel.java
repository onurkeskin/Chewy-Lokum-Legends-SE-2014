import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Insets;
import java.awt.GridLayout;


public class scorePanel extends JPanel {

	int score;
	int moves;
	int level;
	int timer;
	int specialMoveCount;
	JLabel levelLabel;
	JLabel movesLabel;
	JLabel scoreLabel;
	JLabel timerLabel;
	JLabel specialMovesLabel;
	private JLabel goalLabel;
	public scorePanel(){
		setLayout(new GridLayout(0, 1, 0, 0));
		
		goalLabel = new JLabel("Goal: ");
		add(goalLabel);
		
				
		scoreLabel = new JLabel();
		scoreLabel.setText("Score: ");
		add(scoreLabel);
		
		movesLabel = new JLabel();
		movesLabel.setText("Moves left: ");
		add(movesLabel);
		
		timerLabel = new JLabel();
		timerLabel.setText("Timer: ");
		add(timerLabel);
		
		levelLabel = new JLabel();
		levelLabel.setText("Level: ");
		add(levelLabel);
		
		specialMovesLabel = new JLabel();
		specialMovesLabel.setText("Special Move Count: ");
		add(specialMovesLabel);
		
		Font usedFont = new Font("Helvetica", Font.PLAIN, 18);
		goalLabel.setFont(usedFont);
		scoreLabel.setFont(usedFont);
		movesLabel.setFont(usedFont);
		levelLabel.setFont(usedFont);
		timerLabel.setFont(usedFont);
		specialMovesLabel.setFont(usedFont);
	}
	
	public void paint(Graphics g){
		chewyLokumController cont = chewyLokumController.getInstance();			
		score = cont.getPlayerScore();
		moves = cont.getPossibleMoveCount();
		timer = cont.getTimer();
		specialMoveCount = cont.getSpecialSwapCount();
		level = cont.getLevel();
		
		
		goalLabel.setText("Goal: " + cont.getScoreAim());
		
		scoreLabel.setText("Score: "+score);

		movesLabel.setText("Moves left:"+moves);

		levelLabel.setText("Level :"+level);
		
		timerLabel.setText("Timer: " + timer);
		
		specialMovesLabel.setText("Special Move Count: " + specialMoveCount);
		
		super.paint(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	
}
