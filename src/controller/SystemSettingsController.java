package controller;

import java.util.HashMap;
import java.util.Map;

import controller.dialog.ConfirmDialogController;
import controller.dialog.ErrorDialogController;
import controller.dialog.InputDialogController;
import controller.dialog.PasswordDialogController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.General;
import model.SystemSettingsModal;
import operation.GetDialog;
import operation.GetScence;

public class SystemSettingsController extends SystemSettingsModal {
	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtPassword;

	@FXML
	private TextField txtReTypePassword;

	@FXML
	private Label lblReTypePass;

	@FXML
	private Label lblWarningPassword;

	@FXML
	private Label lblWarningReTypePass;

	@FXML
	private Button btnUpdateName;

	@FXML
	private Button btnUpdatePassword;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();
	General genModal = new General();

	private boolean passwordChangeBtnPressed = false;
	private boolean passwordOk = false;

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		initialStage();
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) btnUpdateName.getScene().getWindow();
		Scene scene = (Scene) btnUpdateName.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
	}

	private void initialStage() {
		txtUsername.setText(username());
		txtUsername.setEditable(false);
		txtName.setText(getName());
		txtName.setEditable(false);

		txtPassword.setText(password());
		txtPassword.setEditable(false);
		txtReTypePassword.setVisible(false);
		lblReTypePass.setVisible(false);

		passwordChangeBtnPressed = false;
		passwordOk = false;
		btnUpdatePassword.getStyleClass().add("btnChangePassword");
		btnUpdatePassword.setText("Change");
	}

	private void passwordChangeOkStatus() {
		if (passwordOk) {
			btnUpdatePassword.getStyleClass().clear();
			btnUpdatePassword.getStyleClass().add("button");
			btnUpdatePassword.getStyleClass().add("btnUpdate");
			btnUpdatePassword.setText("Update");
		} else {
			btnUpdatePassword.setText("Cancel");
			btnUpdatePassword.getStyleClass().add("btn-cancel");
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
	@FXML
	private void btnUpdateName(ActionEvent ev) {
		// show and wait
		getDialog.passwordDialog(thisStageInfo());
		((Scene) btnUpdateName.getScene()).getRoot().setEffect(null);

		if (PasswordDialogController.btnOKpressed) {

			if (isPasswordMatched(PasswordDialogController.answer)) {

				InputDialogController.headerText = "New Name";
				InputDialogController.contentText = "Type your new name";
				// show and wait
				getDialog.inputDialog(thisStageInfo());
				((Scene) btnUpdateName.getScene()).getRoot().setEffect(null);
				if (InputDialogController.btnOKpressed) {
					Map<String, String> map = new HashMap<>();
					map.put("name", InputDialogController.answer);
					map.put("username", txtUsername.getText());
					if (setName(map)) {
						// show and wait
						getDialog.confirmDialog(thisStageInfo());
						((Scene) btnUpdateName.getScene()).getRoot().setEffect(null);
						initialStage();
					} else {
						// show and wait
						getDialog.errorDialog(thisStageInfo());
						((Scene) btnUpdateName.getScene()).getRoot().setEffect(null);
					}
				}
			} else {
				ErrorDialogController.headerText = "Wrong Password";
				ErrorDialogController.contentText = "Password didn't match";
				// show and wait
				getDialog.errorDialog(thisStageInfo());
				((Scene) btnUpdateName.getScene()).getRoot().setEffect(null);
			}
		}
	}

	@FXML
	private void btnUpdatePassword(ActionEvent ev) {
		if (passwordChangeBtnPressed) {
			if (passwordOk) {
				if (!txtPassword.getText().equals(txtReTypePassword.getText())) {
					lblWarningReTypePass.setText("Password didn't match");
				} else {
					Map<String, String> map = new HashMap<>();
					map.put("password", txtPassword.getText());
					map.put("username", txtUsername.getText());
					if (setPassword(map)) {
						initialStage();
						ConfirmDialogController.contentText = "Password changed sucsessfully";
						// show and wait
						getDialog.confirmDialog(thisStageInfo());
						((Scene) btnUpdatePassword.getScene()).getRoot().setEffect(null);
					} else {
						// show and wait
						getDialog.errorDialog(thisStageInfo());
						((Scene) btnUpdatePassword.getScene()).getRoot().setEffect(null);
					}
				}
			} else {
				initialStage();
			}
		} else {
			// show and wait
			getDialog.passwordDialog(thisStageInfo());
			((Scene) btnUpdatePassword.getScene()).getRoot().setEffect(null);

			if (PasswordDialogController.btnOKpressed) {
				if (isPasswordMatched(PasswordDialogController.answer)) {
					txtPassword.setEditable(true);
					txtPassword.clear();
					txtReTypePassword.setVisible(true);
					lblReTypePass.setVisible(true);
					btnUpdatePassword.setText("Cancel");
					btnUpdatePassword.getStyleClass().add("btn-cancel");
					passwordChangeBtnPressed = true;
				} else {
					ErrorDialogController.headerText = "Wrong Password";
					ErrorDialogController.contentText = "Password didn't match";
					// show and wait
					getDialog.errorDialog(thisStageInfo());
					((Scene) btnUpdateName.getScene()).getRoot().setEffect(null);
				}
			}
		}
	}

	@FXML
	private void txtPassword() {
		passwordOk = genModal.passwodPerform(txtPassword, lblWarningPassword, txtReTypePassword, lblWarningReTypePass);
		passwordChangeOkStatus();
	}

	@FXML
	private void txtReTypePassword() {
		passwordOk = genModal.reTypePasswordPerform(txtPassword, txtReTypePassword, lblWarningReTypePass);
		passwordChangeOkStatus();
	}

}
