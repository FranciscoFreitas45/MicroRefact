package com.ec.survey.DTO;
 import com.ec.survey.model;
import com.ec.survey.model.administration.ComplexityParameters;
import com.ec.survey.model.administration.GlobalPrivilege;
import com.ec.survey.model.administration.Role;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.PossibleAnswer;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.DomainUpdater;
import com.ec.survey.tools.SkinCreator;
import com.ec.survey.tools.SurveyCreator;
import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.ec.survey.Interface.DomainUpdater;
public class SchemaService extends BasicService{

 private  String showecas;

 public  String cassOss;

 public  String ecfSurveyAlias;

 private  DomainUpdater domaintWorker;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Transactional
public Status getStatus(){
    Session session = sessionFactory.getCurrentSession();
    Query statusQuery = session.createQuery("FROM Status");
    @SuppressWarnings("unchecked")
    List<Status> states = statusQuery.list();
    if (states.isEmpty()) {
        return null;
    }
    session.setReadOnly(states.get(0), false);
    return states.get(0);
}


@Transactional
public Date getLastLDAPSynchronization2Date(){
    Session session = sessionFactory.getCurrentSession();
    Query statusQuery = session.createQuery("FROM Status");
    @SuppressWarnings("unchecked")
    List<Status> states = statusQuery.setReadOnly(true).list();
    if (!states.isEmpty()) {
        return states.get(0).getLastLDAPSynchronization2Date();
    }
    return null;
}


@Transactional
public Date getLastAnswerSetAnonymDate(){
    Session session = sessionFactory.getCurrentSession();
    Query statusQuery = session.createQuery("FROM Status");
    @SuppressWarnings("unchecked")
    List<Status> states = statusQuery.setReadOnly(true).list();
    if (!states.isEmpty()) {
        return states.get(0).getLastAnswerSetAnonymDate();
    }
    return null;
}


@Transactional
public Date getLastLDAPSynchronizationDate(){
    Session session = sessionFactory.getCurrentSession();
    Query statusQuery = session.createQuery("FROM Status");
    @SuppressWarnings("unchecked")
    List<Status> states = statusQuery.setReadOnly(true).list();
    if (!states.isEmpty()) {
        return states.get(0).getLastLDAPSynchronizationDate();
    }
    return null;
}


@Transactional
public void saveLastAnswerSetAnonymDate(Date lastAnswerSetAnonymisedDate){
    Session session = sessionFactory.getCurrentSession();
    Query statusQuery = session.createQuery("FROM Status");
    @SuppressWarnings("unchecked")
    List<Status> states = statusQuery.list();
    if (!states.isEmpty()) {
        Status status = states.get(0);
        session.setReadOnly(status, false);
        status.setLastAnswerSetAnonymDate(lastAnswerSetAnonymisedDate);
    // Save is automatic on Transactional methods
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveLastAnswerSetAnonymDate"))

.queryParam("lastAnswerSetAnonymisedDate",lastAnswerSetAnonymisedDate)
;
restTemplate.put(builder.toUriString(),null);
}


}