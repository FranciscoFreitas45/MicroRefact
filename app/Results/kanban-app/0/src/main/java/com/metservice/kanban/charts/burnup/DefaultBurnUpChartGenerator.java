package com.metservice.kanban.charts.burnup;
 import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.joda.time.LocalDate;
import com.google.gson.internal.Pair;
import com.metservice.kanban.charts.KanbanDrawingSupplier;
import com.metservice.kanban.charts.cumulativeflow.CumulativeFlowChartBuilder;
import com.metservice.kanban.model.EstimatesProject;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemType;
public class DefaultBurnUpChartGenerator implements BurnUpChartGenerator{

 private  ChartWriter chartWriter;

public DefaultBurnUpChartGenerator(ChartWriter chartWriter) {
    this.chartWriter = chartWriter;
}
@Override
public void generateBurnUpChart(KanbanProject project,WorkItemType type,List<WorkItem> workItems,LocalDate startDate,LocalDate currentDate,OutputStream outputStream){
    BurnUpDataModel model = new BurnUpDataModel(type, workItems, startDate, currentDate);
    CategoryDataset dataset = new BurnUpDatasetGenerator().createDataset(model);
    JFreeChart chart = createChart(dataset, project, startDate, currentDate);
    chartWriter.writeChart(outputStream, chart, 800, 600);
}


public JFreeChart createChart(CategoryDataset dataset,KanbanProject project,LocalDate startDate,LocalDate endDate){
    JFreeChart chart = ChartFactory.createStackedAreaChart(// chart title
    "Burn-Up Chart", // domain axis label
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
    plot.setDrawingSupplier(new KanbanDrawingSupplier(3));
    CumulativeFlowChartBuilder.insertJournalEntries(dataset, project, plot, startDate, endDate);
    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setLowerMargin(0.0);
    domainAxis.setUpperMargin(0.0);
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
    // change the auto tick unit selection to integer units only...
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    rangeAxis.setUpperMargin(0.12);
    return chart;
}


public JFreeChart createEstimatesChart(XYDataset dataset,KanbanProject project,EstimatesBurnDownDataModel model){
    JFreeChart chart = ChartFactory.createXYLineChart(// chart title
    "Estimates Burn-Down Chart", // domain axis label
    "$ spent", // range axis label
    "feature points", // data
    dataset, // orientation
    PlotOrientation.VERTICAL, // include legend
    true, true, false);
    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setForegroundAlpha(1f);
    plot.setBackgroundPaint(Color.WHITE);
    plot.setDomainGridlinesVisible(true);
    plot.setDomainGridlinePaint(Color.GRAY);
    plot.setRangeGridlinePaint(Color.GRAY);
    ValueAxis domainAxis = plot.getDomainAxis();
    domainAxis.setLowerMargin(0.0);
    domainAxis.setUpperMargin(0.1);
    // change the auto tick unit selection to integer units only...
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    rangeAxis.setUpperMargin(0.12);
    XYPointerAnnotation annotation = new XYPointerAnnotation("Budget", model.getBudget(), 0, -0.9);
    annotation.setLabelOffset(10);
    plot.addAnnotation(annotation);
    Pair<Integer, LocalDate> lastBudgedEntry = model.getLastBudgedEntry();
    if (lastBudgedEntry != null) {
        annotation = new XYPointerAnnotation("Last budget entry", lastBudgedEntry.first, model.getRemainingFeaturePointForBudget(lastBudgedEntry), -0.9);
        annotation.setLabelOffset(10);
        plot.addAnnotation(annotation);
    }
    annotation = new XYPointerAnnotation("Estimated budget", model.getProjectedBudgetConsumed(), 0, -0.9);
    annotation.setLabelOffset(10);
    plot.addAnnotation(annotation);
    annotation = new XYPointerAnnotation("Project start", 0, model.getAllFeaturePoints(), -0.5);
    annotation.setLabelOffset(15);
    plot.addAnnotation(annotation);
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    plot.setRenderer(renderer);
    return chart;
}


@Override
public void generateEstimatesBurnUpChart(EstimatesProject estimatesProject,List<WorkItem> workItems,OutputStream outputStream){
    EstimatesBurnDownDataModel model = new EstimatesBurnDownDataModel(workItems, estimatesProject);
    XYDataset dataset = new EstimatesBurnDownDatasetGenerator().createDataset(model);
    JFreeChart chart = createEstimatesChart(dataset, estimatesProject.getKanbanProject(), model);
    chartWriter.writeChart(outputStream, chart, 800, 600);
}


}