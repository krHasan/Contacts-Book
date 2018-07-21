package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DashboardModal;
import operation.GetScence;

public class DashboardController extends DashboardModal {
	/////////////////////////////////// Objects////////////////////////////////
	@FXML
	private Label lblHeading;

	@FXML
	private Button btnAddNewContact;

	@FXML
	private Button btnContactsList;

	@FXML
	private Button btnDeleteContacts;

	@FXML
	private Button btnSettings;

	@FXML
	private PieChart chart;

	@FXML
	private Circle face;

	@FXML
	private Line hourHand;

	@FXML
	private Line minuteHand;

	@FXML
	private Line secondHand;

	@FXML
	private Circle spindlespindle;

	@FXML
	private Group analogueClock;

	@FXML
	private Label digitalClock;

	@FXML
	private Label brand;

	@FXML
	private Label lblSilver;

	@FXML
	private Label lblGold;

	@FXML
	private Label lblPlatinum;

	@FXML
	private Label lblSpecial;

	/////////////////////////////////// GlobalVariables////////////////////////////////
	GetScence getWindow = new GetScence();

	/////////////////////////////////// GeneralCode////////////////////////////////
	@FXML
	private void initialize() {
		clock();
		loadChart();
		loadMyChart();
	}

	private Map<String, Object> thisStageInfo() {
		Map<String, Object> map = new HashMap<>();
		Stage stage = (Stage) btnDeleteContacts.getScene().getWindow();
		Scene scene = (Scene) btnDeleteContacts.getScene();
		Double height = scene.getHeight(), width = scene.getWidth();
		map.put("stage", stage);
		map.put("height", height);
		map.put("width", width);

		return map;
	}

	//////////////////////////////////////////// MenuCodes////////////////////////////////////////////
	// -----------------------------------------------------------------------------------------------//
	@FXML
	private void mnuDashboard(ActionEvent event) {
		getWindow.dashboard(thisStageInfo());
	}

	@FXML
	private void mnuAddNewContact(ActionEvent event) {
		getWindow.addNewContact(thisStageInfo());
	}

	@FXML
	private void mnuContactsList(ActionEvent event) {
		getWindow.contactsList(thisStageInfo());
	}

	@FXML
	private void mnuDeleteContact(ActionEvent event) {
		getWindow.deleteContact(thisStageInfo());
	}

	@FXML
	private void mnuSignOut(ActionEvent event) {
		getWindow.signIn(thisStageInfo());
	}

	@FXML
	private void mnuSystemSettings(ActionEvent event) {
		getWindow.systemSettings(thisStageInfo());
	}

	@FXML
	private void mnAboutDeveloper(ActionEvent event) {
		getWindow.aboutDeveloper(thisStageInfo());
	}

	//////////////////////////////////////////// MainCode////////////////////////////////////////////
	// ------------------------------------------------------------------------------------------------//
	@FXML
	private void btnAddNewContact(MouseEvent event) {
		getWindow.addNewContact(thisStageInfo());
	}

	@FXML
	private void btnContactsList(MouseEvent event) {
		getWindow.contactsList(thisStageInfo());
	}

	@FXML
	private void btnDeleteContacts(MouseEvent event) {
		getWindow.deleteContact(thisStageInfo());
	}

	@FXML
	private void btnSettings(MouseEvent event) {
		getWindow.systemSettings(thisStageInfo());
	}

	// ------------------------PieChart----------------------------
	private void loadChart() {
		chart.setData(getPieData());
	}

