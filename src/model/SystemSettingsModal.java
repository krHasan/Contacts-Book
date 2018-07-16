package model;

import database.access.Credentials;

public class SystemSettingsModal extends Credentials{
	
	public String username() {
		return getUsername();
	}
	
	public String name() {
		return getName();
	}

	public String password() {
		return getPassword();
	}
	
	public boolean isPasswordMatched(String pass) {
		if (pass.equals(getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
}
