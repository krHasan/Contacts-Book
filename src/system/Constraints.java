package system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Constraints {

	public static EventHandler<WindowEvent> event;

	public boolean isThereWhiteSpace(String text) {
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(text);
		boolean result = matcher.find();
		return result;
	}

	public static void setStageEvent(EventHandler<WindowEvent> eve) {
		event = eve;
	}

}
