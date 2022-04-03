package com.hmm.activiti.util;
 import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.activiti.bpmn.model.AssociationDirection;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.ActivitiException;
import org.activiti.image.exception.ActivitiImageException;
import org.activiti.image.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CustomProcessDiagramCanvas {

 protected  Logger LOGGER;

 protected  int ARROW_WIDTH;

 protected  int CONDITIONAL_INDICATOR_WIDTH;

 protected  int DEFAULT_INDICATOR_WIDTH;

 protected  int MARKER_WIDTH;

 protected  int FONT_SIZE;

 protected  int FONT_SPACING;

 protected  int TEXT_PADDING;

 protected  int ANNOTATION_TEXT_PADDING;

 protected  int LINE_HEIGHT;

 protected  Color TASK_BOX_COLOR;

 protected  Color SUBPROCESS_BOX_COLOR;

 protected  Color EVENT_COLOR;

 protected  Color CONNECTION_COLOR;

 protected  Color CONDITIONAL_INDICATOR_COLOR;

 protected  Color HIGHLIGHT_COLOR;

 protected  Color LABEL_COLOR;

 protected  Color TASK_BORDER_COLOR;

 protected  Color EVENT_BORDER_COLOR;

 protected  Color SUBPROCESS_BORDER_COLOR;

 protected  Font LABEL_FONT;

 protected  Font ANNOTATION_FONT;

 protected  Font TASK_FONT;

 protected  Stroke THICK_TASK_BORDER_STROKE;

 protected  Stroke GATEWAY_TYPE_STROKE;

 protected  Stroke END_EVENT_STROKE;

 protected  Stroke MULTI_INSTANCE_STROKE;

 protected  Stroke EVENT_SUBPROCESS_STROKE;

 protected  Stroke NON_INTERRUPTING_EVENT_STROKE;

 protected  Stroke HIGHLIGHT_FLOW_STROKE;

 protected  Stroke ANNOTATION_STROKE;

 protected  Stroke ASSOCIATION_STROKE;

 protected  int ICON_PADDING;

 protected  BufferedImage USERTASK_IMAGE;

 protected  BufferedImage SCRIPTTASK_IMAGE;

 protected  BufferedImage SERVICETASK_IMAGE;

 protected  BufferedImage RECEIVETASK_IMAGE;

 protected  BufferedImage SENDTASK_IMAGE;

 protected  BufferedImage MANUALTASK_IMAGE;

 protected  BufferedImage BUSINESS_RULE_TASK_IMAGE;

 protected  BufferedImage SHELL_TASK_IMAGE;

 protected  BufferedImage MULE_TASK_IMAGE;

 protected  BufferedImage CAMEL_TASK_IMAGE;

 protected  BufferedImage TIMER_IMAGE;

 protected  BufferedImage COMPENSATE_THROW_IMAGE;

 protected  BufferedImage COMPENSATE_CATCH_IMAGE;

 protected  BufferedImage ERROR_THROW_IMAGE;

 protected  BufferedImage ERROR_CATCH_IMAGE;

 protected  BufferedImage MESSAGE_THROW_IMAGE;

 protected  BufferedImage MESSAGE_CATCH_IMAGE;

 protected  BufferedImage SIGNAL_CATCH_IMAGE;

 protected  BufferedImage SIGNAL_THROW_IMAGE;

 protected  int canvasWidth;

 protected  int canvasHeight;

 protected  int minX;

 protected  int minY;

 protected  BufferedImage processDiagram;

 protected  Graphics2D g;

 protected  FontMetrics fontMetrics;

 protected  boolean closed;

 protected  ClassLoader customClassLoader;

 protected  String activityFontName;

 protected  String labelFontName;

/**
 * Creates an empty canvas with given width and height. Allows to specify minimal boundaries on the left and upper side of the canvas. This is useful for diagrams that have
 * white space there. Everything beneath these minimum values will be cropped. It's also possible to pass a specific font name and a class loader for the icon images.
 */
public CustomProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, ClassLoader customClassLoader) {
    this.canvasWidth = width;
    this.canvasHeight = height;
    this.minX = minX;
    this.minY = minY;
    if (activityFontName != null) {
        this.activityFontName = activityFontName;
    }
    if (labelFontName != null) {
        this.labelFontName = labelFontName;
    }
    this.customClassLoader = customClassLoader;
    initialize(imageType);
}/**
 * Creates an empty canvas with given width and height. Allows to specify minimal boundaries on the left and upper side of the canvas. This is useful for diagrams that have
 * white space there (eg Signavio). Everything beneath these minimum values will be cropped.
 *
 * @param minX
 *            Hint that will be used when generating the image. Parts that fall below minX on the horizontal scale will be cropped.
 * @param minY
 *            Hint that will be used when generating the image. Parts that fall below minX on the horizontal scale will be cropped.
 */
public CustomProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType) {
    this.canvasWidth = width;
    this.canvasHeight = height;
    this.minX = minX;
    this.minY = minY;
    initialize(imageType);
}
public void drawUserTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(USERTASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawCatchingErrorEvent(GraphicInfo graphicInfo,boolean isInterrupting,double scaleFactor){
    drawCatchingEvent(graphicInfo, isInterrupting, ERROR_CATCH_IMAGE, "error", scaleFactor);
}


public void drawSendTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(SENDTASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawParallelGateway(GraphicInfo graphicInfo,double scaleFactor){
    // rhombus
    drawGateway(graphicInfo);
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    if (scaleFactor == 1.0) {
        // plus inside rhombus
        Stroke orginalStroke = g.getStroke();
        g.setStroke(GATEWAY_TYPE_STROKE);
        // horizontal
        Line2D.Double line = new Line2D.Double(x + 10, y + height / 2, x + width - 10, y + height / 2);
        g.draw(line);
        // vertical
        line = new Line2D.Double(x + width / 2, y + height - 10, x + width / 2, y + 10);
        g.draw(line);
        g.setStroke(orginalStroke);
    }
}


public void drawStartEvent(GraphicInfo graphicInfo,BufferedImage image,double scaleFactor){
    Paint originalPaint = g.getPaint();
    g.setPaint(EVENT_COLOR);
    Ellipse2D circle = new Ellipse2D.Double(graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight());
    g.fill(circle);
    g.setPaint(EVENT_BORDER_COLOR);
    g.draw(circle);
    g.setPaint(originalPaint);
    if (image != null) {
        // calculate coordinates to center image
        int imageX = (int) Math.round(graphicInfo.getX() + (graphicInfo.getWidth() / 2) - (image.getWidth() / 2 * scaleFactor));
        int imageY = (int) Math.round(graphicInfo.getY() + (graphicInfo.getHeight() / 2) - (image.getHeight() / 2 * scaleFactor));
        g.drawImage(image, imageX, imageY, (int) (image.getWidth() / scaleFactor), (int) (image.getHeight() / scaleFactor), null);
    }
}


public void drawThrowingSignalEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawCatchingEvent(graphicInfo, true, SIGNAL_THROW_IMAGE, "signal", scaleFactor);
}


public void drawSignalStartEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawStartEvent(graphicInfo, SIGNAL_CATCH_IMAGE, scaleFactor);
}


public BufferedImage generateBufferedImage(String imageType){
    if (closed) {
        throw new ActivitiImageException("ProcessDiagramGenerator already closed");
    }
    // Try to remove white space
    minX = (minX <= 5) ? 5 : minX;
    minY = (minY <= 5) ? 5 : minY;
    BufferedImage imageToSerialize = processDiagram;
    if (minX >= 0 && minY >= 0) {
        imageToSerialize = processDiagram.getSubimage(minX - 5, minY - 5, canvasWidth - minX + 5, canvasHeight - minY + 5);
    }
    return imageToSerialize;
}


public void drawReceiveTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(RECEIVETASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawCatchingCompensateEvent(GraphicInfo graphicInfo,boolean isInterrupting,double scaleFactor){
    drawCatchingEvent(graphicInfo, isInterrupting, COMPENSATE_CATCH_IMAGE, "compensate", scaleFactor);
}


public void drawActivityMarkers(int x,int y,int width,int height,boolean multiInstanceSequential,boolean multiInstanceParallel,boolean collapsed){
    if (collapsed) {
        if (!multiInstanceSequential && !multiInstanceParallel) {
            drawCollapsedMarker(x, y, width, height);
        } else {
            drawCollapsedMarker(x - MARKER_WIDTH / 2 - 2, y, width, height);
            if (multiInstanceSequential) {
                drawMultiInstanceMarker(true, x + MARKER_WIDTH / 2 + 2, y, width, height);
            } else {
                drawMultiInstanceMarker(false, x + MARKER_WIDTH / 2 + 2, y, width, height);
            }
        }
    } else {
        if (multiInstanceSequential) {
            drawMultiInstanceMarker(true, x, y, width, height);
        } else if (multiInstanceParallel) {
            drawMultiInstanceMarker(false, x, y, width, height);
        }
    }
}


public void drawCatchingTimerEvent(GraphicInfo graphicInfo,boolean isInterrupting,double scaleFactor){
    drawCatchingEvent(graphicInfo, isInterrupting, TIMER_IMAGE, "timer", scaleFactor);
}


public void drawCollapsedCallActivity(String name,GraphicInfo graphicInfo){
    drawCollapsedTask(name, graphicInfo, true);
}


public void drawHighLight(int x,int y,int width,int height){
    Paint originalPaint = g.getPaint();
    Stroke originalStroke = g.getStroke();
    g.setPaint(HIGHLIGHT_COLOR);
    g.setStroke(THICK_TASK_BORDER_STROKE);
    RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20, 20);
    g.draw(rect);
    g.setPaint(originalPaint);
    g.setStroke(originalStroke);
}


public Point getShapeIntersection(Shape shape,Line2D.Double line){
    PathIterator it = shape.getPathIterator(null);
    double[] coords = new double[6];
    double[] pos = new double[2];
    Line2D.Double l = new Line2D.Double();
    while (!it.isDone()) {
        int type = it.currentSegment(coords);
        switch(type) {
            case PathIterator.SEG_MOVETO:
                pos[0] = coords[0];
                pos[1] = coords[1];
                break;
            case PathIterator.SEG_LINETO:
                l = new Line2D.Double(pos[0], pos[1], coords[0], coords[1]);
                if (line.intersectsLine(l)) {
                    return getLinesIntersection(line, l);
                }
                pos[0] = coords[0];
                pos[1] = coords[1];
                break;
            case PathIterator.SEG_CLOSE:
                break;
            default:
        }
        it.next();
    }
    return null;
}


public void drawErrorEndEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawNoneEndEvent(graphicInfo, scaleFactor);
    g.drawImage(ERROR_THROW_IMAGE, (int) (graphicInfo.getX() + (graphicInfo.getWidth() / 4)), (int) (graphicInfo.getY() + (graphicInfo.getHeight() / 4)), (int) (ERROR_THROW_IMAGE.getWidth() / scaleFactor), (int) (ERROR_THROW_IMAGE.getHeight() / scaleFactor), null);
}


