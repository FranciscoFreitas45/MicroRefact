package com.metservice.kanban.DTO;
 import com.metservice.kanban.utils.DateUtils.currentLocalDate;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryPointerAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.metservice.kanban.charts.ChartUtils;
import com.metservice.kanban.charts.KanbanDrawingSupplier;
import com.metservice.kanban.model.KanbanJournalItem;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.utils.Day;
import com.metservice.kanban.utils.WorkingDayUtils;
public class CumulativeFlowChartBuilder {

 private  Logger LOGGER;

 private  LocalDate startDate;

 private  LocalDate endDate;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public CumulativeFlowChartBuilder(LocalDate startDate, LocalDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
}
public String[] getPhasesInInverseOrder(List<String> phases){
    String[] invertedPhases = new String[phases.size()];
    int k = phases.size() - 1;
    for (String phase : phases) {
        invertedPhases[k--] = phase;
    }
    return invertedPhases;
}


public Day[] getListOfDays(List<LocalDate> dates){
    Day[] days = new Day[dates.size()];
    int i = 0;
    for (LocalDate date : dates) {
        days[i++] = new Day(date);
    }
    return days;
}


public CategoryDataset createDataset(List<String> phases,List<WorkItem> workItemList){
    // add start date and end date instead of local date here
    if (endDate == null) {
        endDate = currentLocalDate();
    }
    if (startDate == null) {
        startDate = ChartUtils.getFirstDate(workItemList);
        if (startDate == null) {
            startDate = endDate;
        }
    }
    CumulativeFlowChartMatrix matrix = new CumulativeFlowChartMatrix(phases, startDate, endDate);
    for (WorkItem workItem : workItemList) {
        matrix.registerWorkItem(workItem);
    }
    List<LocalDate> dates = matrix.getOrderedListOfDates();
    double[][] data = matrix.getData();
    Day[] days = getListOfDays(dates);
    return DatasetUtilities.createCategoryDataset(getPhasesInInverseOrder(phases), days, data);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createDataset"))

.queryParam("phases",phases)
.queryParam("workItemList",workItemList)
;
CategoryDataset aux = restTemplate.getForObject(builder.toUriString(),CategoryDataset.class);
return aux;
}


public JFreeChart createChart(CategoryDataset dataset,KanbanProject project){
    JFreeChart chart = ChartFactory.createStackedAreaChart(// chart title
    "Cumulative Flow Chart", // domain axis label
    "", // range axis label
    "", // data
    dataset, // orientation
    PlotOrientation.VERTICAL, // include legend
    true, true, false);
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    plot.setForegroundAlpha(1f);
    plot.setBackgroundPaint(Color.WHITE);
    plot.setDomainGridlinesVisible(true);
    plot.setDomainGridlinePaint(Color.GRAY);
    plot.setRangeGridlinePaint(Color.GRAY);
    plot.setDrawingSupplier(new KanbanDrawingSupplier(dataset.getRowCount()));
    insertJournalEntries(dataset, project, plot, ((Day) dataset.getColumnKey(0)).getDate(), ((Day) dataset.getColumnKey(dataset.getColumnCount() - 1)).getDate());
    final CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setLowerMargin(0.0);
    domainAxis.setUpperMargin(0.0);
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    // change the auto tick unit selection to integer units only...
    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    // this is to show annotations when they are above the chart
    rangeAxis.setUpperMargin(0.12);
    return chart;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createChart"))

.queryParam("dataset",dataset)
.queryParam("project",project)
;
JFreeChart aux = restTemplate.getForObject(builder.toUriString(),JFreeChart.class);
return aux;
}


}