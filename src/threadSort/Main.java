package threadSort;

import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Main extends JFrame{
	
	static ExecutorService executor;
	
	public static void main(final String[] args) {
		executor = Executors.newFixedThreadPool(1);
		new Main();		
	}
	
	public Main() {
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
