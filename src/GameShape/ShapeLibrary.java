import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author Ozan
 *
 */
public class ShapeLibrary {

	private ArrayList<drawable> drawableLokums;
	private ArrayList<drawable> drawableSLokums;
	private ArrayList<drawable> drawableObstacles;

	public ShapeLibrary() {
		drawableLokums = new ArrayList<drawable>();
		drawableSLokums = new ArrayList<drawable>();
		drawableObstacles = new ArrayList<drawable>();
	}

	
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies drawable shape is added to the drawableLokums arrayList.
	 * @ensures Other elements of the arrayList are not changed, erased.
	 */
	public void addDrawableLokum(drawable shape) {
		if (!drawableLokums.contains(shape))
			drawableLokums.add(shape);
	}

	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies drawable shape is added to the drawableSLokums arrayList.
	 * @ensures Other elements of the arrayList are not changed, erased.
	 */
	public void adddrawableSLokum(drawable shape) {
		if (!drawableSLokums.contains(shape))
			drawableSLokums.add(shape);
	}

	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies drawable shape is added to the osbstacles arrayList.
	 * @ensures Other elements of the arrayList are not changed, erased.
	 */
	public void addDrawableObstacle(drawable shape) {
		if (!drawableObstacles.contains(shape))
			drawableObstacles.add(shape);
	}
	/**
	 * @requires
	 * @modifies drawable shape is added to the drawableLokums arrayList.
	 * @ensures arraylist of drawable lokums.
	 */
	public ArrayList<drawable> getDrawableLokums() {
		return drawableLokums;
	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies drawable shape is added to the drawableLokums arrayList.
	 * @ensures Other elements of the arrayList are not changed, erased.
	 */
	public ArrayList<drawable> getDrawableSLokums() {
		return drawableSLokums;
	}
	
	public ArrayList<drawable> getDrawableObstacles() {
		return drawableObstacles;
	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new random lokum with random color.
	 * @ensures  drawable object list grows and it has a new drawable lokum.
	 */
	public static drawable generateDrawableLokum() {
		drawable lokum = new drawable() {
			drawable drawing;

			public void draw(Graphics paramGraphics, position pos, position size) {
				if (drawing == null) {
					final Random gen = new Random();
					final int r = gen.nextInt(255);
					final int g = gen.nextInt(255);
					final int b = gen.nextInt(255);
					drawing = new drawable() {
						public void draw(Graphics paramGraphics, position pos,
								position size) {
							paramGraphics.setColor(new Color(r, g, b));
							paramGraphics.fillRoundRect(pos.getX(), pos.getY(),
									size.getX(), size.getY(), 20, 20);
						}
					};
				}
				drawing.draw(paramGraphics, pos, size);
			}
		};
		return lokum;
	}
	
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new random lokum with random color.
	 * @ensures  drawable object list grows and it has a new drawable slokum.
	 */
	public static drawable generateDrawableSLokum() {
		drawable slokum = new drawable() {
			drawable drawing;
			final Random gen = new Random();
			final int r = gen.nextInt(255);
			final int g = gen.nextInt(255);
			final int b = gen.nextInt(255);

			public void draw(Graphics paramGraphics, position pos, position size) {
				if (drawing == null) {
					drawing = new drawable() {
						public void draw(Graphics paramGraphics, position pos,
								position size) {
							paramGraphics.setColor(new Color(r, g, b));
							paramGraphics.fillOval(pos.getX(), pos.getY(),
									size.getX(), size.getY());

						}
					};
				}
				drawing.draw(paramGraphics, pos, size);
			}
		};
		return slokum;
	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new obstacle
	 * @ensures  drawable object list grows and it has a new drawable lokum.
	 */
	public static drawable generateDrawableobstacle() {
		drawable obstacle = new drawable() {
			drawable drawing;
			final Random gen = new Random();
			final int r = gen.nextInt(255);
			final int g = gen.nextInt(255);
			final int b = gen.nextInt(255);
			
			public void draw(Graphics paramGraphics, position pos, position size) {
				if (drawing == null) {
					drawing = new drawable() {
						public void draw(Graphics paramGraphics, position pos,
								position size) {
							paramGraphics.setColor(new Color(r, g, b));
							paramGraphics.fillRect(pos.getX(), pos.getY(),
									size.getX(), size.getY());
						}
					};
				}
				drawing.draw(paramGraphics, pos, size);
			}
		};
		return obstacle;
	}
	
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new pistachio shape.
	 * @ensures  drawable object list grows and it has a new green pistachio object.
	 */
	public static class greenPistachio implements drawable {

		Image greenPistachioImg;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (greenPistachioImg == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/green-pistachio.png"));
					greenPistachioImg = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(greenPistachioImg, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(greenPistachioImg, pos.getX(), pos.getY(), null);
			}
		}

	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new red rose shape.
	 * @ensures  drawable object list grows and it has a new red rose object.
	 */
	public static class redRose implements drawable {

		Image redRoseImg;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (redRoseImg == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/red-rose.png"));
					redRoseImg = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(redRoseImg, pos.getX(), pos.getY(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(redRoseImg, pos.getX(), pos.getY(), null);
			}
		}

	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new hazel nut shape.
	 * @ensures  drawable object list grows and it has a new green hazel nut object.
	 */
	public static class hazelNut implements drawable {

		Image hazelNutImg;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (hazelNutImg == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/hazel-nut.png"));
					hazelNutImg = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(hazelNutImg, pos.getX(), pos.getY(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(hazelNutImg, pos.getX(), pos.getY(), null);
			}
		}
	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new coconut shape.
	 * @ensures  drawable object list grows and it has a new coconut object.
	 */
	public static class coconut implements drawable {

		Image coconutImg;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (coconutImg == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/coco-nut.png"));
					coconutImg = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(coconutImg, pos.getX(), pos.getY(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(coconutImg, pos.getX(), pos.getY(), null);
			}
		}

	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new manstacle shape.
	 * @ensures  drawable object list grows and it has a new manstacle object.
	 */
	public static class Manstacle implements drawable {
		Image manstacleImage;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (manstacleImage == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/obstacle.png"));
					manstacleImage = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(manstacleImage, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(manstacleImage, pos.getX(), pos.getY(), null);
			}
		}

	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new striped shape.
	 * @ensures  drawable object list grows and it has a new striped object.
	 */
	public static class striped implements drawable {
		Image manstacleImage;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (manstacleImage == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/StripedLokum.png"));
					manstacleImage = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(manstacleImage, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(manstacleImage, pos.getX(), pos.getY(), null);
			}
		}

	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new wrapped shape.
	 * @ensures  drawable object list grows and it has a new wrapped object.
	 */
	public static class wrapped implements drawable {
		Image manstacleImage;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (manstacleImage == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/WrappedLokum.png"));
					manstacleImage = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(manstacleImage, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(manstacleImage, pos.getX(), pos.getY(), null);
			}
		}

	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new colorbomb shape.
	 * @ensures  drawable object list grows and it has a new green colorbomb object.
	 */
	public static class colorBomb implements drawable {
		Image manstacleImage;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (manstacleImage == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/colorBomb.png"));
					manstacleImage = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(manstacleImage, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(manstacleImage, pos.getX(), pos.getY(), null);
			}
		}
	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new supercandy shape.
	 * @ensures  drawable object list grows and it has a new supercandy object.
	 */
	public static class SuperCandy implements drawable {
		Image manstacleImage;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (manstacleImage == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/SuperCandy.png"));
					manstacleImage = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(manstacleImage, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(manstacleImage, pos.getX(), pos.getY(), null);
			}
		}
	}
	/**
	 * @requires a shapeLibrary object constructed.
	 * @modifies shapelibrary has a new time lokum shape.
	 * @ensures  drawable object list grows and it has a new time lokum object.
	 */
	public static class TimeLokum implements drawable {
		Image manstacleImage;

		public void draw(Graphics paramGraphics, position pos, position size) {
			Graphics2D graph = (Graphics2D) paramGraphics;
			if (manstacleImage == null) {
				try {
					BufferedImage img = ImageIO.read(new File(
							"Content/TimeLokum.png"));
					manstacleImage = img.getScaledInstance(size.getX(),
							size.getY(), 0);
					graph.drawImage(manstacleImage, pos.getX(), pos.getY(),
							null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				graph.drawImage(manstacleImage, pos.getX(), pos.getY(), null);
			}
		}
	}
	
	
}
