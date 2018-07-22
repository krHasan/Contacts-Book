package database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseConnection;

public class BackupAccess extends DatabaseConnection {

	public void setLastBackupDate(Date date) {
		String sql = "UPDATE Backup SET lastBackup = ?";
		DateFormat formate = new SimpleDateFormat("HH:mm:ss  EEEE dd MMMM, YYYY");
		String value = formate.format(date);

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, value);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLastBackupDate() {
		String sql = "SELECT lastBackup FROM Backup";
		String date = "";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			date = result.getString("lastBackup");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public void setLastRestoreDate(Date date) {
		String sql = "UPDATE Backup SET lastRestore = ?";
		DateFormat formate = new SimpleDateFormat("HH:mm:ss  EEEE dd MMMM, YYYY");
		String value = formate.format(date);

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, value);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLastRestoreDate() {
		String sql = "SELECT lastRestore FROM Backup";
		String date = "";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			date = result.getString("lastRestore");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}
