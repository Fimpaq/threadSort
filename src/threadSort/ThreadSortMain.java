package threadSort;

import java.awt.Color;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class ThreadSortMain extends JFrame{
	
	public static void main(final String[] args) {
		new ThreadSortMain();		
	}
	
	public ThreadSortMain() {
		this.setLayout(null);
		this.setContentPane(new CanvasComponent());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		this.setBackground(new Color(255,220,220));
		this.setLocationRelativeTo(null);
		
		this.pack();
		this.setVisible(true);
	}
}
