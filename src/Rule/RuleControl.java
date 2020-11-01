import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class RuleControl {

	private static RuleControl instance;
	private Queue<ruleLogicPair> specialRuleQueue;
	private RuleQueue genericRules;
	
	private RuleControl(){
		specialRuleQueue = new LinkedList<ruleLogicPair>();
		genericRules = new RuleQueue();
	}
	
	public static RuleControl getInstance(){
		if(instance == null){
			instance = new RuleControl();
		}
		return instance;
	}
	
	public ruleLogicPair getNextSR(){
		if(!specialRuleQueue.isEmpty()){
			return specialRuleQueue.remove();
		}else return null;
	}
	
	public void instansiateSRules(LinkedList<ruleLogicPair> rules){
		specialRuleQueue = rules;
	}

	/**
	 * @requires a RuleControl object constructed.
	 * @modifies Adds a specialRule to specialRuleQueue
	 * @ensures Other specialRules in Queue are not Changed or deleted.
	 */
	public void addSRule(Rule rule, position pos){
		specialRuleQueue.add(new ruleLogicPair(rule,pos));
	}

	/**
	 * @requires a RuleControl object constructed.
	 * @modifies Sets normalRules in a Game.
	 * @ensures NormalRules LL is set to a new set of rules.
	 */
	public void instansiateRules(RuleQueue ruleQueue){
		genericRules = ruleQueue;
	}

	public void addRule(Rule rule){
		genericRules.add(rule);
	}
	
	public RuleQueue getRules(){
		return genericRules;
	}

	public boolean repOK() {
		if(instance != null && specialRuleQueue != null && genericRules != null)
		return true;
		return false;
	}
	
}
