package com.metservice.kanban.charts.burnup;
 import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.JFreeChart;
public interface ChartWriter {


public void writeChart(OutputStream outputStream,JFreeChart chart,int width,int height)
;

}