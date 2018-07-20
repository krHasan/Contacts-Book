package database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
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

	public Map<String, String> getContactDataById(String id) {
		Map<String, String> map = new HashMap<>();

		String sql = "SELECT * FROM Contacts WHERE globalId = ?";
		int idint = Constraints.stringToInt(id);
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idint);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				map.put("name", result.getString("name"));
				map.put("number1", result.getString("number1"));
				map.put("number2", result.getString("number2"));
				map.put("address", result.getString("address"));
				map.put("priority", result.getString("priority"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public boolean updateContact(Map<String, String> map, String id) {
		int value = 0;
		String sql = "UPDATE Contacts SET name = ?, number1 = ?, number2 = ?, address = ?, priority = ? WHERE globalId = ?";

		int globalId = Constraints.stringToInt(id);
		String name = (String) map.get("name");
		String number1 = (String) map.get("number1");
		String number2 = (String) map.get("number2");
		String address = (String) map.get("address");
		String priority = (String) map.get("priority");

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, number1);
			pstmt.setString(3, number2);
			pstmt.setString(4, address);
			pstmt.setString(5, priority);
			pstmt.setInt(6, globalId);

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
}
