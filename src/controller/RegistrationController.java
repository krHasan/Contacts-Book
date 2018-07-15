package controller;

import java.util.HashMap;
import java.util.Map;

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
	private static boolean invalidated = false;
	/////////////////////////////////// GeneralCodes////////////////////////////////
	@FXML
	private void initialize() {
		nodeStates();
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

	/*
	 * check if part of the node data state is changed. if changed then notify user
	 * at the time being cancel the process.
	 */
	private boolean invalideted() {
		if (nameOk && usernameOk && passwordOk && answerOk) {
			return false;
		} else if (!nameOk && !usernameOk && !passwordOk && !answerOk) {
			return false;
		} else {
			return true;
		}
	}

	private void nodeStates() {
		lblWarningName.setText(null);
		lblWarningUsername.setText(null);
		lblWarningPassword.setText(null);
		lblWarningReTypePassword.setText(null);
		lblWarningAnswer.setText(null);
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

	//////////////////////////////////////////// MainCode////////////////////////////////////////////
	// --------------------------------------------------------------------------------------------//
	@FXML
	private void btn(ActionEvent e) {
		if (btnOk) {
			System.out.println("Comming Soon");
		} else {
			if (invalideted()) {
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
		if (!txtName.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtName.getText().substring(0, 1))) {
				lblWarningName.setText("1st letter can't be white space");
				txtName.clear();
				nameOk = false;
			} else {
				nodeStates();
				nameOk = true;
			}
		}
	}

	@FXML
	private void txtUsername() {
		if (!txtUsername.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtUsername.getText())) {
				lblWarningUsername.setText("White space is not allowed");
				txtUsername.clear();
				usernameOk = false;
			} else {
				nodeStates();
				usernameOk = true;
			}
		}
	}

	@FXML
	private void txtPassword() {
		if (!txtPassword.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtPassword.getText())) {
				lblWarningPassword.setText("White space is not allowed");
				txtPassword.clear();
			} else {
				nodeStates();
			}
		}
	}

	@FXML
	private void txtReTypePassword() {
		if (txtPassword.getText().equals("")) {
			lblWarningReTypePassword.setText("Type Password First");
			txtReTypePassword.clear();
			passwordOk = false;
		} else {
			nodeStates();
			passwordOk = false;

			if (txtPassword.getText().length() <= txtReTypePassword.getText().length()) {
				if (!txtReTypePassword.getText().equals(txtPassword.getText())) {
					lblWarningReTypePassword.setText("Password didn't match");
					txtReTypePassword.clear();
					passwordOk = false;
				} else {
					passwordOk = true;
				}
			}
		}
	}

	@FXML
	private void txtAnswer() {
		if (!txtAnswer.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtAnswer.getText().substring(0, 1))) {
				lblWarningAnswer.setText("1st letter can't be white space");
				txtAnswer.clear();
				answerOk = false;
			} else {
				nodeStates();
				answerOk = true;
			}
		}
	}
}
