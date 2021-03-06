package threadSort;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ReadPanel extends JPanel {
	public ReadPanel() {
		
		this.setLayout(null);
		setBounds(15, 150, 370, 90);
		setVisible(true);		
		
		final Border border = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(border, " einlesen "));		
				
		final JLabel lblFile = new JLabel();
		lblFile.setText("Eingabedatei:");
		lblFile.setBounds(20,30,150,20);
		add(lblFile);
		
		final JTextField txtFile = new JTextField();
		txtFile.setBounds(200,30,150,20);
		add(txtFile);

		final JButton btnStart = new JButton();
		btnStart.setText("Start");
		btnStart.setBounds(200,57,150,20);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String file = txtFile.getText();
				new Work(file);
			}
			
		});
		add(btnStart);
		
	}
	
	@Override
	public void paintComponent(final Graphics graphics) {
		final Graphics2D g = ((Graphics2D)graphics);
		g.setColor(CanvasComponent.panelColor);
		g.fillRect(2, 8, 365, 80);		
	}

}
