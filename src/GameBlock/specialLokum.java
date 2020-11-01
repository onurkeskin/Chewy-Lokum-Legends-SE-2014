
import javax.management.InvalidAttributeValueException;

public class specialLokum extends gameBlock{


		private int type;
		private Rule rule;
		
		public specialLokum(int i, Rule rule) throws InvalidAttributeValueException{
		if(i < 0){
		this.type = i;
		this.rule = rule;
		}
		else
			throw new InvalidAttributeValueException();
		}
		
		public Rule getRule() {
			return rule;
		}

		public int getType() {
			return type;
		}

		
		
		public boolean isObstacle() {
			// TODO Auto-generated method stub
			return false;
		}

		
		public Rule returnSR() {
			return rule;
		}

		@Override
		public boolean repOK() {
			if(type<0 && rule != null) return true;
			return false;
		}

		@Override
		public String toString() {
			return ""+type;
		}

		@Override
		public propertyPack destroy() {
			propertyPack pack = new propertyPack();
			pack.init(null, null, returnSR(),0,0);
			return pack;
		}
	}

