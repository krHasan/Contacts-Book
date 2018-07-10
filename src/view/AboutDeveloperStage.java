package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutDeveloperStage extends Application {
	@Override
	public void start(Stage aboutDeveloperStage) {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
