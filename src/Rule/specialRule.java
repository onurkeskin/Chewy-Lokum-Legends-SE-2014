public class specialRule extends genericRule implements Rule {

	private position pos;

	public specialRule(Integer[][] integers, position pos, boolean explosive,
			int rulePriority, int score) {
		super(integers, explosive, rulePriority, score);
		this.pos = pos;
	}

	public position getPos() {
		return pos;
	}

	public propertyPack applyRule(gameBlock[][] env, position pos) {
		return super.applyRule(env, pos);
	}

}
