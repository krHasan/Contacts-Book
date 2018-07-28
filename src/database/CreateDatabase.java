package database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import operation.GlobalID;

public class CreateDatabase {

	public static Connection firstCconnector() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");

			// for Linux distribution
			String url = "jdbc:sqlite:" + DatabaseConnection.dbAddress;

			conn = DriverManager.getConnection(url);

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Form firstCconnector: " + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public static void createDatabase() {
		File directory = new File(DatabaseConnection.dbDirectory);
		File file = new File(DatabaseConnection.dbAddress);

		if (!directory.exists()) {
			directory.mkdir();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		createCredentialsTable();
		createContactsTable();
		createBackupTable();
		createSystem_SettingsTable();
		initializeDatabase();

	}

	private static void createCredentialsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS 'Credentials' (\n"
				+ "	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `ownerName`	TEXT NOT NULL,\n"
				+ "	`username`	TEXT NOT NULL, `password`	TEXT NOT NULL,\n"
				+ "	`question`	TEXT NOT NULL, `answer`	TEXT NOT NULL)";

		try (Connection conn = firstCconnector(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createContactsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS `Contacts` (	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
				+ "	`globalId`	INTEGER NOT NULL, `name`	TEXT NOT NULL, `number1`	TEXT,\n"
				+ "	`number2`	TEXT, `address`	TEXT NOT NULL, `priority`	TEXT NOT NULL )";

		try (Connection conn = firstCconnector(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createBackupTable() {
		String sql = "CREATE TABLE IF NOT EXISTS `Backup` ( `ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
				+ "	`lastBackup`	TEXT, `lastRestore`	TEXT )";

		try (Connection conn = firstCconnector(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void createSystem_SettingsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS `System_Settings` (\n"
				+ "	`ID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`globalId`	INTEGER NOT NULL)";

		try (Connection conn = firstCconnector(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void initializeDatabase() {

		String initializeBackup = "INSERT INTO Backup (lastBackup, lastRestore) VALUES(?, ?)";
		String initializeSystem_Settings = "INSERT INTO System_Settings(globalId) VALUES (?)";

		if (!GlobalID.idDataExists()) {
			try (Connection conn = firstCconnector();
					PreparedStatement pstmt = conn.prepareStatement(initializeBackup)) {
				pstmt.setString(1, "No Last Backup");
				pstmt.setString(2, "Never Restored");
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!GlobalID.idDataExists()) {
			try (Connection conn = firstCconnector();
					PreparedStatement pstmt = conn.prepareStatement(initializeSystem_Settings)) {
				pstmt.setInt(1, 1);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
