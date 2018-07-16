package model;

import database.access.Credentials;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import operation.ComboboxList;

public class ForgotPasswordModal extends Credentials{
	
	public String username() {
		return getUsername();
	}
	
	public ObservableList<String> getSecurityQuestionList() {
		ObservableList<String> list = FXCollections.observableArrayList(new ComboboxList().getSecurityQuestionList());
		return list;
	}
}
