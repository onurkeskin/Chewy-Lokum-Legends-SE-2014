
public class timeLokum extends gameBlock{
	
	int type;
	
	public timeLokum(int type){
		this.type = type;
	}
	
	@Override
	public int getType() {
		return type;
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public Rule returnSR() {
		return null;
	}

	@Override
	public boolean repOK() {
		return (type > 0);
	}

	@Override
	public String toString() {
		return ""+getType();
	}

	/**
	 * @return
	 * @requires -
	 * @modifies -
	 * @ensures a returned propertyPack with time it extends.
	 */
	@Override
	public propertyPack destroy() {
		propertyPack toReturn = new propertyPack();
		toReturn.setTime(10);
		return toReturn;
	}

}
