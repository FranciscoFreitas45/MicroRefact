package com.metservice.kanban.charts.cycletime.CycleTimeChartBuilder;
 import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GradientBarPainter;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import com.metservice.kanban.charts.KanbanDrawingSupplier;
import com.metservice.kanban.model.WorkItem;
public class WorkItemsComparator implements Serializable,Comparator<WorkItem>{

 private  long serialVersionUID;


@Override
public int compare(WorkItem o1,WorkItem o2){
    return o1.getDate(o1.getCurrentPhase()).compareTo(o2.getDate(o2.getCurrentPhase()));
}


}