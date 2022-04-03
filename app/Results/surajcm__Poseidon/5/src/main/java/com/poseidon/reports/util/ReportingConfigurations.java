package com.poseidon.reports.util;
 import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import java.io.OutputStream;
public class ReportingConfigurations {

private ReportingConfigurations() {
}
public SimpleXlsReportConfiguration configurationReportXls(){
    var xlsReportConfiguration = new SimpleXlsReportConfiguration();
    xlsReportConfiguration.setOnePagePerSheet(false);
    xlsReportConfiguration.setWhitePageBackground(true);
    xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
    xlsReportConfiguration.setRemoveEmptySpaceBetweenColumns(true);
    xlsReportConfiguration.setDetectCellType(true);
    xlsReportConfiguration.setFontSizeFixEnabled(false);
    xlsReportConfiguration.setIgnoreCellBorder(true);
    return xlsReportConfiguration;
}


public SimpleExporterInput exporter(JasperPrint jasperPrint){
    return new SimpleExporterInput(jasperPrint);
}


public SimpleOutputStreamExporterOutput exporterOutput(OutputStream outputStream){
    return new SimpleOutputStreamExporterOutput(outputStream);
}


public SimplePdfReportConfiguration pdfReportConfiguration(){
    var configuration = new SimplePdfReportConfiguration();
    configuration.setSizePageToContent(true);
    return configuration;
}


public SimpleHtmlReportConfiguration configurationForHTML(){
    var configuration = new SimpleHtmlReportConfiguration();
    configuration.setWhitePageBackground(true);
    configuration.setRemoveEmptySpaceBetweenRows(true);
    configuration.setWrapBreakWord(true);
    return configuration;
}


public SimpleDocxReportConfiguration docxReportConfiguration(){
    var configuration = new SimpleDocxReportConfiguration();
    configuration.setFlexibleRowHeight(true);
    return configuration;
}


public SimpleHtmlExporterOutput exportHTML(OutputStream outputStream){
    return new SimpleHtmlExporterOutput(outputStream);
}


}