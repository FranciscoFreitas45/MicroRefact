package com.metservice.kanban.DTO;
 import com.metservice.kanban.charts.ChartUtils.nextWorkingDayAfter;
import com.metservice.kanban.charts.burnup.BurnUpChartSeriesNames.BACKLOG;
import com.metservice.kanban.charts.burnup.BurnUpChartSeriesNames.COMPLETE;
import org.jfree.data.category.DefaultCategoryDataset;
import org.joda.time.LocalDate;
public class ProjectedDatasetPopulator {

 private  BurnUpDataModel model;

public ProjectedDatasetPopulator(BurnUpDataModel model) {
    this.model = model;
}
public LocalDate getProjectedEndDate(){
    double totalSize = model.getTotalSizeOnDate(model.getCurrentDate());
    double completedSize = model.getCompletedSizeOnDate(model.getCurrentDate());
    int elapsedDays = model.getWorkingDays().size() - 1;
    double pointsCompletedPerDay = completedSize / elapsedDays;
    if (pointsCompletedPerDay <= 0) {
        return null;
    }
    LocalDate date = model.getCurrentDate();
    while (completedSize <= totalSize - pointsCompletedPerDay) {
        completedSize += pointsCompletedPerDay;
        date = nextWorkingDayAfter(date);
    }
    return date;
}


}