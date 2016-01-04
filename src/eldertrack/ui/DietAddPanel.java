package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import eldertrack.diet.TableHelper;

public class DietAddPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable mealSearchTable;
	private JTextField searchField;
	private JButton btnSearch;
	private JTable prevMealsTable;
	
	// Constructor
	DietAddPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);

		JScrollPane tableScrollPane = new JScrollPane(mealSearchTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 220, 283, 439);
		add(tableScrollPane);
		
		try {
			DefaultTableModel allEldersData = TableHelper.getElderlyFromQuery("");
			mealSearchTable = new JTable(allEldersData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mealSearchTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"ID", "Menu Item"
			}
		));
		mealSearchTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		mealSearchTable.getColumnModel().getColumn(1).setPreferredWidth(165);
		tableScrollPane.setViewportView(mealSearchTable);
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		lblSelectElderly = new JLabel("Select A Meal");
		lblSelectElderly.setForeground(new Color(0, 128, 128));
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSelectElderly.setBounds(10, 156, 290, 31);
		
		add(lblDietLabel);
		add(lblSelectElderly);
		
		searchField = new JTextField();
		searchField.setBounds(51, 189, 167, 25);
		add(searchField);
		searchField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mealSearchTable.setModel(TableHelper.getElderlyFromQuery("%" + searchField.getText() + "%"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				};
			}
		});
		btnSearch.setBounds(228, 190, 65, 23);
		add(btnSearch);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 189, 47, 22);
		add(lblSearch);
		
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personInfoPanel.setBounds(10, 59, 660, 86);
		add(personInfoPanel);
		personInfoPanel.setLayout(null);
		
		JLabel lblElderid = new JLabel("ElderID: 0000");
		lblElderid.setBounds(8, 44, 175, 14);
		personInfoPanel.add(lblElderid);
		
		JLabel lblInfoName = new JLabel("Adding Meal Entry For:");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 7, 645, 37);
		personInfoPanel.add(lblInfoName);
		
		JLabel lblAge = new JLabel("Age: --");
		lblAge.setBounds(8, 61, 175, 14);
		personInfoPanel.add(lblAge);
		
		JLabel lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(195, 61, 209, 14);
		personInfoPanel.add(lblRoomNumber);
		
		JLabel lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(195, 44, 209, 14);
		personInfoPanel.add(lblNric);
		
		JLabel lblReviewInfo = new JLabel("Previous Meals");
		lblReviewInfo.setForeground(new Color(0, 128, 128));
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(681, 54, 665, 31);
		add(lblReviewInfo);
		
		JButton btnModifyMeals = new JButton("Back (Elderly View)");
		btnModifyMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMAINPANEL);
			}
		});
		btnModifyMeals.setBounds(682, 611, 303, 48);
		add(btnModifyMeals);
		
		JButton btnAddMeal = new JButton("Add Meal Entry");

		btnAddMeal.setBounds(682, 553, 303, 47);
		add(btnAddMeal);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(null);
		nutriInfoPanel.setBounds(306, 156, 364, 503);
		add(nutriInfoPanel);
		nutriInfoPanel.setLayout(null);
		
		JLabel lblStatisticsForToday = new JLabel("Nutritional Information");
		lblStatisticsForToday.setBounds(11, 11, 344, 29);
		nutriInfoPanel.add(lblStatisticsForToday);
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCalories = new JLabel("RDA Calories (kcal):  --- (x% of RDA)");
		lblCalories.setBounds(12, 80, 209, 14);
		nutriInfoPanel.add(lblCalories);
		
		JLabel lblCarbohydrates = new JLabel("Carbohydrates (g): --- (x% of RDA)");
		lblCarbohydrates.setBounds(12, 100, 209, 14);
		nutriInfoPanel.add(lblCarbohydrates);
		
		JLabel lblProtein = new JLabel("Protein(g) :  --- (x% of RDA)");
		lblProtein.setBounds(12, 120, 209, 14);
		nutriInfoPanel.add(lblProtein);
		
		JLabel lblIron = new JLabel("Iron(mg):  --- (x% of RDA)");
		lblIron.setBounds(13, 140, 208, 14);
		nutriInfoPanel.add(lblIron);
		
		JLabel lblVitaminA = new JLabel("Vitamin A (mg):  --- (x% of RDA)");
		lblVitaminA.setBounds(12, 160, 209, 14);
		nutriInfoPanel.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (mg):  --- (x% of RDA)");
		lblVitaminC.setBounds(12, 180, 209, 14);
		nutriInfoPanel.add(lblVitaminC);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (mg):  --- (x% of RDA)");
		lblVitaminE.setBounds(12, 200, 209, 14);
		nutriInfoPanel.add(lblVitaminE);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (mg):  --- (x% of RDA)");
		lblVitaminD.setBounds(12, 220, 209, 14);
		nutriInfoPanel.add(lblVitaminD);
		
		JLabel lblFatg = new JLabel("Fat (g): --- (x% of RDA)");
		lblFatg.setBounds(11, 239, 209, 14);
		nutriInfoPanel.add(lblFatg);
		
		JLabel lblMealName = new JLabel("Meal Name");
		lblMealName.setForeground(Color.BLACK);
		lblMealName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMealName.setBounds(11, 40, 344, 29);
		nutriInfoPanel.add(lblMealName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(682, 83, 303, 459);
		add(scrollPane);
		
		prevMealsTable = new JTable();
		prevMealsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID", "Time of Day", "Meal"
			}
		));
		prevMealsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		prevMealsTable.getColumnModel().getColumn(1).setPreferredWidth(102);
		prevMealsTable.getColumnModel().getColumn(2).setPreferredWidth(208);
		scrollPane.setViewportView(prevMealsTable);

		JButton btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout parentCards = (CardLayout) MainFrame.CardsPanel.getLayout();
				parentCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
			}
		});
		btnMainMenu.setBounds(820, 15, 139, 40);
		add(btnMainMenu);
	}
}
