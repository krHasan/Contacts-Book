package model;

import java.util.List;

import database.access.AutoCompleteData;
import database.access.TableAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import operation.ComboboxList;

public class ContactsListModal extends TableAccess {

	public ObservableList<String> priorityList() {
		ObservableList<String> list = FXCollections.observableArrayList(new ComboboxList().getPriorityListForAll());
		return list;
	}

	public ObservableList<Object> getContactsByName(String searchLetter) {
		ObservableList<Object> contactsData = FXCollections.observableArrayList();
		List<String> listOfNames = AutoCompleteData.name(searchLetter);
		for (String name : listOfNames) {
			contactsData = getContactsByNameData(contactsData, name);
		}
		return contactsData;
	}

	public ObservableList<Object> getContactsByAddress(String searchLetter) {
		ObservableList<Object> contactsData = FXCollections.observableArrayList();
		List<String> listOfAddress = AutoCompleteData.address(searchLetter);
		for (String address : listOfAddress) {
			contactsData = getContactsByAddressData(contactsData, address);
		}
		return contactsData;
	}

	public ObservableList<Object> getContactsByNumber(String searchLetter) {
		ObservableList<Object> contactsData = FXCollections.observableArrayList();
		List<String> listOfNumber = AutoCompleteData.number(searchLetter);
		for (String number : listOfNumber) {
			contactsData = getContactsByNumberData(contactsData, number);
		}
		return contactsData;
	}

	public ObservableList<Object> getContactsById(String searchLetter) {
		ObservableList<Object> contactsData = FXCollections.observableArrayList();
		List<String> listOfIds = AutoCompleteData.globalid(searchLetter);
		for (String id : listOfIds) {
			contactsData = getContactsByIdData(contactsData, id);
		}
		return contactsData;
	}
}
