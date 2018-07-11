package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContactsListStage extends Application {
	@Override
	public void start(Stage contactsListStage) {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
