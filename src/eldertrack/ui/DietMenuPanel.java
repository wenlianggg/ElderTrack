package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class DietMenuPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	private JTable prevMealsTable;
	private JTextField fieldVitA;
	private JTextField fieldVitC;
	private JTextField fieldVitE;
	private JTextField fieldVitD;
	private JTextField fieldFat;
	private JTextField fieldIron;
	private JTextField fieldProtein;
	private JTextField fieldCarbohydrates;
	private JTextField fieldCalories;
	private JTextField textField;
	private JTextField textField_1;
	
	// Constructor
	DietMenuPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		add(lblDietLabel);
		
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personInfoPanel.setBounds(10, 59, 975, 48);
		add(personInfoPanel);
		personInfoPanel.setLayout(null);
		
		JLabel lblInfoName = new JLabel("Editing Available Meals");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 7, 645, 37);
		personInfoPanel.add(lblInfoName);
		
		JButton btnBackToMain = new JButton("Back (Elderly View)");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMAINPANEL);
			}
		});
		
		JButton btnRemoveEntry = new JButton("Remove Menu Entry");
		btnRemoveEntry.setBounds(782, 358, 203, 109);
		add(btnRemoveEntry);
		
		JButton btnModifyEntry = new JButton("Modify Menu Entry");
		btnModifyEntry.setBounds(782, 238, 203, 109);
		add(btnModifyEntry);
		btnBackToMain.setBounds(782, 611, 203, 48);
		add(btnBackToMain);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(null);
		nutriInfoPanel.setBounds(382, 113, 388, 545);
		add(nutriInfoPanel);
		nutriInfoPanel.setLayout(null);
		
		JLabel lblStatisticsForToday = new JLabel("Nutritional Information");
		lblStatisticsForToday.setBounds(10, 14, 344, 29);
		nutriInfoPanel.add(lblStatisticsForToday);
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCalories = new JLabel("Calories (kcal):");
		lblCalories.setBounds(11, 120, 100, 14);
		nutriInfoPanel.add(lblCalories);
		
		JLabel lblCarbohydrates = new JLabel("Carbohydrates (g):");
		lblCarbohydrates.setBounds(10, 145, 100, 14);
		nutriInfoPanel.add(lblCarbohydrates);
		
		JLabel lblProtein = new JLabel("Protein(g):");
		lblProtein.setBounds(10, 187, 100, 14);
		nutriInfoPanel.add(lblProtein);
		
		JLabel lblIron = new JLabel("Iron(mg):");
		lblIron.setBounds(10, 212, 100, 14);
		nutriInfoPanel.add(lblIron);
		
		JLabel lblVitaminA = new JLabel("Vitamin A (mg):");
		lblVitaminA.setBounds(11, 237, 100, 14);
		nutriInfoPanel.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (mg):");
		lblVitaminC.setBounds(10, 262, 100, 14);
		nutriInfoPanel.add(lblVitaminC);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (mg):");
		lblVitaminE.setBounds(11, 287, 100, 14);
		nutriInfoPanel.add(lblVitaminE);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (mg):");
		lblVitaminD.setBounds(11, 312, 100, 14);
		nutriInfoPanel.add(lblVitaminD);
		
		JLabel lblFatg = new JLabel("Fat (g):");
		lblFatg.setBounds(10, 337, 100, 14);
		nutriInfoPanel.add(lblFatg);
		
		JLabel lblMealName = new JLabel("Meal Name");
		lblMealName.setForeground(Color.BLACK);
		lblMealName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMealName.setBounds(11, 66, 125, 29);
		nutriInfoPanel.add(lblMealName);
		
		fieldCalories = new JTextField();
		fieldCalories.setColumns(10);
		fieldCalories.setBounds(109, 117, 207, 20);
		nutriInfoPanel.add(fieldCalories);
		
		fieldCarbohydrates = new JTextField();
		fieldCarbohydrates.setColumns(10);
		fieldCarbohydrates.setBounds(109, 142, 207, 20);
		nutriInfoPanel.add(fieldCarbohydrates);
		
		fieldProtein = new JTextField();
		fieldProtein.setColumns(10);
		fieldProtein.setBounds(109, 184, 207, 20);
		nutriInfoPanel.add(fieldProtein);
		
		fieldIron = new JTextField();
		fieldIron.setColumns(10);
		fieldIron.setBounds(109, 209, 207, 20);
		nutriInfoPanel.add(fieldIron);
		
		fieldVitA = new JTextField();
		fieldVitA.setBounds(109, 234, 207, 20);
		nutriInfoPanel.add(fieldVitA);
		fieldVitA.setColumns(10);
		
		fieldVitC = new JTextField();
		fieldVitC.setBounds(109, 259, 207, 20);
		nutriInfoPanel.add(fieldVitC);
		fieldVitC.setColumns(10);
		
		fieldVitE = new JTextField();
		fieldVitE.setColumns(10);
		fieldVitE.setBounds(109, 284, 207, 20);
		nutriInfoPanel.add(fieldVitE);
		
		fieldVitD = new JTextField();
		fieldVitD.setColumns(10);
		fieldVitD.setBounds(109, 309, 207, 20);
		nutriInfoPanel.add(fieldVitD);
		
		fieldFat = new JTextField();
		fieldFat.setColumns(10);
		fieldFat.setBounds(109, 334, 207, 20);
		nutriInfoPanel.add(fieldFat);
		
		JLabel lblRda = new JLabel("Recommended Daily Allowance (%):");
		lblRda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRda.setBounds(12, 419, 306, 29);
		nutriInfoPanel.add(lblRda);
		
		JLabel lblAddedOnDdmmyy = new JLabel("Added On: dd/mm/yy hh:mmAM");
		lblAddedOnDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedOnDdmmyy.setBounds(12, 475, 306, 20);
		nutriInfoPanel.add(lblAddedOnDdmmyy);
		
		JLabel lblAddedByDdmmyy = new JLabel("Added By: PERSON NAME");
		lblAddedByDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedByDdmmyy.setBounds(12, 494, 257, 20);
		nutriInfoPanel.add(lblAddedByDdmmyy);
		
		JLabel lblLastModifiedDdmmyy = new JLabel("Last Modified: dd/mm/yy hh:mmAM by PERSON NAME");
		lblLastModifiedDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastModifiedDdmmyy.setBounds(12, 514, 318, 20);
		nutriInfoPanel.add(lblLastModifiedDdmmyy);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 63, 232, 33);
		nutriInfoPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 364, 503);
		add(scrollPane);
		
		prevMealsTable = new JTable();
		prevMealsTable.setFont(new Font("Tahoma", Font.PLAIN, 11));
		prevMealsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID", "Meal", "% RDA"
			}
		));
		prevMealsTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		prevMealsTable.getColumnModel().getColumn(0).setMaxWidth(25);
		prevMealsTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		prevMealsTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		prevMealsTable.getColumnModel().getColumn(2).setMaxWidth(70);
		scrollPane.setViewportView(prevMealsTable);
		
		textField = new JTextField();
		textField.setBounds(67, 120, 181, 27);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSearch.setBounds(9, 122, 57, 21);
		add(lblSearch);
		
		JButton btnMenuSearch = new JButton("Search Menu");
		btnMenuSearch.setBounds(258, 116, 111, 33);
		add(btnMenuSearch);
		
		JButton btnNewButton = new JButton("New Menu Entry");
		btnNewButton.setBounds(782, 118, 203, 109);
		add(btnNewButton);

	}
}
