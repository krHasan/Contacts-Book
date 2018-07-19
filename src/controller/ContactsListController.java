package controller;

import java.util.HashMap;
import java.util.Map;

import database.access.TableAccess;
import enums.Contacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AutoComplete;
import model.ContactsListModal;
import operation.GetScence;
import table.ContactsListTable;

public class ContactsListController extends ContactsListModal {
	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private ComboBox<String> cmboPriority;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtNumber;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtAddress;

	@FXML
	private TableView<Object> tbl;

	@FXML
	private TableColumn<ContactsListTable, Integer> tblColNo;

	@FXML
	private TableColumn<ContactsListTable, String> tblColName;

	@FXML
	private TableColumn<ContactsListTable, Integer> tblColId;

	@FXML
	private TableColumn<ContactsListTable, String> tblColNumber;

	@FXML
	private TableColumn<ContactsListTable, String> tblColPriority;

	@FXML
	private TableColumn<ContactsListTable, String> tblColAddress;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	TableColumn<ContactsListTable, String> tblColNum1 = new TableColumn<>("1");
	TableColumn<ContactsListTable, String> tblColNum2 = new TableColumn<>("2");
	GetScence getWindow = new GetScence();
	TableAccess data = new TableAccess();

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		initialState();
	}

	@SuppressWarnings("unchecked")
	private void initialState() {
		tblColNumber.getColumns().addAll(tblColNum1, tblColNum2);
		loadPriority();
		showTableData();
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) cmboPriority.getScene().getWindow();
		Scene scene = (Scene) cmboPriority.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
	}

	private void loadPriority() {
		cmboPriority.setItems(priorityList());
		cmboPriority.getSelectionModel().selectFirst();
	}

	private void showTableData() {
		tableInitialize();
		tbl.setItems(data.getAllContactsData());
	}

	private void tableInitialize() {
		tblColNum1.setPrefWidth(115);
		tblColNum1.setStyle("-fx-alignment:center;");
		tblColNum2.setPrefWidth(115);
		tblColNum2.setStyle("-fx-alignment:center;");
		tblColNo.setCellValueFactory(new PropertyValueFactory<>("tblColNo"));
		tblColName.setCellValueFactory(new PropertyValueFactory<>("tblColName"));
		tblColId.setCellValueFactory(new PropertyValueFactory<>("tblColId"));
		tblColNum1.setCellValueFactory(new PropertyValueFactory<>("tblColNum1"));
		tblColNum2.setCellValueFactory(new PropertyValueFactory<>("tblColNum2"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<>("tblColPriority"));
		tblColAddress.setCellValueFactory(new PropertyValueFactory<>("tblColAddress"));
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
		getWindow.signIn(thisStageInfo());
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
	// ------------------------------------------------------------------------------------------------//
	@FXML
	private void cmboPriority(ActionEvent e) {
		tableInitialize();
		if (cmboPriority.getValue() == "All") {
			tbl.setItems(data.getAllContactsData());
		} else {
			tbl.setItems(data.getPriorityContactsData(cmboPriority.getValue()));
		}
	}

	@FXML
	private void txtName() {
//		txtAddress.clear();
//		txtNumber.clear();
//		txtId.clear();
		AutoComplete.autocomplete(txtName, Contacts.name);
		txtName.textProperty().addListener(InvalidationListener -> {
			String searchLetter = txtName.getText();
			tableInitialize();
			if (searchLetter == null || searchLetter.isEmpty()) {
				tbl.setItems(data.getAllContactsData());
			} else {
				tbl.setItems(getContactsByName(searchLetter));
			}
		});
	}

	@FXML
	private void txtAddress() {
//		txtName.clear();
//		txtNumber.clear();
//		txtId.clear();
		AutoComplete.autocomplete(txtAddress, Contacts.address);
		txtAddress.textProperty().addListener(InvalidationListener -> {
			String searchLetter = txtAddress.getText();
			tableInitialize();
			if (searchLetter == null || searchLetter.isEmpty()) {
				tbl.setItems(data.getAllContactsData());
			} else {
				tbl.setItems(getContactsByAddress(searchLetter));
			}
		});
	}

	@FXML
	private void txtNumber() {
//		txtAddress.clear();
//		txtId.clear();
//		txtName.clear();
		AutoComplete.autocomplete(txtNumber, Contacts.number);
		txtNumber.textProperty().addListener(InvalidationListener -> {
			String searchLetter = txtNumber.getText();
			tableInitialize();
			if (searchLetter == null || searchLetter.isEmpty()) {
				tbl.setItems(data.getAllContactsData());
			} else {
				tbl.setItems(getContactsByNumber(searchLetter));
			}
		});
	}

	@FXML
	private void txtId() {
		// txtAddress.clear();
		// txtNumber.clear();
		// txtName.clear();

		String searchLetter = txtId.getText();
		if (searchLetter.matches("[0-9]{1,7}")) {
			tableInitialize();
			AutoComplete.autocomplete(txtId, Contacts.globalId);
			 txtId.textProperty().addListener(InvalidationListener -> {
				tbl.setItems(getContactsById(searchLetter));
			 });
		} else if (searchLetter == null || searchLetter.isEmpty()) {
			tbl.setItems(data.getAllContactsData());
		} else {
			txtId.clear();
		}
	}

}
