
public class ruleLogicPair {
	private Rule rule;
	private position pos;
	
	public ruleLogicPair(Rule rule, position pos){
		this.rule = rule;
		this.pos = pos;
	}

	
	public Rule getRule() {
		return rule;
	}

	public position getPos() {
		return pos;
	}
}