public void drawCatchingEvent(GraphicInfo graphicInfo,boolean isInterrupting,BufferedImage image,String eventType,double scaleFactor){
    // event circles
    Ellipse2D outerCircle = new Ellipse2D.Double(graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight());
    int innerCircleSize = (int) (4 / scaleFactor);
    if (innerCircleSize == 0) {
        innerCircleSize = 1;
    }
    int innerCircleX = (int) graphicInfo.getX() + innerCircleSize;
    int innerCircleY = (int) graphicInfo.getY() + innerCircleSize;
    int innerCircleWidth = (int) graphicInfo.getWidth() - (2 * innerCircleSize);
    int innerCircleHeight = (int) graphicInfo.getHeight() - (2 * innerCircleSize);
    Ellipse2D innerCircle = new Ellipse2D.Double(innerCircleX, innerCircleY, innerCircleWidth, innerCircleHeight);
    Paint originalPaint = g.getPaint();
    Stroke originalStroke = g.getStroke();
    g.setPaint(EVENT_COLOR);
    g.fill(outerCircle);
    g.setPaint(EVENT_BORDER_COLOR);
    if (isInterrupting == false)
        g.setStroke(NON_INTERRUPTING_EVENT_STROKE);
    g.draw(outerCircle);
    g.setStroke(originalStroke);
    g.setPaint(originalPaint);
    g.draw(innerCircle);
    if (image != null) {
        // calculate coordinates to center image
        int imageX = (int) (graphicInfo.getX() + (graphicInfo.getWidth() / 2) - (image.getWidth() / 2 * scaleFactor));
        int imageY = (int) (graphicInfo.getY() + (graphicInfo.getHeight() / 2) - (image.getHeight() / 2 * scaleFactor));
        if (scaleFactor == 1.0 && "timer".equals(eventType)) {
            // move image one pixel to center timer image
            imageX++;
            imageY++;
        }
        g.drawImage(image, imageX, imageY, (int) (image.getWidth() / scaleFactor), (int) (image.getHeight() / scaleFactor), null);
    }
}


public void drawEventBasedGateway(GraphicInfo graphicInfo,double scaleFactor){
    // rhombus
    drawGateway(graphicInfo);
    if (scaleFactor == 1.0) {
        int x = (int) graphicInfo.getX();
        int y = (int) graphicInfo.getY();
        int width = (int) graphicInfo.getWidth();
        int height = (int) graphicInfo.getHeight();
        double scale = .6;
        GraphicInfo eventInfo = new GraphicInfo();
        eventInfo.setX(x + width * (1 - scale) / 2);
        eventInfo.setY(y + height * (1 - scale) / 2);
        eventInfo.setWidth(width * scale);
        eventInfo.setHeight(height * scale);
        drawCatchingEvent(eventInfo, true, null, "eventGateway", scaleFactor);
        double r = width / 6.;
        // create pentagon (coords with respect to center)
        // top right corner
        int topX = (int) (.95 * r);
        int topY = (int) (-.31 * r);
        // bottom right corner
        int bottomX = (int) (.59 * r);
        int bottomY = (int) (.81 * r);
        int[] xPoints = new int[] { 0, topX, bottomX, -bottomX, -topX };
        int[] yPoints = new int[] { -(int) r, topY, bottomY, bottomY, topY };
        Polygon pentagon = new Polygon(xPoints, yPoints, 5);
        pentagon.translate(x + width / 2, y + width / 2);
        // draw
        g.drawPolygon(pentagon);
    }
}


public void drawCollapsedTask(String name,GraphicInfo graphicInfo,boolean thickBorder){
    // The collapsed marker is now visualized separately
    drawTask(name, graphicInfo, thickBorder);
}


public void drawExpandedSubProcess(String name,GraphicInfo graphicInfo,Boolean isTriggeredByEvent,double scaleFactor){
    RoundRectangle2D rect = new RoundRectangle2D.Double(graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight(), 8, 8);
    // Use different stroke (dashed)
    if (isTriggeredByEvent) {
        Stroke originalStroke = g.getStroke();
        g.setStroke(EVENT_SUBPROCESS_STROKE);
        g.draw(rect);
        g.setStroke(originalStroke);
    } else {
        Paint originalPaint = g.getPaint();
        g.setPaint(SUBPROCESS_BOX_COLOR);
        g.fill(rect);
        g.setPaint(SUBPROCESS_BORDER_COLOR);
        g.draw(rect);
        g.setPaint(originalPaint);
    }
    if (scaleFactor == 1.0 && name != null && !name.isEmpty()) {
        String text = fitTextToWidth(name, (int) graphicInfo.getWidth());
        g.drawString(text, (int) graphicInfo.getX() + 10, (int) graphicInfo.getY() + 15);
    }
}


public void drawSequenceflowWithoutArrow(int srcX,int srcY,int targetX,int targetY,boolean conditional,boolean highLighted,double scaleFactor){
    Paint originalPaint = g.getPaint();
    if (highLighted)
        g.setPaint(HIGHLIGHT_COLOR);
    Line2D.Double line = new Line2D.Double(srcX, srcY, targetX, targetY);
    g.draw(line);
    if (conditional) {
        drawConditionalSequenceFlowIndicator(line, scaleFactor);
    }
    if (highLighted)
        g.setPaint(originalPaint);
}


public void drawCollapsedMarker(int x,int y,int width,int height){
    // rectangle
    int rectangleWidth = MARKER_WIDTH;
    int rectangleHeight = MARKER_WIDTH;
    Rectangle rect = new Rectangle(x + (width - rectangleWidth) / 2, y + height - rectangleHeight - 3, rectangleWidth, rectangleHeight);
    g.draw(rect);
    // plus inside rectangle
    Line2D.Double line = new Line2D.Double(rect.getCenterX(), rect.getY() + 2, rect.getCenterX(), rect.getMaxY() - 2);
    g.draw(line);
    line = new Line2D.Double(rect.getMinX() + 2, rect.getCenterY(), rect.getMaxX() - 2, rect.getCenterY());
    g.draw(line);
}


