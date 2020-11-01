

import javax.imageio.ImageIO;
import javax.management.InvalidAttributeValueException;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



public class mainMenu extends JPanel{
	private ImageIcon img;
	

	public mainMenu(){
		repaint();
		setBounds(0,0,800,800);
		setLayout(null);
		
		JButton btnContinue = new JButton("CONTINUE");
		btnContinue.setFont(new Font("Calibri", Font.BOLD, 22));
		btnContinue.setBounds(300, 458, 211, 61);
		btnContinue.addActionListener(new continueAction("Action", KeyEvent.VK_A));
		add(btnContinue);
		
		JButton btnNewGame = new JButton("NEW GAME");
		btnNewGame.setFont(new Font("Calibri", Font.BOLD, 22));
		btnNewGame.setBounds(300, 532,  211, 61);
		btnNewGame.addActionListener(new playAction("Action", KeyEvent.VK_A));
		add(btnNewGame);
		
		JButton btnOptions = new JButton("SETTINGS");
		btnOptions.setFont(new Font("Calibri", Font.BOLD, 22));
		btnOptions.setBounds(300, 606, 211, 61);
		add(btnOptions);
		
		JButton btnHighScore = new JButton("HIGH SCORES");
		btnHighScore.setFont(new Font("Calibri", Font.BOLD, 22));
		btnHighScore.setBounds(300, 680, 211, 61);
		btnHighScore.addActionListener(new highScoresAction("Action", KeyEvent.VK_A));
		add(btnHighScore);
		
		JButton btnExit = new JButton("EXIT GAME");
		btnExit.setFont(new Font("Impact", Font.PLAIN, 14));
		btnExit.setForeground(Color.RED);
		btnExit.setBounds(626, 714, 97, 25);
		add(btnExit);
		

	}
	
	 protected void paintComponent(Graphics g) {

		    super.paintComponent(g);
			try {
				BufferedImage img=ImageIO.read(new File("content/Untitled-2X.jpg"));
				g.drawImage(img,0,0,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public JPanel getOuter(){
		return this;
	}
	
	private class playAction extends AbstractAction {
		
		
		public playAction(String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }

	      public void actionPerformed(ActionEvent e) {
		    gameProperties.loadProperties();
	    	getOuter().removeAll();
	    	game frame = new game();
			gameStart game = new gameStart(frame.gamePanel,frame.info);
			game.playGame();
			frame.pack();
			frame.setVisible(true);
	      }
	   }
	
	private class highScoresAction extends AbstractAction {
		
		public highScoresAction (String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }

	      public void actionPerformed(ActionEvent e){
	    	  JFrame highScoreFrame = new JFrame();
	    	  highScoreFrame.repaint();
	    	  highScores score = new highScores("scores.txt");
	    	  score.setBounds(0, 0, 400,406);
	    	  highScoreFrame.setBounds(0, 0, 400,406);
	    	  highScoreFrame.getContentPane().add(score);
	    	  highScoreFrame.show();
    	 
	      }
	}

private class continueAction extends AbstractAction {
		
		
		public continueAction(String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }
		
	      public void actionPerformed(ActionEvent e) {
	    	chewyBlob blob = saveLoadManager.loadProperties();
		    gameProperties.loadProperties();
	    	getOuter().removeAll();
	    	game frame = new game();
	    	
			gameStart game = new gameStart(frame.gamePanel,frame.info);
			player p = new player();
			p.setName(blob.getPlayerName());
			p.setScore(blob.getScore());
			
			game.playGame(blob.getGameEnv(),blob.getGameSeed(),p, blob.getGoalScore(), blob.getScore(), blob.getMovesleft(), blob.getLevel(), blob.getSeedSize(),blob.getRemaningTime(),blob.getSpecialSwapCount());
			frame.pack();
			frame.setVisible(true);
	    	  
	      }
	   }


}
