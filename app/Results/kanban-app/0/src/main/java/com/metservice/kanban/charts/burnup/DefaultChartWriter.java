package com.metservice.kanban.charts.burnup;
 import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
public class DefaultChartWriter implements ChartWriter{


@Override
public void writeChart(OutputStream outputStream,JFreeChart chart,int width,int height){
    ChartUtilities.writeChartAsPNG(outputStream, chart, 800, 600);
}


}