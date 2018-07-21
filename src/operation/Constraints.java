package operation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Constraints {

	public static EventHandler<WindowEvent> event;
	private static String idForEditContact = "1";

	public boolean isThereWhiteSpace(String text) {
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(text);
		boolean result = matcher.find();
		return result;
	}

	public static void setStageEvent(EventHandler<WindowEvent> eve) {
		event = eve;
	}

	public static String intToString(int id) {
		return Integer.toString(id);
	}

	public static int stringToInt(String id) {
		return Integer.parseInt(id);
	}

	public static String getIdForEditContact() {
		return idForEditContact;
	}

	public static void setIdForEditContact(String idForEditContact) {
		Constraints.idForEditContact = idForEditContact;
	}

}
