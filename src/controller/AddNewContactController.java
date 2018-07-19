package controller;

import java.util.HashMap;
import java.util.Map;

import controller.dialog.ConfirmDialogController;
import controller.dialog.ErrorDialogController;
import controller.dialog.WarningDialogController;
import enums.Contacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AddNewContactModal;
import model.AutoComplete;
import model.General;
import operation.GetDialog;
import operation.GetScence;

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
	General genModal = new General();

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
		loadPriority();
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
		} else {
			numberOk = false;
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

	private void loadPriority() {
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
		AutoComplete.autocomplete(txtName, Contacts.name);
		txtName.textProperty().addListener(InvalidationListener -> {
			nameOk = genModal.textPerform(txtName, lblWarning);
			nodeStates();
		});
	}

	@FXML
	private void txtNumber1() {
		AutoComplete.autocomplete(txtNumber1, Contacts.number);
		txtNumber1.textProperty().addListener(InvalidationListener -> {
			number1Ok = genModal.numberPerform(txtNumber1, lblWarning);
			nodeStates();
		});
	}

	@FXML
	private void txtNumber2() {
		AutoComplete.autocomplete(txtNumber2, Contacts.number);
		txtNumber2.textProperty().addListener(InvalidationListener -> {
			number2Ok = genModal.numberPerform(txtNumber2, lblWarning);
			nodeStates();
		});
	}

	@FXML
	private void txtAddress() {
		AutoComplete.autocomplete(txtAddress, Contacts.address);
		txtAddress.textProperty().addListener(InvalidationListener -> {
			addressOk = genModal.textPerform(txtAddress, lblWarning);
			nodeStates();
		});
	}
}
