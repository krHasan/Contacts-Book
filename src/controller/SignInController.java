package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.dialog.ConfirmDialogController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.SignInModal;
import operation.GetDialog;
import operation.GetScence;

public class SignInController extends SignInModal {

	/////////////////////////////////// ObjectsDeclaration////////////////////////////////
	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnSignIn;

	@FXML
	private Label lblForgotPassword;

	@FXML
	private Label lblNewUser;

	@FXML
	private Label lblWarning;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();
	ConfirmDialogController confirmController = new ConfirmDialogController();

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	public void initialize() {
		
		if (!isDBConnected()) {
			lblWarning.setText("Database not found");
			lblNewUser.setDisable(true);
			allNodesDown();
		} else if (!isUserPresent()) {
			allNodesDown();
		}
		
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) btnSignIn.getScene().getWindow();
		Scene scene = (Scene) btnSignIn.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
	}

	private void allNodesDown() {
		txtUsername.setDisable(true);
		txtPassword.setDisable(true);
		btnSignIn.setDisable(true);
		lblForgotPassword.setDisable(true);
	}
	//////////////////////////////////////////// MainCode////////////////////////////////////////////
	// --------------------------------------------------------------------------------------------//

	@FXML
	private void btnSignIn(ActionEvent event) throws IOException {
		if (signIn(txtUsername.getText(), txtPassword.getText())) {
			getWindow.dashboard(thisStageInfo());
		} else {
			lblWarning.setText("Username or Password is Wrong");
			txtPassword.clear();
		}
	}

	@FXML
	private void warningLblStates() {
		lblWarning.setText(null);
	}
	
	
	@FXML
	private void lblForgotPassword(MouseEvent e) {
		
		getDialog.confirmDialog(thisStageInfo());
		((Scene) btnSignIn.getScene()).getRoot().setEffect(null);
	}
	
	@FXML
	private void lblNewUser(MouseEvent e) {
		
	}
	
}
