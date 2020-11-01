import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import javax.management.InvalidAttributeValueException;
import javax.management.timer.Timer;
import javax.rmi.CORBA.Tie;

/**
 * @author Onur
 *
 */
public class gameEngine implements Runnable {
	private player player;

	private static gameEngine instance;
	private RuleControl ruleControl;
	private gameBlock[][] env;
	private Queue<SLPlace> slQueue;
	private List<position> destroyedPos;

	private HashMap<Integer, gameBlock> intBlockMap;
	private int possibleNormalLokumCount;
	private int possibleSpecialLokumCount;
	private gameBlock[] gameSeed;

	private AnimationLayer changeList;
	private long totalTime;
	private long timer;
	private long remainingTime;

	private gameEngine() {

	}

	/**
	 * @requires A gameEngine Object created.
	 * @modifies Fields of gameEngine object are set to objects given by the
	 *           method parameters.
	 * @ensures gameEngine object can now be used for other methods.
	 */
	public void init(int totalTime, int NLC, int SLC) {
		this.totalTime = totalTime;
		player = new player();
		destroyedPos = new LinkedList<position>();
		slQueue = new LinkedList<SLPlace>();
		ruleControl = RuleControl.getInstance();
		changeList = new AnimationLayer();
		possibleNormalLokumCount = NLC;
		possibleSpecialLokumCount = SLC;

		timer = new Date().getTime();
		remainingTime = 0;
		// intBlockMap = new HashMap<Integer, gameBlock>();
		// for(int i = 0; i<NLC+SLC;i++){
		//
		// }
	}

	public void init(int totalTime, gameBlock[] seed, int SLC, gameBlock[][] gameEnv,
			player p) {
		this.totalTime = totalTime;
		player = p;
		destroyedPos = new LinkedList<position>();
		slQueue = new LinkedList<SLPlace>();
		ruleControl = RuleControl.getInstance();
		changeList = new AnimationLayer();
		possibleNormalLokumCount = seed.length;
		possibleSpecialLokumCount = SLC;
		env = gameEnv;
		timer = new Date().getTime();
		remainingTime = 0;
		// intBlockMap = new HashMap<Integer, gameBlock>();
		// for(int i = 0; i<NLC+SLC;i++){
		//
		// }
		gameSeed = seed;
	}
	
