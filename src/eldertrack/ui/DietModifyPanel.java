package eldertrack.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
import javax.swing.JCheckBox;


import eldertrack.diet.Elderly;
import eldertrack.diet.MealProperties;
import eldertrack.diet.Meals;
import eldertrack.diet.Nutrition;
import eldertrack.diet.SerializerSQL;
import eldertrack.login.StaffSession;
import eldertrack.misc.TableHelper;

public class DietModifyPanel extends JPanel implements Presentable {
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
	private JCheckBox chckbxHalal;
	private JLabel lblElderid;
	private JLabel lblInfoName;
	private JLabel lblAge;
	private JLabel lblRoomNumber;
	private JLabel lblNric;
	private JLabel lblMealName;
	private CardLayout parentCards;
	private Integer currentElderly;
	private HashMap<Integer, Elderly> eldermap;
	private JLabel lblAddedOn;
	private JLabel lblAddedBy;
	private JLabel lblLastModified;
	private JLabel lblModifiedBy;
	private SimpleDateFormat sdf;
	private JLabel lblDietManagement;
	
	// Constructor
	DietModifyPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		lblDietLabel = new JLabel("ElderTrack Suite");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 281, 54);
		
		add(lblDietLabel);
		
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personInfoPanel.setBounds(10, 59, 975, 86);
		add(personInfoPanel);
		personInfoPanel.setLayout(null);
		
		lblElderid = new JLabel("ElderID: 0000");
		lblElderid.setBounds(8, 44, 175, 14);
		personInfoPanel.add(lblElderid);
		
		lblInfoName = new JLabel("Modifying Meal Entry For:");
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
		lblReviewInfo.setBounds(10, 156, 168, 31);
		add(lblReviewInfo);
		
		JButton btnRemoveEntry = new JButton("Remove Entry");
		btnRemoveEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeEntry();
			}
		});
		btnRemoveEntry.setForeground(new Color(255, 0, 0));
		btnRemoveEntry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveEntry.setBounds(782, 274, 203, 109);
		add(btnRemoveEntry);
		
		JButton btnModifyEntry = new JButton("Save Changes");
		btnModifyEntry.setForeground(new Color(60, 179, 113));
		btnModifyEntry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModifyEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveData();
			}
		});
		btnModifyEntry.setBounds(782, 156, 203, 109);
		add(btnModifyEntry);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
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
		
		JLabel lblVitaminA = new JLabel("Vitamin A (%):");
		lblVitaminA.setBounds(12, 185, 100, 14);
		nutriInfoPanel.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (%):");
		lblVitaminC.setBounds(11, 210, 100, 14);
		nutriInfoPanel.add(lblVitaminC);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (%):");
		lblVitaminE.setBounds(12, 235, 100, 14);
		nutriInfoPanel.add(lblVitaminE);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (%):");
		lblVitaminD.setBounds(12, 260, 100, 14);
		nutriInfoPanel.add(lblVitaminD);
		
		JLabel lblFatg = new JLabel("Fat (g):");
		lblFatg.setBounds(11, 285, 100, 14);
		nutriInfoPanel.add(lblFatg);
		
		lblMealName = new JLabel("Meal Name");
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
		
		sdf = new SimpleDateFormat("HH:mm dd MMM yyyy ");
		
		lblAddedOn = new JLabel("Added On: dd/mm/yy hh:mmAM");
		lblAddedOn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedOn.setBounds(11, 400, 306, 20);
		nutriInfoPanel.add(lblAddedOn);
		
		lblAddedBy = new JLabel("Added By: PERSON NAME");
		lblAddedBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedBy.setBounds(11, 420, 257, 20);
		nutriInfoPanel.add(lblAddedBy);
		
		lblLastModified = new JLabel("Last Modified: dd/mm/yy hh:mmAM");
		lblLastModified.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastModified.setBounds(11, 440, 318, 20);
		nutriInfoPanel.add(lblLastModified);
		
		lblModifiedBy = new JLabel("Modified By: PERSON NAME");
		lblModifiedBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModifiedBy.setBounds(11, 460, 207, 20);
		nutriInfoPanel.add(lblModifiedBy);
		
		chckbxHalal = new JCheckBox("Halal Meal");
		chckbxHalal.setBounds(11, 370, 97, 23);
		nutriInfoPanel.add(chckbxHalal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 364, 461);
		add(scrollPane);
		
		JButton btnBackToDiet = new JButton("Back (Elderly View)");
		btnBackToDiet.setForeground(new Color(30, 144, 255));
		btnBackToDiet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBackToDiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
		        parentCards.show(DietSection.CardsPanel, DietSection.DMAINPANEL);
		        clearData();
			}
		});
		btnBackToDiet.setBounds(782, 611, 203, 48);
		add(btnBackToDiet);
		
		prevMealsTable = new JTable();
		prevMealsTable.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(prevMealsTable);
		
		lblDietManagement = new JLabel("Diet Management\r\n - Meal Tracker (Modify Entries)");
		lblDietManagement.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblDietManagement.setBounds(300, 20, 468, 30);
		add(lblDietManagement);
		prevMealsTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				presentMealData(prevMealsTable.getValueAt(prevMealsTable.getSelectedRow(), 0).toString());
			}
		});
	}

	private void setColumnWidths() {
		prevMealsTable.getTableHeader().setResizingAllowed(false);
		prevMealsTable.getTableHeader().setReorderingAllowed(false);
		prevMealsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prevMealsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		prevMealsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		prevMealsTable.getColumnModel().getColumn(2).setPreferredWidth(250);
	}
	
	public void presentData(int personid) {
		this.currentElderly = personid;
		eldermap = Elderly.getElderlyMap();
		Elderly el = eldermap.get(this.currentElderly);
		prevMealsTable.setModel(el.getMeals().getTableModel());
		setColumnWidths();
		
		lblInfoName.setText(el.getName());
		lblElderid.setText("ElderID: " + el.getId());
		lblAge.setText("Age: " + el.getAge());
		lblRoomNumber.setText("Room Number: " + el.getRoomnum());
		lblNric.setText("NRIC: " + el.getNric());
	}
	
	private void saveData() {
		System.out.println("Saving modified meal...");
		int mealid = Integer.parseInt((String)prevMealsTable.getValueAt(prevMealsTable.getSelectedRow(), 0));
		Nutrition n = new Nutrition();
		
		// Build a new nutrition object with given inputs
		try {
			n.setVita(Integer.parseInt(fieldVitA.getText())).
			setVitc(Integer.parseInt(fieldVitC.getText())).
			setVitd(Integer.parseInt(fieldVitD.getText())).
			setVite(Integer.parseInt(fieldVitE.getText())).
			setFat(Integer.parseInt(fieldFat.getText())).
			setIron(Integer.parseInt(fieldIron.getText())).
			setCarbs(Integer.parseInt(fieldCarbohydrates.getText())).
			setCalories(Integer.parseInt(fieldCalories.getText())).
			setProtein(Integer.parseInt(fieldProtein.getText()));
			
			eldermap.get(this.currentElderly).getMeals().setNutrition(mealid, n);
			Meals m = eldermap.get(this.currentElderly).getMeals();
			SerializerSQL.storeMeals(this.currentElderly, m, TableHelper.getSQLInstance());
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Changes were saved successfully!");
		System.out.println("Saved meal into database!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "ERROR: Please check that all your inputs are correct!");
		}
	}
	
	private void removeEntry() {
		int mealid = Integer.parseInt((String)prevMealsTable.getValueAt(prevMealsTable.getSelectedRow(), 0));
		eldermap = Elderly.getElderlyMap();
		eldermap.get(this.currentElderly).getMeals().removeMeal(mealid);
		SerializerSQL.storeMeals(this.currentElderly, eldermap.get(this.currentElderly).getMeals(), Meals.getSQLInstance());
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Meal Removed!");

		// Refresh table model
		presentData(this.currentElderly);
	}
	
	private void presentMealData(String mid) {
		int id = Integer.parseInt(mid);
		Meals m = eldermap.get(this.currentElderly).getMeals();
		Nutrition n = m.getNutrition(id);
		MealProperties mp = m.getMealProperties(id);
		lblMealName.setText(m.getMealName(id));
		chckbxHalal.setSelected(true);
		if (n != null) {
			fieldVitA.setText(Integer.toString(n.getVita()));
			fieldVitC.setText(Integer.toString(n.getVitc()));
			fieldVitD.setText(Integer.toString(n.getVitd()));
			fieldVitE.setText(Integer.toString(n.getVite()));
			fieldFat.setText(Integer.toString(n.getFat()));
			fieldIron.setText(Integer.toString(n.getIron()));
			fieldCarbohydrates.setText(Integer.toString(n.getCarbs()));
			fieldCalories.setText(Integer.toString(n.getCalories()));
			fieldProtein.setText(Integer.toString(n.getProtein()));
			lblAddedOn.setText("Added on: " + sdf.format(mp.getCreated()));
			lblAddedBy.setText("Added by: " + StaffSession.nameFromID(mp.getCreator()));
			lblLastModified.setText("Last Modified: " + sdf.format(mp.getEdited()));
			lblModifiedBy.setText("Modified By: "+ StaffSession.nameFromID(mp.getEditor()));
		} else {
			System.out.println("Nutrition is null.");
			clearData();
		}
	}
	
	void clearData() {
		lblMealName.setText("");
		fieldVitA.setText("");
		fieldVitC.setText("");
		fieldVitD.setText("");
		fieldVitE.setText("");
		fieldFat.setText("");
		fieldIron.setText("");
		fieldCarbohydrates.setText("");
		fieldCalories.setText("");
		fieldProtein.setText("");
	}

	@Override
	public void printDebug() {
		// TODO Auto-generated method stub
		
	}
}