public void drawCatchingMessageEvent(String name,GraphicInfo graphicInfo,boolean isInterrupting,double scaleFactor){
    drawCatchingEvent(graphicInfo, isInterrupting, MESSAGE_CATCH_IMAGE, "message", scaleFactor);
    drawLabel(name, graphicInfo);
}


public void drawMultilineAnnotationText(String text,int x,int y,int boxWidth,int boxHeight){
    drawMultilineText(text, x, y, boxWidth, boxHeight, false);
}


public void drawPoolOrLane(String name,GraphicInfo graphicInfo){
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    g.drawRect(x, y, width, height);
    // Add the name as text, vertical
    if (name != null && name.length() > 0) {
        // Include some padding
        int availableTextSpace = height - 6;
        // Create rotation for derived font
        AffineTransform transformation = new AffineTransform();
        transformation.setToIdentity();
        transformation.rotate(270 * Math.PI / 180);
        Font currentFont = g.getFont();
        Font theDerivedFont = currentFont.deriveFont(transformation);
        g.setFont(theDerivedFont);
        String truncated = fitTextToWidth(name, availableTextSpace);
        int realWidth = fontMetrics.stringWidth(truncated);
        g.drawString(truncated, x + 2 + fontMetrics.getHeight(), 3 + y + availableTextSpace - (availableTextSpace - realWidth) / 2);
        g.setFont(currentFont);
    }
}


public void drawMultilineCentredText(String text,int x,int y,int boxWidth,int boxHeight){
    drawMultilineText(text, x, y, boxWidth, boxHeight, true);
}


public void drawLabel(String text,GraphicInfo graphicInfo,boolean centered){
    float interline = 1.0f;
    // text
    if (text != null && text.length() > 0) {
        Paint originalPaint = g.getPaint();
        Font originalFont = g.getFont();
        g.setPaint(LABEL_COLOR);
        g.setFont(LABEL_FONT);
        int wrapWidth = 100;
        int textY = (int) (graphicInfo.getY() + graphicInfo.getHeight());
        // TODO: use drawMultilineText()
        AttributedString as = new AttributedString(text);
        as.addAttribute(TextAttribute.FOREGROUND, g.getPaint());
        as.addAttribute(TextAttribute.FONT, g.getFont());
        AttributedCharacterIterator aci = as.getIterator();
        FontRenderContext frc = new FontRenderContext(null, true, false);
        LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
        while (lbm.getPosition() < text.length()) {
            TextLayout tl = lbm.nextLayout(wrapWidth);
            textY += tl.getAscent();
            Rectangle2D bb = tl.getBounds();
            double tX = graphicInfo.getX();
            if (centered)
                tX += (int) (graphicInfo.getWidth() / 2 - bb.getWidth() / 2);
            tl.draw(g, (float) tX, textY);
            textY += tl.getDescent() + tl.getLeading() + (interline - 1.0f) * tl.getAscent();
        }
        // restore originals
        g.setFont(originalFont);
        g.setPaint(originalPaint);
    }
}


public void drawInclusiveGateway(GraphicInfo graphicInfo,double scaleFactor){
    // rhombus
    drawGateway(graphicInfo);
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    int diameter = width / 2;
    if (scaleFactor == 1.0) {
        // circle inside rhombus
        Stroke orginalStroke = g.getStroke();
        g.setStroke(GATEWAY_TYPE_STROKE);
        Ellipse2D.Double circle = new Ellipse2D.Double(((width - diameter) / 2) + x, ((height - diameter) / 2) + y, diameter, diameter);
        g.draw(circle);
        g.setStroke(orginalStroke);
    }
}


