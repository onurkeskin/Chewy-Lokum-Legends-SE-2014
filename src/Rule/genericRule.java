public class genericRule implements Rule, Comparable<Rule> {

	private Integer[][] ruleEnv;
	private boolean explosive;
	private int rulePriority;
	private int score;

	public int getRulePriority() {
		return rulePriority;
	}

	public genericRule(Integer[][] ruleEnv, boolean explosive,
			int rulePriority, int score) {
		this.ruleEnv = ruleEnv;
		this.explosive = explosive;
		this.rulePriority = rulePriority;
		this.score = score;
	}

	public ruleLogicPair getRule() {
		return new ruleLogicPair(this, new position(-1, -1));
	}

	public Integer[][] getRuleEnv() {
		return ruleEnv;
	}

	public boolean isExplosive() {
		return explosive;
	}

	public gameBlock getReturningBlock() {
		return null;
	}

	public int getScore() {
		return score;
	}


	/**
	 * @param env
	 * @param pos
	 * @return
	 * @requires A game Engine object that has an env, and a not null position.
	 * @modifies If the rule is applicable this method will return a properyPack with all the changes
	 * that can made to engine with the rule.
	 * @ensures A propertPack.
	 */
	public propertyPack applyRule(gameBlock[][] env, position pos) {
		propertyPack pack = new propertyPack();
		if (env != null && Helpers.eligiblePos(pos, env)) {
			if (isExplosive()) {
				if (env[pos.getX()][pos.getY()] != null) {
					if (env.length >= pos.getX() + getRuleEnv().length
							&& env[0].length >= pos.getY()
									+ getRuleEnv()[0].length) {

						boolean ruleApplicable = true;
						for (int i = 0; i < getRuleEnv().length; i++) {
							if (ruleApplicable) {
								for (int j = 0; j < getRuleEnv()[0].length; j++) {
									int relativePosX = pos.getX() + i;
									int relativePosY = pos.getY() + j;

									if (!checkBoth(true,getRuleEnv()[i][j], env[relativePosX][relativePosY])) {
												ruleApplicable = false;
												break;
									}
								}
							} else
								break;
						}

						if (ruleApplicable) {
							for (int i = 0; i < getRuleEnv().length; i++) {
								for (int j = 0; j < getRuleEnv()[0].length; j++) {
									int relativePosX = pos.getX() + i;
									int relativePosY = pos.getY() + j;
									if (getRuleEnv()[i][j] != null) {
										pack.addDestroyedPosition(new position(
												relativePosX, (relativePosY)));
									}
								}
							}
							pack.setScore(getScore());
							pack.setLokum(getReturningBlock());
						}
					}
				}
			} else {

				if (env[pos.getX()][pos.getY()] != null) {
					if (env.length >= pos.getX() + getRuleEnv().length
							&& env[0].length >= pos.getY()
									+ getRuleEnv()[0].length) {

						boolean ruleApplicable = true;
						for (int i = 0; i < getRuleEnv().length; i++) {
							if (ruleApplicable) {
								for (int j = 0; j < getRuleEnv()[0].length; j++) {
									int relativePosX = pos.getX() + i;
									int relativePosY = pos.getY() + j;

									if (!checkBoth(false,getRuleEnv()[i][j], env[relativePosX][relativePosY])) {
										ruleApplicable = false;
										break;
									}
								}
							} else
								break;
						}

						if (ruleApplicable) {
							for (int i = 0; i < getRuleEnv().length; i++) {
								for (int j = 0; j < getRuleEnv()[0].length; j++) {
									int relativePosX = pos.getX() + i;
									int relativePosY = pos.getY() + j;
									if (getRuleEnv()[i][j] != null) {
										pack.addDestroyedPosition(new position(
												relativePosX, (relativePosY)));
									}
								}
							}
							pack.setScore(getScore());
							pack.setLokum(getReturningBlock());
						}
					}
				}
			}
		}
		return pack;
	}

	@Override
	public int compareTo(Rule o) {
		if (getRulePriority() == o.getRulePriority())
			return 0;
		else if (getRulePriority() > o.getRulePriority())
			return -1;
		else
			return 1;
	}

	private boolean checkBoth(boolean casex,Integer rulesType,gameBlock lokum){
		if(rulesType == null) return true;
		if(lokum == null) return false;
		if(rulesType == 0){return true;}
		if(lokum.getType() == rulesType){return true;}
		if(lokum.getType() == 13 && !casex){ return true;}
		if(lokum.getType() < 0 && !casex){return true;}
		
		return false;
	}
	
}
