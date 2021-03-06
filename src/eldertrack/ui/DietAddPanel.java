package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import eldertrack.diet.Elderly;
import eldertrack.diet.MenuItem;
import eldertrack.diet.Nutrition;
import eldertrack.misc.TableHelper;
import javax.swing.JCheckBox;

public class DietAddPanel extends JPanel implements Presentable {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private int currentElderly;
	private JTable mealSearchTable;
	private JTextField searchField;
	private JButton btnSearch;
	private JTable prevMealsTable;
	private JLabel lblElderid;
	private JLabel lblAge;
	private JLabel lblRoomNumber;
	private JLabel lblNric;
	private JLabel lblCalories;
	private JLabel lblCarbohydrates;
	private JLabel lblProtein;
	private JLabel lblIron;
	private JLabel lblVitA;
	private JLabel lblVitC;
	private JLabel lblVitE;
	private JLabel lblVitD;
	private JLabel lblFat;
	private JLabel lblMealName;
	private JLabel lblInfoName;
	private CardLayout parentCards;
	private JCheckBox chckbxHalal;
	private JLabel lblDietManagement;
	
	// Constructor
	DietAddPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JScrollPane tableScrollPane = new JScrollPane(mealSearchTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 220, 283, 439);
		add(tableScrollPane);
		
