package com.metservice.kanban.charts;
 import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import com.metservice.kanban.model.HtmlColour;
@SuppressWarnings("serial")
public class KanbanDrawingSupplier extends DefaultDrawingSupplier{

 private  HueRebalancer HUE_REBALANCER;

 private  int numberOfSeries;

/**
 * Default constructor for KanbanDrawingSupplier.
 * @param numberOfSeries - number of different sections (colours) on chart
 */
public KanbanDrawingSupplier(int numberOfSeries) {
    this(getColours(numberOfSeries));
    this.numberOfSeries = numberOfSeries;
}/**
 * Private constructor for KanbanDrawingSupplier, given a template paintSequence.
 * @param paintSequence - the paintSequence to use when drawing
 */
private KanbanDrawingSupplier(Paint[] paintSequence) {
    super(paintSequence, paintSequence, null, null, null, null);
}
public int getNumberOfSeries(){
    return numberOfSeries;
}


public Color[] getColours(int numberOfSeries){
    Color[] colours = new Color[numberOfSeries];
    for (int i = 0; i < numberOfSeries; i++) {
        double rawHue = (double) i / (double) numberOfSeries;
        double rebalancedHue = HUE_REBALANCER.balance(rawHue);
        colours[i] = Color.getHSBColor((float) rebalancedHue, 0.75f, 1.0f);
    }
    return colours;
}


public HtmlColour[] getHtmlColours(int numberOfSeries){
    Color[] colors = getColours(numberOfSeries);
    HtmlColour[] colours = new HtmlColour[colors.length];
    for (int i = 0; i < colors.length; i++) {
        colours[i] = new HtmlColour(colors[i]);
    }
    return colours;
}


}