public void drawCamelTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(CAMEL_TASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawMultiInstanceMarker(boolean sequential,int x,int y,int width,int height){
    int rectangleWidth = MARKER_WIDTH;
    int rectangleHeight = MARKER_WIDTH;
    int lineX = x + (width - rectangleWidth) / 2;
    int lineY = y + height - rectangleHeight - 3;
    Stroke orginalStroke = g.getStroke();
    g.setStroke(MULTI_INSTANCE_STROKE);
    if (sequential) {
        g.draw(new Line2D.Double(lineX, lineY, lineX + rectangleWidth, lineY));
        g.draw(new Line2D.Double(lineX, lineY + rectangleHeight / 2, lineX + rectangleWidth, lineY + rectangleHeight / 2));
        g.draw(new Line2D.Double(lineX, lineY + rectangleHeight, lineX + rectangleWidth, lineY + rectangleHeight));
    } else {
        g.draw(new Line2D.Double(lineX, lineY, lineX, lineY + rectangleHeight));
        g.draw(new Line2D.Double(lineX + rectangleWidth / 2, lineY, lineX + rectangleWidth / 2, lineY + rectangleHeight));
        g.draw(new Line2D.Double(lineX + rectangleWidth, lineY, lineX + rectangleWidth, lineY + rectangleHeight));
    }
    g.setStroke(orginalStroke);
}


public void drawExclusiveGateway(GraphicInfo graphicInfo,double scaleFactor){
    // rhombus
    drawGateway(graphicInfo);
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    int quarterWidth = width / 4;
    int quarterHeight = height / 4;
    if (scaleFactor == 1.0) {
        // X inside rhombus
        Stroke orginalStroke = g.getStroke();
        g.setStroke(GATEWAY_TYPE_STROKE);
        Line2D.Double line = new Line2D.Double(x + quarterWidth + 3, y + quarterHeight + 3, x + 3 * quarterWidth - 3, y + 3 * quarterHeight - 3);
        g.draw(line);
        line = new Line2D.Double(x + quarterWidth + 3, y + 3 * quarterHeight - 3, x + 3 * quarterWidth - 3, y + quarterHeight + 3);
        g.draw(line);
        g.setStroke(orginalStroke);
    }
}


public void drawDefaultSequenceFlowIndicator(Line2D.Double line,double scaleFactor){
    double length = DEFAULT_INDICATOR_WIDTH / scaleFactor, halfOfLength = length / 2, f = 8;
    Line2D.Double defaultIndicator = new Line2D.Double(-halfOfLength, 0, halfOfLength, 0);
    double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
    double dx = f * Math.cos(angle), dy = f * Math.sin(angle), x1 = line.x1 + dx, y1 = line.y1 + dy;
    AffineTransform transformation = new AffineTransform();
    transformation.setToIdentity();
    transformation.translate(x1, y1);
    transformation.rotate((angle - 3 * Math.PI / 4));
    AffineTransform originalTransformation = g.getTransform();
    g.setTransform(transformation);
    g.draw(defaultIndicator);
    g.setTransform(originalTransformation);
}


public void drawMultilineText(String text,int x,int y,int boxWidth,int boxHeight,boolean centered){
    // Create an attributed string based in input text
    AttributedString attributedString = new AttributedString(text);
    attributedString.addAttribute(TextAttribute.FONT, g.getFont());
    attributedString.addAttribute(TextAttribute.FOREGROUND, Color.black);
    AttributedCharacterIterator characterIterator = attributedString.getIterator();
    int currentHeight = 0;
    // Prepare a list of lines of text we'll be drawing
    List<TextLayout> layouts = new ArrayList<TextLayout>();
    String lastLine = null;
    LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, g.getFontRenderContext());
    TextLayout layout = null;
    while (measurer.getPosition() < characterIterator.getEndIndex() && currentHeight <= boxHeight) {
        int previousPosition = measurer.getPosition();
        // Request next layout
        layout = measurer.nextLayout(boxWidth);
        int height = ((Float) (layout.getDescent() + layout.getAscent() + layout.getLeading())).intValue();
        if (currentHeight + height > boxHeight) {
            // The line we're about to add should NOT be added anymore, append three dots to previous one instead
            // to indicate more text is truncated
            if (!layouts.isEmpty()) {
                layouts.remove(layouts.size() - 1);
                if (lastLine.length() >= 4) {
                    lastLine = lastLine.substring(0, lastLine.length() - 4) + "...";
                }
                layouts.add(new TextLayout(lastLine, g.getFont(), g.getFontRenderContext()));
            }
        } else {
            layouts.add(layout);
            lastLine = text.substring(previousPosition, measurer.getPosition());
            currentHeight += height;
        }
    }
    int currentY = y + (centered ? ((boxHeight - currentHeight) / 2) : 0);
    int currentX = 0;
    // Actually draw the lines
    for (TextLayout textLayout : layouts) {
        currentY += textLayout.getAscent();
        currentX = x + (centered ? ((boxWidth - ((Double) textLayout.getBounds().getWidth()).intValue()) / 2) : 0);
        textLayout.draw(g, currentX, currentY);
        currentY += textLayout.getDescent() + textLayout.getLeading();
    }
}


public void drawTextAnnotation(String text,GraphicInfo graphicInfo){
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    Font originalFont = g.getFont();
    Stroke originalStroke = g.getStroke();
    g.setFont(ANNOTATION_FONT);
    Path2D path = new Path2D.Double();
    x += .5;
    int lineLength = 18;
    path.moveTo(x + lineLength, y);
    path.lineTo(x, y);
    path.lineTo(x, y + height);
    path.lineTo(x + lineLength, y + height);
    path.lineTo(x + lineLength, y + height - 1);
    path.lineTo(x + 1, y + height - 1);
    path.lineTo(x + 1, y + 1);
    path.lineTo(x + lineLength, y + 1);
    path.closePath();
    g.draw(path);
    int boxWidth = width - (2 * ANNOTATION_TEXT_PADDING);
    int boxHeight = height - (2 * ANNOTATION_TEXT_PADDING);
    int boxX = x + width / 2 - boxWidth / 2;
    int boxY = y + height / 2 - boxHeight / 2;
    if (text != null && text.isEmpty() == false) {
        drawMultilineAnnotationText(text, boxX, boxY, boxWidth, boxHeight);
    }
    // restore originals
    g.setFont(originalFont);
    g.setStroke(originalStroke);
}


public void drawCollapsedSubProcess(String name,GraphicInfo graphicInfo,Boolean isTriggeredByEvent){
    drawCollapsedTask(name, graphicInfo, false);
}


public void drawSequenceflow(int[] xPoints,int[] yPoints,boolean conditional,boolean isDefault,boolean highLighted,double scaleFactor){
    drawConnection(xPoints, yPoints, conditional, isDefault, "sequenceFlow", AssociationDirection.ONE, highLighted, scaleFactor);
}


public Point getLinesIntersection(Line2D a,Line2D b){
    double d = (a.getX1() - a.getX2()) * (b.getY2() - b.getY1()) - (a.getY1() - a.getY2()) * (b.getX2() - b.getX1());
    double da = (a.getX1() - b.getX1()) * (b.getY2() - b.getY1()) - (a.getY1() - b.getY1()) * (b.getX2() - b.getX1());
    // double db = (a.getX1()-a.getX2())*(a.getY1()-b.getY1()) - (a.getY1()-a.getY2())*(a.getX1()-b.getX1());
    double ta = da / d;
    // double tb = db/d;
    Point p = new Point();
    p.setLocation(a.getX1() + ta * (a.getX2() - a.getX1()), a.getY1() + ta * (a.getY2() - a.getY1()));
    return p;
}


