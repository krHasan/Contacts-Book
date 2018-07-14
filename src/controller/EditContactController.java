package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import operation.GetScence;

public class EditContactController {
	/////////////////////////////////// ObjectsDeclaration////////////////////////////////
	@FXML
	Label lblHeading;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();

	private Map<String, Object> getStage() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) lblHeading.getScene().getWindow();
		Scene scene = (Scene) lblHeading.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);
		
		return map;
	}

	//////////////////////////////////////////// MenuCodes////////////////////////////////////////////
	// -----------------------------------------------------------------------------------------------//
	@FXML
	private void mnuDashboard(ActionEvent event) {
		getWindow.dashboard(getStage());
	}

	@FXML
	private void mnuAddNewContact(ActionEvent event) {
		getWindow.addNewContact(getStage());
	}

	@FXML
	private void mnuContactsList(ActionEvent event) {
		getWindow.contactsList(getStage());
	}

	@FXML
	private void mnuDeleteContact(ActionEvent event) {
		getWindow.deleteContact(getStage());
	}

	@FXML
	private void mnuSignOut(ActionEvent event) {
		getWindow.signIn(getStage());
	}

	@FXML
	private void mnuSystemSettings(ActionEvent event) {
		getWindow.systemSettings(getStage());
	}

	@FXML
	private void mnAboutDeveloper(ActionEvent event) {
		getWindow.aboutDeveloper(getStage());
	}
}
