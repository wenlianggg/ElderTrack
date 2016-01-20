package eldertrack.diet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

import eldertrack.db.SQLObject;

public class Elderly {
	private static SQLObject so = new SQLObject();
	static HashMap<Integer, Elderly> eldermap;
	private Integer id;
	private String name;
	private Integer age;
	private Integer roomnum;
	private String nric;
	private Meals m;
	
	private Elderly(int id, String name, int age, int roomnum, String nric, Meals m) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.roomnum = roomnum;
		this.nric = nric;
		this.m = m;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getRoomnum() {
		return roomnum;
	}

	public String getNric() {
		return nric;
	}

	public Meals getMeals() {
		return m;
	}

	public void setMeals(Meals m) {
		this.m = m;
	}
	
	public void addMeal(String mealid) {
		Integer mid = Integer.parseInt(mealid);
		MealProperties mp = new MealProperties();
		try {
		PreparedStatement ps = so.getPreparedStatement("SELECT * FROM et_menu WHERE itemid = ?");
		ps.setInt(1, mid);
		ResultSet rs = ps.executeQuery();
		rs.next();
		m.getMealProperties().add(mp);
		m.getMealName().add(rs.getString("name"));
		m.getNutrition().add(SerializerSQL.retrieveNutrition(mid, so));
		SerializerSQL.storeMeals(this.id, m, so);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer,Elderly> getElderlyMap() {
		if (Elderly.eldermap == null) {
			PreparedStatement ps;
			ResultSet rs;
			HashMap<Integer,Elderly> eldermap = new HashMap<Integer,Elderly>();
			try {
				ps = so.getPreparedStatement("SELECT id,name,dob,nric,room FROM et_elderly");
				ps.executeQuery();
				rs = ps.executeQuery();
				while(rs.next()) {
					// Get elderly fields
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					String nric = rs.getString("nric");
					Integer roomnum = rs.getInt("room");
					// Calculate age from Date of Birth
					Date dob = rs.getDate("dob");
					LocalDate doblocaldate = LocalDateTime.ofInstant
						(Instant.ofEpochMilli(dob.getTime()), ZoneId.systemDefault()).toLocalDate();
					Integer age = (int) doblocaldate.until(LocalDate.now(), ChronoUnit.YEARS);
					// Obtain meals for the elderly in question
					Meals m = SerializerSQL.retrieveMeals(id, so);
					// Instantiate the elderly object
					Elderly el = new Elderly(id, name, age, roomnum, nric, m);
					// Put elderly into HashMap
					eldermap.put(id, el);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Elderly.eldermap = eldermap;
		}
		return eldermap;
	}
	
	public static boolean updateElderlyInDb(Elderly el) {
		return SerializerSQL.storeMeals(el.getId(), el.getMeals(), so);
	}
}
