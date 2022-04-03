package com.ec.survey.tools.export;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.lang.NotImplementedException;
import org.apache.poi.util.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service("pdfExportCreator")
@Scope("prototype")
public class PdfExportCreator extends ExportCreator{


@Override
public void exportECFOrganizationalResults(){
    throw new NotImplementedException();
}


@Override
public void exportStatisticsQuiz(){
    File file = pdfService.createStatisticsQuizPDF(form.getSurvey(), export.getId().toString());
    FileInputStream fis = new FileInputStream(file);
    IOUtils.copy(fis, outputStream);
    fis.close();
    Files.delete(file.toPath());
}


@Override
public void exportAddressBook(){
    throw new NotImplementedException();
}


@Override
public void exportActivities(){
    throw new NotImplementedException();
}


@Override
public void exportECFGlobalResults(){
    throw new NotImplementedException();
}


@Override
public void exportContent(boolean sync){
    File file = pdfService.createAllIndividualResultsPDF(form.getSurvey(), export.getResultFilter());
    FileInputStream fis = new FileInputStream(file);
    IOUtils.copy(fis, outputStream);
    fis.close();
    Files.delete(file.toPath());
}


@Override
public void exportTokens(){
    throw new NotImplementedException();
}


@Override
public void exportECFProfileResults(){
    throw new NotImplementedException();
}


@Override
public void exportStatistics(){
    File file = pdfService.createStatisticsPDF(form.getSurvey(), export.getId().toString());
    FileInputStream fis = new FileInputStream(file);
    IOUtils.copy(fis, outputStream);
    fis.close();
    Files.delete(file.toPath());
}


}