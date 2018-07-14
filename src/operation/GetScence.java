package operation;

import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GetScence {

	public void aboutDeveloper(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AboutDeveloper.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("About Developer");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewContact(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewContact.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Add New Contact");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contactsList(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ContactsList.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Contacts List");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dashboard(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Dashboard");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteContact(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/DeleteContact.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Delete Contacts");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editContact(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/EditContact.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Edit Contact");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void signIn(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Welcome to Fashion World");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void systemSettings(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/SystemSettings.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("System Settings");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void forgotPassword(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ForgotPassword.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("Forgot Password");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registration(Map<String, Object> map) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
			double height = (double) map.get("height"), width = (double) map.get("width");
			Stage stage = (Stage) map.get("stage");
			Scene scene = new Scene(root, width, height);
			stage.setTitle("User Registration");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
