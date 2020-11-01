import java.util.LinkedList;
import java.util.List;

/**
 * @author Onur
 *
 */
public class AnimationLayer {

	private List<gameList> layer;
	private int current;

	public AnimationLayer() {
		layer = new LinkedList<gameList>();
		layer.add(new gameList());
		current = 0;
	}

	/**
	 * 
	 * @requires -
	 * @modifies current is incremented, new layer is added.
	 * @ensures new commands to this object is sent to this new layer.
	 */
	public void advanceLayer() {
		current++;
		layer.add(new gameList());
	}

	
	/**
	 * 
	 * @requires -
	 * @modifies currentLayer will be propagated 1 position.
	 * @ensures a new layer at start, with empty fields.
	 */
	private void propagateLayer() {
		current++;
		layer.add(0, new gameList());
	}

	/**
	 * 
	 * @requires an instantiated gameList in current position.
	 * @modifies decrements current.
	 * @ensures current cant be decremented below 0
	 */
	public void goBack() {
		if (current > 0)
			current--;
	}

	/**
	 * @param pos
	 * @requires2 an instantiated gameList in current position, position that are not null.
	 * @modifies Adds position objects to destoryList of current layer
	 * @ensures A new entry in destroyList of layer.
	 */
	public void addDestroyToCurrentLayer(position pos) {
		layer.get(current).addNewDestory(pos);
	}
	
	public List<position> getCurrentDestroys(){
		return layer.get(current).getDestroys();
	}

	public void addMoveToCurrentLayer(SLPlace pos1, SLPlace pos2) {
		layer.get(current).addMove(pos1, pos2);
	}

	/**
	 * @param pos1
	 * @param pos2
	 * @requires2 an instantiated gameList in current position, positions that are not null.
	 * @modifies Adds 2 position objects to moveList of current layer
	 * @ensures A new entry in moveList of layer.
	 */
	public void addMoveToCurrentLayer(position pos1, position pos2) {
		layer.get(current).addMove(pos1, pos2);
	}

	/**
	 * @param pos1
	 * @param pos2
	 * @requires an instantiated gameList in current position, 2 positions that are not null.
	 * @modifies Adds 2 position objects to swapList of current layer
	 * @ensures A new entry in swapList of layer.
	 */
	public void addSwapToCurrentLayer(position pos1, position pos2) {
		layer.get(current).addSwap(pos1, pos2);
	}

	
	
	/**
	 * @param array
	 * @requires an array of the type drawable.
	 * @modifies the drawable array according to the first layer in gameList.
	 * @ensures array it took cant be set to null.
	 */
	public void applyFirstChange(drawable[][] array) {
		
		if (current > 0) {
			if(layer.get(0).isEmpty()){
				layer.remove(0);
				current--;
			}
		}
		
		if (current > 0) {	
			boolean pass = true;

			List<pair<position>> swaps = layer.get(0).getSwaps();
			for (int i = 0; i < swaps.size(); i++) {
				drawable temp = array[swaps.get(i).left.getX()][swaps.get(i).left
						.getY()];
				array[swaps.get(i).left.getX()][swaps.get(i).left.getY()] = array[swaps
						.get(i).right.getX()][swaps.get(i).right.getY()];
				array[swaps.get(i).right.getX()][swaps.get(i).right.getY()] = temp;
			}

			List<position> positions = layer.get(0).getDestroys();
			for (int i = 0; i < positions.size(); i++) {
				array[positions.get(i).getX()][positions.get(i).getY()] = null;
			}

			List<pair<SLPlace>> moves = layer.get(0).getMoves();
			for (int i = 0; i < moves.size(); i++) {
				if (moves.get(i).left != null) {
					array[moves.get(i).right.getPos().getX()][moves.get(i).right
							.getPos().getY()] = array[moves.get(i).left
							.getPos().getX()][moves.get(i).left.getPos().getY()];
					array[moves.get(i).left.getPos().getX()][moves.get(i).left
							.getPos().getY()] = null;
				} else {
					if(moves.get(i).right.getLokum() != null){
					drawable it = gameMapper.getInstance().mapThis(
							moves.get(i).right.getLokum().getType());
					array[moves.get(i).right.getPos().getX()][moves.get(i).right
							.getPos().getY()] = it;}
					// array[moves.get(i).right.getPos().getX()][a-1] = null;
					// array[moves.get(i).right.getPos().getX()][a] = it;
					// array[moves.get(i).right.getPos().getX()][a] =
					// gameMapper
					// .getInstance()
					// .mapThis(
					// moves.get(i).right.getLokum().getType());
					System.out.print(true);
				}
			}
			layer.remove(0);
			current--;
		}


	}
}
