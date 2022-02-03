package threadSort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	
	private static JLabel lblTime;
	protected static JProgressBar pb;
	private static JLabel lblStatus;
	
	public StatusPanel() {

		this.setLayout(null);
		setBounds(15, 362, 370, 115);
		setVisible(true);
				
		this.setBorder(new EmptyBorder(0,0,0,0));		
				
		lblTime = new JLabel();
		lblTime.setText("Dauer:");
		lblTime.setBounds(20,22,150,20);
		add(lblTime);
		
		pb = new JProgressBar();
		pb.setValue(0);
		pb.setStringPainted(true);		
		pb.setBounds(20,49,330,20);
		add(pb);
		
		lblStatus = new JLabel();
		lblStatus.setText("[Status]");
		lblStatus.setBounds(20,75,300,20);
		add(lblStatus);
	}
	
	public static void setStatus(final String text) {
		lblStatus.setText(text);
	}
	
	public static void setTime(final String text) {
		lblTime.setText("Dauer: " + text);
	}	

	
	@Override
	public void paintComponent(final Graphics graphics) {
		final Graphics2D g = ((Graphics2D)graphics);
		g.setColor(CanvasComponent.panelColor);
		g.fillRoundRect(2, 8, 365, 105, 20, 20);		
		g.setColor(Color.GRAY);
		g.drawRoundRect(2, 8, 365, 105, 20, 20);
		
	}
}
