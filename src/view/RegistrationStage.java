package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationStage extends Application {
	
	@Override
	public void start(Stage registrationStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
			Scene scene = new Scene(root, 800, 600);
			registrationStage.setTitle("User Registration");
			registrationStage.setScene(scene);
			registrationStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
