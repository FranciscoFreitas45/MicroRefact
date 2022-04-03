package com.ec.survey.service;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.SqlPagination;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.service.ReportingService.ToDo;
import com.ec.survey.service.ReportingService.ToDoItem;
@Service("reportingServiceProxy")
public class ReportingServiceProxy {

 protected  Logger logger;

@Resource(name = "reportingService")
 protected  ReportingService reportingService;

@Value("${enablereportingdatabase}")
 protected  String enablereportingdatabase;


public boolean isReportingDatabaseEnabled(){
    return enablereportingdatabase != null && enablereportingdatabase.equalsIgnoreCase("true");
}


public List<ToDoItem> getToDos(){
    if (!isReportingDatabaseEnabled())
        return null;
    return reportingService.getToDosInternal(-1, -1);
}


public void executeToDo(ToDoItem todo,boolean removeSimilar){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.executeToDoInternal(todo, removeSimilar);
}


public int getNumberOfTables(){
    if (!isReportingDatabaseEnabled())
        return 0;
    return reportingService.getNumberOfTablesInternal();
}


public void removeFromOLAPTable(String surveyUID,String answerSetUID,boolean surveyIsPublished){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.removeFromOLAPTableInternal(surveyUID, answerSetUID, surveyIsPublished);
}


public void addToDo(ToDo todo,String uid,String code){
    this.addToDo(todo, uid, code, false);
}


public void removeToDo(ToDoItem todo,boolean includesimilar){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.removeToDoInternal(todo, includesimilar);
}


public void createOLAPTable(String shortname,boolean draftversion,boolean publishedversion){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.createOLAPTableInternal(shortname, draftversion, publishedversion);
}


public List<Integer> getAnswerSetIDs(Survey survey,ResultFilter filter,SqlPagination sqlPagination){
    if (!isReportingDatabaseEnabled())
        return null;
    return reportingService.getAnswerSetIDsInternal(survey, filter, sqlPagination);
}


public List<List<String>> getAnswerSets(Survey survey,ResultFilter filter,SqlPagination sqlPagination,boolean addlinks,boolean forexport,boolean showuploadedfiles,boolean doNotReplaceAnswerIDs,boolean useXmlDateFormat,boolean showShortnames){
    if (!isReportingDatabaseEnabled())
        return null;
    return reportingService.getAnswerSetsInternal(survey, filter, sqlPagination, addlinks, forexport, showuploadedfiles, doNotReplaceAnswerIDs, useXmlDateFormat, showShortnames);
}


public boolean OLAPTableExists(String uid,boolean draft){
    if (!isReportingDatabaseEnabled())
        return false;
    return reportingService.OLAPTableExistsInternal(uid, draft);
}


public void removeAllToDos(){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.removeAllToDosInternal();
}


public ToDoItem getToDo(int id){
    if (!isReportingDatabaseEnabled())
        return null;
    return reportingService.getToDoInternal(id);
}


public int getNumberOfToDos(){
    if (!isReportingDatabaseEnabled())
        return 0;
    return reportingService.getNumberOfToDosInternal();
}


public void deleteOLAPTable(String uid,boolean draftversion,boolean publishedversion){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.deleteOLAPTableInternal(uid, draftversion, publishedversion);
}


public Date getLastUpdate(Survey survey){
    if (!isReportingDatabaseEnabled())
        return null;
    return reportingService.getLastUpdateInternal(survey);
}


public boolean validateOLAPTable(Survey survey,Integer counter){
    if (!isReportingDatabaseEnabled())
        return false;
    return reportingService.validateOLAPTableInternal(survey, counter);
}


public boolean validateOLAPTables(String surveyUID,boolean isDraft){
    if (!isReportingDatabaseEnabled())
        return false;
    return reportingService.validateOLAPTablesInternal(surveyUID, isDraft);
}


public int getCount(Survey survey,String quid,String auid,boolean noPrefixSearch,boolean noPostfixSearch,String where,Map<String,Object> values){
    if (!isReportingDatabaseEnabled())
        return -1;
    return reportingService.getCountInternal(survey, quid, auid, noPrefixSearch, noPostfixSearch, where, values);
}


public void clearAnswersForQuestionInReportingDatabase(Survey survey,ResultFilter filter,String questionUID,String childUID){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.clearAnswersForQuestionInReportingDatabase(survey, filter, questionUID, childUID);
}


public void updateOLAPTable(String shortname,boolean draftversion,boolean publishedversion){
    if (!isReportingDatabaseEnabled())
        return;
    reportingService.updateOLAPTableInternal(shortname, draftversion, publishedversion);
}


}