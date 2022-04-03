package com.ec.survey.tools.export;
 import java.io.FileInputStream;
import org.apache.commons.lang.NotImplementedException;
import org.apache.poi.util.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service("eusExportCreator")
@Scope("prototype")
public class EusExportCreator extends ExportCreator{


@Override
public void exportECFOrganizationalResults(){
    throw new NotImplementedException();
}


@Override
public void exportStatisticsQuiz(){
    throw new NotImplementedException();
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
    java.io.File zip = surveyService.exportSurvey(form.getSurvey().getShortname(), surveyService, true);
    IOUtils.copy(new FileInputStream(zip), outputStream);
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
    throw new NotImplementedException();
}


}