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

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class DietMainPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable table;
	private JTextField searchField;
	private JButton btnSearch;
	CardLayout parentCards;
	
	// Constructor
	DietMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
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
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSelectElderly.setBounds(10, 57, 290, 31);
		
		add(lblDietLabel);
		add(lblSelectElderly);
		
		searchField = new JTextField();
		searchField.setBounds(56, 97, 165, 25);
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
		btnSearch.setBounds(228, 99, 65, 23);
		add(btnSearch);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 97, 47, 22);
		add(lblSearch);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(310, 97, 665, 468);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblElderid = new JLabel("ElderID:");
		lblElderid.setBounds(10, 55, 209, 14);
		panel.add(lblElderid);
		
		JLabel lblInfoName = new JLabel("Elderly Name");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 11, 645, 37);
		panel.add(lblInfoName);
		
		JLabel lblAge = new JLabel("Age: --");
		lblAge.setBounds(10, 95, 209, 14);
		panel.add(lblAge);
		
		JLabel lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(10, 75, 209, 14);
		panel.add(lblRoomNumber);
		
		JLabel lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(10, 115, 209, 14);
		panel.add(lblNric);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(3, 139, 659, 3);
		panel.add(separator);
		
		JLabel lblStatisticsForToday = new JLabel("Statistics For Today");
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblStatisticsForToday.setBounds(10, 145, 209, 29);
		panel.add(lblStatisticsForToday);
		
		JLabel lblCalories = new JLabel("RDA Calories (kcal):  --- (x% of RDA)");
		lblCalories.setBounds(10, 181, 209, 14);
		panel.add(lblCalories);
		
		JLabel lblCarbohydrates = new JLabel("Carbohydrates (g): --- (x% of RDA)");
		lblCarbohydrates.setBounds(10, 201, 209, 14);
		panel.add(lblCarbohydrates);
		
		JLabel lblProtein = new JLabel("Protein(g) :  --- (x% of RDA)");
		lblProtein.setBounds(10, 221, 209, 14);
		panel.add(lblProtein);
		
		JLabel lblIron = new JLabel("Iron(mg):  --- (x% of RDA)");
		lblIron.setBounds(11, 241, 208, 14);
		panel.add(lblIron);
		
		JLabel lblVitaminA = new JLabel("Vitamin A (mg):  --- (x% of RDA)");
		lblVitaminA.setBounds(10, 261, 209, 14);
		panel.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (mg):  --- (x% of RDA)");
		lblVitaminC.setBounds(10, 281, 209, 14);
		panel.add(lblVitaminC);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (mg):  --- (x% of RDA)");
		lblVitaminD.setBounds(10, 321, 209, 14);
		panel.add(lblVitaminD);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (mg):  --- (x% of RDA)");
		lblVitaminE.setBounds(10, 301, 209, 14);
		panel.add(lblVitaminE);
		
		JLabel lblPreviousMeal = new JLabel("Previous Meal: ______________________");
		lblPreviousMeal.setBounds(9, 357, 209, 14);
		panel.add(lblPreviousMeal);
		
		JLabel lblReviewInfo = new JLabel("Review Information");
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(310, 59, 665, 31);
		add(lblReviewInfo);
		

		
		JButton btnAddMeal = new JButton("Add Meal");
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel dietAddPanel = new DietAddPanel();
				dietAddPanel.setVisible(true);
			}
		});
		btnAddMeal.setBounds(309, 576, 215, 82);
		add(btnAddMeal);
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        CardLayout parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DADDPANEL);
			}
		});
		
		JButton btnModifyMeals = new JButton("Update Existing Meals");
		btnModifyMeals.setBounds(534, 576, 215, 83);
		add(btnModifyMeals);
		btnModifyMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        CardLayout parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMODPANEL);
			}
		});
		
		JButton btnMenuManagement = new JButton("Modify Menu Available");
		btnMenuManagement.setBounds(759, 576, 216, 29);
		add(btnMenuManagement);
		btnMenuManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        CardLayout parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMENUPANEL);
			}
		});
		
		JButton btnViewInMgmt = new JButton("View Elderly in Management Panel");
		btnViewInMgmt.setBounds(759, 611, 216, 45);
		add(btnViewInMgmt);
	}
}