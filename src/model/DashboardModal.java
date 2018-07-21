package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class DashboardModal {

	public ObservableList<PieChart.Data> getPieData() {
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(new PieChart.Data("Silver", 5),
				new PieChart.Data("Gold", 15), new PieChart.Data("Platinum", 30), new PieChart.Data("Special", 50));
		return pieData;
	}

}
