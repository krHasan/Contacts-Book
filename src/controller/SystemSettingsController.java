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
import model.SystemSettingsModal;
import operation.GetDialog;
import operation.GetScence;
import system.Constraints;

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
	Constraints constrains = new Constraints();

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
		btnUpdatePassword.setText("Change Password");
	}

	private void passwordChangeOkStatus() {
		if (passwordOk) {
			btnUpdatePassword.getStyleClass().clear();
			btnUpdatePassword.getStyleClass().add("button");
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
						ConfirmDialogController.contentText = "Password changed sucsessfully";
						// show and wait
						getDialog.confirmDialog(thisStageInfo());
						((Scene) btnUpdatePassword.getScene()).getRoot().setEffect(null);
						initialStage();
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
					txtReTypePassword.setVisible(true);
					lblReTypePass.setVisible(true);
					btnUpdatePassword.setText("Cancel");
					btnUpdatePassword.getStyleClass().add("btn-cancel");
					passwordChangeBtnPressed = true;
				} else {
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
		txtPassword.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtPassword.getText().length() >= 2) {
					passwordOk = true;
					passwordChangeOkStatus();
					lblWarningPassword.setText(null);
				}
			} else { // when property is out of focused
				if (txtPassword.getText().length() == 1) {
					lblWarningPassword.setText("Minimum 2 charecter");
					passwordOk = false;
					passwordChangeOkStatus();
				}
			}
		});

		if (!txtPassword.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtPassword.getText())) {
				lblWarningPassword.setText("White space is not allowed");
				txtPassword.clear();
				passwordOk = false;
				passwordChangeOkStatus();
			} else {
				passwordChangeOkStatus();
				lblWarningPassword.setText(null);
				if (!txtReTypePassword.getText().equals("")) {

					if (txtReTypePassword.getText().equals(txtPassword.getText())) {
						passwordOk = true;
						passwordChangeOkStatus();
						lblWarningReTypePass.setText(null);
					} else {
						lblWarningReTypePass.setText("Password didn't match");
						passwordOk = false;
						passwordChangeOkStatus();
					}
				}
			}
		} else {
			passwordOk = false;
			passwordChangeOkStatus();
		}
	}

	@FXML
	private void txtReTypePassword() {
		if (txtPassword.getText().equals("")) {
			lblWarningReTypePass.setText("Type Password First");
			txtReTypePassword.clear();
			passwordOk = false;
			passwordChangeOkStatus();
		} else {
			passwordOk = false;
			passwordChangeOkStatus();

			if (txtPassword.getText().length() <= txtReTypePassword.getText().length()) {
				if (!txtReTypePassword.getText().equals(txtPassword.getText())) {
					lblWarningReTypePass.setText("Password didn't match");
					txtReTypePassword.clear();
					passwordOk = false;
					passwordChangeOkStatus();
				} else {
					passwordOk = true;
					passwordChangeOkStatus();
					lblWarningReTypePass.setText(null);
				}
			}
		}
	}

}
