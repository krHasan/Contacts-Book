package controller.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WarningDialogController {

	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private Label dialogHeader;

	@FXML
	private Label content;

	@FXML
	private Button btnOK;

	@FXML
	private Button btnCancel;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	public static boolean btnOKpressed = false;
	public static boolean btnCancelPressed = false;
	public static String headerText = "Confirm Dialog";
	public static String contentText = "This is a confirmation request";

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
		btnOKpressed = true;
		btnCancelPressed = false;
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
}