public List<GraphicInfo> connectionPerfectionizer(SHAPE_TYPE sourceShapeType,SHAPE_TYPE targetShapeType,GraphicInfo sourceGraphicInfo,GraphicInfo targetGraphicInfo,List<GraphicInfo> graphicInfoList){
    Shape shapeFirst = createShape(sourceShapeType, sourceGraphicInfo);
    Shape shapeLast = createShape(targetShapeType, targetGraphicInfo);
    if (graphicInfoList != null && graphicInfoList.size() > 0) {
        GraphicInfo graphicInfoFirst = graphicInfoList.get(0);
        GraphicInfo graphicInfoLast = graphicInfoList.get(graphicInfoList.size() - 1);
        if (shapeFirst != null) {
            graphicInfoFirst.setX(shapeFirst.getBounds2D().getCenterX());
            graphicInfoFirst.setY(shapeFirst.getBounds2D().getCenterY());
        }
        if (shapeLast != null) {
            graphicInfoLast.setX(shapeLast.getBounds2D().getCenterX());
            graphicInfoLast.setY(shapeLast.getBounds2D().getCenterY());
        }
        Point p = null;
        if (shapeFirst != null) {
            Line2D.Double lineFirst = new Line2D.Double(graphicInfoFirst.getX(), graphicInfoFirst.getY(), graphicInfoList.get(1).getX(), graphicInfoList.get(1).getY());
            p = getIntersection(shapeFirst, lineFirst);
            if (p != null) {
                graphicInfoFirst.setX(p.getX());
                graphicInfoFirst.setY(p.getY());
            }
        }
        if (shapeLast != null) {
            Line2D.Double lineLast = new Line2D.Double(graphicInfoLast.getX(), graphicInfoLast.getY(), graphicInfoList.get(graphicInfoList.size() - 2).getX(), graphicInfoList.get(graphicInfoList.size() - 2).getY());
            p = getIntersection(shapeLast, lineLast);
            if (p != null) {
                graphicInfoLast.setX(p.getX());
                graphicInfoLast.setY(p.getY());
            }
        }
    }
    return graphicInfoList;
}


public void drawThrowingCompensateEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawCatchingEvent(graphicInfo, true, COMPENSATE_THROW_IMAGE, "compensate", scaleFactor);
}


public void drawBusinessRuleTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(BUSINESS_RULE_TASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawMessageStartEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawStartEvent(graphicInfo, MESSAGE_CATCH_IMAGE, scaleFactor);
}


public InputStream generateImage(String imageType){
    if (closed) {
        throw new ActivitiImageException("ProcessDiagramGenerator already closed");
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
        // Try to remove white space
        minX = (minX <= 5) ? 5 : minX;
        minY = (minY <= 5) ? 5 : minY;
        BufferedImage imageToSerialize = processDiagram;
        if (minX >= 0 && minY >= 0) {
            imageToSerialize = processDiagram.getSubimage(minX - 5, minY - 5, canvasWidth - minX + 5, canvasHeight - minY + 5);
        }
        ImageIO.write(imageToSerialize, imageType, out);
    } catch (IOException e) {
        throw new ActivitiImageException("Error while generating process image", e);
    } finally {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException ignore) {
        // Exception is silently ignored
        }
    }
    return new ByteArrayInputStream(out.toByteArray());
}


public void close(){
    g.dispose();
    closed = true;
}


public void drawServiceTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(SERVICETASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawConditionalSequenceFlowIndicator(Line2D.Double line,double scaleFactor){
    if (scaleFactor > 1.0)
        return;
    int horizontal = (int) (CONDITIONAL_INDICATOR_WIDTH * 0.7);
    int halfOfHorizontal = horizontal / 2;
    int halfOfVertical = CONDITIONAL_INDICATOR_WIDTH / 2;
    Polygon conditionalIndicator = new Polygon();
    conditionalIndicator.addPoint(0, 0);
    conditionalIndicator.addPoint(-halfOfHorizontal, halfOfVertical);
    conditionalIndicator.addPoint(0, CONDITIONAL_INDICATOR_WIDTH);
    conditionalIndicator.addPoint(halfOfHorizontal, halfOfVertical);
    AffineTransform transformation = new AffineTransform();
    transformation.setToIdentity();
    double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
    transformation.translate(line.x1, line.y1);
    transformation.rotate((angle - Math.PI / 2d));
    AffineTransform originalTransformation = g.getTransform();
    g.setTransform(transformation);
    g.draw(conditionalIndicator);
    Paint originalPaint = g.getPaint();
    g.setPaint(CONDITIONAL_INDICATOR_COLOR);
    g.fill(conditionalIndicator);
    g.setPaint(originalPaint);
    g.setTransform(originalTransformation);
}


public void drawTask(String name,GraphicInfo graphicInfo,boolean thickBorder){
    Paint originalPaint = g.getPaint();
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    // Create a new gradient paint for every task box, gradient depends on x and y and is not relative
    g.setPaint(TASK_BOX_COLOR);
    int arcR = 6;
    if (thickBorder)
        arcR = 3;
    // shape
    RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, arcR, arcR);
    g.fill(rect);
    g.setPaint(TASK_BORDER_COLOR);
    if (thickBorder) {
        Stroke originalStroke = g.getStroke();
        g.setStroke(THICK_TASK_BORDER_STROKE);
        g.draw(rect);
        g.setStroke(originalStroke);
    } else {
        g.draw(rect);
    }
    g.setPaint(originalPaint);
    // text
    if (name != null && name.length() > 0) {
        int boxWidth = width - (2 * TEXT_PADDING);
        int boxHeight = height - 16 - ICON_PADDING - ICON_PADDING - MARKER_WIDTH - 2 - 2;
        int boxX = x + width / 2 - boxWidth / 2;
        int boxY = y + height / 2 - boxHeight / 2 + ICON_PADDING + ICON_PADDING - 2 - 2;
        drawMultilineCentredText(name, boxX, boxY, boxWidth, boxHeight);
    }
}


