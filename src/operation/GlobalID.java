package operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;

public class GlobalID extends DatabaseConnection {

	public static int getGlobalid() {
		int globalId = 0;

		String sql = "SELECT globalId FROM System_Settings";

		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			if (result.next()) {
				globalId = result.getInt("globalId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		increseGlobalId(globalId + 1);
		return globalId;
	}

	private static void increseGlobalId(int oldId) {
		String sql = "UPDATE System_Settings SET globalId = ?";

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, oldId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
