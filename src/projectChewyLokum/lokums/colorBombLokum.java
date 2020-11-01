import javax.management.InvalidAttributeValueException;


public class colorBombLokum extends specialLokum{

	public colorBombLokum(int i,int t)
			throws InvalidAttributeValueException {
		super(i, new colorBombRule(t));
	}

	
	
}
