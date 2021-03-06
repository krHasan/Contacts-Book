package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import operation.GetScence;

public class AboutDeveloperController {

	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private Label lblDeveloperName;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) lblDeveloperName.getScene().getWindow();
		Scene scene = (Scene) lblDeveloperName.getScene();
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

	//////////////////////////////////////////// MainCodes////////////////////////////////////////////
	// ---------------------------------------------------------------------------------------------//
	@FXML
	private void hyperlinkLinkedin(ActionEvent event) {
		openWebpage("https://www.linkedin.com/in/kr-hasan"); // clicking this link will open Linkedin profile
	}

	@FXML
	private void hyperlinkGit(ActionEvent event) {
		openWebpage("https://github.com/krHasan"); // clicking this link will open github profile
	}

	// method for opening the parameter link on user default browser
	private static void openWebpage(String url) {
		try {
			new ProcessBuilder("x-www-browser", url).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