public Shape createShape(SHAPE_TYPE shapeType,GraphicInfo graphicInfo){
    if (SHAPE_TYPE.Rectangle.equals(shapeType)) {
        // source is rectangle
        return new Rectangle2D.Double(graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight());
    } else if (SHAPE_TYPE.Rhombus.equals(shapeType)) {
        // source is rhombus
        Path2D.Double rhombus = new Path2D.Double();
        rhombus.moveTo(graphicInfo.getX(), graphicInfo.getY() + graphicInfo.getHeight() / 2);
        rhombus.lineTo(graphicInfo.getX() + graphicInfo.getWidth() / 2, graphicInfo.getY() + graphicInfo.getHeight());
        rhombus.lineTo(graphicInfo.getX() + graphicInfo.getWidth(), graphicInfo.getY() + graphicInfo.getHeight() / 2);
        rhombus.lineTo(graphicInfo.getX() + graphicInfo.getWidth() / 2, graphicInfo.getY());
        rhombus.lineTo(graphicInfo.getX(), graphicInfo.getY() + graphicInfo.getHeight() / 2);
        rhombus.closePath();
        return rhombus;
    } else if (SHAPE_TYPE.Ellipse.equals(shapeType)) {
        // source is ellipse
        return new Ellipse2D.Double(graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight());
    } else {
    // unknown source element, just do not correct coordinates
    }
    return null;
}


public String fitTextToWidth(String original,int width){
    String text = original;
    // remove length for "..."
    int maxWidth = width - 10;
    while (fontMetrics.stringWidth(text + "...") > maxWidth && text.length() > 0) {
        text = text.substring(0, text.length() - 1);
    }
    if (!text.equals(original)) {
        text = text + "...";
    }
    return text;
}


public void drawNoneStartEvent(GraphicInfo graphicInfo){
    drawStartEvent(graphicInfo, null, 1.0);
}


public void drawTimerStartEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawStartEvent(graphicInfo, TIMER_IMAGE, scaleFactor);
}


public void drawScriptTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(SCRIPTTASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawThrowingNoneEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawCatchingEvent(graphicInfo, true, null, "none", scaleFactor);
}


public Point getIntersection(Shape shape,Line2D.Double line){
    if (shape instanceof Ellipse2D) {
        return getEllipseIntersection(shape, line);
    } else if (shape instanceof Rectangle2D || shape instanceof Path2D) {
        return getShapeIntersection(shape, line);
    } else {
        // something strange
        return null;
    }
}


public void drawErrorStartEvent(GraphicInfo graphicInfo,double scaleFactor){
    drawNoneStartEvent(graphicInfo);
    g.drawImage(ERROR_CATCH_IMAGE, (int) (graphicInfo.getX() + (graphicInfo.getWidth() / 4)), (int) (graphicInfo.getY() + (graphicInfo.getHeight() / 4)), (int) (ERROR_CATCH_IMAGE.getWidth() / scaleFactor), (int) (ERROR_CATCH_IMAGE.getHeight() / scaleFactor), null);
}


