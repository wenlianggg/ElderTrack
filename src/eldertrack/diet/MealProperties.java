package eldertrack.diet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MealProperties implements java.io.Serializable {
	private static final long serialVersionUID = 2000L;
	String lasteditedby;
	Date lastedited;
	String createdby;
	Date created;
	public MealProperties() {
		lasteditedby = "--";
		lastedited = new Date();
		createdby = "Random Person";
		created = new Date();
	}
	
	void print() {
		SimpleDateFormat datef = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss, zzzz");
		System.out.println();
		System.out.println("Last Edited By: " + lasteditedby);
		System.out.println("Last Edited: " + datef.format(lastedited));
		System.out.println("Created By: " + createdby);
		System.out.println("Created: " + datef.format(created));
	}
	
	void modify() {
	}
}
