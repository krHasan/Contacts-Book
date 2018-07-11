package operation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GoToOperation {

	public void goToAboutDeveloper(Stage aboutDeveloperStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AboutDeveloper.fxml"));
			Scene scene = new Scene(root,800,600);
			aboutDeveloperStage.setTitle("About Developer");
			aboutDeveloperStage.setScene(scene);
			aboutDeveloperStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goToAddNewContact(Stage addNewContactStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddNewContact.fxml"));
			Scene scene = new Scene(root, 800, 600);
			addNewContactStage.setTitle("Add New Contact");
			addNewContactStage.setScene(scene);
			addNewContactStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToContactsList(Stage contactsListStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ContactsList.fxml"));
			Scene scene = new Scene(root,800,600);
			contactsListStage.setTitle("Contacts List");
			contactsListStage.setScene(scene);
			contactsListStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goToDashboard(Stage dashboardStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			Scene scene = new Scene(root, 800, 600);
			dashboardStage.setTitle("Dashboard");
			dashboardStage.setScene(scene);
			dashboardStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToDeleteContact(Stage deleteContactStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("DeleteContact.fxml"));
			Scene scene = new Scene(root,800,600);
			deleteContactStage.setTitle("Delete Contacts");
			deleteContactStage.setScene(scene);
			deleteContactStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goToEditContact(Stage editContactStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("EditContact.fxml"));
			Scene scene = new Scene(root,800,600);
			editContactStage.setTitle("Edit Contact");
			editContactStage.setScene(scene);
			editContactStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goToSignIn(Stage signInStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			Scene scene = new Scene(root,800,600);
			signInStage.setTitle("Welcome to Fashion World");
			signInStage.setScene(scene);
			signInStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goToSystemSettings(Stage systemSettingsStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SystemSettings.fxml"));
			Scene scene = new Scene(root,800,600);
			systemSettingsStage.setTitle("System Settings");
			systemSettingsStage.setScene(scene);
			systemSettingsStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
