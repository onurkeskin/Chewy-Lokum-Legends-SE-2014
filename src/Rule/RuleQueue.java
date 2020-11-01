import java.util.Iterator;
import java.util.LinkedList;


public class RuleQueue implements Iterable<Rule>{
	LinkedList<Rule> rules;
	
	public RuleQueue(){
		rules = new LinkedList<Rule>();
	}
	
	public void add(Rule rule){
		if(rules.isEmpty()){
			rules.add(rule);
		}else {
			Iterator<Rule> rator = rules.iterator();
			int currPlace = 0;
			boolean found = false;
			while(rator.hasNext()){
				Rule curr = rator.next();
				if(curr.getRulePriority() < rule.getRulePriority()){
					rules.add(currPlace,rule);
					found = true;
					break;
				}
				currPlace++;
			}
			if(!found){rules.add(rule);}
		}
	}

	public Iterator<Rule> iterator() {
		return rules.iterator();
	}
	
	
	
}
