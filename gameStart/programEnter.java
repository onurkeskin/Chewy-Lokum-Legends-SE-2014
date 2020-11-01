import javax.swing.JFrame;


public class programEnter {
	public static JFrame frame;
	public static mainMenu menu;
	
	
	
	public static void main(String[] args){
		frame = new JFrame();
	    menu = new mainMenu();
		frame.add(menu);
		frame.setBounds(0,0,800,800);
		menu.setBounds(0,0,800,800);
		frame.show();
	}
	
}
