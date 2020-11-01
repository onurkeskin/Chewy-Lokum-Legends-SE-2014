import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import net.miginfocom.swing.MigLayout;

import javax.swing.JSplitPane;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;


public class game extends JFrame {

	private JPanel contentPane;
	public JPanel gamePanel;
	private JPanel textPanel;
	private JPanel game;
	public JPanel info;
	public JPanel menu;
	private JButton saveGameBtn;
	private JButton pauseGameBtn;
	/**
	 * Create the frame.
	 */
	public game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		contentPane.setLayout(new MigLayout("", "[" + gameProperties.getRowCount() * gameProperties.getShapeSize()
				+ "px,grow][300px,grow]", "[" + gameProperties.getRowCount() * gameProperties.getShapeSize()
				+  "px,grow]"));
		
		gamePanel = new JPanel();
		contentPane.add(gamePanel, "cell 0 0,grow");
		gamePanel.setLayout(null);
		
		textPanel = new JPanel();
		contentPane.add(textPanel, "cell 1 0,grow");
		textPanel.setLayout(new MigLayout("", "[876px]", "[831px]"));
		
		game = new JPanel();
		textPanel.add(game, "cell 0 0,grow");
		game.setLayout(new GridLayout(0, 1, 0, 0));
		
		info = new JPanel();
		game.add(info);
		info.setLayout(new GridLayout(1, 0, 0, 0));
		
		menu = new JPanel();
		game.add(menu);
		menu.setLayout(new GridLayout(0, 2, 0, 0));
		
		saveGameBtn = new JButton("Save Game");
		saveGameBtn.addActionListener(new saveAction("Action", KeyEvent.VK_A));
		menu.add(saveGameBtn);
		
		pauseGameBtn = new JButton("Pause Game");
		menu.add(pauseGameBtn);
	}
	
	public void addController(MouseListener controller){
		addMouseListener(controller);	//need instance of controller before can add it as a listener 
	}
	
private class saveAction extends AbstractAction {
		
		
		public  saveAction(String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }

	      public void actionPerformed(ActionEvent e) {
	    	  saveLoadManager.saveGame(gameEngine.getInstance(), chewyLokumController.getInstance());
	    	  
	      }
	   }
	
}
