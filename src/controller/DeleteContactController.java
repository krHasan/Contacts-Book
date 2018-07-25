package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.dialog.ConfirmDialogController;
import controller.dialog.ErrorDialogController;
import controller.dialog.PasswordDialogController;
import database.access.ContactAccess;
import enums.Contacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import operation.GetDialog;
import operation.GetScence;

public class DeleteContactController extends ContactAccess {
	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private Label lblHeading;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnCancel;

	@FXML
	private GridPane grid;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();
	List<String> idList = new ArrayList<>();

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		loadContacts();
		btnStatus();
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) lblHeading.getScene().getWindow();
		Scene scene = (Scene) lblHeading.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
	}

	private void btnStatus() {
		if (idList.size() == 0) {
			btnDelete.setDisable(true);
		} else {
			btnDelete.setDisable(false);
		}
	}

	//////////////////////////////////////////// MenuCodes////////////////////////////////////////////
	// -----------------------------------------------------------------------------------------------//
	@FXML
	private void mnuDashboard(ActionEvent event) {
		getWindow.dashboard(thisStageInfo());
	}

	@FXML
	private void mnuAddNewContact(ActionEvent event) {
		getWindow.addNewContact(thisStageInfo());
	}

	@FXML
	private void mnuContactsList(ActionEvent event) {
		getWindow.contactsList(thisStageInfo());
	}

	@FXML
	private void mnuDeleteContact(ActionEvent event) {
		getWindow.deleteContact(thisStageInfo());
	}

	@FXML
	private void mnuSignOut(ActionEvent event) {
		getWindow.signIn(thisStageInfo());
	}

	@FXML
	private void mnuSystemSettings(ActionEvent event) {
		getWindow.systemSettings(thisStageInfo());
	}

	@FXML
	private void mnuBackupAndRestore(ActionEvent event) {
		getWindow.backupAndRestore(thisStageInfo());
	}

	@FXML
	private void mnAboutDeveloper(ActionEvent event) {
		getWindow.aboutDeveloper(thisStageInfo());
	}

	//////////////////////////////////////////// MainCode////////////////////////////////////////////
	// --------------------------------------------------------------------------------------------//
	private void loadContacts() {
		List<Map<Enum<Contacts>, String>> list = getAllContactData();

		int rowIndex = 1;
		for (Map<Enum<Contacts>, String> map : list) {

			// create a row with 30 height
			RowConstraints row = new RowConstraints();
			row.setPrefHeight(30);
			grid.getRowConstraints().add(row);

			// create contact information objects
			int columnIndex = 1;
			CheckBox checkbox = new CheckBox(map.get(Contacts.globalId));
			checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) { // checkbox is checked
					idList.add(checkbox.getText());
					btnStatus();
				} else { // checkbox is unchecked
					idList.remove(checkbox.getText());
					btnStatus();
				}
			});
			Text lblName = new Text(map.get(Contacts.name));
			Text lblNum1 = new Text(map.get(Contacts.number1));
			Text lblNum2 = new Text(map.get(Contacts.number2));
			Text lblPriority = new Text(map.get(Contacts.priority));
			Text lblAddress = new Text(map.get(Contacts.address));

			// assign contact information to grid row
			grid.add(checkbox, columnIndex, rowIndex);
			grid.add(lblName, ++columnIndex, rowIndex);
			grid.add(lblNum1, ++columnIndex, rowIndex);
			GridPane.setHalignment(lblNum1, HPos.CENTER);
			grid.add(lblNum2, ++columnIndex, rowIndex);
			GridPane.setHalignment(lblNum2, HPos.CENTER);
			grid.add(lblPriority, ++columnIndex, rowIndex);
			GridPane.setHalignment(lblPriority, HPos.CENTER);
			grid.add(lblAddress, ++columnIndex, rowIndex);

			++rowIndex;
		}
	}

	@FXML
	private void btnDelete(MouseEvent e) {
		// show and wait
		getDialog.passwordDialog(thisStageInfo());
		((Scene) btnDelete.getScene()).getRoot().setEffect(null);
		if (PasswordDialogController.passwordOk) {
			for (String id : idList) {
				deleteContactById(id);
			}
			ConfirmDialogController.contentText = "Contacts has been successfully deleted";
			// show and wait
			getDialog.confirmDialog(thisStageInfo());
			((Scene) btnDelete.getScene()).getRoot().setEffect(null);
			getWindow.deleteContact(thisStageInfo());
		} else {
			ErrorDialogController.headerText = "Wrong Passowrd";
			ErrorDialogController.contentText = "Password didn't match";
			// show and wait
			getDialog.errorDialog(thisStageInfo());
			((Scene) btnDelete.getScene()).getRoot().setEffect(null);
		}
	}

	@FXML
	private void btnCancel(MouseEvent e) {
		getWindow.dashboard(thisStageInfo());
	}

}
