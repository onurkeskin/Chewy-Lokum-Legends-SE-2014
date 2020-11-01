import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;

public class gameProperties {

	static private int rowCount;
	static private int columnCount;
	static private int shapeSize;
	
	public static void loadProperties(){

		try {
			 
				File fXmlFile = new File("properties.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				
				processPropertiesDoc(doc);
				
				
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	e.toString();
	    	e.toString();
	        }
	}

	private static void processPropertiesDoc(Document doc) {
		NodeList map = doc.getChildNodes();
		NodeList nodes = map.item(0).getChildNodes();
		for(int i = 0; i< nodes.getLength() ;i++){
			processNode(nodes.item(i));
		}
	}
	
	private static void processNode(Node n){
		
		  int type = n.getNodeType();
		
		switch (type) {
        case Node.ATTRIBUTE_NODE:

            break;
        
        case Node.ELEMENT_NODE:
        	if(n.getNodeName().equals( "GameDimensions" )){
        		NamedNodeMap XY = n.getAttributes();
        		rowCount = Integer.valueOf(XY.item(0).getNodeValue());
        		columnCount = Integer.valueOf(XY.item(1).getNodeValue());
        	}
        	
        	if(n.getNodeName().equals( "BlockShape" )){
        		NamedNodeMap XY = n.getAttributes();
        		shapeSize = Integer.valueOf(XY.item(0).getNodeValue());
        	}
            break;
        case Node.TEXT_NODE:
        	if(n.getNodeName().equals( "GameDimensions" )){
        		NamedNodeMap XY = n.getAttributes();
        		rowCount = Integer.valueOf(XY.item(0).getTextContent());
        		columnCount = Integer.valueOf(XY.item(1).getTextContent());
        	}
        	
        	if(n.getNodeName().equals( "BlockShape" )){
        		NamedNodeMap XY = n.getAttributes();
        		shapeSize = Integer.valueOf(XY.item(0).getTextContent());
        	}
            
		}
	}
	
	
	
	
	
	
	
	
	
	
	public static int getRowCount() {
		return rowCount;
	}

	public static int getColumnCount() {
		return columnCount;
	}

	public static int getShapeSize() {
		return shapeSize;
	}
	
}
