package controller.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputDialogController {
	
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
	public static String headerText = "Input Dialog";
	public static String contentText = "This is a input dialog";
	public static String answer = "Not Found";

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
