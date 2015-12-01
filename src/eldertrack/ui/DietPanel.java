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
import javax.swing.JSeparator;

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
		tableScrollPane.setBounds(10, 130, 275, 500);
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
		btnSearch.setBounds(211, 98, 74, 23);
		add(btnSearch);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 97, 47, 22);
		add(lblSearch);
		
		JPanel panel = new JPanel();
		panel.setBounds(309, 97, 665, 440);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblInfoName = new JLabel("Elderly Name");
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 11, 645, 37);
		panel.add(lblInfoName);
		
		JLabel lblAge = new JLabel("Age: --");
		lblAge.setBounds(10, 84, 105, 14);
		panel.add(lblAge);
		
		JLabel lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(10, 59, 105, 14);
		panel.add(lblRoomNumber);
		
		JLabel lblNewLabel = new JLabel("NRIC: SXXXXXXXA");
		lblNewLabel.setBounds(10, 109, 105, 14);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 166, 665, 2);
		panel.add(separator);
		
		JLabel lblRecommendedCalorieIntake = new JLabel("RDA Calories: xxx kcal");
		lblRecommendedCalorieIntake.setBounds(10, 179, 645, 92);
		panel.add(lblRecommendedCalorieIntake);
		
		JLabel lblReviewInfo = new JLabel("Review Information");
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReviewInfo.setBounds(309, 58, 154, 25);
		add(lblReviewInfo);
		
		JButton btnModifyMeals = new JButton("Update Existing Meals");
		btnModifyMeals.setBounds(534, 548, 215, 83);
		add(btnModifyMeals);
		
		JButton btnAddMeal = new JButton("Add Meal");
		btnAddMeal.setBounds(309, 548, 215, 82);
		add(btnAddMeal);
		
		JButton btnNewButton = new JButton("View Elderly in Management Panel");
		btnNewButton.setBounds(759, 588, 215, 40);
		add(btnNewButton);
		
		JButton btnMenuManagement = new JButton("Modify Menu Available");
		btnMenuManagement.setBounds(759, 548, 215, 29);
		add(btnMenuManagement);

	}
}