	// ------------------------ClockCode----------------------------
	private void clock() {
		brand.setText("krHasan");
		Calendar calender = GregorianCalendar.getInstance();
		DateFormat formateDATEEEddMMM = new SimpleDateFormat("EEEE dd MMMM, YYYY");
		String date = formateDATEEEddMMM.format(calender.getTime());
		digitalClock.setText(date);

		// determine the starting time.
		final double seedSecondDegrees = calender.get(Calendar.SECOND) * (360 / 60);
		final double seedMinuteDegrees = (calender.get(Calendar.MINUTE) + seedSecondDegrees / 360.0) * (360 / 60);
		final double seedHourDegrees = (calender.get(Calendar.HOUR) + seedMinuteDegrees / 360.0) * (360 / 12);

		// define rotations to map the analogueClock to the current time.
		final Rotate hourRotate = new Rotate(seedHourDegrees);
		final Rotate minuteRotate = new Rotate(seedMinuteDegrees);
		final Rotate secondRotate = new Rotate(seedSecondDegrees);
		hourHand.getTransforms().add(hourRotate);
		minuteHand.getTransforms().add(minuteRotate);
		secondHand.getTransforms().add(secondRotate);

		// the hour hand rotates twice a day.
		final Timeline hourTime = new Timeline(new KeyFrame(Duration.hours(12),
				new KeyValue(hourRotate.angleProperty(), 360 + seedHourDegrees, Interpolator.LINEAR)));

		// the minute hand rotates once an hour.
		final Timeline minuteTime = new Timeline(new KeyFrame(Duration.minutes(60),
				new KeyValue(minuteRotate.angleProperty(), 360 + seedMinuteDegrees, Interpolator.LINEAR)));

		// move second hand rotates once a minute.
		final Timeline secondTime = new Timeline(new KeyFrame(Duration.seconds(60),
				new KeyValue(secondRotate.angleProperty(), 360 + seedSecondDegrees, Interpolator.LINEAR)));

		// the digital clock updates once a second.
		// final Timeline digitalTime = new Timeline(new KeyFrame(Duration.seconds(0),
		// new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(ActionEvent actionEvent) {
		// Calendar calendar = GregorianCalendar.getInstance();
		// String hourString = pad(2, '0',
		// calendar.get(Calendar.HOUR) == 0 ? "12" : calendar.get(Calendar.HOUR) + "");
		// String minuteString = pad(2, '0', calendar.get(Calendar.MINUTE) + "");
		// String secondString = pad(2, '0', calendar.get(Calendar.SECOND) + "");
		// String ampmString = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" :
		// "PM";
		// digitalClock.setText(hourString + ":" + minuteString + ":" + secondString + "
		// " + ampmString);
		// }
		// }), new KeyFrame(Duration.seconds(1)));

		// time never ends.
		hourTime.setCycleCount(Animation.INDEFINITE);
		minuteTime.setCycleCount(Animation.INDEFINITE);
		secondTime.setCycleCount(Animation.INDEFINITE);
		// digitalTime.setCycleCount(Animation.INDEFINITE);

		// start the analogueClock.
		// digitalTime.play();
		secondTime.play();
		minuteTime.play();
		hourTime.play();

		// add a glow effect whenever the mouse is positioned over the clock.
		final Glow glow = new Glow();
		analogueClock.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				analogueClock.setEffect(glow);
			}
		});
		analogueClock.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				analogueClock.setEffect(null);
			}
		});
	}

	// private String pad(int fieldWidth, char padChar, String s) {
	// StringBuilder sb = new StringBuilder();
	// for (int i = s.length(); i < fieldWidth; i++) {
	// sb.append(padChar);
	// }
	// sb.append(s);
	// return sb.toString();
	// }

	// ------------------------CustomerNumberGraphics----------------------------
	private static final double boxWidth = 375.00;

	private void loadMyChart() {
		double silverNumber = 1;
		double goldNumber = 15;
		double platinumNumber = 25;
		double specialNumber = 14;

		double total = (silverNumber + goldNumber + platinumNumber + specialNumber);

		double silverWidth = (((silverNumber / total) * 100.00) / 100.00) * boxWidth;
		double goldWidth = (((goldNumber / total) * 100.00) / 100.00) * boxWidth;
		double platinumWidth = (((platinumNumber / total) * 100.00) / 100.00) * boxWidth;
		double specialWidth = (((specialNumber / total) * 100.00) / 100.00) * boxWidth;

		System.out.println((((silverNumber / total) * 100.00) / 100.00) * boxWidth);

		lblSilver.setMinWidth(silverWidth);
		lblSilver.setPrefWidth(silverWidth);
		lblSilver.setMaxWidth(silverWidth);

		lblGold.setMinWidth(goldWidth);
		lblGold.setPrefWidth(goldWidth);
		lblGold.setMaxWidth(goldWidth);

		lblPlatinum.setMinWidth(platinumWidth);
		lblPlatinum.setPrefWidth(platinumWidth);
		lblPlatinum.setMaxWidth(platinumWidth);

		lblSpecial.setMinWidth(specialWidth);
		lblSpecial.setPrefWidth(specialWidth);
		lblSpecial.setMaxWidth(specialWidth);
	}

}
