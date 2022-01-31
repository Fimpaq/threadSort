package threadSort;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CanvasComponent extends JComponent {
	
	static final int frameWidth = 400;
	static final int frameHeigth = 500;
	static final Color panelColor = new Color(180,255,180);
	
	public CanvasComponent() {
		this.setPreferredSize(new Dimension(frameWidth, frameHeigth));	
		this.setLayout(null);		

		add(new GeneratePanel());
		add(new ReadPanel());
		add(new SortPanel());		
		add(new TimePanel());		

	}
}
