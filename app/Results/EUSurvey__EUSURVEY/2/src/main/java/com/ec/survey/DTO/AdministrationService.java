package com.ec.survey.DTO;
 import com.ec.survey.exception.MessageException;
import com.ec.survey.model;
import com.ec.survey.model.administration;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.LoginAlreadyExistsException;
import com.ec.survey.tools.Tools;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util;
import java.util.Map.Entry;
import org.springframework.util.StringUtils;
import com.ec.survey.Interface.SqlQueryService;
public class AdministrationService extends BasicService{

 private  SqlQueryService sqlQueryService;

 private  String adminuser;

 private  String adminpassword;

 private  String stressuser;

 private  String stresspassword;

 private  String smtpServer;

 private  String smtpPort;

 private  String sender;

 private  String host;


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<Role> getAllRoles(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Role");
    return query.list();
}


public String getStressUser(){
    return stressuser;
}


public String getHql(UserFilter filter,HashMap<String,Object> parameters){
    StringBuilder hql = new StringBuilder("SELECT DISTINCT u FROM User u LEFT JOIN u.roles as r WHERE u.id > 0");
    if (filter.getLogin() != null && filter.getLogin().length() > 0) {
        hql.append(" AND u.login like :login");
        parameters.put("login", "%" + filter.getLogin() + "%");
    }
    if (filter.getEmail() != null && filter.getEmail().length() > 0) {
        hql.append(" AND u.email like :email");
        parameters.put(Constants.EMAIL, "%" + filter.getEmail() + "%");
    }
    if (filter.getComment() != null && filter.getComment().length() > 0) {
        hql.append(" AND u.comment like :comment");
        parameters.put("comment", "%" + filter.getComment() + "%");
    }
    if (filter.getLanguages() != null) {
        int i = 0;
        hql.append(" AND (");
        for (String lang : filter.getLanguages()) {
            if (lang.trim().length() > 0) {
                String l = "lang" + i++;
                if (i > 1) {
                    hql.append(" OR");
                }
                hql.append(" ( u.language like :").append(l).append(")");
                parameters.put(l, lang.trim());
            }
        }
        hql.append(" )");
    }
    if (filter.getECAS() != null && filter.getSystem() != null) {
        if (filter.getECAS() && !filter.getSystem()) {
            hql.append(" AND u.type = 'ECAS'");
        } else if (!filter.getECAS() && filter.getSystem()) {
            hql.append(" AND u.type = 'SYSTEM'");
        }
    }
    if (filter.getBanned() != null && filter.getBanned() && filter.getUnbanned() != null && filter.getUnbanned()) {
    // show all
    } else if (filter.getBanned() != null && filter.getBanned()) {
        hql.append(" AND u.frozen = true");
    } else if (filter.getUnbanned() != null && filter.getUnbanned()) {
        hql.append(" AND u.frozen = false");
    }
    if (filter.getRoles() != null) {
        int i = 0;
        hql.append(" AND (");
        for (String role : filter.getRoles()) {
            if (role.trim().length() > 0) {
                String l = "role" + i++;
                if (i > 1) {
                    hql.append(" OR");
                }
                hql.append(" r.id like :").append(l);
                parameters.put(l, Integer.parseInt(role.trim()));
            }
        }
        hql.append(" )");
    }
    if (filter.getECASaccess() != null && filter.getNoECASaccess() != null)
        if (filter.getECASaccess() && !filter.getNoECASaccess()) {
            hql.append(" AND u.canAccessEcasFunctionality = true");
        } else if (!filter.getECASaccess() && filter.getNoECASaccess()) {
            hql.append(" AND u.canAccessEcasFunctionality = false");
        }
    if (filter.getECaccess() != null && filter.getNoECaccess() != null)
        if (filter.getECaccess() && !filter.getNoECaccess()) {
            hql.append(" AND u.canAccessECFunctionality = true");
        } else if (!filter.getECaccess() && filter.getNoECaccess()) {
            hql.append(" AND u.canAccessECFunctionality = false");
        }
    if (filter.getSortKey() != null && filter.getSortKey().length() > 0) {
        hql.append(" ORDER BY u.").append(filter.getSortKey());
        if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
            hql.append(" ").append(filter.getSortOrder());
        } else {
            hql.append(" DESC");
        }
    }
    return hql.toString();
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<Integer> getAllUserIDs(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT u.id FROM User u");
    return query.list();
}


@Transactional(readOnly = true)
public User getUser(Integer id){
    Session session = sessionFactory.getCurrentSession();
    return (User) session.get(User.class, id);
}


@Transactional(readOnly = true)
public UsersConfiguration getUsersConfiguration(int userId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM UsersConfiguration c where c.userId = :userId").setInteger("userId", userId);
    @SuppressWarnings("unchecked")
    List<UsersConfiguration> list = query.list();
    if (list.isEmpty()) {
        return null;
    }
    return list.get(0);
}


@Transactional(readOnly = true)
public Map<String,String> getECASUserLoginsByEmail(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT USER_EMAIL, USER_LOGIN FROM USERS WHERE USER_TYPE = 'ECAS'");
    @SuppressWarnings("rawtypes")
    List res = query.list();
    HashMap<String, String> result = new HashMap<>();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        if (!result.containsKey((String) a[0])) {
            result.put((String) a[0], (String) a[1]);
        }
    }
    return result;
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<User> getUsers(UserFilter filter,SqlPagination sqlPagination){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    Query query = session.createQuery(getHql(filter, parameters));
    sqlQueryService.setParameters(query, parameters);
    return query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).setFirstResult(sqlPagination.getFirstResult()).setMaxResults(sqlPagination.getMaxResult()).list();
}


