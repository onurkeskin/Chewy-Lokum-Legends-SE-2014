import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * @author deniz
 *
 */
public class graphicsEngine extends JPanel{

	private static graphicsEngine instance;
	private drawable[][] graphicsArray; 
	private gameMapper mapper;
	private gameEngine engine;
	private List<position> clicked;
	private int shapeSize;
	
	
	private graphicsEngine(){
		super();
	}
	
	public static graphicsEngine getInstance(){
		if(instance == null) instance = new graphicsEngine();
		return instance;
	}
	
	
	public void init(){
		clicked = new LinkedList<position>();
		mapper = mapper.getInstance();
		engine = engine.getInstance();
		shapeSize = gameProperties.getShapeSize();
		graphicsArray = mapper.generateDrawableArray(engine.gameAsInt());
	}
	
	public void rewriteEngineDrawing(){
		graphicsArray = mapper.generateDrawableArray(engine.gameAsInt());
	}
	
	/** (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		super.paint(g);
		
		
		engine.getChangeList().applyFirstChange(graphicsArray);
		
		for(int i = 0; i<clicked.size(); i++){
			g.setColor(Color.BLUE);
			g.fillRect((clicked.get(i).getX())*shapeSize,(clicked.get(i).getY())*shapeSize
					, shapeSize, shapeSize);
		}
		
//		List <position> destroys = engine.getChangeList().getCurrentDestroys();
//		for(int i = 0; i< destroys.size(); i++){
//			g.setColor(Color.RED);
//			g.fillRect(( destroys.get(i).getX())*shapeSize,( destroys.get(i).getY())*shapeSize
//					, shapeSize, shapeSize);
//		}
		
		
		for ( int i = 0; i<graphicsArray.length; i++ ) {
			for (int j = 0; j< graphicsArray[0].length; j++ ) {
				g.setColor(Color.BLACK);
				g.drawRect(i*shapeSize,j*shapeSize, shapeSize, shapeSize);
				if(graphicsArray[i][j] != null)
				graphicsArray[i][j].draw(g,
								  new position(i*shapeSize,j*shapeSize),
								  new position(shapeSize,shapeSize));
			}
		}
		

		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
//		try {
//			BufferedImage img = ImageIO.read(new File(
//					"Content/Border.png"));
//			Image border = img.getScaledInstance(graphicsArray.length*shapeSize,
//					graphicsArray[0].length*shapeSize, 0);
//			g.drawImage(border, 0,0, null);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * @param pos
	 * @requires object to be instantiated
	 * @modifies adds position 
	 * @ensures other systems won't change
	 */
	public void addToClicked(position pos){
		clicked.add(pos);
	}
	
	/**
	 * @param pos
	 * @requires object to be instantiated and an added position
	 * @modifies removes added position
	 * @ensures other systems won't change
	 */
	public void removeFromClicked(position pos){
		for(int i = 0; i<clicked.size(); i++){
			if(clicked.get(i).equals(pos)){
				clicked.remove(i);
			}
		}
	}
	
	/**
	 * 
	 * @requires position in list
	 * @modifies removes and adds to list
	 * @ensures
	 */
	public void removeFromClickeds(){
		clicked = new LinkedList<position>();
	}
	
	public void setGameGraphics(drawable[][] graphicsArray){
		this.graphicsArray = graphicsArray;
	}
	
	/**
	 * @param controller
	 * @requires a position to be clicked
	 * @modifies adds mouse listener
	 * @ensures
	 */
	public void addController(MouseListener controller){
		addMouseListener(controller);	//need instance of controller before can add it as a listener 
	}
		
	
	
}
