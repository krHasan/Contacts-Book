package database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import database.DatabaseConnection;

public class Credentials extends DatabaseConnection {

	public static boolean authentication(String username, String password) {
		boolean feedback = false;
		String sql = "SELECT ownerName FROM Credentials WHERE username = ? AND password = ?";
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				feedback = true;
			} else {
				feedback = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedback;
	}

	public static boolean isUserPresent() {
		String sql = "SELECT username FROM Credentials";
		// create connection and get owner name
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {

			if (result.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static String getSecurityQuestion() {
		String sql = "SELECT question FROM Credentials";
		String question = "No Question Found";
		// create connection and get the security question.
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			question = result.getString("question");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return question;
	}

	public static String getSecurityAnswer() {
		String sql = "SELECT answer FROM Credentials";
		String question = "No Answer Found";
		// create connection and get the security question answer.
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			question = result.getString("answer");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return question;
	}

	public static String getUsername() {
		String sql = "SELECT username FROM Credentials";
		String question = "No Username Found";
		// create connection and get the security question answer.
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			question = result.getString("username");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return question;
	}

	public static String getPassword() {
		String sql = "SELECT password FROM Credentials";
		String question = "No Password Found";
		// create connection and get the password.
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			question = result.getString("password");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return question;
	}

	public static String getName() {
		String sql = "SELECT ownerName FROM Credentials";
		String question = "No owner name Found";
		// create connection and get the owner name.
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			question = result.getString("ownerName");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return question;
	}

	public boolean setUserCredentials(Map<String, String> map) {
		int value = 0;
		String sql = "INSERT INTO Credentials (ownerName, username, password, question, answer) \n"
				+ "VALUES (?,?,?,?,?)";

		String name = (String) map.get("name");
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		String question = (String) map.get("question");
		String answer = (String) map.get("answer");

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.setString(4, question);
			pstmt.setString(5, answer);

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

	public boolean setName(Map<String, String> map) {
		int value = 0;
		String sql = "UPDATE Credentials SET ownerName = ? WHERE username = ?";

		String name = map.get("name");
		String username = map.get("username");

		// create connection and set the owner name.
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, username);
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

	// update password, security question if user forgot his password
	public boolean updateUserCredentials(Map<String, String> map) {
		int value = 0;
		String sql = "UPDATE Credentials SET password = ?, question = ?, answer = ? WHERE username = ?";

		String password = (String) map.get("password");
		String question = (String) map.get("question");
		String answer = (String) map.get("answer");
		String username = (String) map.get("username");

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, password);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			pstmt.setString(4, username);

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
	
	public boolean setPassword(Map<String, String> map) {
		int value = 0;
		String sql = "UPDATE Credentials SET password = ? WHERE username = ?";

		String name = map.get("password");
		String username = map.get("username");

		// create connection and set the owner name.
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, username);
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
