
public class player {

	private int score;
	private String name;
	
	public player(){
		score = 0;
		name = "";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addScore(int score){
		this.score+=score;
	}
	
	public int getScore(){ return score;}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void resetScore(){
		score = 0;
	}

	public boolean repOK() {
		if(name != null) return true;
		return false;
	}
	
}
