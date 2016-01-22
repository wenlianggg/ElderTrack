package eldertrack.diet;

import java.text.SimpleDateFormat;
import java.util.Date;

import eldertrack.ui.MainFrame;

public class MealProperties implements java.io.Serializable {
	private static final long serialVersionUID = 2003;
	private String mealdescription;
	private int editedbyid;
	private Date edited;
	private int createdbyid;
	private Date created;
	
	MealProperties() {
		edited = new Date();
		createdbyid = MainFrame.getInstance().getSessionInstance().getStaffid();
		created = new Date();
	}
	
	void print() {
		SimpleDateFormat datef = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss, zzzz");
		System.out.println();
		System.out.println("Meal description: " + mealdescription);
		System.out.println("Last Edited By: " + editedbyid);
		System.out.println("Last Edited: " + datef.format(edited));
		System.out.println("Created By: " + createdbyid);
		System.out.println("Created: " + datef.format(created));
	}
	
	void modify() {
		editedbyid = MainFrame.getInstance().getSessionInstance().getStaffid();
		edited = new Date();
	}

	public String getMealdescription() {
		return this.mealdescription;
	}

	public Integer getEditor() {
		return this.editedbyid;
	}

	public Date getEdited() {
		return this.edited;
	}

	public Integer getCreator() {
		return this.createdbyid;
	}

	public Date getCreated() {
		return this.created;
	}
	
	
}
