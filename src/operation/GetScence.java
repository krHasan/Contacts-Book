package operation;

import java.util.HashMap;
import java.util.Map;

import controller.ForgotPasswordController;
import controller.RegistrationController;
import controller.dialog.WarningDialogController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import system.Constraints;

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

			// first remove the previous event
			stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);

			// create a blank new event
			EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent evnt) {
				}
			};
			stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			Constraints.setStageEvent(event);

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

			// first remove the previous event
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			// Map<String, Object> map1 = new HashMap<>();
			// map1.put("stage", stage);
			// GetDialog getDialog = new GetDialog();
			//
			// create a new event
			// EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
			// @Override
			// public void handle(WindowEvent evnt) {
			// if (RegistrationController.getInvalideted()) {
			// WarningDialogController.headerText = "Discard Changes?";
			// WarningDialogController.contentText = "Do you want to leave without
			// finishing?";
			// // show and wait
			// getDialog.warningDialog(map1);
			// scene.getRoot().setEffect(null);
			// if (WarningDialogController.btnOKpressed) {
			// stage.close();
			// } else {
			// evnt.consume();
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
			// }
			// } else {
			// stage.close();
			// }
			// }
			// };
			// stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			// Constraints.setStageEvent(event);
			//
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

			// first remove the previous event
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			// Map<String, Object> map1 = new HashMap<>();
			// map1.put("stage", stage);
			// GetDialog getDialog = new GetDialog();
			//
			// create a new event
			// EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
			// @Override
			// public void handle(WindowEvent evnt) {
			// if (RegistrationController.getInvalideted()) {
			// WarningDialogController.headerText = "Discard Changes?";
			// WarningDialogController.contentText = "Do you want to leave without
			// finishing?";
			// // show and wait
			// getDialog.warningDialog(map1);
			// scene.getRoot().setEffect(null);
			// if (WarningDialogController.btnOKpressed) {
			// stage.close();
			// } else {
			// evnt.consume();
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
			// }
			// } else {
			// stage.close();
			// }
			// }
			// };
			// stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			// Constraints.setStageEvent(event);
			//
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

			// first remove the previous event
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			// Map<String, Object> map1 = new HashMap<>();
			// map1.put("stage", stage);
			// GetDialog getDialog = new GetDialog();
			//
			// create a new event
			// EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
			// @Override
			// public void handle(WindowEvent evnt) {
			// if (RegistrationController.getInvalideted()) {
			// WarningDialogController.headerText = "Discard Changes?";
			// WarningDialogController.contentText = "Do you want to leave without
			// finishing?";
			// // show and wait
			// getDialog.warningDialog(map1);
			// scene.getRoot().setEffect(null);
			// if (WarningDialogController.btnOKpressed) {
			// stage.close();
			// } else {
			// evnt.consume();
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
			// }
			// } else {
			// stage.close();
			// }
			// }
			// };
			// stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			// Constraints.setStageEvent(event);
			//
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

			// first remove the previous event
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			// Map<String, Object> map1 = new HashMap<>();
			// map1.put("stage", stage);
			// GetDialog getDialog = new GetDialog();
			//
			// create a new event
			// EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
			// @Override
			// public void handle(WindowEvent evnt) {
			// if (RegistrationController.getInvalideted()) {
			// WarningDialogController.headerText = "Discard Changes?";
			// WarningDialogController.contentText = "Do you want to leave without
			// finishing?";
			// // show and wait
			// getDialog.warningDialog(map1);
			// scene.getRoot().setEffect(null);
			// if (WarningDialogController.btnOKpressed) {
			// stage.close();
			// } else {
			// evnt.consume();
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
			// }
			// } else {
			// stage.close();
			// }
			// }
			// };
			// stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			// Constraints.setStageEvent(event);
			//
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

			// first remove the previous event
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			// Map<String, Object> map1 = new HashMap<>();
			// map1.put("stage", stage);
			// GetDialog getDialog = new GetDialog();
			//
			// create a new event
			// EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
			// @Override
			// public void handle(WindowEvent evnt) {
			// if (RegistrationController.getInvalideted()) {
			// WarningDialogController.headerText = "Discard Changes?";
			// WarningDialogController.contentText = "Do you want to leave without
			// finishing?";
			// // show and wait
			// getDialog.warningDialog(map1);
			// scene.getRoot().setEffect(null);
			// if (WarningDialogController.btnOKpressed) {
			// stage.close();
			// } else {
			// evnt.consume();
			// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
			// }
			// } else {
			// stage.close();
			// }
			// }
			// };
			// stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			// Constraints.setStageEvent(event);
			//
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

			// first remove the previous event
			stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);

			// create a blank new event
			EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent evnt) {
				}
			};
			stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			Constraints.setStageEvent(event);

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

			// first remove the previous event
			stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);

			// create a new event
			EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent evnt) {
				}
			};
			stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			Constraints.setStageEvent(event);

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

			// first remove the previous event
			stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			Map<String, Object> map1 = new HashMap<>();
			map1.put("stage", stage);
			GetDialog getDialog = new GetDialog();

			// create a new event
			EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent evnt) {
					if (ForgotPasswordController.getInvalideted()) {
						WarningDialogController.headerText = "Discard Changes?";
						WarningDialogController.contentText = "Do you want to leave without finishing?";
						// show and wait
						getDialog.warningDialog(map1);
						scene.getRoot().setEffect(null);
						if (WarningDialogController.btnOKpressed) {
							stage.close();
						} else {
							evnt.consume();
							// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
						}
					} else {
						stage.close();
					}
				}
			};
			stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);
			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			Constraints.setStageEvent(event);

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

			// first remove the previous event
			stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, Constraints.event);
			Map<String, Object> map1 = new HashMap<>();
			map1.put("stage", stage);
			GetDialog getDialog = new GetDialog();

			// create a new event
			EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent evnt) {
					if (RegistrationController.getInvalideted()) {
						WarningDialogController.headerText = "Discard Changes?";
						WarningDialogController.contentText = "Do you want to leave without finishing?";
						// show and wait
						getDialog.warningDialog(map1);
						scene.getRoot().setEffect(null);
						if (WarningDialogController.btnOKpressed) {
							stage.close();
						} else {
							evnt.consume();
							// stage.removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this);
						}
					} else {
						stage.close();
					}
				}
			};
			stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event);

			/*
			 * send the new event to Constraints class in order to use this in the next
			 * stage which will remove this first.
			 */
			Constraints.setStageEvent(event);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
