package com.ec.survey.model.AnswerSet;
 import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.Matrix;
import com.ec.survey.model.survey.RatingQuestion;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.ConversionTools;
import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util;
public class ExplanationData {

 public  String text;

 public  List<File> files;


}