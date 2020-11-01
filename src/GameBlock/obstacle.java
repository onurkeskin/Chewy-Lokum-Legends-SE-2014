
public class obstacle extends gameBlock {

	
	@Override
	public int getType() {
		return 0;
	}

	
	public boolean isObstacle() {
		return true;
	}


	@Override
	public specialRule returnSR() {
		return null;
	}


	@Override
	public boolean repOK() {
		if(getType() == 0) return true;
		return false;
	}


	@Override
	public String toString() {
		return ""+getType();
	}


	@Override
	public propertyPack destroy() {
		return null;
	}

}
