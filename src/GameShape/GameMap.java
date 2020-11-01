import java.util.HashMap;


public class GameMap {

	private static HashMap<Integer,drawable> map;
	
	private GameMap(){
		map = new HashMap<Integer,drawable>();
	}
	
	public drawable getShapeBinding(int i)
	{
		if (map == null){ 
			new GameMap();
			return null;
		}
		else return map.get(i);
	}
	
	
	
	/**
	 * @throws BindingExists
	 * @requires Object constructed, the key is not already on the hashmap
	 * @modifies A new key value pair is added to the map.
	 * @ensures no key is replaced.
	 */
	public void putNewBinding(Integer num,drawable shape) throws BindingExists{
		if (map == null){ 
			new GameMap();
		}
		else
		{
			if(map.get(num)==null){
				map.put(num, shape);
			}else {
				throw new BindingExists();
			}
			
		}
	}
	
	
	public class BindingExists extends Exception{
		private static final long serialVersionUID = 1L;
		public BindingExists() {
			super("Binding already exists");
		}
		
	}
}


