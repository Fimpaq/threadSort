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
public class GeneratePanel extends JPanel {

	public GeneratePanel() {
		this.setLayout(null);
		setBounds(15, 20, 370, 115);
		setVisible(true);

		final Border border = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(border, " generieren "));

		final JLabel lblAmount = new JLabel();
		lblAmount.setText("Anzahl der Elemente:");
		lblAmount.setBounds(20, 30, 150, 20);
		add(lblAmount);

		final JTextField txtAmount = new JTextField();
		txtAmount.setBounds(200, 30, 150, 20);
		add(txtAmount);

		final JLabel lblfile = new JLabel();
		lblfile.setText("Ausgabedatei:");
		lblfile.setBounds(20, 57, 150, 20);
		add(lblfile);

		final JTextField txtFile = new JTextField();
		txtFile.setBounds(200, 57, 125, 20);
		add(txtFile);

		final JLabel lblFileExt = new JLabel();
		lblFileExt.setText(".txt");
		lblFileExt.setBounds(325, 57, 120, 20);
		add(lblFileExt);

		final JButton btnStart = new JButton();
		btnStart.setText("Start");
		btnStart.setBounds(200, 83, 150, 20);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				if (txtAmount.getText().equals("")) {
					txtAmount.setText("pls write amount!");
					txtAmount.requestFocus();
					txtAmount.selectAll();
					
				} else {
					if (txtFile.getText().equals("")) {
						txtFile.setText("pls write filename!");
						txtFile.requestFocus();
						txtFile.selectAll();
					} else {
						final int amount = Integer.parseInt(txtAmount.getText());
						final String file = txtFile.getText();
						new Work(amount, file);
					}
				}
			}

		});
		add(btnStart);

	}

	@Override
	public void paintComponent(final Graphics graphics) {
		final Graphics2D g = ((Graphics2D) graphics);
		g.setColor(CanvasComponent.panelColor);
		g.fillRect(2, 8, 365, 105);
	}
}
