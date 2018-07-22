package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import database.DatabaseConnection;

public class DeleteUserCredentials extends DatabaseConnection {

	String deleteCredentials = "DELETE FROM Credentials";
	String deleteContacts = "DELETE FROM Contacts";
	String initializeSystem_Settings = "UPDATE System_Settings SET globalId = ? WHERE ID = 1";
	String initializeBackup = "UPDATE Backup SET lastBackup = ?, lastRestore = ? WHERE ID = 1";

	public void deleteAllData() {

		try (Connection conn = connector(); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(deleteCredentials);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection conn = connector(); Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(deleteContacts);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection conn = connector();
				PreparedStatement pstmt = conn.prepareStatement(initializeSystem_Settings)) {
			pstmt.setInt(1, 1);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(initializeBackup)) {
			pstmt.setString(1, "No Last Backup");
			pstmt.setString(2, "Never Restored");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
