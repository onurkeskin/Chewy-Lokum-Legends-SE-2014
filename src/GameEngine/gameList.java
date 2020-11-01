import java.util.LinkedList;
import java.util.List;


public class gameList {
	
	private List<pair<SLPlace>> moves;
	private List<position> destroys;
	private List<pair<position>> swaps;
	
	public gameList(){
		moves = new LinkedList<pair<SLPlace>>();
		destroys = new LinkedList<position>();
		swaps = new LinkedList<pair<position>>();
	}
	
	public void addNewDestory(position pos){
		destroys.add(pos);
	}
	
	public void addMove(SLPlace p1, SLPlace p2){
		moves.add(new pair<SLPlace>(p1,p2));
	}
	public void addMove(position p1, position p2){
		moves.add(new pair<SLPlace>(new SLPlace(null,p1),new SLPlace(null,p2)));
	}
	
	public position getFirstDestroy(){
		return destroys.get(0);
	}
	
	public position getFromDestroy(int i){
		return destroys.get(i);
	}
	
	public position removeFirstDestroy(){
		return destroys.remove(0);
	}
	
	public position removeFromDestroys(int i){
		return destroys.remove(i);
	}
	
	public pair<SLPlace> getFirstMove(){
		return moves.get(0);
	}
	
	public pair<SLPlace> getFromMoves(int i){
		return moves.get(i);
	}
	
	public pair<SLPlace> removeFirstMove(){
		return moves.remove(0);
	}
	
	public pair<SLPlace> removeFromMoves(int i){
		return moves.remove(i);
	}
	
	public List<pair<SLPlace>> getMoves() {
		return moves;
	}
	
	public List<pair<position>> getSwaps() {
		return swaps;
	}
	
	public void addSwap(position pos1,position pos2){
		swaps.add(new pair<position>(pos1, pos2));
	}
	
	public List<position> getDestroys() {
		return destroys;
	}

	public boolean isEmpty(){
		return (moves.isEmpty() && destroys.isEmpty() && swaps.isEmpty());
		
	}
}
