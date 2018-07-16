package controller;

import java.util.HashMap;
import java.util.Map;

import controller.dialog.ErrorDialogController;
import controller.dialog.WarningDialogController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.RegistrationModal;
import operation.GetDialog;
import operation.GetScence;
import system.Constraints;

public class RegistrationController extends RegistrationModal {

	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private TextField txtName;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	@FXML
	private TextField txtReTypePassword;

	@FXML
	private TextField txtAnswer;

	@FXML
	private ComboBox<String> cmboQuestion;

	@FXML
	private Label lblWarningName;

	@FXML
	private Label lblWarningUsername;

	@FXML
	private Label lblWarningPassword;

	@FXML
	private Label lblWarningReTypePassword;

	@FXML
	private Label lblWarningAnswer;

	@FXML
	private Button btn;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();
	Constraints constrains = new Constraints();

	private boolean nameOk = false, usernameOk = false, passwordOk = false, answerOk = false;
	private boolean btnOk = false;
	/*
	 * invalidated check if part of the node data state has been changed. if changed
	 * then notify user at the time being cancel the process.
	 */
	private static boolean invalidated = false;

	/////////////////////////////////// GeneralCodes////////////////////////////////
	@FXML
	private void initialize() {
		nodeStates();
		loadQuestion();
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) btn.getScene().getWindow();
		Scene scene = (Scene) btn.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
	}

	private void nodeStates() {
		if (nameOk && usernameOk && passwordOk && answerOk) {
			btn.getStyleClass().clear();
			btn.getStyleClass().add("button");
			btn.setText("Registration");
			btnOk = true;
		} else {
			btn.setText("Cancel");
			btn.getStyleClass().add("btn-cancel");
			btnOk = false;
		}
		if (nameOk || usernameOk || passwordOk || answerOk) {
			invalidated = true;
		}
	}

	public static boolean getInvalideted() {
		return invalidated;
	}

	private Map<String, String> save() {
		Map<String, String> map = new HashMap<>();
		map.put("name", txtName.getText());
		map.put("username", txtUsername.getText());
		map.put("password", txtPassword.getText());
		map.put("question", cmboQuestion.getValue());
		map.put("answer", txtAnswer.getText());

		return map;
	}

	private void loadQuestion() {
		cmboQuestion.setItems(getSecurityQuestionList());
		cmboQuestion.getSelectionModel().selectFirst();
	}
	//////////////////////////////////////////// MainCode////////////////////////////////////////////
	// --------------------------------------------------------------------------------------------//
	@FXML
	private void btn(ActionEvent e) {
		if (btnOk) {
			if (txtName.getText().equals(null)) {
				lblWarningName.setText("Minimum 2 charecter");
			} else if (txtUsername.getText().equals(null)) {
				lblWarningUsername.setText("Minimum 2 charecter");
			} else if (txtAnswer.getText().equals(null)) {
				lblWarningAnswer.setText("Minimum 2 charecter");
			} else {
				if (setUserCredentials(save())) {
					// show and wait
					getDialog.confirmDialog(thisStageInfo());
					((Scene) btn.getScene()).getRoot().setEffect(null);
					getWindow.signIn(thisStageInfo());
				} else {
					ErrorDialogController.contentText = "Registration unsuccessful";
					// show and wait
					getDialog.errorDialog(thisStageInfo());
					((Scene) btn.getScene()).getRoot().setEffect(null);
					getWindow.signIn(thisStageInfo());
				}
			}
		} else {
			if (invalidated) {
				WarningDialogController.headerText = "Discard Changes?";
				WarningDialogController.contentText = "Do you want to leave without finishing?";
				// show and wait
				getDialog.warningDialog(thisStageInfo());
				((Scene) btn.getScene()).getRoot().setEffect(null);
				if (WarningDialogController.btnOKpressed) {
					getWindow.signIn(thisStageInfo());
				}
			} else {
				getWindow.signIn(thisStageInfo());
			}
		}
	}

	@FXML
	private void txtName() {
		txtName.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtName.getText().length() >= 2) {
					nameOk = true;
					nodeStates();
					lblWarningName.setText(null);
				}
			} else { // when property is out of focused
				if (txtName.getText().length() == 1) {
					lblWarningName.setText("Minimum 2 charecter");
					nameOk = false;
					nodeStates();
				}
			}
		});

		if (!txtName.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtName.getText().substring(0, 1))) {
				lblWarningName.setText("1st letter can't be white space");
				txtName.clear();
				nameOk = false;
				nodeStates();
			} else {
				nameOk = true;
				nodeStates();
				lblWarningName.setText(null);
			}
		} else {
			nameOk = false;
			nodeStates();
		}
	}

	@FXML
	private void txtUsername() {
		txtUsername.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtUsername.getText().length() >= 2) {
					usernameOk = true;
					nodeStates();
					lblWarningUsername.setText(null);
				}
			} else { // when property is out of focused
				if (txtUsername.getText().length() == 1) {
					lblWarningUsername.setText("Minimum 2 charecter");
					usernameOk = false;
					nodeStates();
				}
			}
		});

		if (!txtUsername.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtUsername.getText())) {
				lblWarningUsername.setText("White space is not allowed");
				txtUsername.clear();
				usernameOk = false;
				nodeStates();
			} else {
				usernameOk = true;
				nodeStates();
				lblWarningUsername.setText(null);
			}
		} else {
			usernameOk = false;
			nodeStates();
		}
	}

	@FXML
	private void txtPassword() {
		txtPassword.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtPassword.getText().length() >= 2) {
					passwordOk = true;
					nodeStates();
					lblWarningPassword.setText(null);
				}
			} else { // when property is out of focused
				if (txtPassword.getText().length() == 1) {
					lblWarningPassword.setText("Minimum 2 charecter");
					passwordOk = false;
					nodeStates();
				}
			}
		});

		if (!txtPassword.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtPassword.getText())) {
				lblWarningPassword.setText("White space is not allowed");
				txtPassword.clear();
				passwordOk = false;
				nodeStates();
			} else {
				nodeStates();
				lblWarningPassword.setText(null);
				
				if (!txtReTypePassword.getText().equals("")) {

					if (txtReTypePassword.getText().equals(txtPassword.getText())) {
						passwordOk = true;
						nodeStates();
						lblWarningReTypePassword.setText(null);
					} else {
						lblWarningReTypePassword.setText("Password didn't match");
						passwordOk = false;
						nodeStates();
					}
				}
			}
		} else {
			passwordOk = false;
			nodeStates();
		}
	}

	@FXML
	private void txtReTypePassword() {
		if (txtPassword.getText().equals("")) {
			lblWarningReTypePassword.setText("Type Password First");
			txtReTypePassword.clear();
			passwordOk = false;
			nodeStates();
		} else {
			passwordOk = false;
			nodeStates();

			if (txtPassword.getText().length() <= txtReTypePassword.getText().length()) {
				if (!txtReTypePassword.getText().equals(txtPassword.getText())) {
					lblWarningReTypePassword.setText("Password didn't match");
					txtReTypePassword.clear();
					passwordOk = false;
					nodeStates();
				} else {
					passwordOk = true;
					nodeStates();
					lblWarningReTypePassword.setText(null);
				}
			}
		}
	}

	@FXML
	private void txtAnswer() {
		txtAnswer.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtAnswer.getText().length() >= 2) {
					answerOk = true;
					nodeStates();
					lblWarningAnswer.setText(null);
				}
			} else { // when property is out of focused
				if (txtAnswer.getText().length() == 1) {
					lblWarningAnswer.setText("Minimum 2 charecter");
					answerOk = false;
					nodeStates();
				}
			}
		});

		if (!txtAnswer.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtAnswer.getText().substring(0, 1))) {
				lblWarningAnswer.setText("1st letter can't be white space");
				txtAnswer.clear();
				answerOk = false;
				nodeStates();
			} else {
				answerOk = true;
				nodeStates();
				lblWarningAnswer.setText(null);
			}
		} else {
			answerOk = false;
			nodeStates();
		}
	}
}
