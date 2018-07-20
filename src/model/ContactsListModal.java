package model;

import database.access.TableAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import operation.ComboboxList;

public class ContactsListModal extends TableAccess {

	public ObservableList<String> priorityList() {
		ObservableList<String> list = FXCollections.observableArrayList(new ComboboxList().getPriorityListForAll());
		return list;
	}

}
