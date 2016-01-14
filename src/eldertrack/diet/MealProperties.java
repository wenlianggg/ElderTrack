package eldertrack.diet;

import java.text.SimpleDateFormat;
import java.util.Date;

import eldertrack.ui.MainFrame;

public class MealProperties implements java.io.Serializable {
	private static final long serialVersionUID = 2001L;
	private String mealdescription;
	private String lasteditedby;
	private Date lastedited;
	private String createdby;
	private Date created;
	
	MealProperties() {
		lasteditedby = "--";
		lastedited = new Date();
		createdby = "Random Person";
		created = new Date();
	}
	
	void print() {
		SimpleDateFormat datef = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss, zzzz");
		System.out.println();
		System.out.println("Meal description: " + mealdescription);
		System.out.println("Last Edited By: " + lasteditedby);
		System.out.println("Last Edited: " + datef.format(lastedited));
		System.out.println("Created By: " + createdby);
		System.out.println("Created: " + datef.format(created));
	}
	
	void modify() {
		lasteditedby = MainFrame.getInstance().getSessionInstance().getFullName();
		lastedited = new Date();
	}

	public String getMealdescription() {
		return mealdescription;
	}

	public String getLasteditedby() {
		return lasteditedby;
	}

	public Date getLastedited() {
		return lastedited;
	}

	public String getCreatedby() {
		return createdby;
	}

	public Date getCreated() {
		return created;
	}
	
	
}
