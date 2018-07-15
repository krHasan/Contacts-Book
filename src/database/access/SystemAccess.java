package database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;

public class SystemAccess extends DatabaseConnection {

	public boolean hasdb() {
		String sql = "SELECT * FROM Credentials";
		boolean re = false;

		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			re = true;
		} catch (Exception e) {
			re = false;
		}

		return re;
	}
}