	public void init(int totalTime, int NLC, int SLC, gameBlock[][] gameEnv,
			player p) {
		this.totalTime = totalTime;
		player = p;
		destroyedPos = new LinkedList<position>();
		slQueue = new LinkedList<SLPlace>();
		ruleControl = RuleControl.getInstance();
		changeList = new AnimationLayer();
		possibleNormalLokumCount = NLC;
		possibleSpecialLokumCount = SLC;
		env = gameEnv;
		timer = new Date().getTime();
		remainingTime = 0;
		// intBlockMap = new HashMap<Integer, gameBlock>();
		// for(int i = 0; i<NLC+SLC;i++){
		//
		// }
		gameSeed = new gameBlock[NLC];
		for (int i = 1; i < NLC + 1; i++) {
			try {
				gameSeed[i - 1] = new lokum(i);
			} catch (InvalidAttributeValueException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return A created game or running game Instance.
	 * @requires ---
	 * @modifies if an instance of gameEngine is available returns it, else
	 *           creates a new instance of gameEngine and returns it.
	 * @ensures a gameEngine class object.
	 */
	public static gameEngine getInstance() {
		if (instance == null)
			instance = new gameEngine();
		return instance;
	}

	// Checks the swap pairs first, if they are illegal throws an exception, if
	// not
	// will continue to do swaps.
	/**
	 * @throws IllegalArgumentException
	 * 
	 * @requires A game instance that is created, controller should give the
	 *           input of list of pairs to swap.
	 * @modifies for each pair object right pair and left pair locations on the
	 *           board are swapped if the given object is not illegal
	 * @ensures A game board with changed locations that come from the
	 *          controller
	 */
	public void applyChanges(List<pair<position>> pairs)
			throws IllegalArgumentException {
		for (int i = 0; i < pairs.size(); i++) {
			if (!eligibleSwap(pairs.get(i).left, pairs.get(i).right)) {
				throw new IllegalArgumentException();
			}
		}

		for (int i = 0; i < pairs.size(); i++) {
			int pos1X = pairs.get(i).left.getX();
			int pos1Y = pairs.get(i).left.getY();

			int pos2X = pairs.get(i).right.getX();
			int pos2Y = pairs.get(i).right.getY();

			gameBlock temp = env[pos2X][pos2Y];
			env[pos2X][pos2Y] = env[pos1X][pos1Y];
			env[pos1X][pos1Y] = temp;
		}

		applyRulesOnSwaps(pairs);

	}

	private boolean applySwapsRules(List<pair<position>> pairs) {
		RuleQueue gameRules = ruleControl.getRules();
		boolean toReturn = false;
		Iterator<Rule> rator = gameRules.iterator();
		for (int i = 0; i < pairs.size(); i++) {
			while (rator.hasNext()) {
				Rule rule = rator.next();
				boolean compare = applyRuleOnPos(rule, pairs.get(i).left);
				if (compare)
					return true;
				compare = applyRuleOnPos(rule, pairs.get(i).right);
				if (compare)
					return true;
			}

		}
		return toReturn;
	}

	// checks if the pos is in game border locations.Helper for eligible swap
	private boolean eligiblePos(position pos) {
		int x1 = pos.getX();
		int y1 = pos.getY();

		if (x1 >= 0 && x1 < env.length && y1 >= 0 && y1 < env[0].length
				&& env[x1][y1] != null && env[x1][y1].getType() != 0)
			return true;
		else
			return false;
	}

	// eligibleSwap tests pos1 and pos2 are positions that can be exist on
	// board, and
	// If they are eligible to swap (meaning that they are adjacent)
	// This function look like also occurs in the chewyLokumController
	private boolean eligibleSwap(position pos1, position pos2) {
		int x1 = pos1.getX();
		int x2 = pos2.getX();

		int y1 = pos1.getY();
		int y2 = pos2.getY();

		// "^" operator in java means XOR. It is logical to use it here since
		// adjaceny in this
		// context means only horizontal and vertical, not diagonal.
		if (eligiblePos(pos1) && eligiblePos(pos2)) {
			if (x1 - x2 <= 1 && x1 - x2 >= -1 && y1 - y2 <= 1 && y1 - y2 >= -1) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private boolean applyRulesOnStart() {
		boolean process = false;
		while (applyNormalRules()) {
			applySpecialRules();
			addSLs();
			rebalance();
			process = true;
		}
		return process;
	}

	private boolean applyRulesOnSwaps(List<pair<position>> pairs) {
		for (int i = 0; i < pairs.size(); i++) {
			changeList.addSwapToCurrentLayer(pairs.get(i).left,
					pairs.get(i).right);
		}
		changeList.advanceLayer();
		boolean process = false;
		boolean goOn = applySwapsRules(pairs);
		if (goOn) {
			changeList.advanceLayer();
			applySpecialRules();
			changeList.advanceLayer();
			addSLs();
			changeList.advanceLayer();
			rebalance();
			changeList.advanceLayer();
			process = true;
			while (!destroyedPos.isEmpty()) {
				boolean toRemove = applyRulesOnPositions(untilTop(destroyedPos));
				if (toRemove){
					destroyedPos.clear();}
				changeList.advanceLayer();
				applySpecialRules();
				changeList.advanceLayer();
				addSLs();
				changeList.advanceLayer();
				rebalance();
				changeList.advanceLayer();
				//
				// int asda = new Random().nextInt(5);
				if (!destroyedPos.isEmpty()) {
					changeList.advanceLayer();
				}
				
			}
		}
		return process;
	}

	private List<position> untilTop(List<position> destroyedPos) {
		List<position> returnList = new LinkedList<position>();

		for (int i = 0; i < destroyedPos.size(); i++) {
			position currPos = destroyedPos.get(i);
			for (int x = 0; x <= currPos.getX(); x++) {
				returnList.add(new position(x, currPos.getY()));
			}
		}
		return returnList;
	}

	private void rebalance() {
		for (int i = 0; i < destroyedPos.size(); i++) {
			position currPos = destroyedPos.get(i);
			for (int x = currPos.getY(); x >= 0; x--) {
				if (x - 1 >= 0 && env[currPos.getX()][x] == null) {
					env[currPos.getX()][x] = env[currPos.getX()][x - 1];
					env[currPos.getX()][x - 1] = null;
					changeList.addMoveToCurrentLayer(
							new SLPlace(env[currPos.getX()][x], new position(
									currPos.getX(), x - 1)), new SLPlace(
									env[currPos.getX()][x], new position(
											currPos.getX(), x)));
					// changeList.advanceLayer();
				}
			}
		}

		for (int i = 0; i < env.length; i++) {
			for (int j = 0; j < env[0].length; j++) {
				if (env[i][j] == null) {
					gameBlock block = RandomizeBlock(new position(i, j));
					if(block == null){
						block = RandomizeBlock(new position(i, j));
					}
					changeList.addMoveToCurrentLayer(null, new SLPlace(block,
							new position(i, j)));

				}
			}
		}

	}

	private gameBlock RandomizeBlock(position pos) {

		LinkedList<gameBlock> chooseFrom = new LinkedList<gameBlock>();
		try {
			chooseFrom.add(env[pos.getX() - 1][pos.getY()]);
		} catch (Exception e) {
		}
		try {
			chooseFrom.add(env[pos.getX() + 1][pos.getY()]);
		} catch (Exception e) {
		}
		try {
			chooseFrom.add(env[pos.getX()][pos.getY() - 1]);
		} catch (Exception e) {
		}
		try {
			chooseFrom.add(env[pos.getX()][pos.getY() + 1]);
		} catch (Exception e) {
		}

		LinkedList<gameBlock> uniqueList = new LinkedList<gameBlock>(
				new HashSet<gameBlock>(chooseFrom));

		if (uniqueList.size() < gameSeed.length) {
			LinkedList<gameBlock> eligibleLoks = new LinkedList<gameBlock>();
			
			gameBlock timeLokum = null;
			
			for (int i = 0; i < gameSeed.length; i++) {
				boolean pass = true;
				for (int j = 0; j < uniqueList.size(); j++) {
					if (uniqueList.get(j) == gameSeed[i]) {
						pass = false;
					}
				}
				
				if (pass)
					if (!(gameSeed[i].getType() == 13)){
						eligibleLoks.add(gameSeed[i]); 
					}else{
						timeLokum = gameSeed[i];
					}		
			}

			if (eligibleLoks.size() > 0) {
				Random rnd = new Random();
				int timeLokChance = rnd.nextInt(100);
				if(timeLokum == null) timeLokChance = 20;
				if(timeLokChance>5){
				int num = rnd.nextInt(eligibleLoks.size());
				env[pos.getX()][pos.getY()] = eligibleLoks.get(num);
				return eligibleLoks.get(num);
				}else{
					env[pos.getX()][pos.getY()] = timeLokum;
					if(timeLokum == null){
						return timeLokum;
					}
					return timeLokum;
				}
			} else {
				Random rnd = new Random();
				int num = rnd.nextInt(possibleNormalLokumCount);
				env[pos.getX()][pos.getY()] = gameSeed[num];
				return gameSeed[num];
			}
		} else {
			Random rnd = new Random();
			int num = rnd.nextInt(possibleNormalLokumCount);
			env[pos.getX()][pos.getY()] = gameSeed[num];
			return gameSeed[num];
		}
	}

	private boolean applyNormalRules() {
		RuleQueue gameRules = ruleControl.getRules();
		boolean toReturn = false;
		Iterator<Rule> rator = gameRules.iterator();
		while (rator.hasNext()) {
			Rule rule = rator.next();
			boolean compare = applyRuleOnBoard(rule);
			if (compare) {
				toReturn = true;
			}
		}

		return toReturn;
	}

	private void applySpecialRules() {
		ruleLogicPair currSR = ruleControl.getNextSR();
		while (currSR != null) {
			propertyPack pack = currSR.getRule()
					.applyRule(env, currSR.getPos());
			if (pack.getDestroyedPositions() != null) {
				for (int i = 0; i < pack.getDestroyedPositions().size(); i++) {
					destroy(pack.getDestroyedPositions().get(i));
				}
				player.setScore(player.getScore() + pack.getScore());
			}
			if (pack.getTime() != 0) {
				timer += pack.getTime() * 1000;
			}
			currSR = ruleControl.getNextSR();
		}
	}

	private void addSLs() {
		while (!slQueue.isEmpty()) {
			SLPlace curr = slQueue.remove();
			put(curr.getLokum(), curr.getPos());
			changeList.addMoveToCurrentLayer(null, new SLPlace(curr.getLokum(),
					curr.getPos()));
		}
	}

	private boolean applyGenericRule(Rule appliedRule, position pos) {
		boolean k = applyRuleOnPos(appliedRule, pos);
		return k;
	}

	private boolean applyRulesOnPositions(List<position> positions) {
	RuleQueue gameRules = ruleControl.getRules();
	boolean toReturn = false;
	Iterator<Rule> rator = gameRules.iterator();

	while (rator.hasNext()) {
		Rule rule = rator.next();
		for (int i = 0; i < positions.size(); i++) {
			boolean compare= applyRuleOnPos(rule, positions.get(i));
			if (compare)
				changeList.advanceLayer();
				toReturn = true;
		}
	}
	return toReturn;
}

	private boolean applyRuleOnPos(Rule appliedRule, position pos) {

		Integer[][] asArr = appliedRule.getRuleEnv();
		LinkedList<position> filledPos = Helpers.filledPos(asArr);
		propertyPack pack = new propertyPack();

		int posX = pos.getX();
		int posY = pos.getY();

		for (int i = 0; i < filledPos.size(); i++) {
			if (pack.isEmpty()) {
				int relX = posX - filledPos.get(i).getX();
				int relY = posY - filledPos.get(i).getY();

				pack = appliedRule.applyRule(env, new position(relX, relY));

				if (!pack.isEmpty()) {
					if (pack.getDestroyedPositions() != null) {
						for (int a = 0; a < pack.getDestroyedPositions().size(); a++) {
							destroy(pack.getDestroyedPositions().get(a));
						}
						player.setScore(player.getScore() + pack.getScore());
					}
					if (pack.getLokum() != null) {
						slQueue.add(new SLPlace(pack.getLokum(), new position(
								pos.getX(), pos.getY())));
					}
					if (pack.getTime() != 0) {
						timer += pack.getTime();
					}
					return true;
				}
			} else
				break;
		}

		return !pack.isEmpty();
	}

	private boolean applyRuleOnBoard(Rule appliedRule) {

		boolean toReturn = false;
		for (int i = 0; i < env.length; i++) {
			for (int j = 0; j < env[0].length; j++) {
				boolean toAdd = applyGenericRule(appliedRule,
						new position(i, j));
				if (toAdd)
					return true;
			}
		}
		return toReturn;
	}

	private void destroy(position pos) {
		gameBlock destroyedBlock = env[pos.getX()][pos.getY()];
		if (destroyedBlock != null) {
			propertyPack pack = destroyedBlock.destroy();
			if (pack != null) {
				if (pack.getDestroyedPositions() != null) {

				}
				if (pack.getScore() != 0) {
					player.setScore(player.getScore() + pack.getScore());
				}
				if (pack.getTime() != 0) {
					timer += pack.getTime() * 1000;
				}

				if (pack.getRule() != null) {
					ruleControl.addSRule(pack.getRule(), pos);
				}
			}

			// if (destroyedBlock.getType() < 0) {
			// ruleControl.addSRule(destroyedBlock.returnSR(), pos);
			// }

			destroyedPos.add(pos);
			changeList.addDestroyToCurrentLayer(pos);
			env[pos.getX()][pos.getY()] = null;
		}
	}

	private boolean emptySpot(position pos) {
		if (eligiblePos(pos) && env[pos.getX()][pos.getY()] == null)
			return true;
		return false;
	}

	private void put(gameBlock block, position pos) {
		env[pos.getX()][pos.getY()] = block;
	}

	/**
	 * @return gameEnvironment as an integer array.
	 * @requires An initiated game Environment object
	 * @modifies nothing.
	 * @ensures gameEnvironment object is not changed in any way and the user
	 *          only gets the values of game blocks within the board.
	 */
	public Integer[][] gameAsInt() {
		Integer[][] toReturn = new Integer[env.length][env[0].length];

		for (int i = 0; i < env.length; i++) {
			for (int j = 0; j < env[0].length; j++) {
				if (env[i][j] != null)
					toReturn[i][j] = env[i][j].getType();
			}
		}
		return toReturn;
	}

	public int getPossibleNormalLokumCount() {
		return possibleNormalLokumCount;
	}

	public int getPossibleSpecialLokumCount() {
		return possibleSpecialLokumCount;
	}

	/**
	 * @requires an initiated gameEngine object
	 * @modifies the environment arrays array indexes are set to randomly chosen
	 *           gameblocks.
	 * @ensures a gameblock array with no empty spaces.
	 */
	public void generateRandomStart(gameBlock[] seed, int rowCount, int colCount) {
		env = new gameBlock[rowCount][colCount];
		gameSeed = seed;

		int newSpecial = 0;
		int newNormal = 0;

		// for (int count = 0; count < seed.length; count++) {
		// if (seed[count].getType() < 0) {
		// newSpecial++;
		// } else if (seed[count].getType() > 0) {
		// newNormal++;
		// }
		// }
		//
		// possibleNormalLokumCount = newNormal;
		// possibleSpecialLokumCount = newSpecial;
		//
		// Random rng = new Random();
		// int totalSize = possibleNormalLokumCount;;
		//
		// for (int i = 0; i < env.length; i++) {
		// for (int j = 0; j < env[0].length; j++) {
		// int random = rng.nextInt(totalSize);
		// env[i][j] = seed[random];
		// }
		// }

		for (int i = 0; i < env.length; i++) {
			for (int j = 0; j < env[0].length; j++) {
				if (env[i][j] == null)
					RandomizeBlock(new position(i, j));
			}
		}

		applyRulesOnStart();
		
		remainingTime = totalTime;
		changeList = new AnimationLayer();
		player.resetScore();
		slQueue.clear();
		destroyedPos.clear();
	}

	public Integer askType(position pos) {
		if (env[pos.getX()][pos.getY()] != null)
			return env[pos.getX()][pos.getY()].getType();
		else
			return null;
	}

	public int getCurrentScore() {
		return player.getScore();
	}

	public void run() {
		while (true) {
			remainingTime = totalTime + ((timer - new Date().getTime()) / 1000);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getTime() {
		return (int) remainingTime;
	}

	public boolean repOK() {
		if (instance != null && ruleControl != null && isEnvOK()
				&& ruleControl.repOK() && player.repOK()) {
			return true;
		}
		return false;
	}

	public String toString() {
		String toReturn = "";
		toReturn += "PLAYER: name: " + player.getName() + "score: "
				+ player.getScore() + "\n";
		toReturn += "RULES: " + ruleControl.toString() + "\n";
		toReturn += "GAME ENVIRONMENT:" + printEnv() + "\n";
		toReturn += "SLOKUM QUEUE" + slQueue.toString() + "\n";
		toReturn += "TIMERS: total time: " + totalTime + "remaining time: "
				+ remainingTime + "\n";
		return toReturn;
	}

	private String printEnv() {
		String toReturn = "";
		for (int i = 0; i < env.length; i++) {
			for (int j = 0; j < env[0].length; j++) {
				if (env[i][j] != null && env[i][j].repOK())
					toReturn += " x: " + i + " y: " + j + " type: "
							+ env[i][j].toString() + "|";
			}
			toReturn += "\n";
		}
		return toReturn;
	}

	private boolean isEnvOK() {
		for (int i = 0; i < env.length; i++) {
			for (int j = 0; j < env[0].length; j++) {
				if (env[i][j] == null || !env[i][j].repOK()) {
					return false;
				}
			}
		}
		return true;
	}

	public void applyChangesSpecial(List<pair<position>> pairs) {
		for (int i = 0; i < pairs.size(); i++) {
			int pos1X = pairs.get(i).left.getX();
			int pos1Y = pairs.get(i).left.getY();

			int pos2X = pairs.get(i).right.getX();
			int pos2Y = pairs.get(i).right.getY();

			gameBlock temp = env[pos2X][pos2Y];
			env[pos2X][pos2Y] = env[pos1X][pos1Y];
			env[pos1X][pos1Y] = temp;
		}

		applyRulesOnSwaps(pairs);
	}

	public AnimationLayer getChangeList() {
		return changeList;
	}

}
