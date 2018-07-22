package model;

import database.access.ContactAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class DashboardModal extends ContactAccess {

	public ObservableList<PieChart.Data> getPieData() {
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
				new PieChart.Data("Silver", getSilverContactAmount()),
				new PieChart.Data("Gold", getGoldContactAmount()),
				new PieChart.Data("Platinum", getPlatinumContactAmount()),
				new PieChart.Data("Special", getSpecialContactAmount()));
		return pieData;
	}

}
