package threadSort;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class SortFrame extends JFrame{
	
	final static Color frameColor = new Color(255,220,220);
	static JComponent contentPane;
	
	public static void main(final String[] args) {
		new SortFrame();		
	}
	
	public SortFrame() {
		this.setLayout(null);
		
		contentPane = new CanvasComponent();
		this.setContentPane(contentPane);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		this.setBackground(frameColor);
		this.setLocationRelativeTo(null);
		
		this.pack();
		this.setVisible(true);
	}
}
