import java.util.LinkedList;

public class chewyBlob {

	private int seedSize;
	private LinkedList<SLPlace> lists;
	private String playerName;
	private Integer goalScore;
	private Integer score;
	private Integer movesleft;
	private Integer level;
	private long remainingTime;
	private int specialSwapCount;
	private LinkedList<Integer>  gameSeed;

	public chewyBlob(LinkedList<SLPlace> lists,LinkedList<Integer> gameSeed2, String playerName,
			Integer goalScore, Integer score, Integer movesleft, Integer level,
			int seedSize, long remainingTime, int specialSwapCount) {
		super();
		this.lists = lists;
		this.playerName = playerName;
		this.goalScore = goalScore;
		this.score = score;
		this.movesleft = movesleft;
		this.level = level;
		this.seedSize = seedSize;
		this.specialSwapCount = specialSwapCount;
		this.remainingTime = remainingTime;
		this.gameSeed=gameSeed2;
	}

	public gameBlock[][] getGameEnv() {
		gameBlock[][] toReturn = new gameBlock[getHighestRow(lists)][getHighestColumn(lists)];
		for (int i = 0; i < lists.size(); i++) {
			toReturn[lists.get(i).getPos().getX()][lists.get(i).getPos().getY()] = lists
					.get(i).getLokum();
		}

		return toReturn;
	}

	public void setLists(LinkedList<SLPlace> lists) {
		this.lists = lists;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getGoalScore() {
		return goalScore;
	}

	public void setGoalScore(Integer goalScore) {
		this.goalScore = goalScore;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMovesleft() {
		return movesleft;
	}

	public void setMovesleft(Integer movesleft) {
		this.movesleft = movesleft;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	private int getHighestRow(LinkedList<SLPlace> list) {
		int toReturn = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPos().getX() > toReturn)
				toReturn = list.get(i).getPos().getX();
		}
		return toReturn + 1;
	}

	private int getHighestColumn(LinkedList<SLPlace> list) {
		int toReturn = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPos().getY() > toReturn)
				toReturn = list.get(i).getPos().getY();
		}
		return toReturn + 1;
	}

	public int getSeedSize() {
		return seedSize;
	}

	public long getRemaningTime() {
		return remainingTime;
	}

	public int getSpecialSwapCount() {
		return specialSwapCount;
	}

	public LinkedList<Integer>  getGameSeed() {
		return gameSeed;
	}

}
