package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import eldertrack.diet.*;
import eldertrack.misc.TableHelper;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class DietMainPanel extends JPanel {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable eldersTable;
	private JTextField searchField;
	private JButton btnSearch;
	CardLayout parentCards;
	private JLabel lblSearch;
	private JPanel infoPanel;
	private JLabel lblElderid;
	private JLabel lblInfoName;
	private JLabel lblAge;
	private JLabel lblRoomNumber;
	private JLabel lblNric;
	private JSeparator separator;
	private JLabel lblStatisticsForToday;
	private JLabel lblCalories;
	private JLabel lblCarbohydrates;
	private JLabel lblProtein;
	private JLabel lblIron;
	private JLabel lblVitaminA;
	private JLabel lblVitaminC;
	private JLabel lblVitaminD;
	private JLabel lblVitaminE;
	private JLabel lblPreviousMeal;
	private JLabel lblReviewInfo;
	private JButton btnAddMeal;
	private JButton btnModifyMeals;
	private JButton btnMenuManagement;
	private JButton btnViewInMgmt;
	private JButton btnMainMenu;
	private JScrollPane tableScrollPane;
	
	// Constructor
	DietMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);

		tableScrollPane = new JScrollPane(eldersTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);
		DefaultTableModel allEldersData = TableHelper.getElderlyBasic("");
		eldersTable = new JTable(allEldersData);
		eldersTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        int row = eldersTable.getSelectedRow();
		        if (row >= 0) {
		        	presentData(eldersTable.getValueAt(row, 0).toString());
		        }
		    }
		});
		eldersTable.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableScrollPane.setViewportView(eldersTable);
		
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
				eldersTable.setModel(TableHelper.getElderlyBasic("%" + searchField.getText() + "%"));
				eldersTable.getColumnModel().getColumn(0).setPreferredWidth(36);
			}
		});
		btnSearch.setBounds(228, 99, 65, 23);
		add(btnSearch);
		
		lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 97, 47, 22);
		add(lblSearch);
		
		infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		infoPanel.setBounds(310, 97, 665, 468);
		add(infoPanel);
		infoPanel.setLayout(null);
		
		lblElderid = new JLabel("ElderID:");
		lblElderid.setBounds(10, 55, 209, 14);
		infoPanel.add(lblElderid);
		
		lblInfoName = new JLabel("Elderly Name");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 11, 645, 37);
		infoPanel.add(lblInfoName);
		
		lblAge = new JLabel("Age: --");
		lblAge.setBounds(10, 95, 209, 14);
		infoPanel.add(lblAge);
		
		lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setBounds(10, 75, 209, 14);
		infoPanel.add(lblRoomNumber);
		
		lblNric = new JLabel("NRIC: SXXXXXXXA");
		lblNric.setBounds(10, 115, 209, 14);
		infoPanel.add(lblNric);
		
		separator = new JSeparator();
		separator.setBounds(3, 139, 659, 3);
		infoPanel.add(separator);
		
		lblStatisticsForToday = new JLabel("Statistics For Today");
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblStatisticsForToday.setBounds(10, 145, 209, 29);
		infoPanel.add(lblStatisticsForToday);
		
		lblCalories = new JLabel("RDA Calories (kcal):  --- (x% of RDA)");
		lblCalories.setBounds(10, 181, 209, 14);
		infoPanel.add(lblCalories);
		
		lblCarbohydrates = new JLabel("Carbohydrates (g): --- (x% of RDA)");
		lblCarbohydrates.setBounds(10, 201, 209, 14);
		infoPanel.add(lblCarbohydrates);
		
		lblProtein = new JLabel("Protein(g) :  --- (x% of RDA)");
		lblProtein.setBounds(10, 221, 209, 14);
		infoPanel.add(lblProtein);
		
		lblIron = new JLabel("Iron(mg):  --- (x% of RDA)");
		lblIron.setBounds(11, 241, 208, 14);
		infoPanel.add(lblIron);
		
		lblVitaminA = new JLabel("Vitamin A (mg):  --- (x% of RDA)");
		lblVitaminA.setBounds(10, 261, 209, 14);
		infoPanel.add(lblVitaminA);
		
		lblVitaminC = new JLabel("Vitamin C (mg):  --- (x% of RDA)");
		lblVitaminC.setBounds(10, 281, 209, 14);
		infoPanel.add(lblVitaminC);
		
		lblVitaminD = new JLabel("Vitamin D (mg):  --- (x% of RDA)");
		lblVitaminD.setBounds(10, 321, 209, 14);
		infoPanel.add(lblVitaminD);
		
		lblVitaminE = new JLabel("Vitamin E (mg):  --- (x% of RDA)");
		lblVitaminE.setBounds(10, 301, 209, 14);
		infoPanel.add(lblVitaminE);
		
		lblPreviousMeal = new JLabel("Previous Meal: ______________________");
		lblPreviousMeal.setBounds(9, 357, 209, 14);
		infoPanel.add(lblPreviousMeal);
		
		lblReviewInfo = new JLabel("Review Information");
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(310, 59, 665, 31);
		add(lblReviewInfo);
		
		btnAddMeal = new JButton("Add Meal");
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
		        parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DADDPANEL);
			}
		});
		
		btnModifyMeals = new JButton("Update Existing Meals");
		btnModifyMeals.setBounds(534, 576, 215, 83);
		add(btnModifyMeals);
		btnModifyMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMODPANEL);
			}
		});
		
		btnMenuManagement = new JButton("Edit Menu");
		btnMenuManagement.setBounds(759, 576, 216, 54);
		add(btnMenuManagement);
		btnMenuManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        parentCards = (CardLayout) DietPanel.CardsPanel.getLayout();
		        parentCards.show(DietPanel.CardsPanel, DietPanel.DMENUPANEL);
			}
		});
		
		btnViewInMgmt = new JButton("View Elderly in Management Panel");
		btnViewInMgmt.setBounds(759, 631, 216, 28);
		add(btnViewInMgmt);
		
		btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout mainCards = (CardLayout) MainFrame.CardsPanel.getLayout();
				mainCards.show(MainFrame.CardsPanel, MainFrame.MENUPANEL);
			}
		});
		btnMainMenu.setBounds(820, 15, 139, 40);
		add(btnMainMenu);
	}
	
	private void presentData(String id) {
		HashMap<Integer, Elderly> eldermap = Elderly.getElderlyMap();
		Elderly el = eldermap.get(Integer.parseInt(id));
		
		lblInfoName.setText(el.getName());
		lblElderid.setText("ElderID: " + el.getId());
		lblAge.setText("Age: " + el.getAge());
		lblRoomNumber.setText("Room Number: " + el.getRoomnum());
		lblNric.setText("NRIC: " + el.getNric());
		
		el.getMeals();
		lblCalories.setText("RDA Calories (kcal): (x% of RDA)");
		lblCarbohydrates.setText("Carbohydrates (g): --- (x% of RDA)");
		lblProtein.setText("Protein(g) :  --- (x% of RDA)");		
		lblIron.setText("Iron(mg):  --- (x% of RDA)");		
		lblVitaminA.setText("Vitamin A (mg):  --- (x% of RDA)");		
		lblVitaminC.setText("Vitamin C (mg):  --- (x% of RDA)");		
		lblVitaminD.setText("Vitamin D (mg):  --- (x% of RDA)");		
		lblVitaminE.setText("Vitamin E (mg):  --- (x% of RDA)");
		lblPreviousMeal.setText("Previous Meal: ______________________");

	}
}