import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author ozan
 *
 */
public class chewyLokumController implements MouseListener, Runnable {
	private static chewyLokumController instance;

	private List<pair<position>> swapList;
	private int allowedSwaps;
	private int specialSwapCount;
	private position currentClickedPos;
	private gameEngine engine;
	private graphicsEngine gEngine;
	public scorePanel scoreEngine;
	private boolean pause;

	private int level;
	private int scoreAim;

	private int possibleMoveCount;
	private int playerScore;
	private List<JPanel> subscribedPanels;

	private chewyLokumController() {
		subscribedPanels = new LinkedList<JPanel>();
	}

	public static chewyLokumController getInstance() {
		if (instance == null)
			instance = new chewyLokumController();
		return instance;
	}

	/**
	 * @param scoreAim
	 * @param currentScore
	 * @param moveCount
	 * @param allowedSwaps
	 * @param level
	 * @requires 
	 * 
	 * @modifies
	 * @ensures
	 */
	public void init(int scoreAim, int currentScore, int moveCount,
			int allowedSwaps, int level) {
		pause = false;
		this.allowedSwaps = allowedSwaps;
		swapList = new LinkedList<pair<position>>();
		engine = gameEngine.getInstance();

		gEngine = graphicsEngine.getInstance();
		scoreEngine = new scorePanel();
		subscribedPanels.add(gEngine);
		subscribedPanels.add(scoreEngine);

		playerScore = currentScore;
		this.possibleMoveCount = moveCount;
		this.scoreAim = scoreAim;

	}

	public void init(int scoreAim, int currentScore, int moveCount,
			int allowedSwaps, int level, int allowedSpecialSwapCount) {
		pause = false;
		this.allowedSwaps = allowedSwaps;
		swapList = new LinkedList<pair<position>>();
		engine = gameEngine.getInstance();

		gEngine = graphicsEngine.getInstance();
		scoreEngine = new scorePanel();
		subscribedPanels.add(gEngine);
		subscribedPanels.add(scoreEngine);

		specialSwapCount = allowedSpecialSwapCount;
		playerScore = currentScore;
		this.possibleMoveCount = moveCount;
		this.scoreAim = scoreAim;

	}

	/**
	 * @requires a Controller Object instantiated.
	 * @modifies checks the stage of user input, then will modify the
	 *           gameEngine.
	 * @ensures gameEngine is fed with inputs, result of these inputs should be
	 *          reflected on graphicsEngine.
	 */
	public void processClick(position pos) {
		if (!pause) {
			pos = normalizePos(pos);
			System.out.print(engine.askType(pos));
			if (swapList.size() <= allowedSwaps && engine.askType(pos) != null
					&& engine.askType(pos) != 0) {
				if (currentClickedPos == null) {
					currentClickedPos = pos;
					gEngine.addToClicked(pos);
					paintSubscritions();
				} else {
					if (!currentClickedPos.equals(pos)) {
						if (eligibleSwap(pos, currentClickedPos)) {
							swapList.add(new pair<position>(pos,
									currentClickedPos));
							currentClickedPos = null;
							gEngine.addToClicked(pos);
							if (swapList.size() == allowedSwaps) {
								engine.applyChanges(swapList);
								currentClickedPos = null;
								swapList.clear();
								gEngine.removeFromClickeds();
								possibleMoveCount--;
								playerScore = engine.getCurrentScore();

								processGameOverScenerio();
							}
							paintSubscritions();
						} else if (specialSwapCount > 0) {
							swapList.add(new pair<position>(pos,
									currentClickedPos));
							currentClickedPos = null;
							gEngine.addToClicked(pos);
							if (swapList.size() == allowedSwaps) {
								engine.applyChangesSpecial(swapList);
								currentClickedPos = null;
								swapList.clear();
								gEngine.removeFromClickeds();
								possibleMoveCount--;
								specialSwapCount--;
								playerScore = engine.getCurrentScore();
								
								processGameOverScenerio();
							}
							paintSubscritions();
						} else {
							gEngine.removeFromClicked(currentClickedPos);
							currentClickedPos = null;
							paintSubscritions();
						}
					} else {
						gEngine.removeFromClicked(currentClickedPos);
						currentClickedPos = null;
						paintSubscritions();
					}
				}
			}
		}
	}

	public int getPossibleMoveCount() {
		return possibleMoveCount;
	}

	private position normalizePos(position pos) {
		int x = pos.getX() / gameProperties.getShapeSize();
		int y = pos.getY() / gameProperties.getShapeSize();
		return new position(x, y);
	}

	public List<pair<position>> getChanges() {
		return swapList;
	}

	private boolean eligibleSwap(position pos1, position pos2) {
		int x1 = pos1.getX();
		int y1 = pos1.getY();
		int x2 = pos2.getX();
		int y2 = pos2.getY();

		if (engine.askType(pos1) != 0 && engine.askType(pos2) != 0) {
			if (x1 - x2 <= 1 && x1 - x2 >= -1 && y1 - y2 <= 1 && y1 - y2 >= -1) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	/**
	 * 
	 * @requires game should continue to pause the game
	 * @modifies  stops the game continuation 
	 * @ensures    game can not be played.
	 */
	public void pauseGame() {
		pause = true;
	}

	/**
	 * @return playerscore
	 * @requires score
	 * @modifies
	 * @ensures   there is a score which is returned.
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * @return  time left
	 * @requires   
	 * @modifies
	 * @ensures
	 */
	public int getTimer() {
		return engine.getTime();
	}

	private void paintSubscritions() {
		for (int i = 0; i < subscribedPanels.size(); i++) {
			subscribedPanels.get(i).repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int clickedPosX = e.getPoint().x;
		int clickedPosY = e.getPoint().y;
		processClick(new position(clickedPosX, clickedPosY));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			paintSubscritions();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int getScoreAim() {
		return scoreAim;
	}

	public int getLevel() {
		return level;
	}

	public int getSpecialSwapCount() {
		return specialSwapCount;
	}

	private void processGameOverScenerio(){
		if(playerScore >= scoreAim){
			
		}
		
		if(possibleMoveCount <= 0){
			gameOverPop gameOver = new gameOverPop(
					false,playerScore);
			pauseGame();
			gameOver.show();
		}
		
		if(engine.getTime() <= 0){
			gameOverPop gameOver = new gameOverPop(
					false,playerScore);
			pauseGame();
			gameOver.show();
		}
	}
	
}
