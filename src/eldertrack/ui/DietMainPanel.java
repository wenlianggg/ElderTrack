package eldertrack.ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TrayIcon.MessageType;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import eldertrack.diet.*;
import eldertrack.login.AccessLevel;
import eldertrack.misc.TableHelper;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class DietMainPanel extends JPanel implements Presentable {
	private static final long serialVersionUID = 4318548492960279050L;
	JLabel lblDietLabel;
	JLabel lblSelectElderly;
	private JTable eldersTable;
	private JTextField searchField;
	private JButton btnSearch;
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
	CardLayout parentCards;
	private JLabel lblDietManagement;
	private AccessLevel al;
	
	// Constructor
	DietMainPanel() {
		setBounds(0, 0, 995, 670);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		al = MainFrame.getInstance().getSessionInstance().getAccessLevel();
		tableScrollPane = new JScrollPane(eldersTable);
		tableScrollPane.setViewportBorder(null);
		tableScrollPane.setBounds(10, 130, 283, 529);
		add(tableScrollPane);
		DefaultTableModel allEldersData = TableHelper.getElderlyBasic("");
		eldersTable = new JTable(allEldersData);
		eldersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eldersTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		    	int selectedElderly = Integer.parseInt(eldersTable.getValueAt(eldersTable.getSelectedRow(), 0).toString());
		    	presentData(selectedElderly);
		    }
		});
		setColumnWidths();
		tableScrollPane.setViewportView(eldersTable);
		
		lblDietLabel = new JLabel("ElderTrack Suite");
		lblDietLabel.setForeground(SystemColor.textHighlight);
		lblDietLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
		lblDietLabel.setBounds(10, 0, 290, 54);
		
		lblSelectElderly = new JLabel("Select An Elderly");
		lblSelectElderly.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSelectElderly.setBounds(10, 69, 290, 24);
		
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
				setColumnWidths();
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
		lblElderid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblElderid.setBounds(10, 55, 209, 20);
		infoPanel.add(lblElderid);
		
		lblInfoName = new JLabel("Elderly Name");
		lblInfoName.setForeground(new Color(0, 128, 128));
		lblInfoName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInfoName.setBounds(10, 11, 645, 37);
		infoPanel.add(lblInfoName);
		
		lblAge = new JLabel("Age: --");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(10, 95, 209, 20);
		infoPanel.add(lblAge);
		
		lblRoomNumber = new JLabel("Room Number: --");
		lblRoomNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoomNumber.setBounds(10, 75, 209, 20);
		infoPanel.add(lblRoomNumber);
		
		lblNric = new JLabel("NRIC: --");
		lblNric.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNric.setBounds(10, 115, 209, 20);
		infoPanel.add(lblNric);
		
		separator = new JSeparator();
		separator.setBounds(2, 146, 659, 3);
		infoPanel.add(separator);
		
		lblStatisticsForToday = new JLabel("Statistics For Today");
		lblStatisticsForToday.setForeground(new Color(0, 128, 128));
		lblStatisticsForToday.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblStatisticsForToday.setBounds(11, 158, 209, 29);
		infoPanel.add(lblStatisticsForToday);
		
		lblCalories = new JLabel("RDA Calories (kcal):  --- ");
		lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCalories.setBounds(11, 195, 209, 20);
		infoPanel.add(lblCalories);
		
		lblCarbohydrates = new JLabel("Carbohydrates (g): --- ");
		lblCarbohydrates.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCarbohydrates.setBounds(11, 215, 209, 20);
		infoPanel.add(lblCarbohydrates);
		
		lblProtein = new JLabel("Protein(g) :  --- ");
		lblProtein.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProtein.setBounds(11, 235, 209, 20);
		infoPanel.add(lblProtein);
		
		lblIron = new JLabel("Iron(mg):  --- ");
		lblIron.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIron.setBounds(12, 255, 208, 20);
		infoPanel.add(lblIron);
		
		lblVitaminA = new JLabel("Vitamin A (mg):  --- ");
		lblVitaminA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVitaminA.setBounds(11, 275, 209, 20);
		infoPanel.add(lblVitaminA);
		
		lblVitaminC = new JLabel("Vitamin C (mg):  --- ");
		lblVitaminC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVitaminC.setBounds(11, 295, 209, 20);
		infoPanel.add(lblVitaminC);
		
		lblVitaminD = new JLabel("Vitamin D (mg):  --- ");
		lblVitaminD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVitaminD.setBounds(11, 335, 209, 20);
		infoPanel.add(lblVitaminD);
		
		lblVitaminE = new JLabel("Vitamin E (mg):  --- ");
		lblVitaminE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVitaminE.setBounds(11, 315, 209, 20);
		infoPanel.add(lblVitaminE);
		
		lblPreviousMeal = new JLabel("Previous Meal: ______________________");
		lblPreviousMeal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreviousMeal.setBounds(10, 370, 286, 37);
		infoPanel.add(lblPreviousMeal);
		
		lblReviewInfo = new JLabel("Review Information");
		lblReviewInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReviewInfo.setBounds(310, 59, 665, 31);
		add(lblReviewInfo);
		
		btnAddMeal = new JButton("Record Meal");
		btnAddMeal.setForeground(new Color(0, 0, 128));
		btnAddMeal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddMeal.setBounds(309, 576, 215, 82);
		add(btnAddMeal);
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DietAddPanel dap = MainFrame.getInstance().getDietPanel().getDietAddPanel();
				// Present person data on DietAddPanel
				if (eldersTable.getSelectedRow() != -1) {
					int selectedElderly = Integer.parseInt(eldersTable.getValueAt(eldersTable.getSelectedRow(), 0).toString());
					dap.presentData(selectedElderly);
					parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
					parentCards.show(DietSection.CardsPanel, DietSection.DADDPANEL);
				} else
					JOptionPane.showMessageDialog(null, "Please select an elderly before proceeding!");

			}
		});
		
		btnModifyMeals = new JButton("Update Existing");
		btnModifyMeals.setForeground(new Color(210, 105, 30));
		btnModifyMeals.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModifyMeals.setBounds(534, 576, 215, 83);
		add(btnModifyMeals);
		btnModifyMeals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					DietModifyPanel dmp = MainFrame.getInstance().getDietPanel().getDietModifyPanel();
					// Present person data on DietAddPanel
					if (eldersTable.getSelectedRow() != -1) {
						int selectedElderly = Integer.parseInt(eldersTable.getValueAt(eldersTable.getSelectedRow(), 0).toString());
						dmp.presentData(selectedElderly);
						parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
						parentCards.show(DietSection.CardsPanel, DietSection.DMODPANEL);
					} else
						JOptionPane.showMessageDialog(null, "Please select an elderly before proceeding!");

				}
			});
		
		btnMenuManagement = new JButton("Edit Menu");
		btnMenuManagement.setForeground(new Color(205, 92, 92));
		btnMenuManagement.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnMenuManagement.setBounds(759, 576, 216, 54);
		add(btnMenuManagement);
		btnMenuManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (al == AccessLevel.ADMIN || al == AccessLevel.MANAGER) {
					parentCards = (CardLayout) DietSection.CardsPanel.getLayout();
					parentCards.show(DietSection.CardsPanel, DietSection.DMENUPANEL);
				} else
					JOptionPane.showMessageDialog(null, "Insufficient Permissions");
			}
		});
		
		btnViewInMgmt = new JButton("View Elderly in Management");
		btnViewInMgmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (eldersTable.getSelectedRow() != -1) {
					if (al == AccessLevel.ADMIN || al == AccessLevel.MANAGER || al == AccessLevel.SRSTAFF) {
						Integer selectedelderly = Integer.parseInt(eldersTable.getValueAt(eldersTable.getSelectedRow(), 0).toString());
						CardLayout mainCards = (CardLayout) MainFrame.CardsPanel.getLayout();
						mainCards.show(MainFrame.CardsPanel, MainFrame.MGMTPANEL);
						JTable jtb = MainFrame.getInstance().getManagementPanel().getElderlyTable();
						jtb.getSelectionModel().setSelectionInterval(selectedelderly, selectedelderly);
					} else
						JOptionPane.showMessageDialog(null, "Insufficient Permissions");
				} else
					JOptionPane.showMessageDialog(null, "Please select an elderly before proceeding!");
			}
		});
		btnViewInMgmt.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		
		lblDietManagement = new JLabel("Diet Management\r\n - Main Menu");
		lblDietManagement.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblDietManagement.setBounds(300, 20, 331, 30);
		add(lblDietManagement);

	}
	
	private void setColumnWidths() {
		eldersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eldersTable.getTableHeader().setResizingAllowed(false);
		eldersTable.getTableHeader().setReorderingAllowed(false);
		eldersTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		eldersTable.getColumnModel().getColumn(0).setMaxWidth(25);
		eldersTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		eldersTable.getColumnModel().getColumn(1).setMaxWidth(200);
		eldersTable.getColumnModel().getColumn(2).setPreferredWidth(120);
	}
	
	public void presentData(int personid) {
		HashMap<Integer, Elderly> eldermap = Elderly.getElderlyMap();
		Elderly el = eldermap.get(personid);
		
		lblInfoName.setText(el.getName());
		lblElderid.setText("ElderID: " + el.getId());
		lblAge.setText("Age: " + el.getAge());
		lblRoomNumber.setText("Room Number: " + el.getRoomnum());
		lblNric.setText("NRIC: " + el.getNric());
		
		Nutrition n = el.getMeals().getNutritionToday();
		lblCalories.setText("RDA Calories (kcal): " + n.getCalories());
		lblCarbohydrates.setText("Carbohydrates (g): " + n.getCarbs());
		lblProtein.setText("Protein(g): " + n.getProtein());		
		lblIron.setText("Iron(mg): " + n.getIron());		
		lblVitaminA.setText("Vitamin A (%): " + n.getVita());		
		lblVitaminC.setText("Vitamin C (%): " + n.getVitc());		
		lblVitaminD.setText("Vitamin D (%): " + n.getVitd());		
		lblVitaminE.setText("Vitamin E (%): " + n.getVite());
		
		lblPreviousMeal.setText("Previous Meal: " + el.getMeals().getPrevMealName());

	}

	@Override
	public void printDebug() {
		// TODO Auto-generated method stub
		
	}
}