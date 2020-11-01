import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import javax.management.InvalidAttributeValueException;
import javax.sound.midi.ControllerEventListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class saveLoadManager {

	public static chewyBlob loadProperties() {

		try {

			File fXmlFile = new File("save.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList map = doc.getChildNodes();
			Node node = map.item(0);
			return processNode(node);

		} catch (Exception e) {
			e.printStackTrace();
			e.toString();
			e.toString();
			return null;
		}
	}

	private static chewyBlob processNode(Node n) {

		LinkedList<SLPlace> lists = new LinkedList<SLPlace>();
		String playerName = null;
		Integer goalScore = null;
		Integer score = null;
		Integer movesleft = null;
		Integer level = null;
		Integer specialMovesleft = null;
		Long remainingTime = null;
		HashMap<String, Integer> mapSI = null;
		LinkedList<Integer> gameSeed = new LinkedList<Integer>();

		NodeList startlist = n.getChildNodes();
		for (int start = 0; start < startlist.getLength(); start++) {
			int type = startlist.item(start).getNodeType();
			switch (type) {
			case Node.ELEMENT_NODE:

				if (startlist.item(start).getNodeName().equals("board")) {
					mapSI = new HashMap<String, Integer>();
					int countOfMapped = 1;

					NodeList map = startlist.item(start).getChildNodes();
					for (int i = 0; i < map.getLength(); i++) {
						Node node = map.item(i);
						if (node.getNodeName().equals("lokums")) {
							NodeList boardMap = node.getChildNodes();
							for (int j = 0; j < boardMap.getLength(); j++) {

								if (boardMap.item(j).getNodeName()
										.equals("lokum")) {
									gameBlock currentLokum;
									Integer xCoor = null;
									Integer yCoor = null;
									String blockType = null;

									NodeList lokumNode = boardMap.item(j)
											.getChildNodes();
									for (int x = 0; x < lokumNode.getLength(); x++) {
										if (lokumNode.item(x).getNodeName()
												.equals("position")) {
											NodeList posNodes = lokumNode.item(
													x).getChildNodes();
											for (int posNodeCount = 0; posNodeCount < posNodes
													.getLength(); posNodeCount++) {
												if (posNodes.item(posNodeCount)
														.getNodeName()
														.equals("xcoord")) {
													xCoor = Integer
															.valueOf(posNodes
																	.item(posNodeCount)
																	.getTextContent());
												}
												if (posNodes.item(posNodeCount)
														.getNodeName()
														.equals("ycoord")) {
													yCoor = Integer
															.valueOf(posNodes
																	.item(posNodeCount)
																	.getTextContent());
												}
											}
										}
										if (lokumNode.item(x).getNodeName()
												.equals("type")) {
											blockType = lokumNode.item(x)
													.getTextContent();
											if (mapSI.get(blockType) == null) {
												if (blockType
														.equals("13")) {
													mapSI.put(blockType,countOfMapped);
													gameSeed.add(13);
												} else {
													mapSI.put(blockType,
															countOfMapped);
													countOfMapped++;
													gameSeed.add(countOfMapped);
												}
											}
										}

									}
									if (xCoor != null && yCoor != null
											&& blockType != null) {
										try {
											lists.add(new SLPlace(new lokum(
													mapSI.get(blockType)),
													new position(xCoor, yCoor)));
										} catch (InvalidAttributeValueException e) {
											e.printStackTrace();
										}
									}

								}
							}
						}

						if (node.getNodeName().equals("obstacles")) {
							NodeList obstacles = node.getChildNodes();
							for (int x = 0; x < obstacles.getLength(); x++) {
								if (obstacles.item(x).getNodeName()
										.equals("obstacle")) {
									Integer xCoor = null;
									Integer yCoor = null;

									NodeList obstacle = obstacles.item(x)
											.getChildNodes();
									for (int dummy = 0; dummy < obstacle
											.getLength(); dummy++)
										if (obstacle.item(dummy).getNodeName()
												.equals("position")) {
											NodeList posNodes = obstacle.item(
													dummy).getChildNodes();
											for (int posNodeCount = 0; posNodeCount < posNodes
													.getLength(); posNodeCount++) {
												if (posNodes.item(posNodeCount)
														.getNodeName()
														.equals("xcoord")) {
													xCoor = Integer
															.valueOf(posNodes
																	.item(posNodeCount)
																	.getTextContent());
												}
												if (posNodes.item(posNodeCount)
														.getNodeName()
														.equals("ycoord")) {
													yCoor = Integer
															.valueOf(posNodes
																	.item(posNodeCount)
																	.getTextContent());
												}
											}
										}
									lists.add(new SLPlace(new obstacle(),
											new position(xCoor, yCoor)));
								}
							}
						}
					}
				}

				if (startlist.item(start).getNodeName().equals("player")) {
					NodeList playersNodes = startlist.item(start)
							.getChildNodes();
					for (int i = 0; i < playersNodes.getLength(); i++) {
						if (playersNodes.item(i).getNodeName().equals("name")) {
							playerName = playersNodes.item(i).getTextContent();
						}
					}
				}
				if (startlist.item(start).getNodeName().equals("goalscore")) {
					goalScore = Integer.parseInt(startlist.item(start)
							.getTextContent());
				}
				if (startlist.item(start).getNodeName().equals("currentscore")) {
					score = Integer.parseInt(startlist.item(start)
							.getTextContent());
				}
				if (startlist.item(start).getNodeName().equals("movesleft")) {
					movesleft = Integer.parseInt(startlist.item(start)
							.getTextContent());
				}

				if (startlist.item(start).getNodeName()
						.equals("specialMovesLeft")) {
					specialMovesleft = Integer.parseInt(startlist.item(start)
							.getTextContent());
				}

				if (startlist.item(start).getNodeName().equals("remainingTime")) {
					remainingTime = Long.valueOf(startlist.item(start)
							.getTextContent());
				}

				if (startlist.item(start).getNodeName().equals("level")) {
					level = Integer.parseInt(startlist.item(start)
							.getTextContent());
				}
				break;

			}
		}

		chewyBlob blob = new chewyBlob(lists, gameSeed, playerName, goalScore,
				score, movesleft, level, mapSI.size(), remainingTime,
				specialMovesleft);
		return blob;
	}

	public static void saveGame(gameEngine engine, chewyLokumController cont) {
		Integer[][] toSave = engine.gameAsInt();
		int score = engine.getCurrentScore();

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("game");
			doc.appendChild(rootElement);

			Element player = doc.createElement("player");
			rootElement.appendChild(player);

			Element id = doc.createElement("id");
			player.appendChild(id);
			id.appendChild(doc.createTextNode("0"));

			Element sc = doc.createElement("score");
			player.appendChild(sc);
			sc.appendChild(doc.createTextNode("" + score));

			Element boardElement = doc.createElement("board");
			rootElement.appendChild(boardElement);

			Element lokums = doc.createElement("lokums");
			boardElement.appendChild(lokums);

			Element obstacles = doc.createElement("obstacles");
			boardElement.appendChild(obstacles);

			for (int i = 0; i < toSave.length; i++) {
				for (int j = 0; j < toSave[0].length; j++) {
					if (toSave[i][j] != null && toSave[i][j] != 0) {
						Element lokum = doc.createElement("lokum");
						lokums.appendChild(lokum);

						Element color = doc.createElement("color");
						color.appendChild(doc.createTextNode("notImp"));
						lokum.appendChild(color);

						Element position = doc.createElement("position");
						Element xcoord = doc.createElement("xcoord");
						xcoord.appendChild(doc.createTextNode("" + i));
						Element ycoord = doc.createElement("ycoord");
						ycoord.appendChild(doc.createTextNode("" + j));
						position.appendChild(xcoord);
						position.appendChild(ycoord);
						lokum.appendChild(position);

						Element type = doc.createElement("type");
						type.appendChild(doc.createTextNode("" + toSave[i][j]));
						lokum.appendChild(type);

					} else {
						Element obs = doc.createElement("obstacle");
						obstacles.appendChild(obs);

						Element color = doc.createElement("color");
						color.appendChild(doc.createTextNode("notImp"));
						obs.appendChild(color);

						Element position = doc.createElement("position");
						Element xcoord = doc.createElement("xcoord");
						xcoord.appendChild(doc.createTextNode("" + i));
						Element ycoord = doc.createElement("ycoord");
						ycoord.appendChild(doc.createTextNode("" + j));
						position.appendChild(xcoord);
						position.appendChild(ycoord);
						obs.appendChild(position);
					}
				}
			}

			Element goalElement = doc.createElement("goalscore");
			goalElement
					.appendChild(doc.createTextNode("" + cont.getScoreAim()));
			rootElement.appendChild(goalElement);

			Element currentScoreElement = doc.createElement("currentscore");
			currentScoreElement.appendChild(doc.createTextNode(""
					+ cont.getPlayerScore()));
			rootElement.appendChild(currentScoreElement);

			Element movesleftElement = doc.createElement("movesleft");
			movesleftElement.appendChild(doc.createTextNode(""
					+ cont.getPossibleMoveCount()));
			rootElement.appendChild(movesleftElement);

			Element levelElement = doc.createElement("level");
			levelElement.appendChild(doc.createTextNode("" + cont.getLevel()));
			rootElement.appendChild(levelElement);

			Element remainingTimeElement = doc.createElement("remainingTime");
			remainingTimeElement.appendChild(doc.createTextNode(""
					+ cont.getTimer()));
			rootElement.appendChild(remainingTimeElement);

			Element specalMovesLeftElement = doc
					.createElement("specialMovesLeft");
			specalMovesLeftElement.appendChild(doc.createTextNode(""
					+ cont.getSpecialSwapCount()));
			rootElement.appendChild(specalMovesLeftElement);

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("save.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

}
