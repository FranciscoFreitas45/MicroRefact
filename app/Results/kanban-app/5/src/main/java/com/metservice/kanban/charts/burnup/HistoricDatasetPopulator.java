package com.metservice.kanban.charts.burnup;
 import com.metservice.kanban.charts.burnup.BurnUpChartSeriesNames.BACKLOG;
import com.metservice.kanban.charts.burnup.BurnUpChartSeriesNames.COMPLETE;
import com.metservice.kanban.charts.burnup.BurnUpChartSeriesNames.IN_PROGRESS;
import org.jfree.data.category.DefaultCategoryDataset;
import org.joda.time.LocalDate;
public class HistoricDatasetPopulator {

 private  BurnUpDataModel model;

public HistoricDatasetPopulator(BurnUpDataModel model) {
    this.model = model;
}
public void populateDataset(DefaultCategoryDataset dataset){
    for (LocalDate date : model.getWorkingDays()) {
        dataset.addValue(model.getCompletedSizeOnDate(date), COMPLETE, date);
        dataset.addValue(model.getInProgressSizeOnDate(date), IN_PROGRESS, date);
        dataset.addValue(model.getBacklogSizeOnDate(date), BACKLOG, date);
    }
}


}