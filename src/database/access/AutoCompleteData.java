package database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import database.DatabaseConnection;
import operation.Constraints;

public class AutoCompleteData extends DatabaseConnection {

	public static List<String> name(String searchLetter) {
		final SortedSet<String> entries = new TreeSet<>();

		String sql = "SELECT name FROM Contacts";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			while (result.next()) {
				entries.add(result.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listOfWords = entries.stream().filter(e -> e.toLowerCase().contains(searchLetter.toLowerCase()))
				.collect(Collectors.toList());
		return listOfWords;
	}

	public static List<String> number(String searchLetter) {
		final SortedSet<String> entries = new TreeSet<>();

		String sql1 = "SELECT number1 FROM Contacts";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql1)) {
			while (result.next()) {
				if (result.getString("number1") == null || result.getString("number1").isEmpty()) {
				} else {
					entries.add(result.getString("number1"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql2 = "SELECT number2 FROM Contacts";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql2)) {
			while (result.next()) {
				if (result.getString("number2") == null || result.getString("number2").isEmpty()) {
				} else {
					entries.add(result.getString("number2"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listOfWords = entries.stream().filter(e -> e.toLowerCase().contains(searchLetter.toLowerCase()))
				.collect(Collectors.toList());
		return listOfWords;
	}

	public static List<String> globalid(String searchLetter) {
		final SortedSet<String> entries = new TreeSet<>();

		String sql = "SELECT globalId FROM Contacts";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			while (result.next()) {
				entries.add(Constraints.intToString(result.getInt("globalId")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listOfWords = entries.stream().filter(e -> e.toLowerCase().contains(searchLetter.toLowerCase()))
				.collect(Collectors.toList());
		return listOfWords;
	}

	public static List<String> address(String searchLetter) {
		final SortedSet<String> entries = new TreeSet<>();

		String sql = "SELECT address FROM Contacts";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			while (result.next()) {
				entries.add(result.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listOfWords = entries.stream().filter(e -> e.toLowerCase().contains(searchLetter.toLowerCase()))
				.collect(Collectors.toList());
		return listOfWords;
	}
}
