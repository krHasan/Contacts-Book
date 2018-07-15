package system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constraints {

	public boolean isThereWhiteSpace(String text) {
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(text);
		boolean result = matcher.find();
		return result;
	}

}
