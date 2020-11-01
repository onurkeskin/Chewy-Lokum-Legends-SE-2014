
public interface Rule {

	
	public propertyPack applyRule(gameBlock[][] env, position pos);
	public ruleLogicPair getRule();
	public Integer[][] getRuleEnv();

	
	public boolean isExplosive();
	public gameBlock getReturningBlock();
	public int getScore();
	public int getRulePriority();
}
