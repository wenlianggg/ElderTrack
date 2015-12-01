package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DietPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable table;
	
	// Constructor
	DietPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
        String[] columnNames = {"ID", "Name", "Room Number"};

		Object[][] data = {{"Kathy", "Smith", "Snowboarding", new Integer(5)},
		{"John", "Doe", "Rowing", new Integer(3)},
		{"Sue", "Black", "Knitting", new Integer(2)},
		{"Jane", "White", "Speed reading", new Integer(20)},
		{"Joe", "Brown", "Pool", new Integer(10)}};
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 99, 336, 328);
		add(scrollPane);
		
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		lblSelectElderly = new JLabel("Select Elderly");
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectElderly.setBounds(10, 65, 119, 34);
		
		add(lblDietLabel);
		add(lblSelectElderly);

	}
}
