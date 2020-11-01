
public class position {

	private int x;
	private int y;
	
	public position(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean equals(position pos){
		if(this.x == pos.x && this.y == pos.y) return true;
		else return false;
	}
	
	public String toString(){
		return "X: " + x +" Y: " + y; 
	}
}
