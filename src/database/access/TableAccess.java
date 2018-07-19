package database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import table.ContactsListTable;

public class TableAccess extends DatabaseConnection {

	public ObservableList<Object> getAllContactsData() {
		final ObservableList<Object> contactsData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM Contacts";

		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
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

	public ObservableList<Object> getPriorityContactsData(String priority) {
		final ObservableList<Object> contactsData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM Contacts WHERE priority = ?";

		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, priority);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
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

	public ObservableList<Object> getContactsByNameData(ObservableList<Object> list, String name) {
		String sql = "SELECT * FROM Contacts WHERE name = ?";
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				list.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2, tblColPriority,
						tblColAddress));
				++slNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ObservableList<Object> getContactsByAddressData(ObservableList<Object> list, String address) {
		String sql = "SELECT * FROM Contacts WHERE address = ?";
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, address);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				list.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2, tblColPriority,
						tblColAddress));
				++slNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ObservableList<Object> getContactsByNumberData(ObservableList<Object> list, String number) {

		String sql1 = "SELECT * FROM Contacts WHERE number1 = ?";
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql1)) {

			pstmt.setString(1, number);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				list.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2, tblColPriority,
						tblColAddress));
				++slNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql2 = "SELECT * FROM Contacts WHERE number2 = ?";
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql2)) {

			pstmt.setString(1, number);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				list.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2, tblColPriority,
						tblColAddress));
				++slNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ObservableList<Object> getContactsByIdData(ObservableList<Object> list, String id) {
		String sql = "SELECT * FROM Contacts WHERE globalId = ?";
		
		int idint = Integer.parseInt(id);
		try (Connection conn = connector(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idint);
			ResultSet result = pstmt.executeQuery();

			int slNo = 1;

			while (result.next()) {
				Integer tblColNo = slNo;
				String tblColName = result.getString("name");
				Integer tblColId = result.getInt("globalId");
				String tblColNum1 = result.getString("number1");
				String tblColNum2 = result.getString("number2");
				String tblColPriority = result.getString("priority");
				String tblColAddress = result.getString("address");

				list.add(new ContactsListTable(tblColNo, tblColName, tblColId, tblColNum1, tblColNum2, tblColPriority,
						tblColAddress));
				++slNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
