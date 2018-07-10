package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemSettingsStage extends Application {
	@Override
	public void start(Stage systemSettingsStage) {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}

