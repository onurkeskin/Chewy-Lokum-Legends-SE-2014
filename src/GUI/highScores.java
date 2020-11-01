import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;


public class highScores extends JPanel {

	/**
	 * Create the panel.
	 */
	private highScoreManip scores;
	
	public highScores(String loc) {
		scores =  new highScoreManip(loc);
		
		setBorder(new TitledBorder(null, "HighScores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JLabel label_12 = new JLabel("");
		label_12.setBounds(6, 20, 116, 38);
		add(label_12);
		
		JLabel lblNewLabel = new JLabel("High Scores");
		lblNewLabel.setBounds(122, 20, 146, 38);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		add(lblNewLabel);
		
		JLabel label_13 = new JLabel("");
		label_13.setBounds(238, 20, 116, 38);
		add(label_13);
		
		JLabel lblNewLabel_1 = new JLabel("1.)");
		lblNewLabel_1.setBounds(6, 58, 116, 38);
		add(lblNewLabel_1);
		
		JLabel place1 = new JLabel("New label");
		place1.setBounds(122, 58, 116, 38);
		add(place1);
		
		JLabel label_14 = new JLabel("");
		label_14.setBounds(238, 58, 116, 38);
		add(label_14);
		
		JLabel label = new JLabel("2.)");
		label.setBounds(6, 96, 116, 38);
		add(label);
		
		JLabel place2 = new JLabel("New label");
		place2.setBounds(122, 96, 116, 38);
		add(place2);
		
		JLabel label_15 = new JLabel("");
		label_15.setBounds(238, 96, 116, 38);
		add(label_15);
		
		JLabel label_1 = new JLabel("3.)");
		label_1.setBounds(6, 134, 116, 38);
		add(label_1);
		
		JLabel place3 = new JLabel("New label");
		place3.setBounds(122, 134, 116, 38);
		add(place3);
		
		JLabel label_16 = new JLabel("");
		label_16.setBounds(238, 134, 116, 38);
		add(label_16);
		
		JLabel label_2 = new JLabel("4.)");
		label_2.setBounds(6, 172, 116, 38);
		add(label_2);
		
		JLabel place4 = new JLabel("New label");
		place4.setBounds(122, 172, 116, 38);
		add(place4);
		
		JLabel label_18 = new JLabel("");
		label_18.setBounds(238, 172, 116, 38);
		add(label_18);
		
		JLabel label_3 = new JLabel("5.)");
		label_3.setBounds(6, 210, 116, 38);
		add(label_3);
		
		JLabel place5 = new JLabel("New label");
		place5.setBounds(122, 210, 116, 38);
		add(place5);
		
		JLabel label_17 = new JLabel("");
		label_17.setBounds(238, 210, 116, 38);
		add(label_17);
		
		JLabel label_4 = new JLabel("6.)");
		label_4.setBounds(6, 248, 116, 38);
		add(label_4);
		
		JLabel place6 = new JLabel("New label");
		place6.setBounds(122, 248, 116, 38);
		add(place6);
		
		JLabel label_20 = new JLabel("");
		label_20.setBounds(238, 248, 116, 38);
		add(label_20);
		
		JLabel label_5 = new JLabel("7.)");
		label_5.setBounds(6, 286, 116, 38);
		add(label_5);
		
		JLabel place7 = new JLabel("New label");
		place7.setBounds(122, 286, 116, 38);
		add(place7);
		
		JLabel label_19 = new JLabel("");
		label_19.setBounds(238, 286, 116, 38);
		add(label_19);
		
		JLabel label_23 = new JLabel("8.)");
		label_23.setBounds(6, 324, 116, 38);
		add(label_23);
		
		JLabel place8 = new JLabel("New label");
		place8.setBounds(122, 324, 116, 38);
		add(place8);

		JLabel[] scoreLabels = new JLabel[8];
		scoreLabels[0]=place1;
		scoreLabels[1]=place2;
		scoreLabels[2]=place3;
		scoreLabels[3]=place4;
		scoreLabels[4]=place5;
		scoreLabels[5]=place6;
		scoreLabels[6]=place7;
		scoreLabels[7]=place8;
		
		JButton btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.setBounds(238, 324, 146, 38);
		btnReturnToMenu.addActionListener(new returnToMenu("Action", KeyEvent.VK_A));
		add(btnReturnToMenu);
		
		List<String> list;
		try {
			list = scores.getScores();
			if(!list.isEmpty()){
			for (int i = 0; i<list.size();i++){
				scoreLabels[i].setText(list.get(i).split("/")[1]+ ":" + list.get(i).split("/")[0]);
			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
	}
	
	private class returnToMenu extends AbstractAction{
		
		public returnToMenu(String name, Integer mnemonic) {
	        super(name);
	        putValue(MNEMONIC_KEY, mnemonic);
	     }
		
		public void actionPerformed(ActionEvent arg0){
			programEnter.frame.getContentPane().removeAll();
			programEnter.frame.setBounds(100, 100, 248, 432);
			programEnter.frame.getContentPane().repaint();
			
			mainMenu menu = new mainMenu();
			programEnter.frame.add(menu);
			programEnter.frame.setBounds(0,0,800,800);
			programEnter.frame.setBounds(0,0,800,800);
			programEnter.frame.show();
		}
	}


}