		mealSearchTable = new JTable(TableHelper.getMeals(""));
		setMenuColumnWidths();
		mealSearchTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        presentMealData(mealSearchTable.getValueAt(mealSearchTable.getSelectedRow(), 0).toString());
		    }
		});
		tableScrollPane.setViewportView(mealSearchTable);
		
		lblDietLabel = new JLabel("ElderTrack Suite");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 283, 54);
		
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
				mealSearchTable.setModel(TableHelper.getMeals("%" + searchField.getText() + "%"));
				setMenuColumnWidths();
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
		
		lblElderid = new JLabel("ElderID: 0000");
		lblElderid.setBounds(8, 44, 175, 14);
		personInfoPanel.add(lblElderid);
		
		lblInfoName = new JLabel("Adding Meal Entry For:");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 7, 645, 37);
		personInfoPanel.add(lblInfoName);
		
		lblAge = new JLabel("Age: --");
		lblAge.setBounds(8, 61, 175, 14);
		personInfoPanel.add(lblAge);
		
		lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(195, 61, 209, 14);
		personInfoPanel.add(lblRoomNumber);
		
		lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(195, 44, 209, 14);
		personInfoPanel.add(lblNric);
		
		JLabel lblReviewInfo = new JLabel("Previous Meals");
		lblReviewInfo.setForeground(new Color(0, 128, 128));
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(681, 54, 665, 31);
		add(lblReviewInfo);
		
		JButton btnBackToDiet = new JButton("Back (Elderly View)");
		btnBackToDiet.setForeground(new Color(30, 144, 255));
		btnBackToDiet.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBackToDiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
		        parentCards.show(DietSection.CardsPanel, DietSection.DMAINPANEL);
			}
		});
		btnBackToDiet.setBounds(682, 611, 303, 48);
		add(btnBackToDiet);
		
		JButton btnAddMeal = new JButton("Add Meal Entry");
		btnAddMeal.setForeground(new Color(30, 144, 255));
		btnAddMeal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mealSearchTable.getSelectedRow() != -1)
					addMealEntry(mealSearchTable.getValueAt(mealSearchTable.getSelectedRow(), 0).toString());
				else
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please select an item!");
			}
		});

		btnAddMeal.setBounds(682, 553, 303, 47);
		add(btnAddMeal);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		nutriInfoPanel.setBounds(306, 156, 364, 503);
		add(nutriInfoPanel);
		nutriInfoPanel.setLayout(null);
		
		JLabel lblStatisticsForToday = new JLabel("Nutritional Information");
		lblStatisticsForToday.setBounds(11, 11, 344, 29);
		nutriInfoPanel.add(lblStatisticsForToday);
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		lblCalories = new JLabel("RDA Calories (kcal):  --- ");
		lblCalories.setBounds(12, 80, 209, 14);
		nutriInfoPanel.add(lblCalories);
		
		lblCarbohydrates = new JLabel("Carbohydrates (g): --- ");
		lblCarbohydrates.setBounds(12, 100, 209, 14);
		nutriInfoPanel.add(lblCarbohydrates);
		
		lblProtein = new JLabel("Protein(g) :  --- ");
		lblProtein.setBounds(12, 120, 209, 14);
		nutriInfoPanel.add(lblProtein);
		
		lblIron = new JLabel("Iron(mg):  --- ");
		lblIron.setBounds(14, 159, 208, 14);
		nutriInfoPanel.add(lblIron);
		
		lblVitA = new JLabel("Vitamin A (%):  --- ");
		lblVitA.setBounds(13, 179, 209, 14);
		nutriInfoPanel.add(lblVitA);
		
		lblVitC = new JLabel("Vitamin C (%):  --- ");
		lblVitC.setBounds(13, 199, 209, 14);
		nutriInfoPanel.add(lblVitC);
		
		lblVitE = new JLabel("Vitamin E (%):  --- ");
		lblVitE.setBounds(13, 219, 209, 14);
		nutriInfoPanel.add(lblVitE);
		
		lblVitD = new JLabel("Vitamin D (%):  --- ");
		lblVitD.setBounds(13, 239, 209, 14);
		nutriInfoPanel.add(lblVitD);
		
		lblFat = new JLabel("Fat (g): --- ");
		lblFat.setBounds(13, 140, 209, 14);
		nutriInfoPanel.add(lblFat);
		
		lblMealName = new JLabel("Meal Name");
		lblMealName.setForeground(Color.BLACK);
		lblMealName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMealName.setBounds(11, 40, 344, 29);
		nutriInfoPanel.add(lblMealName);
		
		chckbxHalal = new JCheckBox("Halal");
		chckbxHalal.setBounds(11, 255, 164, 23);
		chckbxHalal.setEnabled(false);
		nutriInfoPanel.add(chckbxHalal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(682, 83, 303, 459);
		add(scrollPane);
		
		prevMealsTable = new JTable();

		scrollPane.setViewportView(prevMealsTable);
		
		lblDietManagement = new JLabel("Diet Management\r\n - Meal Tracker (New Meal)");
		lblDietManagement.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblDietManagement.setBounds(300, 20, 414, 30);
		add(lblDietManagement);
	}
	
	private void setMenuColumnWidths() {
		mealSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mealSearchTable.getTableHeader().setResizingAllowed(false);
		mealSearchTable.getTableHeader().setReorderingAllowed(false);
		mealSearchTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		mealSearchTable.getColumnModel().getColumn(0).setMaxWidth(35);
		mealSearchTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		mealSearchTable.getColumnModel().getColumn(1).setMaxWidth(80);
		mealSearchTable.getColumnModel().getColumn(2).setPreferredWidth(130);
		mealSearchTable.getColumnModel().getColumn(3).setPreferredWidth(55);
		mealSearchTable.getColumnModel().getColumn(3).setMaxWidth(70);
	}
	
	private void setPrevMealsColumnWidth() {
		prevMealsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prevMealsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		prevMealsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		prevMealsTable.getColumnModel().getColumn(2).setPreferredWidth(250);
	}
	
	public void presentData(int personid) {
		Elderly el = Elderly.getElderlyMap().get(personid);
		prevMealsTable.setModel(el.getMeals().getTableModel());
		setPrevMealsColumnWidth();
		this.currentElderly = personid;
		
		lblInfoName.setText(el.getName());
		lblElderid.setText("ElderID: " + el.getId());
		lblAge.setText("Age: " + el.getAge());
		lblRoomNumber.setText("Room Number: " + el.getRoomnum());
		lblNric.setText("NRIC: " + el.getNric());
	}
	
	private void addMealEntry(String mid) {
		HashMap<Integer, Elderly> eldermap = Elderly.getElderlyMap();
		Elderly el = eldermap.get(this.currentElderly);
		el.addMeal(mid);
		presentData(this.currentElderly);
		setPrevMealsColumnWidth();
	}
	
	private void presentMealData(String mid) {
		MenuItem mi = MenuItem.getMenuMap().get(Integer.parseInt(mid));
		lblMealName.setText(mi.getName());
		chckbxHalal.setSelected(mi.isHalal());
		Nutrition n = mi.getNutrition();
		chckbxHalal.setSelected(mi.isHalal());
		if (n != null) {
			lblVitA.setText("Vitamin A (%): " + Integer.toString(n.getVita()));
			lblVitC.setText("Vitamin C (%): " + Integer.toString(n.getVitc()));
			lblVitD.setText("Vitamin D (%): " + Integer.toString(n.getVitd()));
			lblVitE.setText("Vitamin E (%): " + Integer.toString(n.getVite()));
			lblFat.setText("Fat (g): " + Integer.toString(n.getFat()));
			lblIron.setText("Iron (mg): " + Integer.toString(n.getIron()));
			lblCarbohydrates.setText("Carbohydrates (g): " + Integer.toString(n.getCarbs()));
			lblCalories.setText("Calories (kcal): " + Integer.toString(n.getCalories()));
			lblProtein.setText("Protein (g): " + Integer.toString(n.getProtein()));
		} else {
			lblVitA.setText("Vitamin A (%): ");
			lblVitC.setText("Vitamin C (%): ");
			lblVitD.setText("Vitamin D (%): ");
			lblVitE.setText("Vitamin E (%): ");
			lblFat.setText("Fat (g): ");
			lblIron.setText("Iron (g): ");
			lblCarbohydrates.setText("Carbohydrates (g):");
			lblCalories.setText("Calories (kcal): ");
			lblProtein.setText("Protein (g): ");
		}
	}

	@Override
	public void printDebug() {
		// TODO Auto-generated method stub
		
	}
}
