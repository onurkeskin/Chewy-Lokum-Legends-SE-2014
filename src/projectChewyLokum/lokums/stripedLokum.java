import javax.management.InvalidAttributeValueException;


public class stripedLokum extends specialLokum{

	private boolean direction;
	
	public stripedLokum(int i, boolean direction)
			throws InvalidAttributeValueException {
		super(i, new stripedRule(direction));
		this.direction = direction;
	}
	
	
	
	
	
}
