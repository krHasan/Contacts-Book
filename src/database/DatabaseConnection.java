package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.access.SystemAccess;

public class DatabaseConnection {
	Connection connection;

	public final static String dbDirectory = "/home/" + System.getProperty("user.name") + "/.contacts-book/";

	public final static String dbName = "ContactsList.db";

	public final static String dbAddress = dbDirectory + dbName;

	public DatabaseConnection() {
		connection = connector();
	}

	public static Connection connector() {
		// SQLite connection string
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");

			// for development
			// String url = "jdbc:sqlite:ContactsList.db";

			// for Linux distribution
			String url = "jdbc:sqlite:" + dbAddress;

			conn = DriverManager.getConnection(url);

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public boolean isDBConnected() {
		try {
			SystemAccess access = new SystemAccess();
			return access.hasdb();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
