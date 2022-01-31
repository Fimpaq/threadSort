package threadSort;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class GeneratePanel extends JPanel{

	
	public GeneratePanel() {
		this.setLayout(null);
		setBounds(15, 20, 370, 115);
		setVisible(true);		
		
		Border border = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(border, " generieren "));		
		
		
		JLabel lblAmount;
		lblAmount = new JLabel();
		lblAmount.setText("Anzahl der Elemente:");
		lblAmount.setBounds(20,30,150,20);
		add(lblAmount);
		
		JTextField txtAmount;
		txtAmount = new JTextField();
		txtAmount.setBounds(200,30,150,20);
		add(txtAmount);

		
		JLabel lblfile;
		lblfile = new JLabel();
		lblfile.setText("Ausgabedatei:");
		lblfile.setBounds(20,57,150,20);
		add(lblfile);

		JTextField txtFile;
		txtFile = new JTextField();
		txtFile.setBounds(200,57,150,20);
		add(txtFile);
		
		JButton btnStart;
		btnStart = new JButton();
		btnStart.setText("Start");
		btnStart.setBounds(200,83,150,20);
		add(btnStart);
		
	}
	
	@Override
	public void paintComponent(final Graphics graphics) {
		final Graphics2D g = ((Graphics2D)graphics);
		g.setColor(CanvasComponent.panelColor);
		g.fillRect(2, 8, 365, 105);		
	}
}