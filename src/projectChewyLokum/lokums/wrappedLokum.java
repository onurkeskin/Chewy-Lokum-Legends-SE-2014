import javax.management.InvalidAttributeValueException;


public class wrappedLokum extends specialLokum{

	public wrappedLokum(int i) throws InvalidAttributeValueException {
		super(i, new wrappedRule());
	}

}
