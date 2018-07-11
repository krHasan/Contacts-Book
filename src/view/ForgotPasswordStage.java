package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ForgotPasswordStage extends Application {

	@Override
	public void start(Stage forgotPasswordStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
			Scene scene = new Scene(root, 800, 600);
			forgotPasswordStage.setTitle("Forgot Password");
			forgotPasswordStage.setScene(scene);
			forgotPasswordStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
