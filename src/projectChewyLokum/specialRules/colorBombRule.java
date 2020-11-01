
public class colorBombRule implements Rule {

	Integer type;
	
	public colorBombRule(){
	}
	
	public colorBombRule(Integer type){
		this.type = type;
	}
	
	
	/**
	 * @param env
	 * @param pos
	 * @return
	 * @requires a gameblock array and , a not null position
	 * @modifies positions in environment with matching type are added to pack.
	 * @ensures a propertyPack is returned.
	 */
	public propertyPack applyRule(gameBlock[][] env, position pos) {
		propertyPack pack = new propertyPack();
		int n = 0;
		
		for(int i = 0; i< env.length; i++){
			for (int j = 0; j< env[0].length; j++){
				if(env[i][j] != null && type == env[i][j].getType()){
					pack.addDestroyedPosition(new position(i,j));
					n++;
				}
			}
		}
		pack.setScore(n * n * 60);
		return pack;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
