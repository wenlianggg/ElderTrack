package eldertrack.misc;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eldertrack.db.SQLObject;

public class ProfilePicUploader {
	private static SQLObject so = new SQLObject();
	public static void main(String[] args) {
			PreparedStatement ps = so.getPreparedStatement("UPDATE et_staff SET profilepic = ? WHERE staffid = 1");
			File img = new File("profilepic.jpg");
			System.out.println(img.exists());
			try {
				ps.setObject(1, img);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
