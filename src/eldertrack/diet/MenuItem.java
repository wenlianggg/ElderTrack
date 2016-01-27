package eldertrack.diet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import eldertrack.db.SQLObject;
import eldertrack.misc.TableHelper;
import eldertrack.ui.MainFrame;

public class MenuItem {
	private static HashMap<Integer, MenuItem> menumap;
	private Integer itemid;
	private String category;
	private String name;
	private boolean halal;
	private Nutrition nutrition;
	private Date dateadded;
	private Integer addedBy;
	private boolean active;
	private Integer modifiedBy;
	
	private MenuItem(Integer itemid, String category, String name, 
			boolean halal, Nutrition nutrition,Date dateadded, 
			Integer addedBy, boolean active, Integer modifiedBy) {
		this.itemid = itemid;
		this.category = category;
		this.name = name;
		this.halal = halal;
		this.nutrition = nutrition;
		this.dateadded = dateadded;
		this.addedBy = addedBy;
		this.active = active;
		this.modifiedBy = modifiedBy;
	}
	
	public MenuItem(String category, String name, boolean halal, Nutrition nutrition, boolean active) {
		this.category = category;
		this.name = name;
		this.halal = halal;
		this.nutrition = nutrition;
		this.addedBy = MainFrame.getInstance().getSessionInstance().getStaffid();
		this.dateadded = new Date();
		try {
			PreparedStatement ps1 = TableHelper.getSQLInstance().getPreparedStatement
				("SELECT * FROM et_menu WHERE name LIKE ?");
			ps1.setString(1, this.name);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				JOptionPane.showMessageDialog(null, "Meal Exists!");
				return;
			}
			PreparedStatement ps2 = TableHelper.getSQLInstance().getPreparedStatementWithKey
				("INSERT INTO et_menu (category, name, halal, addedby, nutrition, dateadded) VALUES (?, ?, ?, ?, ?, ?)");
			ps2.setString(1, this.category);
			ps2.setString(2, this.name);
			ps2.setBoolean(3, this.halal);
			ps2.setInt(4, this.addedBy);
			ps2.setObject(5, this.nutrition);
			ps2.setDate(6, new java.sql.Date(dateadded.getTime()));
			ps2.executeUpdate();
			refreshMap();
			JOptionPane.showMessageDialog(null, "Meal Added!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "One of the fields are empty or invalid!");
		}
	}
	
	// Setter Method
	public void editMenuItem(String category, String name, boolean halal, Nutrition nutrition) {
		this.category = category;
		this.name = name;
		this.halal = halal;
		this.nutrition = nutrition;
		this.modifiedBy = MainFrame.getInstance().getSessionInstance().getStaffid();
		PreparedStatement ps = TableHelper.getSQLInstance().getPreparedStatement("UPDATE et_menu SET category=?, name=?, halal=? WHERE itemid=?");
		try {
			ps.setString(1, category);
			ps.setString(2, name);
			ps.setBoolean(3, halal);
			ps.setInt(4, itemid);
			ps.executeUpdate();
			SerializerSQL.storeNutrition(itemid, nutrition, TableHelper.getSQLInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		refreshMap();
		JOptionPane.showMessageDialog(null, "Meal Edited!");
	}
	
	public void removeMenuItem() {
		PreparedStatement ps = TableHelper.getSQLInstance().getPreparedStatement("UPDATE et_menu SET active=? WHERE itemid=?");
		try {
			ps.setBoolean(1, false);
			ps.setInt(2, this.itemid);
			ps.executeUpdate();
			refreshMap();
			JOptionPane.showMessageDialog(null, "Meal Removed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Getter Methods
	public int getItemid() {
		return itemid;
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public boolean isHalal() {
		return halal;
	}
	public Nutrition getNutrition() {
		return nutrition;
	}
	public Date getDateadded() {
		return dateadded;
	}
	public int getAddedby() {
		return addedBy;
	}
	public boolean isActive() {
		return active;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	
	
	private static void refreshMap() {
		SQLObject so = TableHelper.getSQLInstance();
		HashMap<Integer,MenuItem> menumap = new HashMap<Integer,MenuItem>();
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = so.getPreparedStatement("SELECT * FROM et_menu");
			ps.executeQuery();
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer itemid = rs.getInt("itemid");
				String category = rs.getString("category");
				String name = rs.getString("name");
				boolean halal = rs.getBoolean("halal");
				Nutrition nutrition = SerializerSQL.retrieveNutrition(itemid, so);
				Date dateadded = rs.getTimestamp("dateadded");
				Integer addedby = rs.getInt("addedby");
				boolean active = rs.getBoolean("active");
				Integer modifiedby = rs.getInt("modifiedby");
				MenuItem mi = new MenuItem(itemid, category, name, halal, nutrition, dateadded, addedby, active, modifiedby);
				// Put Menu Item into HashMap
				menumap.put(itemid, mi);
			}
			MenuItem.menumap = menumap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer,MenuItem> getMenuMap() {
		if (MenuItem.menumap == null) {
			refreshMap();
		}
		return menumap;
	}
}
