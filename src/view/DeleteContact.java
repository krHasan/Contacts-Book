package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleteContact extends Application {
	@Override
	public void start(Stage deleteContactStage) {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}