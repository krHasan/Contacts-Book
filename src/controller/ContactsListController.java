package controller;

import java.util.HashMap;
import java.util.Map;

import database.access.ContactAccess;
import database.access.TableAccess;
import enums.Contacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AutoComplete;
import model.ContactsListModal;
import operation.Constraints;
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
	private TableView<ContactsListTable> tbl;

	@FXML
	private TableColumn<ContactsListTable, String> tblColNo;

	@FXML
	private TableColumn<ContactsListTable, String> tblColName;

	@FXML
	private TableColumn<ContactsListTable, String> tblColId;

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
	final Clipboard clipboard = Clipboard.getSystemClipboard();
	final ClipboardContent clipContent = new ClipboardContent();

	// 3 mouse click to go to EditContact Window
	int firstRowStore = 0;
	boolean positionDelevered = true;
	int counts = 0;

	// authenticate that user clicked on same cell 3 times
	private boolean dataDelivered = true;
	private String firstDataStore = null;
	private int sameDataCount = 0;

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
	private void mnuBackupAndRestore(ActionEvent event) {
		getWindow.backupAndRestore(thisStageInfo());
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
		txtAddress.clear();
		txtNumber.clear();
		txtId.clear();
		AutoComplete.autocomplete(txtName, Contacts.name);
		String searchLetter = txtName.getText();
		tableInitialize();
		if (searchLetter == null || searchLetter.isEmpty()) {
			tbl.setItems(data.getAllContactsData());
		} else {
			tbl.setItems(getContactsByNameData(searchLetter));
		}
	}

	@FXML
	private void txtAddress() {
		txtName.clear();
		txtNumber.clear();
		txtId.clear();
		AutoComplete.autocomplete(txtAddress, Contacts.address);
		String searchLetter = txtAddress.getText();
		tableInitialize();
		if (searchLetter == null || searchLetter.isEmpty()) {
			tbl.setItems(data.getAllContactsData());
		} else {
			tbl.setItems(getContactsByAddressData(searchLetter));
		}
	}

	@FXML
	private void txtNumber() {
		txtAddress.clear();
		txtId.clear();
		txtName.clear();
		AutoComplete.autocomplete(txtNumber, Contacts.number);
		String searchLetter = txtNumber.getText();
		tableInitialize();
		if (searchLetter == null || searchLetter.isEmpty()) {
			tbl.setItems(data.getAllContactsData());
		} else {
			tbl.setItems(getContactsByNumberData(searchLetter));
		}
	}

	@FXML
	private void txtId() {
		txtAddress.clear();
		txtNumber.clear();
		txtName.clear();

		String searchLetter = txtId.getText();
		if (searchLetter.matches("[0-9]{1,7}")) {
			tableInitialize();
			AutoComplete.autocomplete(txtId, Contacts.globalId);
			tbl.setItems(getContactsByIdData(searchLetter));
		} else if (searchLetter == null || searchLetter.isEmpty()) {
			tbl.setItems(data.getAllContactsData());
		} else {
			txtId.clear();
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void tblClick(MouseEvent e) {
		// get the cell data on which user clicked
		if (tbl.getSelectionModel().getSelectedItem() != null) {
			TableViewSelectionModel<ContactsListTable> modal = tbl.getSelectionModel();

			// pos: TablePosition [ row: 4, column: ..., tableView: ... ]
			TablePosition<ContactsListTable, String> pos = modal.getSelectedCells().get(0);
			// System.out.println("\n" + "265 pos: " + pos);
			// System.out.println("266 column: " + pos.getColumn());
			if (pos.getColumn() != -1) {
				// row: 4
				int row = pos.getRow();
				// System.out.println("270 row: " + row);
				// item: table.ContactsListTable@5f0e043f
				ContactsListTable item = (ContactsListTable) tbl.getItems().get(row);
				// System.out.println("273 item: " + item);
				// col: javafx.scene.control.TableColumn@4c5575d8
				TableColumn<ContactsListTable, String> col = pos.getTableColumn();
				// System.out.println("276 col: " + col);
				// data: Rakib Hasan
				String data = col.getCellObservableValue(item).getValue();
				// System.out.println("279 data: " + data);

				// copy data to clip board
				clipContent.putString(data);
				clipboard.setContent(clipContent);

				// 3 mouse click on id column to go to EditContact Window
				TablePosition<ContactsListTable, String> firstPos = tbl.getSelectionModel().getSelectedCells().get(0);

				int dataMatchcount = dataMatch(data);
				if (clickCount(firstPos.getRow()) == 2) {
					// System.out.println("290 inside 3 click if");

					// authenticate that user clicked on same cell 3 times
					if (dataMatchcount == 2) {
						// System.out.println("294 inside same data if");
						ContactsListTable getOnlyIdData = tbl.getSelectionModel().getSelectedItem();
						if (ContactAccess.isThisIdDataExists(getOnlyIdData.getTblColId())) {
							Constraints.setIdForEditContact(getOnlyIdData.getTblColId());
							getWindow.editContact(thisStageInfo());
						}
						dataDelivered = true;
						firstDataStore = null;
						sameDataCount = 0;
						// System.out.println("302 dataDelivered: " + dataDelivered);
						// System.out.println("303 firstDataStore: " + firstDataStore);
						// System.out.println("304 sameDataCount: " + sameDataCount);
					}

					positionDelevered = true;
					counts = 0;
					firstRowStore = 0;
					// System.out.println("310 positionDelevered: " + positionDelevered);
					// System.out.println("311 counts: " + counts);
					// System.out.println("312 firstRowStore: " + firstRowStore);
				}
			}
			modal.clearSelection();
		}
	}

	// 3 mouse click on id column to go to EditContact Window
	private int clickCount(int firstRow) {
		// System.out.println("324 firstRow: " + firstRow);
		if (positionDelevered) {
			firstRowStore = firstRow;
			// System.out.println("328 firstRowStore: " + firstRowStore);
			positionDelevered = false;
			// System.out.println("326 positionDelevered: " + positionDelevered);
		} else {
			if (firstRowStore == firstRow) {
				++counts;
				// System.out.println("333 counts: " + counts);
			} else {
				positionDelevered = true;
				// System.out.println("336 positionDelevered: " + positionDelevered);
			}
		}
		return counts;
	}

	// authenticate that user clicked on same cell 3 times
	private int dataMatch(String data) {
		// System.out.println("344 data: " + data);
		if (dataDelivered) {
			firstDataStore = data;
			// System.out.println("348 data: " + data);
			dataDelivered = false;
			// System.out.println("346 dataDelivered: " + dataDelivered);
		} else {
			if (firstDataStore.equals(data)) {
				++sameDataCount;
				// System.out.println("353 sameDataCount: " + sameDataCount);
			} else {
				dataDelivered = true;
				// System.out.println("356 dataDelivered: " + dataDelivered);
			}
		}
		return sameDataCount;
	}

	// tblColId.setCellFactory(
	// new Callback<TableColumn<ContactsListTable, Integer>,
	// TableCell<ContactsListTable, Integer>>() {
	//
	// @Override
	// public TableCell<ContactsListTable, Integer>
	// call(TableColumn<ContactsListTable, Integer> param) {
	// TableCell<ContactsListTable, Integer> cell = new TableCell<ContactsListTable,
	// Integer>();
	//
	// cell.setOnMouseClicked(e -> {
	// System.out.println(cell.getText());
	// });
	// return cell;
	// }
	// });

}
