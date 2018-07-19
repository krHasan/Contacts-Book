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
import model.General;
import operation.GetDialog;
import operation.GetScence;

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
	General genModal = new General();

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
		passwordOk = genModal.passwodPerform(txtPassword, lblWarningPassword, txtReTypePassword,
				lblWarningReTypePassword);
		nodeStates();
	}

	@FXML
	private void txtReTypePassword() {
		passwordOk = genModal.reTypePasswordPerform(txtPassword, txtReTypePassword, lblWarningReTypePassword);
		nodeStates();
	}

	@FXML
	private void txtAnswer() {
		answerOk = genModal.textPerform(txtAnswer, lblWarningAnswer);
		nodeStates();
	}
}
