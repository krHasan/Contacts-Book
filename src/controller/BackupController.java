package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import controller.dialog.ConfirmDialogController;
import controller.dialog.ErrorDialogController;
import database.access.BackupAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import operation.GetDialog;
import operation.GetScence;

public class BackupController extends BackupAccess {

	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private Button btnBackup;

	@FXML
	private Button btnRestore;

	@FXML
	private Label lblBackup;

	@FXML
	private Label lblRestore;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();
	GetDialog getDialog = new GetDialog();

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		initialState();
	}

	private void initialState() {
		lblBackup.setText("Last Backup- " + getLastBackupDate());
		lblRestore.setText("Last Restored- " + getLastRestoreDate());
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) btnBackup.getScene().getWindow();
		Scene scene = (Scene) btnBackup.getScene();
		double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
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

	//////////////////////////////////////////// MainCodes////////////////////////////////////////////
	// ---------------------------------------------------------------------------------------------//
	@SuppressWarnings("resource")
	@FXML
	private void btnBackup(MouseEvent event) throws IOException {
		// data file to backup
		File sourcefile = new File("ContactsList.db");

		if (sourcefile.exists()) {
			// initiate file chooser
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose Folder to Save Data");
			fileChooser.setInitialFileName("ContactsList.db");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

			// show file chooser to select folder where to backup
			File destFile = fileChooser.showSaveDialog((Stage) btnBackup.getScene().getWindow());

			if (destFile != null) {
				FileChannel source = null;
				FileChannel destination = null;

				try {
					// copy form source file to destination folder
					source = new FileInputStream(sourcefile).getChannel();
					destination = new FileOutputStream(destFile).getChannel();
					destination.transferFrom(source, 0, source.size());

					ConfirmDialogController.contentText = "Database Backup Successfull";
					// show and wait
					getDialog.confirmDialog(thisStageInfo());
					((Scene) btnBackup.getScene()).getRoot().setEffect(null);

					// update and show backup time
					Calendar calender = GregorianCalendar.getInstance();
					setLastBackupDate(calender.getTime());
					initialState();
				} finally {
					if (source != null) {
						source.close();
					}
					if (destination != null) {
						destination.close();
					}
				}
			}
		} else {
			ErrorDialogController.headerText = "Not Found!";
			ErrorDialogController.contentText = "Database not found!";
			// show and wait
			getDialog.errorDialog(thisStageInfo());
			((Scene) btnBackup.getScene()).getRoot().setEffect(null);
		}
	}

	@SuppressWarnings("resource")
	@FXML
	private void btnRestore(MouseEvent event) throws IOException {
		// initialize the file chooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Database to Restore");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		// filter that it shows and let to open only ContactsList.db
		fileChooser.getExtensionFilters().add(new ExtensionFilter("DB File", "ContactsList.db"));
		File sourcefile = fileChooser.showOpenDialog((Stage) btnBackup.getScene().getWindow());

		if (sourcefile != null) {
			// restore db file to application folder by default
			File destFile = new File("ContactsList.db");
			if (!destFile.exists()) {
				destFile.createNewFile();
			}

			// copy file form user chooses folder to application folder
			FileChannel source = null;
			FileChannel destination = null;
			try {
				destination = new FileInputStream(destFile).getChannel();
				source = new FileOutputStream(sourcefile).getChannel();
				source.transferFrom(destination, 0, destination.size());

				ConfirmDialogController.contentText = "Database Successfuly Restored";
				// show and wait
				getDialog.confirmDialog(thisStageInfo());
				((Scene) btnBackup.getScene()).getRoot().setEffect(null);

				// update and show Restore time
				Calendar calender = GregorianCalendar.getInstance();
				setLastRestoreDate(calender.getTime());
				initialState();
			} finally {
				if (source != null) {
					source.close();
				}
				if (destination != null) {
					destination.close();
				}
			}
		}
	}

}
