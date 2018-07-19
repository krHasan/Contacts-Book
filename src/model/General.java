package model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import operation.Constraints;

public class General {

	Constraints constrains = new Constraints();
	private static boolean mgsOk = false;
	private static boolean numOk = false;
	private static boolean passOk = false;

	public boolean textPerform(TextField txt, Label lblWarning) {

		txt.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (!newPropertyValue) { // when property is out of focused
				if (txt.getText().length() >= 2) {
					mgsOk = true;
					lblWarning.setText(null);
				} else {
					mgsOk = false;
					lblWarning.setText("Minimum 2 charecter");
				}
			}
		});

		if (!txt.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txt.getText().substring(0, 1))) {
				lblWarning.setText("1st letter can't be white space");
				txt.clear();
				mgsOk = false;
			} else {
				lblWarning.setText(null);

				if (txt.getText().length() >= 2) {
					mgsOk = true;
				} else {
					mgsOk = false;
				}
			}
		} else {
			mgsOk = false;
		}

		return mgsOk;
	}

	public boolean numberPerform(TextField txt, Label lblWarning) {

		txt.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (!newPropertyValue) { // when property is out of focused
				if (txt.getText().length() >= 1 && txt.getText().length() < 11) {
					lblWarning.setText("Minimum 11 charecter");
					numOk = false;
				}
			}
		});

		if (!txt.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txt.getText().substring(0, 1))) {
				lblWarning.setText("1st letter can't be white space");
				txt.clear();
				numOk = false;
			} else {
				lblWarning.setText(null);

				if (txt.getText().length() >= 11) {
					numOk = true;
				} else if (txt.getText().length() >= 1 && txt.getText().length() < 11) {
					numOk = false;
				}
			}
		} else {
			numOk = false;
		}

		return numOk;
	}

	public boolean passwodPerform(TextField txtPassword, Label lblWarningPassword, TextField txtReTypePassword,
			Label lblWarningReTypePassword) {

		txtPassword.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
			if (!newPropertyValue) { // when property is out of focused
				if (txtPassword.getText().length() == 1) {
					lblWarningPassword.setText("Minimum 2 charecter");
					passOk = false;
				}
			}
		});

		if (!txtPassword.getText().equals("")) {
			if (constrains.isThereWhiteSpace(txtPassword.getText())) {
				lblWarningPassword.setText("White space is not allowed");
				txtPassword.clear();
				passOk = false;
			} else {
				lblWarningPassword.setText(null);

				if (txtPassword.getText().length() == 1) {
					mgsOk = false;
				} else if (txtPassword.getText().length() >= 2) {
					mgsOk = true;
				}

				if (!txtReTypePassword.getText().equals("")) {

					if (txtReTypePassword.getText().equals(txtPassword.getText())) {
						passOk = true;
						lblWarningReTypePassword.setText(null);
					} else {
						lblWarningReTypePassword.setText("Password didn't match");
						passOk = false;
					}
				}
			}
		} else {
			passOk = false;
		}

		return passOk;
	}

	public boolean reTypePasswordPerform(TextField txtPassword, TextField txtReTypePassword,
			Label lblWarningReTypePassword) {

		if (txtPassword.getText().equals("")) {
			lblWarningReTypePassword.setText("Type Password First");
			txtReTypePassword.clear();
			passOk = false;
		} else {
			passOk = false;

			if (txtPassword.getText().length() <= txtReTypePassword.getText().length()) {
				if (!txtReTypePassword.getText().equals(txtPassword.getText())) {
					lblWarningReTypePassword.setText("Password didn't match");
					txtReTypePassword.clear();
					passOk = false;
				} else {
					passOk = true;
					lblWarningReTypePassword.setText(null);
				}
			}
		}
		return passOk;
	}

}
