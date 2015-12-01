package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import eldertrack.diet.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DietPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable table;
	private JTextField searchField;
	private JButton btnSearch;
	
	// Constructor
	DietPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 274, 427);
		add(tableScrollPane);
		
		
		try {
			DefaultTableModel allEldersData = TableHelper.getElderlyFromQuery("");
			table = new JTable(allEldersData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableScrollPane.setViewportView(table);
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		lblSelectElderly = new JLabel("Select An Elderly");
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectElderly.setBounds(10, 55, 134, 31);
		
		add(lblDietLabel);
		add(lblSelectElderly);
		
		searchField = new JTextField();
		searchField.setBounds(56, 97, 145, 25);
		add(searchField);
		searchField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(TableHelper.getElderlyFromQuery("%" + searchField.getText() + "%"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				};
			}
		});
		btnSearch.setBounds(211, 98, 73, 23);
		add(btnSearch);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 97, 47, 22);
		add(lblSearch);

	}
}
