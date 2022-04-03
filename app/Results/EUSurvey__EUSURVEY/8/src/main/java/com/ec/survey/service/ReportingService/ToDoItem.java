package com.ec.survey.service.ReportingService;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ec.survey.exception.MessageException;
import com.ec.survey.exception.TooManyFiltersException;
import com.ec.survey.model.Answer;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.model.Form;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.ResultFilter.ResultFilterSortKey;
import com.ec.survey.model.Setting;
import com.ec.survey.model.SqlPagination;
import com.ec.survey.model.survey.ChoiceQuestion;
import com.ec.survey.model.survey.DateQuestion;
import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.EmailQuestion;
import com.ec.survey.model.survey.FreeTextQuestion;
import com.ec.survey.model.survey.GalleryQuestion;
import com.ec.survey.model.survey.Matrix;
import com.ec.survey.model.survey.MatrixOrTable;
import com.ec.survey.model.survey.MultipleChoiceQuestion;
import com.ec.survey.model.survey.NumberQuestion;
import com.ec.survey.model.survey.Question;
import com.ec.survey.model.survey.RankingItem;
import com.ec.survey.model.survey.RankingQuestion;
import com.ec.survey.model.survey.RatingQuestion;
import com.ec.survey.model.survey.RegExQuestion;
import com.ec.survey.model.survey.SingleChoiceQuestion;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.Table;
import com.ec.survey.model.survey.TimeQuestion;
import com.ec.survey.model.survey.Upload;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import org.hibernate.exception.SQLGrammarException;
public class ToDoItem {

 public  int Id;

 public  ToDo Type;

 public  String UID;

 public  String Code;

public ToDoItem(int id, int type, String uid, String code) {
    Id = id;
    Type = ToDo.values()[type];
    UID = uid;
    Code = code;
}
}