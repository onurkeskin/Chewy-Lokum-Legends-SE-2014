
public class pair<T> {

	T right;
	T left;
	
	public pair(T left,T right){
		this.right = right;
		this.left = left;
	}
	
	public String toString(){
		return "Right: " + right.toString() + " Left : " + left.toString();
	}
}
