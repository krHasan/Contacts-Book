package database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import database.DatabaseConnection;
import operation.Constraints;
import operation.GlobalID;

public class ContactAccess extends DatabaseConnection {

	public boolean addNewContact(Map<String, String> map) {
		int value = 0;
		String sql = "INSERT INTO Contacts (globalId, name, number1, number2, address, priority) \n"
				+ "VALUES (?,?,?,?,?,?)";

		int globalId = GlobalID.getGlobalid();
		String name = (String) map.get("name");
		String number1 = (String) map.get("number1");
		String number2 = (String) map.get("number2");
		String address = (String) map.get("address");
		String priority = (String) map.get("priority");

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, globalId);
			pstmt.setString(2, name);
			pstmt.setString(3, number1);
			pstmt.setString(4, number2);
			pstmt.setString(5, address);
			pstmt.setString(6, priority);

			value = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isThisIdData(String data) {
		boolean feedback = false;
		String sql = "SELECT globalId FROM Contacts";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {

			while (result.next()) {
				if (Constraints.intToString(result.getInt("globalId")).equals(data)) {
					feedback = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return feedback;
	}

}
