package controller;

import java.util.HashMap;
import java.util.Map;

import controller.dialog.ConfirmDialogController;
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
import model.ForgotPasswordModal;
import operation.GetDialog;
import operation.GetScence;
import system.Constraints;

public class ForgotPasswordController extends ForgotPasswordModal {

	/////////////////////////////////// Objects////////////////////////////////
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
	private Label lblWarningPassword;

	@FXML
	private Label lblWarningReTypePassword;

	@FXML
	private Label lblWarningAnswer;

	@FXML
	private Button btn;

	/////////////////////////////////// GlobalObjects////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();
	Constraints constrains = new Constraints();

	private boolean passwordOk = false, answerOk = false;
	private boolean btnOk = false;
	/*
	 * invalidated check if part of the node data state has been changed. if changed
	 * then notify user at the time being cancel the process.
	 */
	private static boolean invalidated = false;

	/////////////////////////////////// GeneralCodes////////////////////////////////
	@FXML
	private void initialize() {
		txtUsername.setDisable(true);
		txtUsername.setText(username());
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
		if (passwordOk && answerOk) {
			btn.getStyleClass().clear();
			btn.getStyleClass().add("button");
			btn.setText("Update");
			btnOk = true;
		} else {
			btn.setText("Cancel");
			btn.getStyleClass().add("btn-cancel");
			btnOk = false;
		}
		if (passwordOk || answerOk) {
			invalidated = true;
		}
	}

	public static boolean getInvalideted() {
		return invalidated;
	}

	private Map<String, String> save() {
		Map<String, String> map = new HashMap<>();
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

	//////////////////////////////////////////// MainCondes////////////////////////////////////////////
	// ------------------------------------------------------------------------------------------------//
	@FXML
	private void btn(ActionEvent e) {
		if (btnOk) {
			if (txtAnswer.getText().equals(null)) {
				lblWarningAnswer.setText("Minimum 2 charecter");
			} else {
				if (updateUserCredentials(save())) {
					ConfirmDialogController.contentText = "Password successfully updated";
					// show and wait
					getDialog.confirmDialog(thisStageInfo());
					((Scene) btn.getScene()).getRoot().setEffect(null);
					getWindow.signIn(thisStageInfo());
				} else {
					ErrorDialogController.contentText = "Update unsuccessful";
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
	private void txtPassword() {
		txtPassword.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtPassword.getText().length() >= 2) {
					passwordOk = true;
					lblWarningPassword.setText(null);
				}
			} else { // when property is out of focused
				if (txtPassword.getText().length() == 1) {
					lblWarningPassword.setText("Minimum 2 charecter");
					passwordOk = false;
				}
			}
		});

		if (!txtPassword.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtPassword.getText())) {
				lblWarningPassword.setText("White space is not allowed");
				txtPassword.clear();
				passwordOk = false;
			} else {
				nodeStates();
				lblWarningPassword.setText(null);
				if (!txtReTypePassword.getText().equals("")) {

					if (txtReTypePassword.getText().equals(txtPassword.getText())) {
						passwordOk = true;
						lblWarningReTypePassword.setText(null);
					} else {
						lblWarningReTypePassword.setText("Password didn't match");
						passwordOk = false;
					}
				}
			}
		} else {
			passwordOk = false;
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
					lblWarningAnswer.setText(null);
				}
			} else { // when property is out of focused
				if (txtAnswer.getText().length() == 1) {
					lblWarningAnswer.setText("Minimum 2 charecter");
					answerOk = false;
				}
			}
		});

		if (!txtAnswer.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtAnswer.getText().substring(0, 1))) {
				lblWarningAnswer.setText("1st letter can't be white space");
				txtAnswer.clear();
				answerOk = false;
			} else {
				nodeStates();
				answerOk = true;
				lblWarningAnswer.setText(null);
			}
		} else {
			answerOk = false;
		}
	}
}
