package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddNewContactStage extends Application {
	@Override
	public void start(Stage addNewContactStage) {
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

	public static void main(String[] args) {
		launch(args);
	}

}
