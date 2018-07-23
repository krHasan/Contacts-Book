package database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import operation.Constraints;
import table.ContactsListTable;

public class TableAccess extends DatabaseConnection {

	public ObservableList<ContactsListTable> getAllContactsData() {
		final ObservableList<ContactsListTable> contactsData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM Contacts";

		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {

			int slNo = 1;

			while (result.next()) {
				String tblColNo = Constraints.intToString(slNo);
				String tblColName = result.getString("name");
				String tblColId = Constraints.intToString(result.getInt("globalId"));
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
						tblColPriority, tblColAddress));
				++slNo;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactsData;
	}

	public ObservableList<ContactsListTable> getPriorityContactsData(String priority) {
		final ObservableList<ContactsListTable> contactsData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM Contacts WHERE priority = ?";

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, priority);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				String tblColNo = Constraints.intToString(slNo);
				String tblColName = result.getString("name");
				String tblColId = Constraints.intToString(result.getInt("globalId"));
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
						tblColPriority, tblColAddress));
				++slNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contactsData;
	}

	// ------------------------Name----------------------------
	public ObservableList<ContactsListTable> getContactsByNameData(String searchLetter) {
		final ObservableList<ContactsListTable> contactsData = FXCollections.observableArrayList();
		List<String> listOfNames = AutoCompleteData.name(searchLetter);

		int slNo = 1;
		for (String name : listOfNames) {
			String sql = "SELECT * FROM Contacts WHERE name = ?";
			try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, name);
				ResultSet result = pstmt.executeQuery();

				while (result.next()) {
					String tblColNo = Constraints.intToString(slNo);
					String tblColName = result.getString("name");
					String tblColId = Constraints.intToString(result.getInt("globalId"));
					String tblColNum1 = result.getString("number1");
					String tblColNum2 = result.getString("number2");
					String tblColPriority = result.getString("priority");
					String tblColAddress = result.getString("address");

					contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
							tblColPriority, tblColAddress));
					++slNo;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return contactsData;
	}

	// ------------------------Address----------------------------
	public ObservableList<ContactsListTable> getContactsByAddressData(String searchLetter) {
		final ObservableList<ContactsListTable> contactsData = FXCollections.observableArrayList();
		List<String> listOfAddress = AutoCompleteData.address(searchLetter);

		int slNo = 1;
		for (String address : listOfAddress) {
			String sql = "SELECT * FROM Contacts WHERE address = ?";
			try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, address);
				ResultSet result = pstmt.executeQuery();

				while (result.next()) {
					String tblColNo = Constraints.intToString(slNo);
					String tblColName = result.getString("name");
					String tblColId = Constraints.intToString(result.getInt("globalId"));
					String tblColNum1 = result.getString("number1");
					String tblColNum2 = result.getString("number2");
					String tblColPriority = result.getString("priority");
					String tblColAddress = result.getString("address");

					contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
							tblColPriority, tblColAddress));
					++slNo;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contactsData;
	}

	// ------------------------Number----------------------------
	public ObservableList<ContactsListTable> getContactsByNumberData(String searchLetter) {
		final ObservableList<ContactsListTable> contactsData = FXCollections.observableArrayList();
		List<String> listOfNumber = AutoCompleteData.number(searchLetter);

		int slNo = 1;
		for (String number : listOfNumber) {
			String sql1 = "SELECT * FROM Contacts WHERE number1 = ?";
			try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql1)) {

				pstmt.setString(1, number);
				ResultSet result = pstmt.executeQuery();

				while (result.next()) {
					String tblColNo = Constraints.intToString(slNo);
					String tblColName = result.getString("name");
					String tblColId = Constraints.intToString(result.getInt("globalId"));
					String tblColNum1 = result.getString("number1");
					String tblColNum2 = result.getString("number2");
					String tblColPriority = result.getString("priority");
					String tblColAddress = result.getString("address");

					contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
							tblColPriority, tblColAddress));
					++slNo;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String sql2 = "SELECT * FROM Contacts WHERE number2 = ?";
			try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql2)) {

				pstmt.setString(1, number);
				ResultSet result = pstmt.executeQuery();

				while (result.next()) {
					String tblColNo = Constraints.intToString(slNo);
					String tblColName = result.getString("name");
					String tblColId = Constraints.intToString(result.getInt("globalId"));
					String tblColNum1 = result.getString("number1");
					String tblColNum2 = result.getString("number2");
					String tblColPriority = result.getString("priority");
					String tblColAddress = result.getString("address");

					contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
							tblColPriority, tblColAddress));
					++slNo;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contactsData;
	}

	// ------------------------ID----------------------------
	public ObservableList<ContactsListTable> getContactsByIdData(String searchLetter) {
		final ObservableList<ContactsListTable> contactsData = FXCollections.observableArrayList();
		List<String> listOfIds = AutoCompleteData.globalid(searchLetter);

		int slNo = 1;
		for (String id : listOfIds) {
			String sql = "SELECT * FROM Contacts WHERE globalId = ?";
			int idint = Constraints.stringToInt(id);

			try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idint);
				ResultSet result = pstmt.executeQuery();

				while (result.next()) {
					String tblColNo = Constraints.intToString(slNo);
					String tblColName = result.getString("name");
					String tblColId = Constraints.intToString(result.getInt("globalId"));
					String tblColNum1 = result.getString("number1");
					String tblColNum2 = result.getString("number2");
					String tblColPriority = result.getString("priority");
					String tblColAddress = result.getString("address");

					contactsData.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2,
							tblColPriority, tblColAddress));
					++slNo;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contactsData;
	}
}
