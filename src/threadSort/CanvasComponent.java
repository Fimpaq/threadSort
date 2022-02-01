package threadSort;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class CanvasComponent extends JComponent {

	
	static final int frameWidth = 570;
	static final int frameHeigth = 530;
	static final Color panelColor = new Color(180,255,180);
	static DefaultListModel<Integer> listModel = new DefaultListModel<>();
	
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
		
		// OutputList
		JList<Integer> jList = new JList<>(listModel);
		jList.setBackground(panelColor);
		jList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String num = jList.getSelectedValue().toString();
				int idx = jList.getSelectedIndex();
				
				StatusPanel.setStatus("[" + idx + "]   " + num);
			}
			
		});
		JScrollPane scrollPane = new JScrollPane(jList);
		scrollPane.setBounds(405,28,140,450);
		scrollPane.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		scrollPane.setAutoscrolls(true);
		add(scrollPane);
		
		
		
	}
}
