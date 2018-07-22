package operation;

import java.util.Map;

import controller.dialog.WarningDialogController;
import javafx.scene.Scene;

public class MenuToWindow {

	GetDialog getDialog = new GetDialog();
	GetScence getWindow = new GetScence();

	public void mnuToDashboard(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.dashboard(thisStageInfo);
			}
		} else {
			getWindow.dashboard(thisStageInfo);
		}
	}

	public void mnuToAddNewContact(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.addNewContact(thisStageInfo);
			}
		} else {
			getWindow.addNewContact(thisStageInfo);
		}
	}

	public void mnuToContactsList(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.contactsList(thisStageInfo);
			}
		} else {
			getWindow.contactsList(thisStageInfo);
		}
	}

	public void mnuToDeleteContact(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.deleteContact(thisStageInfo);
			}
		} else {
			getWindow.deleteContact(thisStageInfo);
		}
	}

	public void mnuToSignOut(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.signIn(thisStageInfo);
			}
		} else {
			getWindow.signIn(thisStageInfo);
		}
	}

	public void mnuToSystemSettings(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.systemSettings(thisStageInfo);
			}
		} else {
			getWindow.systemSettings(thisStageInfo);
		}
	}

	public void mnuToBackupAndRestore(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.backupAndRestore(thisStageInfo);
			}
		} else {
			getWindow.backupAndRestore(thisStageInfo);
		}
	}

	public void mnuAboutDeveloper(boolean invalidated, Map<String, Object> thisStageInfo) {
		if (invalidated) {
			WarningDialogController.headerText = "Discard Changes?";
			WarningDialogController.contentText = "Do you want to leave without finishing?";
			// show and wait
			getDialog.warningDialog(thisStageInfo);
			((Scene) thisStageInfo.get("scene")).getRoot().setEffect(null);
			if (WarningDialogController.btnOKpressed) {
				getWindow.aboutDeveloper(thisStageInfo);
			}
		} else {
			getWindow.aboutDeveloper(thisStageInfo);
		}
	}

}
