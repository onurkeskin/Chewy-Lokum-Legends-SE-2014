
public class normalRule extends genericRule implements Rule{

	private gameBlock returningBlock;
	
	public normalRule(Integer[][] ruleEnv, gameBlock returningBlock,boolean explosive, int rulePriority, int score) {
		super(ruleEnv, explosive, rulePriority, score);
		this.returningBlock = returningBlock;
	}


	public gameBlock getReturningBlock() {
		return returningBlock;
	}


	public ruleLogicPair getRule() {
		
		return new ruleLogicPair(this,new position(-1,-1));
	}


	/**
	 * @param env
	 * @param pos
	 * @return
	 * @requires  gameblock environment and position
	 * @modifies  if the rule can be applied returns pack adding it the returning block.
	 * @ensures  returns property pack which can be used by engine.
	 */
	public propertyPack applyRule(gameBlock[][] env, position pos) {
		propertyPack pack = super.applyRule(env, pos);
		
		if(!(pack.getDestroyedPositions() == null)){
			pack.setLokum(returningBlock);
		}
		
		return pack;
	}


	
}
