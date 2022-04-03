package com.ec.survey.DTO;
 import com.ec.survey.exception.MessageException;
import com.ec.survey.model.Archive;
import com.ec.survey.model.ArchiveFilter;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.ImportResult;
import com.ec.survey.tools.SurveyExportHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.util;
import com.ec.survey.Interface.SqlQueryService;
public class ArchiveService extends BasicService{

 protected  TaskExecutor taskExecutorLong;

 private  SqlQueryService sqlQueryService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Transactional(readOnly = true)
public List<Archive> getArchivesToRestart(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Archive a WHERE a.finished = false AND a.error IS NULL");
    @SuppressWarnings("unchecked")
    List<Archive> result = query.list();
    return result;
}


@Transactional(readOnly = true)
public int getNumberOfArchives(Integer userId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT COUNT(*) FROM Archive a WHERE a.userId = :userId").setInteger("userId", userId);
    return ConversionTools.getValue(query.uniqueResult());
}


@Transactional(readOnly = true)
public Archive get(int id){
    Session session = sessionFactory.getCurrentSession();
    return (Archive) session.get(Archive.class, id);
}


@Transactional
public String getSurveyUIDForArchivedSurveyShortname(String shortname){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT a.surveyUID FROM Archive a WHERE a.surveyShortname = :shortname");
    query.setString(Constants.SHORTNAME, shortname);
    return (String) query.uniqueResult();
}


@Transactional
public List<Archive> getArchivesForUser(int userid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Archive a WHERE a.userId = :userId").setInteger("userId", userid);
    @SuppressWarnings("unchecked")
    List<Archive> result = query.list();
    return result;
}


@Transactional(readOnly = true)
public List<Archive> getAllArchives(ArchiveFilter filter,int page,int rowsPerPage,boolean includingErrors){
    Session session = sessionFactory.getCurrentSession();
    String hql = "FROM Archive a WHERE a.error IS NULL";
    if (includingErrors) {
        hql = "FROM Archive a WHERE a.id > 0";
    }
    Map<String, Object> params = new HashMap<>();
    if (filter.getUniqueId() != null && filter.getUniqueId().trim().length() > 0) {
        hql += " AND a.surveyUID LIKE :uid";
        params.put("uid", "%" + filter.getUniqueId() + "%");
    }
    if (filter.getUserId() > 0) {
        hql += " AND a.userId = :userid";
        params.put("userid", filter.getUserId());
    }
    if (filter.getFinished() != null && filter.getFinished()) {
        hql += " AND a.finished = true";
    }
    if (filter.getShortname() != null && filter.getShortname().trim().length() > 0) {
        hql += " AND a.surveyShortname LIKE :shortname";
        params.put(Constants.SHORTNAME, "%" + filter.getShortname() + "%");
    }
    if (filter.getTitle() != null && filter.getTitle().trim().length() > 0) {
        hql += " AND a.surveyTitle LIKE :title";
        params.put("title", "%" + filter.getTitle() + "%");
    }
    if (filter.getOwner() != null && filter.getOwner().trim().length() > 0) {
        hql += " AND a.owner LIKE :owner";
        params.put("owner", "%" + filter.getOwner() + "%");
    }
    if (filter.getCreatedFrom() != null) {
        hql += " AND a.created >= :createdFrom";
        params.put("createdFrom", filter.getCreatedFrom());
    }
    if (filter.getCreatedTo() != null) {
        hql += " AND a.created < :createdTo";
        Calendar c = Calendar.getInstance();
        c.setTime(filter.getCreatedTo());
        c.add(Calendar.DAY_OF_MONTH, 1);
        params.put("createdTo", c.getTime());
    }
    if (filter.getArchivedFrom() != null) {
        hql += " AND a.archived >= :archivedFrom";
        params.put("archivedFrom", filter.getArchivedFrom());
    }
    if (filter.getArchivedTo() != null) {
        hql += " AND a.archived < :archivedTo";
        Calendar c = Calendar.getInstance();
        c.setTime(filter.getArchivedTo());
        c.add(Calendar.DAY_OF_MONTH, 1);
        params.put("archivedTo", c.getTime());
    }
    if (filter.getSortKey() != null && filter.getSortKey().length() > 0) {
        hql += " ORDER BY " + filter.getSortKey() + " " + filter.getSortOrder();
    }
    Query query = session.createQuery(hql);
    sqlQueryService.setParameters(query, params);
    @SuppressWarnings("unchecked")
    List<Archive> result = query.setFirstResult((page - 1) * rowsPerPage).setMaxResults(rowsPerPage).list();
    return result;
}


@Transactional
public Archive getArchive(Integer userid,String shortname){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Archive a WHERE a.userId = :userId AND a.surveyShortname = :shortname AND a.finished = true AND a.error IS NULL").setInteger("userId", userid).setString(Constants.SHORTNAME, shortname);
    @SuppressWarnings("unchecked")
    List<Archive> result = query.list();
    if (!result.isEmpty())
        return result.get(0);
    return null;
}


@Transactional(readOnly = false)
public void add(Archive archive){
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(archive);
    session.flush();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))

.queryParam("archive",archive)
;
restTemplate.put(builder.toUriString(),null);
}


}