public String getAdminPassword(){
    return adminpassword;
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<User> getAllUsers(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM User");
    return query.list();
}


@Transactional(readOnly = true)
public OneTimePasswordResetCode getOneTimePasswordResetCode(String code){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM OneTimePasswordResetCode c where c.code = :code").setString("code", code);
    @SuppressWarnings("unchecked")
    List<OneTimePasswordResetCode> list = query.list();
    if (list.isEmpty()) {
        throw new MessageException("No item found for code " + code);
    }
    if (list.size() > 1) {
        throw new MessageException("Multiple items found for code " + code);
    }
    return list.get(0);
}


@Transactional(readOnly = true)
public User getUserForLogin(String login,boolean ecas){
    Session session = sessionFactory.getCurrentSession();
    String hql = "FROM User u where u.login = :login  AND u.type = :type";
    Query query = session.createQuery(hql).setString("login", login);
    if (ecas) {
        query.setString("type", User.ECAS);
    } else {
        query.setString("type", User.SYSTEM);
    }
    @SuppressWarnings("unchecked")
    List<User> list = query.list();
    if (list.isEmpty())
        throw new MessageException("No user found for login " + login);
    if (list.size() > 1)
        throw new MessageException("Multiple users found for login " + login);
    return list.get(0);
}


@Transactional
public List<Integer> getUserAccountsForDeletion(){
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query = session.createSQLQuery("SELECT USER_ID FROM USERS WHERE USER_DELETED = 1 AND USER_DELDATE < NOW() - INTERVAL 7 DAY");
    @SuppressWarnings("rawtypes")
    List users = query.list();
    List<Integer> result = new ArrayList<>();
    for (Object o : users) {
        result.add(ConversionTools.getValue(o));
    }
    return result;
}


@Transactional(readOnly = true)
public Map<Integer,Role> getAllRolesAsMap(){
    List<Role> roles = getAllRoles();
    Map<Integer, Role> result = new HashMap<>();
    for (Role role : roles) {
        result.put(role.getId(), role);
    }
    return result;
}


@Transactional(readOnly = true)
public Role getRole(Integer id){
    Session session = sessionFactory.getCurrentSession();
    return (Role) session.get(Role.class, id);
}


public String getStressPassword(){
    return stresspassword;
}


@Transactional(readOnly = true)
public int getNumberOfUsers(UserFilter filter){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    Query query = session.createQuery(getHql(filter, parameters));
    for (Entry<String, Object> entry : parameters.entrySet()) {
        Object value = entry.getValue();
        if (value instanceof String) {
            query.setString(entry.getKey(), (String) value);
        } else if (value instanceof Integer) {
            query.setInteger(entry.getKey(), (Integer) value);
        } else if (value instanceof Date) {
            query.setDate(entry.getKey(), (Date) value);
        }
    }
    return query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list().size();
}


@Transactional(readOnly = true)
public User getUserForLoginAndInitialize(String login,boolean ecas){
    User user = getUserForLogin(login, ecas);
    if (user != null) {
        Hibernate.initialize(user.getRoles());
    }
    return user;
}


@Transactional(readOnly = true)
public String[] getLoginsForPrefix(String term,String emailterm,boolean forPrivileges){
    Session session = sessionFactory.getCurrentSession();
    Query query = null;
    if (term.length() > 0 && (emailterm != null && emailterm.length() > 0)) {
        query = session.createQuery("FROM User u where u.login like :login and u.email like :email and u.type = :type order by u.login asc").setString("type", User.SYSTEM).setString("login", "%" + term + "%").setString(Constants.EMAIL, "%" + emailterm + "%");
    } else if (emailterm != null && emailterm.length() > 0) {
        query = session.createQuery("FROM User u where u.email like :email and u.type = :type order by u.login asc").setString("type", User.SYSTEM).setString(Constants.EMAIL, "%" + emailterm + "%");
    } else {
        query = session.createQuery("FROM User u where u.login like :login and u.type = :type order by u.login asc").setString("type", User.SYSTEM).setString("login", "%" + term + "%");
    }
    @SuppressWarnings("unchecked")
    List<User> list = query.setMaxResults(100).list();
    String[] result = new String[list.size()];
    int counter = 0;
    for (User user : list) {
        if (forPrivileges) {
            result[counter++] = "<tr data-id='" + user.getId() + "' id='" + user.getLogin() + "'><td>" + user.getLogin() + "</td><td>" + (user.getGivenName() == null ? "&nbsp;" : user.getGivenName()) + "</td><td>" + (user.getSurName() == null ? "&nbsp;" : user.getSurName()) + "</td><td>&nbsp;</td></tr>";
        } else {
            result[counter++] = user.getLogin();
        }
    }
    return result;
}


public String getAdminUser(){
    return adminuser;
}


}