public void drawConnection(int[] xPoints,int[] yPoints,boolean conditional,boolean isDefault,String connectionType,AssociationDirection associationDirection,boolean highLighted,double scaleFactor){
    Paint originalPaint = g.getPaint();
    Stroke originalStroke = g.getStroke();
    g.setPaint(CONNECTION_COLOR);
    if (connectionType.equals("association")) {
        g.setStroke(ASSOCIATION_STROKE);
    } else if (highLighted) {
        g.setPaint(HIGHLIGHT_COLOR);
        g.setStroke(HIGHLIGHT_FLOW_STROKE);
    }
    for (int i = 1; i < xPoints.length; i++) {
        Integer sourceX = xPoints[i - 1];
        Integer sourceY = yPoints[i - 1];
        Integer targetX = xPoints[i];
        Integer targetY = yPoints[i];
        Line2D.Double line = new Line2D.Double(sourceX, sourceY, targetX, targetY);
        g.draw(line);
    }
    if (isDefault) {
        Line2D.Double line = new Line2D.Double(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        drawDefaultSequenceFlowIndicator(line, scaleFactor);
    }
    if (conditional) {
        Line2D.Double line = new Line2D.Double(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        drawConditionalSequenceFlowIndicator(line, scaleFactor);
    }
    if (associationDirection.equals(AssociationDirection.ONE) || associationDirection.equals(AssociationDirection.BOTH)) {
        Line2D.Double line = new Line2D.Double(xPoints[xPoints.length - 2], yPoints[xPoints.length - 2], xPoints[xPoints.length - 1], yPoints[xPoints.length - 1]);
        drawArrowHead(line, scaleFactor);
    }
    if (associationDirection.equals(AssociationDirection.BOTH)) {
        Line2D.Double line = new Line2D.Double(xPoints[1], yPoints[1], xPoints[0], yPoints[0]);
        drawArrowHead(line, scaleFactor);
    }
    g.setPaint(originalPaint);
    g.setStroke(originalStroke);
}


public void drawManualTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(MANUALTASK_IMAGE, name, graphicInfo, scaleFactor);
}


public void drawGateway(GraphicInfo graphicInfo){
    Polygon rhombus = new Polygon();
    int x = (int) graphicInfo.getX();
    int y = (int) graphicInfo.getY();
    int width = (int) graphicInfo.getWidth();
    int height = (int) graphicInfo.getHeight();
    rhombus.addPoint(x, y + (height / 2));
    rhombus.addPoint(x + (width / 2), y + height);
    rhombus.addPoint(x + width, y + (height / 2));
    rhombus.addPoint(x + (width / 2), y);
    g.draw(rhombus);
}


public void drawAssociation(int[] xPoints,int[] yPoints,AssociationDirection associationDirection,boolean highLighted,double scaleFactor){
    boolean conditional = false, isDefault = false;
    drawConnection(xPoints, yPoints, conditional, isDefault, "association", associationDirection, highLighted, scaleFactor);
}


public void initialize(String imageType){
    if ("png".equalsIgnoreCase(imageType)) {
        this.processDiagram = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
    } else {
        this.processDiagram = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
    }
    this.g = processDiagram.createGraphics();
    if ("png".equalsIgnoreCase(imageType) == false) {
        this.g.setBackground(new Color(255, 255, 255, 0));
        this.g.clearRect(0, 0, canvasWidth, canvasHeight);
    }
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setPaint(Color.black);
    Font font = new Font(activityFontName, Font.BOLD, FONT_SIZE);
    g.setFont(font);
    this.fontMetrics = g.getFontMetrics();
    LABEL_FONT = new Font(labelFontName, Font.ITALIC, 10);
    try {
        USERTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/userTask.png", customClassLoader));
        SCRIPTTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/scriptTask.png", customClassLoader));
        SERVICETASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/serviceTask.png", customClassLoader));
        RECEIVETASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/receiveTask.png", customClassLoader));
        SENDTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/sendTask.png", customClassLoader));
        MANUALTASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/manualTask.png", customClassLoader));
        BUSINESS_RULE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/businessRuleTask.png", customClassLoader));
        SHELL_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/shellTask.png", customClassLoader));
        CAMEL_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/camelTask.png", customClassLoader));
        MULE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/muleTask.png", customClassLoader));
        TIMER_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/timer.png", customClassLoader));
        COMPENSATE_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/compensate-throw.png", customClassLoader));
        COMPENSATE_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/compensate.png", customClassLoader));
        ERROR_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/error-throw.png", customClassLoader));
        ERROR_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/error.png", customClassLoader));
        MESSAGE_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/message-throw.png", customClassLoader));
        MESSAGE_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/message.png", customClassLoader));
        SIGNAL_THROW_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/signal-throw.png", customClassLoader));
        SIGNAL_CATCH_IMAGE = ImageIO.read(ReflectUtil.getResource("org/activiti/icons/signal.png", customClassLoader));
    } catch (IOException e) {
        LOGGER.warn("Could not load image for process diagram creation: {}", e.getMessage());
    }
}


public void drawNoneEndEvent(GraphicInfo graphicInfo,double scaleFactor){
    Paint originalPaint = g.getPaint();
    Stroke originalStroke = g.getStroke();
    g.setPaint(EVENT_COLOR);
    Ellipse2D circle = new Ellipse2D.Double(graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight());
    g.fill(circle);
    g.setPaint(EVENT_BORDER_COLOR);
    if (scaleFactor == 1.0) {
        g.setStroke(END_EVENT_STROKE);
    } else {
        g.setStroke(new BasicStroke(2.0f));
    }
    g.draw(circle);
    g.setStroke(originalStroke);
    g.setPaint(originalPaint);
}


public Point getEllipseIntersection(Shape shape,Line2D.Double line){
    double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
    double x = shape.getBounds2D().getWidth() / 2 * Math.cos(angle) + shape.getBounds2D().getCenterX();
    double y = shape.getBounds2D().getHeight() / 2 * Math.sin(angle) + shape.getBounds2D().getCenterY();
    Point p = new Point();
    p.setLocation(x, y);
    return p;
}


public void drawArrowHead(Line2D.Double line,double scaleFactor){
    int doubleArrowWidth = (int) (2 * ARROW_WIDTH / scaleFactor);
    if (doubleArrowWidth == 0) {
        doubleArrowWidth = 2;
    }
    Polygon arrowHead = new Polygon();
    arrowHead.addPoint(0, 0);
    int arrowHeadPoint = (int) (-ARROW_WIDTH / scaleFactor);
    if (arrowHeadPoint == 0) {
        arrowHeadPoint = -1;
    }
    arrowHead.addPoint(arrowHeadPoint, -doubleArrowWidth);
    arrowHeadPoint = (int) (ARROW_WIDTH / scaleFactor);
    if (arrowHeadPoint == 0) {
        arrowHeadPoint = 1;
    }
    arrowHead.addPoint(arrowHeadPoint, -doubleArrowWidth);
    AffineTransform transformation = new AffineTransform();
    transformation.setToIdentity();
    double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
    transformation.translate(line.x2, line.y2);
    transformation.rotate((angle - Math.PI / 2d));
    AffineTransform originalTransformation = g.getTransform();
    g.setTransform(transformation);
    g.fill(arrowHead);
    g.setTransform(originalTransformation);
}


public void drawCatchingSignalEvent(GraphicInfo graphicInfo,boolean isInterrupting,double scaleFactor){
    drawCatchingEvent(graphicInfo, isInterrupting, SIGNAL_CATCH_IMAGE, "signal", scaleFactor);
}


public void drawMuleTask(String name,GraphicInfo graphicInfo,double scaleFactor){
    drawTask(MULE_TASK_IMAGE, name, graphicInfo, scaleFactor);
}


}