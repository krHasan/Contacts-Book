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
import model.AddNewContactModal;
import operation.GetDialog;
import operation.GetScence;
import system.Constraints;

public class AddNewContactController extends AddNewContactModal {

	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private TextField txtName;

	@FXML
	private TextField txtNumber1;

	@FXML
	private TextField txtNumber2;

	@FXML
	private TextField txtAddress;

	@FXML
	private ComboBox<String> cmboPriority;

	@FXML
	private Button btn;

	@FXML
	private Label lblWarning;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();
	Constraints constrains = new Constraints();

	private boolean nameOk = false, number1Ok = false, number2Ok = false, numberOk = false, addressOk = false;
	private boolean btnOk = false;
	/*
	 * invalidated check if part of the node data state has been changed. if changed
	 * then notify user at the time being cancel the process.
	 */
	private static boolean invalidated = false;

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		initialState();
	}

	private void initialState() {
		lblWarning.setText(null);
		loadQuestion();
		txtName.clear();
		txtNumber1.clear();
		txtNumber2.clear();
		txtAddress.clear();
		nameOk = false;
		number1Ok = false;
		number2Ok = false;
		addressOk = false;
		numberOk = false;
		invalidated = false;
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

	private void nodeStates() {

		if (number1Ok || number2Ok) {
			numberOk = true;
		}

		if (nameOk && numberOk && addressOk) {
			btn.getStyleClass().clear();
			btn.getStyleClass().add("button");
			btn.setText("Add Contact");
			btnOk = true;
		} else {
			btn.setText("Cancel");
			btn.getStyleClass().add("btn-cancel");
			btnOk = false;
		}
		if (nameOk || numberOk || addressOk) {
			invalidated = true;
		}
	}

	public static boolean getInvalideted() {
		return invalidated;
	}

	private Map<String, String> save() {
		Map<String, String> map = new HashMap<>();
		map.put("name", txtName.getText());
		map.put("number1", txtNumber1.getText());
		map.put("number2", txtNumber2.getText());
		map.put("address", txtAddress.getText());
		map.put("priority", cmboPriority.getValue());

		return map;
	}

	private void loadQuestion() {
		cmboPriority.setItems(priorityList());
		cmboPriority.getSelectionModel().selectFirst();
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
	private void btn(ActionEvent e) {
		if (btnOk) {
			if (addNewContact(save())) {
				ConfirmDialogController.contentText = "A new contact successfully added to list.";
				// show and wait
				getDialog.confirmDialog(thisStageInfo());
				((Scene) btn.getScene()).getRoot().setEffect(null);
				initialState();
			} else {
				ErrorDialogController.contentText = "Adding contact has been discarded";
				initialState();
			}
		} else {
			if (invalidated) {
				WarningDialogController.headerText = "Discard Changes?";
				WarningDialogController.contentText = "Do you want to leave without finishing?";
				// show and wait
				getDialog.warningDialog(thisStageInfo());
				((Scene) btn.getScene()).getRoot().setEffect(null);
				if (WarningDialogController.btnOKpressed) {
					getWindow.dashboard(thisStageInfo());
				}
			} else {
				getWindow.dashboard(thisStageInfo());
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
					lblWarning.setText(null);
				}
			} else { // when property is out of focused
				if (txtName.getText().length() == 1) {
					lblWarning.setText("Minimum 2 charecter");
					nameOk = false;
					nodeStates();
				}
			}
		});

		if (!txtName.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtName.getText().substring(0, 1))) {
				lblWarning.setText("1st letter can't be white space");
				txtName.clear();
				nameOk = false;
				nodeStates();
			} else {
				nameOk = true;
				nodeStates();
				lblWarning.setText(null);
			}
		} else {
			nameOk = false;
			nodeStates();
		}
	}

	@FXML
	private void txtNumber1() {
		txtNumber1.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtNumber1.getText().length() >= 11) {
					number1Ok = true;
					nodeStates();
					lblWarning.setText(null);
				}
			} else { // when property is out of focused
				if (txtNumber1.getText().length() >= 1 && txtNumber1.getText().length() < 11) {
					lblWarning.setText("Minimum 11 charecter");
					number1Ok = false;
					nodeStates();
				}
			}
		});

		if (!txtNumber1.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtNumber1.getText().substring(0, 1))) {
				lblWarning.setText("1st letter can't be white space");
				txtNumber1.clear();
				number1Ok = false;
				nodeStates();
			} else {
				number1Ok = true;
				nodeStates();
				lblWarning.setText(null);
			}
		} else {
			number1Ok = false;
			nodeStates();
		}
	}

	@FXML
	private void txtNumber2() {
		txtNumber2.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtNumber2.getText().length() >= 11) {
					number2Ok = true;
					nodeStates();
					lblWarning.setText(null);
				}
			} else { // when property is out of focused
				if (txtNumber2.getText().length() >= 1 && txtNumber2.getText().length() < 11) {
					lblWarning.setText("Minimum 11 charecter");
					number2Ok = false;
					nodeStates();
				}
			}
		});

		if (!txtNumber2.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtNumber2.getText().substring(0, 1))) {
				lblWarning.setText("1st letter can't be white space");
				txtNumber2.clear();
				number2Ok = false;
				nodeStates();
			} else {
				number2Ok = true;
				nodeStates();
				lblWarning.setText(null);
			}
		} else {
			number2Ok = false;
			nodeStates();
		}
	}

	@FXML
	private void txtAddress() {
		txtAddress.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (newPropertyValue) {// when property is focused
				if (txtAddress.getText().length() >= 2) {
					addressOk = true;
					nodeStates();
					lblWarning.setText(null);
				}
			} else { // when property is out of focused
				if (txtAddress.getText().length() == 1) {
					lblWarning.setText("Minimum 2 charecter");
					addressOk = false;
					nodeStates();
				}
			}
		});
		if (!txtAddress.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtAddress.getText().substring(0, 1))) {
				lblWarning.setText("1st letter can't be white space");
				txtAddress.clear();
				addressOk = false;
				nodeStates();
			} else {
				addressOk = true;
				nodeStates();
				lblWarning.setText(null);
			}
		} else {
			addressOk = false;
			nodeStates();
		}
	}
}
