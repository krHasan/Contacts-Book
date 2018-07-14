package model;

import databaseAccess.Credentials;

public class SignInModal extends Credentials {

	public boolean signIn(String username, String password) {
		return authentication(username, password);
	}
}
