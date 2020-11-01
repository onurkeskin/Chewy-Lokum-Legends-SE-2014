import java.util.LinkedList;


public class propertyPack {
	
	private LinkedList<position> destroyedPositions;
	private gameBlock lokum;
	private int score;
	private int time;
	private Rule rule;
	

	public propertyPack(){
		super();
	}
	
	
	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public propertyPack(LinkedList<position> destroyedPositions, lokum lokum) {
		super();
		this.destroyedPositions = destroyedPositions;
		this.lokum = lokum;
	}

	public void init(LinkedList<position> destroyedPositions, lokum lokum, Rule rule, int score,int time){
		this.destroyedPositions = destroyedPositions;
		this.lokum = lokum;
		this.score = score;
		this.time = time;
		this.rule = rule;
	}
	

	public void init(LinkedList<position> destroyedPositions, lokum lokum,int score){
		this.destroyedPositions = destroyedPositions;
		this.lokum = lokum;
		this.score = score;
	}
	
	public void init(LinkedList<position> destroyedPositions, lokum lokum,int score,int time){
		this.destroyedPositions = destroyedPositions;
		this.lokum = lokum;
		this.score = score;
		this.time = time;
	}
	
	public void init(LinkedList<position> destroyedPositions){
		this.destroyedPositions = destroyedPositions;
	}


	
	public LinkedList<position> getDestroyedPositions() {
		return destroyedPositions;
	}


	public void setDestroyedPositions(LinkedList<position> destroyedPositions) {
		this.destroyedPositions = destroyedPositions;
	}


	public gameBlock getLokum() {
		return lokum;
	}


	public void setLokum(gameBlock returningBlock) {
		this.lokum = returningBlock;
	}
	
	public void addDestroyedPosition(position destroyedPosition) {
		if(destroyedPositions == null)destroyedPositions = new LinkedList<position>();
		destroyedPositions.add(destroyedPosition);
	}

	public boolean isEmpty(){
		if(destroyedPositions != null || lokum != null || time != 0 || rule != null || score != 0){
			return false;
		}else return true;
	}
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	
	public Rule getRule() {
		return rule;
	}


}
