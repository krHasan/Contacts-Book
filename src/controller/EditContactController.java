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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AutoComplete;
import model.EditContactModal;
import model.General;
import operation.Constraints;
import operation.GetDialog;
import operation.GetScence;
import operation.MenuToWindow;

public class EditContactController extends EditContactModal {
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
	MenuToWindow mnuToWindow = new MenuToWindow();

	private boolean nameOk = false, number1Ok = false, number2Ok = false, numberOk = false, addressOk = false;
	private boolean btnOk = false;
	/*
	 * invalidated check if part of the node data state has been changed. if changed
	 * then notify user at the time being cancel the process.
	 */
	private static boolean invalidated = false;

	String id = Constraints.getIdForEditContact();

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		loadPriority();

		Map<String, String> map = getContactDataById(id);
		txtName.setText(map.get("name"));
		nameOk = true;
		txtNumber1.setText(map.get("number1"));
		number1Ok = true;
		txtNumber2.setText(map.get("number2"));
		number2Ok = true;
		txtAddress.setText(map.get("address"));
		addressOk = true;
		cmboPriority.setValue(map.get("priority"));

		initialState();
	}

	private void initialState() {
		lblWarning.setText(null);
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
			btn.setDisable(false);
			btnOk = true;
		} else {
			btn.setDisable(true);
			btnOk = false;
		}
		if (nameOk || numberOk || addressOk) {
			invalidated = true;
		} else {
			invalidated = false;
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
		mnuToWindow.mnuToDashboard(invalidated, thisStageInfo());
	}

	@FXML
	private void mnuAddNewContact(ActionEvent event) {
		mnuToWindow.mnuToAddNewContact(invalidated, thisStageInfo());
	}

	@FXML
	private void mnuContactsList(ActionEvent event) {
		mnuToWindow.mnuToContactsList(invalidated, thisStageInfo());
	}

	@FXML
	private void mnuDeleteContact(ActionEvent event) {
		mnuToWindow.mnuToDeleteContact(invalidated, thisStageInfo());
	}

	@FXML
	private void mnuSignOut(ActionEvent event) {
		mnuToWindow.mnuToSignOut(invalidated, thisStageInfo());
	}

	@FXML
	private void mnuSystemSettings(ActionEvent event) {
		mnuToWindow.mnuToSystemSettings(invalidated, thisStageInfo());
	}

	@FXML
	private void mnuBackupAndRestore(ActionEvent event) {
		mnuToWindow.mnuToBackupAndRestore(invalidated, thisStageInfo());
	}

	@FXML
	private void mnAboutDeveloper(ActionEvent event) {
		mnuToWindow.mnuAboutDeveloper(invalidated, thisStageInfo());
	}

	//////////////////////////////////////////// MainCode////////////////////////////////////////////
	// --------------------------------------------------------------------------------------------//
	@FXML
	private void btn(MouseEvent e) {
		if (btnOk) {
			if (updateContact(save(), id)) {
				ConfirmDialogController.contentText = "Contact has been successfully updated";
				// show and wait
				getDialog.confirmDialog(thisStageInfo());
				((Scene) btn.getScene()).getRoot().setEffect(null);
				getWindow.contactsList(thisStageInfo());
			} else {
				ErrorDialogController.contentText = "Updating contact has been discarded";
				getWindow.contactsList(thisStageInfo());
			}
		}
	}

	@FXML
	private void btnCancel(MouseEvent e) {
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
