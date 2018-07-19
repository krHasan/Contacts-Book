package model;

import java.util.LinkedList;
import java.util.List;

import database.access.AutoCompleteData;
import enums.Contacts;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AutoComplete {

	// ------------------------GlobalVariables----------------------------
	private static ContextMenu entriesPopup = new ContextMenu();
	static AutoCompleteData fromDatabase = new AutoCompleteData();

	// ------------------------MainCode----------------------------
	public static void autocomplete(TextField txt, Contacts name) {

		String searchLetter = txt.getText();
		if (searchLetter == null || searchLetter.isEmpty()) {
			entriesPopup.hide();
		} else {
			List<String> listOfWords = entries(searchLetter, name);
			if (!listOfWords.isEmpty()) {

				populatePopup(txt, listOfWords, searchLetter);
				if (!entriesPopup.isShowing()) { // optional
					entriesPopup.show(txt, Side.BOTTOM, 0, 0); // position of popup
				}

			} else {
				entriesPopup.hide();
			}
		}

		txt.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
			entriesPopup.hide();
		});
	}

	@SuppressWarnings("unlikely-arg-type")
	private static List<String> entries(String searchLetter, Contacts name) {
		if (name.equals(Contacts.name)) {

			return AutoCompleteData.name(searchLetter);

		} else if (name.equals(Contacts.globalId)) {

			return AutoCompleteData.globalid(searchLetter);

		} else if (name.equals(Contacts.number)) {

			return AutoCompleteData.number(searchLetter);

		} else {

			return AutoCompleteData.address(searchLetter);

		}

	}

	private static void populatePopup(TextField txt, List<String> listOfWords, String searchLetter) {
		List<CustomMenuItem> menuItems = new LinkedList<>();
		// list of 5 or founded suggestion count
		int maxEntries = 5;
		int count = Math.min(listOfWords.size(), maxEntries);

		for (int i = 0; i < count; i++) {// built list as set of labels
			final String word = listOfWords.get(i);
			Label lblEntry = new Label();

			lblEntry.setGraphic(buildTextFlow(word, searchLetter));// style the searchLetter to highlight
			lblEntry.setPrefHeight(10);
			CustomMenuItem item = new CustomMenuItem(lblEntry, true);
			lblEntry.setCursor(Cursor.HAND);
			menuItems.add(item);

			item.setOnAction(actionEvent -> {
				txt.setText(word);
				entriesPopup.hide();
			});
		}
		// "Refresh" the context menu
		entriesPopup.getItems().clear();
		entriesPopup.getItems().addAll(menuItems);
	}

	private static TextFlow buildTextFlow(String word, String searchLetter) {// word = "Rakib", searchLetter ="a"
		int filterIndex = word.toLowerCase().indexOf(searchLetter.toLowerCase());// here filterIndex will be 1
		Text textBefore = new Text(word.substring(0, filterIndex));// textBefore: "R"
		Text textAfter = new Text(word.substring(filterIndex + searchLetter.length()));// textAfter: "kib"
		Text textFilter = new Text(word.substring(filterIndex, filterIndex + searchLetter.length()));// textFilter: "a";
		// styling the "a"
		textFilter.setFill(Color.DARKBLUE);
		textFilter.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
		return new TextFlow(textBefore, textFilter, textAfter);// make R,a,kib
	}

}
