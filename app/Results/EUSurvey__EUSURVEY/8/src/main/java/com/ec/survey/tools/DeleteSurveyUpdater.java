package com.ec.survey.tools;
 import com.ec.survey.service.SurveyService;
import com.ec.survey.service.SystemService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SystemService;
@Service("deleteSurveysWorker")
@Scope("singleton")
public class DeleteSurveyUpdater implements Runnable{

 protected  Logger logger;

@Resource(name = "surveyService")
 private  SurveyService surveyService;

@Resource(name = "systemService")
 private  SystemService systemService;


@Override
public void run(){
    int lastId = 0;
    try {
        List<Integer> surveys = surveyService.getSurveysMarkedDeleted();
        for (Integer id : surveys) {
            lastId = id;
            try {
                surveyService.delete(id, true, true);
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage(), e);
                systemService.sendAdminErrorMessage("Error during deletion of Survey " + lastId + " " + e.getLocalizedMessage());
            }
        }
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
}


}