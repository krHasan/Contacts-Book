package databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;

public class Credentials extends DatabaseConnection {

	public boolean authentication(String username, String password) {
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

	public boolean isUserPresent() {
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

	public String getSecurityQuestion() {
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

	public String getSecurityAnswer() {
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

	public String getUsername() {
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

	public String getPassword() {
		String sql = "SELECT password FROM Credentials";
		String question = "No Password Found";
		// create connection and get the security question answer.
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			question = result.getString("password");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return question;
	}

}
