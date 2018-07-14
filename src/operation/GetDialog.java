package operation;

import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GetDialog {

	public void confirmDialog(Map<String, Object> map) {
		try {
			Stage parentStage = (Stage) map.get("stage");
			parentStage.getScene().getRoot().setEffect(new BoxBlur());
			
			Stage stage = new Stage(StageStyle.UNDECORATED);
			Parent root = FXMLLoader.load(getClass().getResource("/view/dialogBox/ConfirmDialog.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initOwner(parentStage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setX(parentStage.getX() + ((parentStage.getWidth() - 550)/2));
			stage.setY(parentStage.getY() + ((parentStage.getHeight() - 180)/2));
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
