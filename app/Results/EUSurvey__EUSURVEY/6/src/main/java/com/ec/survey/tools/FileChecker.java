package com.ec.survey.tools;
 import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.base.File;
import java.util.Map;
public class FileChecker {


public boolean isDelphiExplanationFile(File file,Survey survey){
    if (!survey.getIsDelphi()) {
        return false;
    }
    final Map<String, Element> questions = survey.getQuestionMapByUniqueId();
    final String questionUid = file.getQuestionUid();
    final Element question = questions.get(questionUid);
    return question != null && question.isDelphiElement();
}


}