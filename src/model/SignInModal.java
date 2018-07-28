package model;

import database.access.Credentials;

public class SignInModal extends Credentials {

	public boolean signIn(String username, String password) {
		return authentication(username, password);
	}

	public String securityQuestion() {
		return getSecurityQuestion();
	}

	public boolean isSecurityAnswerIsRight(String answer) {
		boolean result = false;
		if (answer.equals(getSecurityAnswer())) {
			result = true;
		}

		return result;
	}

	public boolean isUsernameIsRight(String username) {
		boolean result = false;
		if (username.equals(getUsername())) {
			result = true;
		}

		return result;
	}

	public boolean isPasswordIsRight(String pass) {
		boolean result = false;
		if (pass.equals(getPassword())) {
			result = true;
		}

		return result;
	}
}
