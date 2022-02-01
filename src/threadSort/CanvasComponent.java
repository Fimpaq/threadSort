package threadSort;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CanvasComponent extends JComponent {

	
	static final int frameWidth = 570;
	static final int frameHeigth = 530;
	static final Color panelColor = new Color(180,255,180);
	
	public CanvasComponent() {
		this.setPreferredSize(new Dimension(frameWidth, frameHeigth));	
		this.setLayout(null);		

		add(new GeneratePanel());
		add(new ReadPanel());
		add(new SortPanel());		
		add(new StatusPanel());	
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(215, 490, 150, 25);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(btnQuit);
		
		
	}
}
