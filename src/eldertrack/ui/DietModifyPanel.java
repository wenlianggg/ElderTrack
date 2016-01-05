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

public class DietModifyPanel extends JPanel {
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
	
	// Constructor
	DietModifyPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		
		lblDietLabel = new JLabel("ElderTrack Diet Management");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 557, 54);
		
		add(lblDietLabel);
		
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personInfoPanel.setBounds(10, 59, 975, 86);
		add(personInfoPanel);
		personInfoPanel.setLayout(null);
		
		JLabel lblElderid = new JLabel("ElderID: 0000");
		lblElderid.setBounds(8, 44, 175, 14);
		personInfoPanel.add(lblElderid);
		
		JLabel lblInfoName = new JLabel("Modifying Meal Entry For:");
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
		lblReviewInfo.setBounds(10, 156, 168, 31);
		add(lblReviewInfo);
		
		JButton btnBackToMain = new JButton("Back (Elderly View)");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMAINPANEL);
			}
		});
		
		JButton btnRemoveEntry = new JButton("Remove Meal Entry");
		btnRemoveEntry.setBounds(782, 156, 203, 109);
		add(btnRemoveEntry);
		
		JButton btnModifyEntry = new JButton("Remove Meal Entry");
		btnModifyEntry.setBounds(782, 276, 203, 109);
		add(btnModifyEntry);
		btnBackToMain.setBounds(782, 611, 203, 48);
		add(btnBackToMain);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(null);
		nutriInfoPanel.setBounds(384, 156, 388, 503);
		add(nutriInfoPanel);
		nutriInfoPanel.setLayout(null);
		
		JLabel lblStatisticsForToday = new JLabel("Nutritional Information");
		lblStatisticsForToday.setBounds(11, 11, 344, 29);
		nutriInfoPanel.add(lblStatisticsForToday);
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCalories = new JLabel("Calories (kcal):");
		lblCalories.setBounds(12, 80, 100, 14);
		nutriInfoPanel.add(lblCalories);
		
		JLabel lblCarbohydrates = new JLabel("Carbohydrates (g):");
		lblCarbohydrates.setBounds(11, 105, 100, 14);
		nutriInfoPanel.add(lblCarbohydrates);
		
		JLabel lblProtein = new JLabel("Protein(g):");
		lblProtein.setBounds(11, 135, 100, 14);
		nutriInfoPanel.add(lblProtein);
		
		JLabel lblIron = new JLabel("Iron(mg):");
		lblIron.setBounds(11, 160, 100, 14);
		nutriInfoPanel.add(lblIron);
		
		JLabel lblVitaminA = new JLabel("Vitamin A (mg):");
		lblVitaminA.setBounds(12, 185, 100, 14);
		nutriInfoPanel.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (mg):");
		lblVitaminC.setBounds(11, 210, 100, 14);
		nutriInfoPanel.add(lblVitaminC);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (mg):");
		lblVitaminE.setBounds(12, 235, 100, 14);
		nutriInfoPanel.add(lblVitaminE);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (mg):");
		lblVitaminD.setBounds(12, 260, 100, 14);
		nutriInfoPanel.add(lblVitaminD);
		
		JLabel lblFatg = new JLabel("Fat (g):");
		lblFatg.setBounds(11, 285, 100, 14);
		nutriInfoPanel.add(lblFatg);
		
		JLabel lblMealName = new JLabel("Meal Name");
		lblMealName.setForeground(Color.BLACK);
		lblMealName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMealName.setBounds(11, 40, 344, 29);
		nutriInfoPanel.add(lblMealName);
		
		fieldCalories = new JTextField();
		fieldCalories.setColumns(10);
		fieldCalories.setBounds(110, 77, 207, 20);
		nutriInfoPanel.add(fieldCalories);
		
		fieldCarbohydrates = new JTextField();
		fieldCarbohydrates.setColumns(10);
		fieldCarbohydrates.setBounds(110, 102, 207, 20);
		nutriInfoPanel.add(fieldCarbohydrates);
		
		fieldProtein = new JTextField();
		fieldProtein.setColumns(10);
		fieldProtein.setBounds(110, 132, 207, 20);
		nutriInfoPanel.add(fieldProtein);
		
		fieldIron = new JTextField();
		fieldIron.setColumns(10);
		fieldIron.setBounds(110, 157, 207, 20);
		nutriInfoPanel.add(fieldIron);
		
		fieldVitA = new JTextField();
		fieldVitA.setBounds(110, 182, 207, 20);
		nutriInfoPanel.add(fieldVitA);
		fieldVitA.setColumns(10);
		
		fieldVitC = new JTextField();
		fieldVitC.setBounds(110, 207, 207, 20);
		nutriInfoPanel.add(fieldVitC);
		fieldVitC.setColumns(10);
		
		fieldVitE = new JTextField();
		fieldVitE.setColumns(10);
		fieldVitE.setBounds(110, 232, 207, 20);
		nutriInfoPanel.add(fieldVitE);
		
		fieldVitD = new JTextField();
		fieldVitD.setColumns(10);
		fieldVitD.setBounds(110, 257, 207, 20);
		nutriInfoPanel.add(fieldVitD);
		
		fieldFat = new JTextField();
		fieldFat.setColumns(10);
		fieldFat.setBounds(110, 282, 207, 20);
		nutriInfoPanel.add(fieldFat);
		
		JLabel lblRda = new JLabel("Recommended Daily Allowance (%):");
		lblRda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRda.setBounds(11, 339, 306, 29);
		nutriInfoPanel.add(lblRda);
		
		JLabel lblAddedOnDdmmyy = new JLabel("Added On: dd/mm/yy hh:mmAM");
		lblAddedOnDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedOnDdmmyy.setBounds(11, 412, 306, 20);
		nutriInfoPanel.add(lblAddedOnDdmmyy);
		
		JLabel lblAddedByDdmmyy = new JLabel("Added By: PERSON NAME");
		lblAddedByDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedByDdmmyy.setBounds(11, 431, 257, 20);
		nutriInfoPanel.add(lblAddedByDdmmyy);
		
		JLabel lblLastModifiedDdmmyy = new JLabel("Last Modified: dd/mm/yy hh:mmAM by PERSON NAME");
		lblLastModifiedDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastModifiedDdmmyy.setBounds(11, 451, 318, 20);
		nutriInfoPanel.add(lblLastModifiedDdmmyy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 364, 461);
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
