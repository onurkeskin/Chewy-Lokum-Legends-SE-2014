
public abstract class gameBlock {
	
	public abstract int getType(); 
	
	public abstract boolean isObstacle();
	
	public abstract Rule returnSR();

	public abstract boolean repOK();
	
	public abstract String toString();

	public abstract propertyPack destroy();
}
