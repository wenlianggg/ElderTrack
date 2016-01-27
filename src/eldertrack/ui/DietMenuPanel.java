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
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import eldertrack.diet.MenuItem;
import eldertrack.diet.Nutrition;
import eldertrack.login.StaffSession;
import eldertrack.misc.TableHelper;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class DietMenuPanel extends JPanel implements Presentable {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	private JTable availMealsTable;
	private JTextField fieldVitA;
	private JTextField fieldVitC;
	private JTextField fieldVitE;
	private JTextField fieldVitD;
	private JTextField fieldFat;
	private JTextField fieldIron;
	private JTextField fieldProtein;
	private JTextField fieldCarbohydrates;
	private JTextField fieldCalories;
	private JTextField searchQuery;
	private JTextField fieldMealName;
	private JLabel lblRda;
	private JLabel lblAddedDate;
	private JLabel lblAddedBy;
	private JLabel lblLastModified;
	private JCheckBox chckbxHalal;
	private Integer selectedRow = -1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
	private JRadioButton rdbtnBreakfast;
	private JRadioButton rdbtnLunch;
	private JRadioButton rdbtnDinner;
	
	// Constructor
	DietMenuPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		MenuItem.getMenuMap();
		
		lblDietLabel = new JLabel("ElderTrack Suite");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 281, 54);
		
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
		btnBackToMain.setForeground(SystemColor.textHighlight);
		btnBackToMain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        CardLayout parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
		        parentCards.show(DietSection.CardsPanel, DietSection.DMAINPANEL);
			}
		});
		
		JButton btnRemoveEntry = new JButton("Remove Entry");
		btnRemoveEntry.setForeground(new Color(255, 0, 0));
		btnRemoveEntry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeEntry();
			}
		});
		btnRemoveEntry.setBounds(782, 358, 203, 54);
		add(btnRemoveEntry);
		
		JButton btnUpdateEntry = new JButton("Save Entry");
		btnUpdateEntry.setForeground(new Color(255, 102, 0));
		btnUpdateEntry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdateEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateMenu();
			}
		});
		btnUpdateEntry.setBounds(782, 238, 203, 109);
		add(btnUpdateEntry);
		btnBackToMain.setBounds(782, 611, 203, 48);
		add(btnBackToMain);
		
		JPanel nutriInfoPanel = new JPanel();
		nutriInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
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
		
		JLabel lblFatg = new JLabel("Fat (g):");
		lblFatg.setBounds(11, 169, 100, 14);
		nutriInfoPanel.add(lblFatg);
		
		JLabel lblProtein = new JLabel("Protein(g):");
		lblProtein.setBounds(10, 195, 100, 14);
		nutriInfoPanel.add(lblProtein);
		
		JLabel lblIron = new JLabel("Iron(mg):");
		lblIron.setBounds(10, 220, 100, 14);
		nutriInfoPanel.add(lblIron);
		
		JLabel lblVitaminA = new JLabel("Vitamin A (%):");
		lblVitaminA.setBounds(11, 255, 100, 14);
		nutriInfoPanel.add(lblVitaminA);
		
		JLabel lblVitaminC = new JLabel("Vitamin C (%):");
		lblVitaminC.setBounds(10, 280, 100, 14);
		nutriInfoPanel.add(lblVitaminC);
		
		JLabel lblVitaminE = new JLabel("Vitamin E (%):");
		lblVitaminE.setBounds(11, 305, 100, 14);
		nutriInfoPanel.add(lblVitaminE);
		
		JLabel lblVitaminD = new JLabel("Vitamin D (%):");
		lblVitaminD.setBounds(11, 330, 100, 14);
		nutriInfoPanel.add(lblVitaminD);

		
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
		fieldProtein.setBounds(109, 192, 207, 20);
		nutriInfoPanel.add(fieldProtein);
		
		fieldIron = new JTextField();
		fieldIron.setColumns(10);
		fieldIron.setBounds(109, 217, 207, 20);
		nutriInfoPanel.add(fieldIron);
		
		fieldVitA = new JTextField();
		fieldVitA.setBounds(109, 252, 207, 20);
		nutriInfoPanel.add(fieldVitA);
		fieldVitA.setColumns(10);
		
		fieldVitC = new JTextField();
		fieldVitC.setBounds(109, 277, 207, 20);
		nutriInfoPanel.add(fieldVitC);
		fieldVitC.setColumns(10);
		
		fieldVitE = new JTextField();
		fieldVitE.setColumns(10);
		fieldVitE.setBounds(109, 302, 207, 20);
		nutriInfoPanel.add(fieldVitE);
		
		fieldVitD = new JTextField();
		fieldVitD.setColumns(10);
		fieldVitD.setBounds(109, 327, 207, 20);
		nutriInfoPanel.add(fieldVitD);
		
		fieldFat = new JTextField();
		fieldFat.setColumns(10);
		fieldFat.setBounds(109, 167, 208, 20);
		nutriInfoPanel.add(fieldFat);
		
		lblRda = new JLabel("Recommended Daily Allowance (%):");
		lblRda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRda.setBounds(12, 419, 306, 29);
		nutriInfoPanel.add(lblRda);
		
		lblAddedDate = new JLabel("Added On: dd/mm/yy");
		lblAddedDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedDate.setBounds(12, 475, 306, 20);
		nutriInfoPanel.add(lblAddedDate);
		
		lblAddedBy = new JLabel("Added By: PERSON NAME");
		lblAddedBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddedBy.setBounds(12, 494, 257, 20);
		nutriInfoPanel.add(lblAddedBy);
		
		lblLastModified = new JLabel("Last Modified: PERSON NAME");
		lblLastModified.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastModified.setBounds(12, 514, 318, 20);
		nutriInfoPanel.add(lblLastModified);
		
		fieldMealName = new JTextField();
		fieldMealName.setBounds(146, 63, 232, 33);
		nutriInfoPanel.add(fieldMealName);
		fieldMealName.setColumns(10);
		
		chckbxHalal = new JCheckBox(" Item Halal");
		chckbxHalal.setBounds(10, 354, 77, 23);
		nutriInfoPanel.add(chckbxHalal);
		
		rdbtnBreakfast = new JRadioButton("Breakfast");
		buttonGroup.add(rdbtnBreakfast);
		rdbtnBreakfast.setBounds(109, 354, 71, 23);
		nutriInfoPanel.add(rdbtnBreakfast);
		
		rdbtnLunch = new JRadioButton("Lunch");
		buttonGroup.add(rdbtnLunch);
		rdbtnLunch.setBounds(182, 354, 53, 23);
		nutriInfoPanel.add(rdbtnLunch);
		
		rdbtnDinner = new JRadioButton("Dinner");
		buttonGroup.add(rdbtnDinner);
		rdbtnDinner.setBounds(237, 354, 65, 23);
		nutriInfoPanel.add(rdbtnDinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 364, 503);
		add(scrollPane);
		
		availMealsTable = new JTable(TableHelper.getMeals(""));
		setColumnWidths();
		availMealsTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        selectedRow = (Integer) availMealsTable.getValueAt(availMealsTable.getSelectedRow(), 0);
		        presentData(selectedRow);
		    }
		});
		availMealsTable.setFont(new Font("Tahoma", Font.PLAIN, 11));


		scrollPane.setViewportView(availMealsTable);
		
		searchQuery = new JTextField();
		searchQuery.setBounds(67, 120, 181, 27);
		add(searchQuery);
		searchQuery.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSearch.setBounds(9, 122, 57, 21);
		add(lblSearch);
		
		JButton btnMenuSearch = new JButton("Search Menu");
		btnMenuSearch.setBounds(258, 116, 111, 33);
		add(btnMenuSearch);
		btnMenuSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				availMealsTable.setModel(TableHelper.getMeals("%" + searchQuery.getText() + "%"));
				setColumnWidths();
			}
		});
		
		JButton btnCreateNewMeal = new JButton("New Entry");
		btnCreateNewMeal.setForeground(new Color(34, 139, 34));
		btnCreateNewMeal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateNewMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEntry();
			}
		});
		btnCreateNewMeal.setBounds(782, 118, 203, 109);
		add(btnCreateNewMeal);
		
		JLabel lblDietManagement = new JLabel("Diet Management\r\n - Menu Management");
		lblDietManagement.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblDietManagement.setBounds(300, 20, 388, 30);
		add(lblDietManagement);
		
		JButton btnClrFlds = new JButton("Clear Fields");
		btnClrFlds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		btnClrFlds.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClrFlds.setForeground(Color.GRAY);
		btnClrFlds.setBounds(782, 423, 203, 54);
		add(btnClrFlds);
	}
	
	private void setColumnWidths() {
		availMealsTable.getTableHeader().setResizingAllowed(false);
		availMealsTable.getTableHeader().setReorderingAllowed(false);
		availMealsTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		availMealsTable.getColumnModel().getColumn(0).setMaxWidth(35);
		availMealsTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		availMealsTable.getColumnModel().getColumn(1).setMaxWidth(80);
		availMealsTable.getColumnModel().getColumn(2).setPreferredWidth(130);
		availMealsTable.getColumnModel().getColumn(3).setPreferredWidth(55);
		availMealsTable.getColumnModel().getColumn(3).setMaxWidth(70);
	}
	
	public void presentData(int mealid) {
		MenuItem mi = MenuItem.getMenuMap().get(mealid);
		fieldMealName.setText(mi.getName());
		chckbxHalal.setSelected(mi.isHalal());
		lblAddedBy.setText("Added By: " + StaffSession.nameFromID(mi.getAddedby()));
		lblAddedDate.setText("Added On: " + sdf.format(mi.getDateadded()));
		lblLastModified.setText("Last Modified By: " + StaffSession.nameFromID(mi.getModifiedBy()));
		Nutrition n = mi.getNutrition();
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
		} else {
			clearFields();
			fieldMealName.setText(mi.getName());
		}
		switch(mi.getCategory()) {
			case "Breakfast":
				rdbtnBreakfast.setSelected(true);
				break;
			case "Lunch":
				rdbtnLunch.setSelected(true);
				break;
			case "Dinner":
				rdbtnDinner.setSelected(true);
				break;
		}
	}
	
	private void clearFields() {
		fieldMealName.setText("");
		fieldVitA.setText("");
		fieldVitC.setText("");
		fieldVitD.setText("");
		fieldVitE.setText("");
		fieldFat.setText("");
		fieldIron.setText("");
		fieldCarbohydrates.setText("");
		fieldCalories.setText("");
		fieldProtein.setText("");
		chckbxHalal.setSelected(false);
	}
	
	private void updateMenu() {
		try {
			MenuItem.getMenuMap().get(selectedRow).
					editMenuItem(getSelectedRadio(), fieldMealName.getText(), chckbxHalal.isSelected(), getNutritionFromFields());
				availMealsTable.setModel(TableHelper.getMeals("%" + searchQuery.getText() + "%"));
				setColumnWidths();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "One of the fields are empty or invalid!");
		}
	}
	
	private void removeEntry() {
		MenuItem.getMenuMap().get(selectedRow).removeMenuItem();
		availMealsTable.setModel(TableHelper.getMeals("%" + searchQuery.getText() + "%"));
		setColumnWidths();
		clearFields();
	}
	
	private void addEntry() {
		try {
			new MenuItem(getSelectedRadio(), fieldMealName.getText(), chckbxHalal.isSelected(), getNutritionFromFields(), true);
			availMealsTable.setModel(TableHelper.getMeals("%" + searchQuery.getText() + "%"));
			setColumnWidths();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "One of the fields were empty or invalid!");
		}
	}
	
	private Nutrition getNutritionFromFields() throws NumberFormatException {
		int cal = Integer.parseInt(fieldCalories.getText()),
				prot = Integer.parseInt(fieldProtein.getText()),
				fat = Integer.parseInt(fieldFat.getText()),
				carbs = Integer.parseInt(fieldCarbohydrates.getText()),
				iron = Integer.parseInt(fieldIron.getText()),
				vita = Integer.parseInt(fieldVitA.getText()),
				vitc = Integer.parseInt(fieldVitC.getText()),
				vitd = Integer.parseInt(fieldVitD.getText()),
				vite = Integer.parseInt(fieldVitE.getText());
		return new Nutrition(cal, prot, fat, carbs, iron, vita, vitc, vitd, vite);
	}
	
	private String getSelectedRadio() {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

	@Override
	public void printDebug() {
		// TODO Auto-generated method stub
		
	}
}
