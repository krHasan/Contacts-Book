package model;

import database.access.ContactAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import operation.ComboboxList;

public class AddNewContactModal extends ContactAccess {

	public ObservableList<String> priorityList() {
		ObservableList<String> list = FXCollections.observableArrayList(new ComboboxList().getPriorityList());
		return list;
	}

}
