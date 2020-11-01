
public class wrappedRule implements Rule {

	/**
	 * @param env
	 * @param pos
	 * @return
	 * @requires an gameBlock array and position that is not null.
	 * @modifies gameBlocks that are adjacent to position are added to a propertyPack 's destroyed position
	 * @ensures a properyPack returned with changes
	 */
	@Override
	public propertyPack applyRule(gameBlock[][] env, position pos) {
		propertyPack pack = new propertyPack();
		for(int i = -1; i<=1;i++){
			for(int j = -1; j<=1;j++){
				int relX = pos.getX()+i;
				int relY = pos.getY()+j;
				if(Helpers.eligiblePos(new position(relX, relY), env)){
					pack.addDestroyedPosition(new position(relX, relY));
				}
			}
		}
		pack.setScore(1080);
		return pack;
	}

	@Override
	public ruleLogicPair getRule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[][] getRuleEnv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExplosive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public gameBlock getReturningBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRulePriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
