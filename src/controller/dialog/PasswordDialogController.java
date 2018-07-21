package controller.dialog;

import database.access.Credentials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PasswordDialogController {
	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private Label dialogHeader;

	@FXML
	private Label content;

	@FXML
	private Button btnOK;

	@FXML
	private Button btnCancel;

	@FXML
	private TextField txtAnswer;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	public static boolean btnOKpressed = false;
	public static boolean btnCancelPressed = false;
	public static String headerText = "Password Varification";
	public static String contentText = "Type Your Current Password";
	public static String answer = "Not Found";

	public static boolean passwordOk = false;

	/////////////////////////////////// GeneralCodes////////////////////////////////
	@FXML
	private void initialize() {
		dialogHeader.setText(headerText);
		content.setText(contentText);
	}

	//////////////////////////////////////////// MainCodes////////////////////////////////////////////
	// ---------------------------------------------------------------------------------------------//
	@FXML
	private void btnOK(ActionEvent e) {
		answer = txtAnswer.getText();
		btnOKpressed = true;
		btnCancelPressed = false;
		passwordOk = isPasswordMatched(answer);
		Stage stage = (Stage) btnOK.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void btnCancel(ActionEvent e) {
		btnCancelPressed = true;
		btnOKpressed = false;
		Stage stage = (Stage) btnOK.getScene().getWindow();
		stage.close();
	}

	private boolean isPasswordMatched(String pass) {
		if (pass.equals(Credentials.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
