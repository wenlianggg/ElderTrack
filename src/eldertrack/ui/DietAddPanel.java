package eldertrack.ui;

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
		tableScrollPane.setBounds(10, 220, 283, 413);
		add(tableScrollPane);
		
		try {
			DefaultTableModel allEldersData = TableHelper.getElderlyFromQuery("");
			mealSearchTable = new JTable(allEldersData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mealSearchTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		mealSearchTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
					"ID", "Time of Day", "Meal"
				}
			));
		tableScrollPane.setViewportView(mealSearchTable);
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		lblSelectElderly = new JLabel("Select A Meal");
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 59, 660, 86);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblElderid = new JLabel("ElderID: 0000");
		lblElderid.setBounds(8, 44, 175, 14);
		panel.add(lblElderid);
		
		JLabel lblInfoName = new JLabel("Adding Meal Entry For:");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 7, 645, 37);
		panel.add(lblInfoName);
		
		JLabel lblAge = new JLabel("Age: --");
		lblAge.setBounds(8, 61, 175, 14);
		panel.add(lblAge);
		
		JLabel lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(195, 61, 209, 14);
		panel.add(lblRoomNumber);
		
		JLabel lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(195, 44, 209, 14);
		panel.add(lblNric);
		
		JLabel lblReviewInfo = new JLabel("Previous Meals");
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(680, 59, 665, 31);
		add(lblReviewInfo);
		
		JButton btnModifyMeals = new JButton("Back (Elderly View)");
		btnModifyMeals.setBounds(682, 588, 303, 48);
		add(btnModifyMeals);
		
		JButton btnAddMeal = new JButton("Add Meal Entry");
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddMeal.setBounds(306, 588, 364, 47);
		add(btnAddMeal);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(306, 156, 364, 421);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStatisticsForToday = new JLabel("Nutritional Information");
		lblStatisticsForToday.setBounds(11, 11, 344, 29);
		panel_1.add(lblStatisticsForToday);
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCalories = new JLabel("RDA Calories (kcal):  --- (x% of RDA)");
		lblCalories.setBounds(11, 74, 209, 14);
		panel_1.add(lblCalories);
		
		JLabel lblCarbohydrates = new JLabel("Carbohydrates (g): --- (x% of RDA)");
		lblCarbohydrates.setBounds(11, 94, 209, 14);
		panel_1.add(lblCarbohydrates);
		
		JLabel lblProtein = new JLabel("Protein(g) :  --- (x% of RDA)");
		lblProtein.setBounds(11, 114, 209, 14);
		panel_1.add(lblProtein);
		
		JLabel lblIron = new JLabel("Iron(mg):  --- (x% of RDA)");
		lblIron.setBounds(12, 134, 208, 14);
		panel_1.add(lblIron);
		
		JLabel lblVitaminA = new JLabel("Vitamin A (mg):  --- (x% of RDA)");
		lblVitaminA.setBounds(11, 154, 209, 14);
		panel_1.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (mg):  --- (x% of RDA)");
		lblVitaminC.setBounds(11, 174, 209, 14);
		panel_1.add(lblVitaminC);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (mg):  --- (x% of RDA)");
		lblVitaminE.setBounds(11, 194, 209, 14);
		panel_1.add(lblVitaminE);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (mg):  --- (x% of RDA)");
		lblVitaminD.setBounds(11, 214, 209, 14);
		panel_1.add(lblVitaminD);
		
		JLabel lblPreviousMeal = new JLabel("Previous Meal: ______________________");
		lblPreviousMeal.setBounds(10, 299, 209, 14);
		panel_1.add(lblPreviousMeal);
		
		JLabel lblFatg = new JLabel("Fat (g): --- (x% of RDA)");
		lblFatg.setBounds(10, 233, 209, 14);
		panel_1.add(lblFatg);
		
		JLabel lblMealName = new JLabel("Meal Name");
		lblMealName.setForeground(Color.BLACK);
		lblMealName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMealName.setBounds(11, 40, 344, 29);
		panel_1.add(lblMealName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(684, 98, 298, 479);
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

	}
}
