
public class stripedRule implements Rule {



	private boolean direction;


	public stripedRule(boolean direction){
		this.direction = direction;
	}
	
	/**
	 * @param env
	 * @param pos
	 * @return
	 * @requires an gameBlock array and position that is not null.
	 * @modifies gameBlocks that are diagonal or horizontal according to direction are added to destroyedPositions
	 * of propertyPack
	 * @ensures a propertyPack object
	 */
	public propertyPack applyRule(gameBlock[][] env, position pos) {
		propertyPack pack = new propertyPack();
		int n = 0;
		
		if(direction) {
			int align = pos.getX();
			
			for(int i = 0; i<env[0].length; i++){
				if(env[align][i] != null && env[align][i].getType() != 0){
					pack.addDestroyedPosition(new position(align,i));
					n++;
					}
				}
			pack.setScore(n*60);
		}else {
			int align = pos.getY();
			
			for(int j = 0; j<env.length; j++){
				if(env[j][align] != null && env[j][align].getType() != 0){
					pack.addDestroyedPosition(new position(j,align));
					n++;
					}
				}
			pack.setScore(n*60);
		}
		
		return pack;
	}


	public ruleLogicPair getRule() {		
		return null;
	}


	public Integer[][] getRuleEnv() {
		return null;
	}


	public boolean isExplosive() {
		return false;
	}


	public gameBlock getReturningBlock() {
		return null;
	}


	public int getScore() {
		return 0;
	}

	@Override
	public int getRulePriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
