import java.io.FileNotFoundException;

import javax.management.InvalidAttributeValueException;


public class lokum extends gameBlock {
	
	private int type;

	
	public lokum(int i) throws InvalidAttributeValueException{
	if(i > 0){
	this.type = i;
	}
	else
		throw new InvalidAttributeValueException();
	}
	
	public int getType() {
		return type;
	}

	
	/**
	 * @return
	 * @throws Nothing
	 * @requires Nothing
	 * @modifies Nothing
	 * @ensures False
	 */
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * @return
	 * @throws Nothing
	 * @requires Nothing
	 * @modifies Nothing
	 * @ensures Null
	 */
	public specialRule returnSR() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean repOK() {
		if(type > 0) return true;
		return false;
	}

	@Override
	public String toString() {
		return "" + type;
	}

	@Override
	/**
	 * @return
	 * @throws Nothing
	 * @requires Nothing
	 * @modifies Nothing
	 * @ensures Null
	 */
	public propertyPack destroy() {
		return null;
	}

}
