package table;

import javafx.beans.property.SimpleStringProperty;

public class ContactsListTable {

	private SimpleStringProperty tblColNo;
	private SimpleStringProperty tblColName;
	private SimpleStringProperty tblColId;
	private SimpleStringProperty tblColNum1;
	private SimpleStringProperty tblColNum2;
	private SimpleStringProperty tblColPriority;
	private SimpleStringProperty tblColAddress;

	public ContactsListTable(String tblColNo, String tblColName, String tblColId, String tblColNum1, String tblColNum2,
			String tblColPriority, String tblColAddress) {

		this.tblColNo = new SimpleStringProperty(tblColNo);
		this.tblColName = new SimpleStringProperty(tblColName);
		this.tblColId = new SimpleStringProperty(tblColId);
		this.tblColNum1 = new SimpleStringProperty(tblColNum1);
		this.tblColNum2 = new SimpleStringProperty(tblColNum2);
		this.tblColPriority = new SimpleStringProperty(tblColPriority);
		this.tblColAddress = new SimpleStringProperty(tblColAddress);
	}

	public final SimpleStringProperty tblColNoProperty() {
		return this.tblColNo;
	}

	public final String getTblColNo() {
		return this.tblColNoProperty().get();
	}

	public final void setTblColNo(final String tblColNo) {
		this.tblColNoProperty().set(tblColNo);
	}

	public final SimpleStringProperty tblColNameProperty() {
		return this.tblColName;
	}

	public final String getTblColName() {
		return this.tblColNameProperty().get();
	}

	public final void setTblColName(final String tblColName) {
		this.tblColNameProperty().set(tblColName);
	}

	public final SimpleStringProperty tblColIdProperty() {
		return this.tblColId;
	}

	public final String getTblColId() {
		return this.tblColIdProperty().get();
	}

	public final void setTblColId(final String tblColId) {
		this.tblColIdProperty().set(tblColId);
	}

	public final SimpleStringProperty tblColNum1Property() {
		return this.tblColNum1;
	}

	public final String getTblColNum1() {
		return this.tblColNum1Property().get();
	}

	public final void setTblColNum1(final String tblColNum1) {
		this.tblColNum1Property().set(tblColNum1);
	}

	public final SimpleStringProperty tblColNum2Property() {
		return this.tblColNum2;
	}

	public final String getTblColNum2() {
		return this.tblColNum2Property().get();
	}

	public final void setTblColNum2(final String tblColNum2) {
		this.tblColNum2Property().set(tblColNum2);
	}

	public final SimpleStringProperty tblColPriorityProperty() {
		return this.tblColPriority;
	}

	public final String getTblColPriority() {
		return this.tblColPriorityProperty().get();
	}

	public final void setTblColPriority(final String tblColPriority) {
		this.tblColPriorityProperty().set(tblColPriority);
	}

	public final SimpleStringProperty tblColAddressProperty() {
		return this.tblColAddress;
	}

	public final String getTblColAddress() {
		return this.tblColAddressProperty().get();
	}

	public final void setTblColAddress(final String tblColAddress) {
		this.tblColAddressProperty().set(tblColAddress);
	}

}
