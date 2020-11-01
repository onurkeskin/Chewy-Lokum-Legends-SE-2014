import java.awt.EventQueue;
import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import javax.management.InvalidAttributeValueException;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class gameStart{

	 private JPanel gamePanel;
	 private JPanel scorePanel;
	 private gameBlock[] gameSeed;
	 
	 public gameStart(JPanel gamePanel2,JPanel scorePanel2){
		 gamePanel = gamePanel2;
		 scorePanel = scorePanel2;
	 }
	
	public void playGame(){
		
		
		gameEngine engine = gameEngine.getInstance();
		RuleControl rules = RuleControl.getInstance();
		engine.init(100,4,0);
		try {
			gameSeed = gameEnvBlock();
			engine.generateRandomStart(gameSeed, gameProperties.getRowCount(), gameProperties.getColumnCount());
			rules.instansiateRules(starterRules());
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
		ShapeLibrary lib = commonShapes();
		
		gameMapper mapper = gameMapper.getInstance();
		mapper.init(lib, 14, 10);
		
		graphicsEngine gEngine = graphicsEngine.getInstance();
		gEngine.init();
		gamePanel.add(gEngine);
		
		gEngine.setBounds(0, 0, gameProperties.getShapeSize()*(gameProperties.getColumnCount())+400, gameProperties.getShapeSize()*(gameProperties.getRowCount()+1));
		chewyLokumController controller = chewyLokumController.getInstance();
		controller.init(500000,0,50,1,1,10);
		gEngine.addController(controller);
		
		scorePanel.add(controller.scoreEngine);
		
		Thread thEngine = new Thread(engine);
		Thread thController = new Thread(controller);
		thEngine.start();
		thController.start();
		gEngine.rewriteEngineDrawing();
		
	}
	
	public void playGame(gameBlock[][] env,LinkedList<Integer> seed,player p,int goalScore,int currentScore,int movesLeft,int level, int seedSize,long remainingTime,int specialSwapCount){
		gameEngine engine = gameEngine.getInstance();
		RuleControl rules = RuleControl.getInstance();

		
		gameSeed = new gameBlock[seedSize];
		for(int i = 1;i<=seedSize;i++){
			try {
				if(seed.get(i-1)!=13){
				gameSeed[i-1] = new lokum(i);}
				else{
					gameSeed[i-1] = new timeLokum(13);
				}
			} catch (InvalidAttributeValueException e) {
				e.printStackTrace();
			}
		}
		
		engine.init((new Long(remainingTime)).intValue(),gameSeed,0,env,p);
		rules.instansiateRules(starterRules());
		
		ShapeLibrary lib = commonShapes();
		
		gameMapper mapper = gameMapper.getInstance();
		mapper.init(lib, 14, 10);
		
		graphicsEngine gEngine = graphicsEngine.getInstance();
		gEngine.init();
		gamePanel.add(gEngine);
		
		gEngine.setBounds(0, 0, gameProperties.getShapeSize()*(gameProperties.getColumnCount()), gameProperties.getShapeSize()*(gameProperties.getRowCount()+1));
		gEngine.repaint();
		chewyLokumController controller = chewyLokumController.getInstance();
		controller.init(goalScore,currentScore,movesLeft,1,level,specialSwapCount);
		gEngine.addController(controller);
		
		scorePanel.add(controller.scoreEngine);
		
		Thread thEngine = new Thread(engine);
		Thread thController = new Thread(controller);
		thEngine.start();
		thController.start();
		gEngine.rewriteEngineDrawing();
		
	}
	
	private gameBlock[] gameEnvBlock() throws InvalidAttributeValueException{
			lokum l1 = new lokum(1);
			lokum l2 = new lokum(2);
			lokum l3 = new lokum(3);	
			lokum l4 = new lokum(4);
			lokum l5 = new lokum(5);	
			timeLokum l6 = new timeLokum(13);
			obstacle o1 = new obstacle();
			
			gameBlock[] toReturn = {l1,l2,l3,l4,l5,l6};
			return toReturn;
	}

	private ShapeLibrary commonShapes(){
		ShapeLibrary lib = new ShapeLibrary();
		lib.addDrawableLokum(new ShapeLibrary.greenPistachio());
		lib.addDrawableLokum(new ShapeLibrary.redRose());
		lib.addDrawableLokum(new ShapeLibrary.hazelNut());
		lib.addDrawableLokum(new ShapeLibrary.coconut());
		lib.addDrawableLokum(new ShapeLibrary.SuperCandy());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		lib.addDrawableLokum(new ShapeLibrary.TimeLokum());
		
		lib.adddrawableSLokum(new ShapeLibrary.striped());
		lib.adddrawableSLokum(new ShapeLibrary.wrapped());
		
		lib.adddrawableSLokum(new ShapeLibrary.colorBomb());
		lib.adddrawableSLokum(new ShapeLibrary.colorBomb());
		lib.adddrawableSLokum(new ShapeLibrary.colorBomb());
		lib.adddrawableSLokum(new ShapeLibrary.colorBomb());
		lib.adddrawableSLokum(new ShapeLibrary.colorBomb());
		
		lib.addDrawableObstacle(new ShapeLibrary.Manstacle());
		
		return lib;
	}
	
	private RuleQueue starterRules(){
		RuleQueue toReturn = new RuleQueue();
		
		// STRIPED LOKUM
		stripedLokum StripedLokum = null;
		try {
			StripedLokum = new stripedLokum(-1,true);
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
		// END OF STRIPED LOKUM
		
		
		// WRAPPED LOKUM
		wrappedLokum wrappedLokum = null;
		try {
			wrappedLokum = new wrappedLokum(-2);
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
		// END OF WRAPPED LOKUM
		
		
		LinkedList<gameBlock> ruledBlocks = new LinkedList<gameBlock>();
		for(int dum = 0; dum < gameSeed.length; dum++){
			if(gameSeed[dum].getType()>0 && gameSeed[dum].getType()!=13){
				ruledBlocks.add(gameSeed[dum]);
			}
		}
		
		gameBlock[] colorBombs= new gameBlock[ruledBlocks.size()];
		for(int c = 0; c< ruledBlocks.size();c++){
			try {
				colorBombs[c] = new colorBombLokum(-3-c,c);
			} catch (InvalidAttributeValueException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i< ruledBlocks.size();i++){
			Integer curr = ruledBlocks.get(i).getType();
			normalRule RPenta = new normalRule(new Integer[][]{{curr},{curr},{curr},{curr},{curr}},colorBombs[i], false,4,200);
			normalRule RPentaUpside = new normalRule(new Integer[][]{{curr,curr,curr,curr,curr}},colorBombs[i], false,4,200);
			normalRule RTriple = new normalRule(new Integer[][]{{curr},{curr},{curr}},null, false,1,60);
			normalRule RTripleUpside = new normalRule(new Integer[][]{{curr,curr,curr}},null, false,1,60);
			normalRule StripedRule = new normalRule(new Integer[][]{{curr},{curr},{curr},{curr}},StripedLokum, false,2,120);
			normalRule RStripedRule = new normalRule(new Integer[][]{{curr,curr,curr,curr}},StripedLokum, false,2,120);
			normalRule WrappedRule1 = new normalRule(new Integer[][]{{null,curr,null},{null,curr,null},{curr,curr,curr}}, wrappedLokum, false,3,200);
			normalRule WrappedRule5 = new normalRule(new Integer[][]{{curr,curr,curr},{null,curr,null},{null,curr,null}}, wrappedLokum, false,3,200);
			normalRule WrappedRule9 = new normalRule(new Integer[][]{{curr,null,null},{curr,curr,curr},{curr,null,null}}, wrappedLokum, false,3,200);
			normalRule WrappedRule13 = new normalRule(new Integer[][]{{null,null,curr},{curr,curr,curr},{null,null,curr}}, wrappedLokum, false,3,200);	
		
			toReturn.add(RPenta);
			toReturn.add(RPentaUpside);
			toReturn.add(RTriple);
			toReturn.add(RTripleUpside);
			toReturn.add(StripedRule);
			toReturn.add(RStripedRule);
			toReturn.add(WrappedRule1);
			toReturn.add(WrappedRule5);
			toReturn.add(WrappedRule9);
			toReturn.add(WrappedRule13);
		}
		
		
		normalRule stripedStripedUp = new normalRule(new Integer[][]{{-1,-1}}, null, true,5,240);
		normalRule stripedStriped = new normalRule(new Integer[][]{{-1},{-1}}, null, true,5,240);
		
		normalRule stripedWrappedUp = new normalRule(new Integer[][]{{-1,-2}}, null, true,5,240);
		normalRule stripedWrapped = new normalRule(new Integer[][]{{-1},{-2}}, null, true,5,240);
		normalRule stripedWrappedUp2 = new normalRule(new Integer[][]{{-2,-1}}, null, true,5,240);
		normalRule stripedWrapped2 = new normalRule(new Integer[][]{{-2},{-1}}, null, true,5,240);
		

		normalRule stripedBombUp = new normalRule(new Integer[][]{{-1,-3}}, null, true,5,500);
		normalRule stripedBomb = new normalRule(new Integer[][]{{-1},{-3}}, null, true,5,500);
		normalRule stripedBombUp2 = new normalRule(new Integer[][]{{-3,-1}}, null, true,5,500);
		normalRule stripedBomb2 = new normalRule(new Integer[][]{{-3},{-1}}, null, true,5,500);
		normalRule stripedBombUp3 = new normalRule(new Integer[][]{{-1,-4}}, null, true,5,500);
		normalRule stripedBomb3 = new normalRule(new Integer[][]{{-1},{-4}}, null, true,5,500);
		normalRule stripedBombUp4 = new normalRule(new Integer[][]{{-4,-1}}, null, true,5,500);
		normalRule stripedBomb4 = new normalRule(new Integer[][]{{-4},{-1}}, null, true,5,500);
		normalRule stripedBombUp5 = new normalRule(new Integer[][]{{-1,-5}}, null, true,5,500);
		normalRule stripedBomb5 = new normalRule(new Integer[][]{{-1},{-5}}, null, true,5,500);
		normalRule stripedBombUp6 = new normalRule(new Integer[][]{{-5,-1}}, null, true,5,500);
		normalRule stripedBomb6 = new normalRule(new Integer[][]{{-5},{-1}}, null, true,5,500);
		normalRule stripedBombUp7 = new normalRule(new Integer[][]{{-1,-6}}, null, true,5,500);
		normalRule stripedBomb7 = new normalRule(new Integer[][]{{-1},{-6}}, null, true,5,500);
		normalRule stripedBombUp8 = new normalRule(new Integer[][]{{-6,-1}}, null, true,5,500);
		normalRule stripedBomb8 = new normalRule(new Integer[][]{{-6},{-1}}, null, true,5,500);
		
		
		normalRule wrappedWrappedUp = new normalRule(new Integer[][]{{-2,-2}}, null, true,5,240);
		normalRule wrappedWrapped = new normalRule(new Integer[][]{{-2},{-2}}, null, true,5,240);
		
		normalRule wrappedBombUp = new normalRule(new Integer[][]{{-2,-3}}, null, true,5,500);
		normalRule wrappedBomb = new normalRule(new Integer[][]{{-2},{-3}}, null, true,5,500);
		normalRule wrappedBombUp2 = new normalRule(new Integer[][]{{-3,-2}}, null, true,5,500);
		normalRule wrappedBomb2 = new normalRule(new Integer[][]{{-3},{-2}}, null, true,5,500);
		normalRule wrappedBombUp3 = new normalRule(new Integer[][]{{-2,-4}}, null, true,5,500);
		normalRule wrappedBomb3 = new normalRule(new Integer[][]{{-2},{-4}}, null, true,5,500);
		normalRule wrappedBombUp4 = new normalRule(new Integer[][]{{-4,-2}}, null, true,5,500);
		normalRule wrappedBomb4 = new normalRule(new Integer[][]{{-4},{-2}}, null, true,5,500);
		normalRule wrappedBombUp5 = new normalRule(new Integer[][]{{-2,-5}}, null, true,5,500);
		normalRule wrappedBomb5 = new normalRule(new Integer[][]{{-2},{-5}}, null, true,5,500);
		normalRule wrappedBombUp6 = new normalRule(new Integer[][]{{-5,-2}}, null, true,5,500);
		normalRule wrappedBomb6 = new normalRule(new Integer[][]{{-5},{-2}}, null, true,5,500);
		normalRule wrappedBombUp7 = new normalRule(new Integer[][]{{-2,-6}}, null, true,5,500);
		normalRule wrappedBomb7 = new normalRule(new Integer[][]{{-2},{-6}}, null, true,5,500);
		normalRule wrappedBombUp8 = new normalRule(new Integer[][]{{-6,-2}}, null, true,5,500);
		normalRule wrappedBomb8 = new normalRule(new Integer[][]{{-6},{-2}}, null, true,5,500);
		
		
		normalRule BombBombUp = new normalRule(new Integer[][]{{-3,-3}}, null, true,5,1000);
		normalRule BombBomb = new normalRule(new Integer[][]{{-3},{-3}}, null, true,5,1000);
		normalRule BombBombUp2 = new normalRule(new Integer[][]{{-4,-4}}, null, true,5,1000);
		normalRule BombBomb2 = new normalRule(new Integer[][]{{-4},{-4}}, null, true,5,1000);
		normalRule BombBombUp3 = new normalRule(new Integer[][]{{-5,-5}}, null, true,5,1000);
		normalRule BombBomb3 = new normalRule(new Integer[][]{{-5},{-5}}, null, true,5,1000);
		normalRule BombBombUp4 = new normalRule(new Integer[][]{{-6,-6}}, null, true,5,1000);
		normalRule BombBomb4 = new normalRule(new Integer[][]{{-6},{-6}}, null, true,5,1000);
		
		toReturn.add(stripedStriped);
		toReturn.add(stripedStripedUp);
		
		toReturn.add(stripedWrappedUp);
		toReturn.add(stripedWrapped);
		toReturn.add(stripedWrappedUp2);
		toReturn.add(stripedWrapped2);
		toReturn.add(stripedBomb);
		toReturn.add(stripedBombUp);
		toReturn.add(stripedBomb2);
		toReturn.add(stripedBombUp2);
		toReturn.add(stripedBomb3);
		toReturn.add(stripedBombUp3);
		toReturn.add(stripedBomb4);
		toReturn.add(stripedBombUp4);
		toReturn.add(stripedBomb5);
		toReturn.add(stripedBombUp5);
		toReturn.add(stripedBomb6);
		toReturn.add(stripedBombUp6);
		toReturn.add(stripedBomb7);
		toReturn.add(stripedBombUp7);
		toReturn.add(stripedBomb8);
		toReturn.add(stripedBombUp8);
		
		toReturn.add(wrappedWrappedUp);
		toReturn.add(wrappedWrapped);
		toReturn.add(wrappedBomb);
		toReturn.add(wrappedBombUp);
		toReturn.add(wrappedBomb2);
		toReturn.add(wrappedBombUp2);
		toReturn.add(wrappedBomb3);
		toReturn.add(wrappedBombUp3);
		toReturn.add(wrappedBomb4);
		toReturn.add(wrappedBombUp4);
		toReturn.add(wrappedBomb5);
		toReturn.add(wrappedBombUp5);
		toReturn.add(wrappedBomb6);
		toReturn.add(wrappedBombUp6);
		toReturn.add(wrappedBomb7);
		toReturn.add(wrappedBombUp7);
		toReturn.add(wrappedBomb8);
		toReturn.add(wrappedBombUp8);
		
		
		toReturn.add(BombBomb);
		toReturn.add(BombBombUp);
		toReturn.add(BombBomb2);
		toReturn.add(BombBombUp2);
		toReturn.add(BombBomb3);
		toReturn.add(BombBombUp3);
		toReturn.add(BombBomb4);
		toReturn.add(BombBombUp4);
		
		return toReturn;
	}
}
