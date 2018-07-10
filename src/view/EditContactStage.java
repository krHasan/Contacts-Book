package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditContactStage extends Application {
	@Override
	public void start(Stage editContactStage) {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
