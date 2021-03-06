package com.app.DAO.iml;
 import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.DAO.InstituteDAO;
import com.app.pojo.Institute;
import com.app.pojo.Login;
import com.app.pojo.Role;
import com.app.pojo.Student;
import com.app.pojo.Teacher;
@Repository("InstituteDAO")
public class instituteDAOImpl implements InstituteDAO{

@Autowired
 private SessionFactory sessionFactory;


@Override
@Transactional
public List<Institute> getall(){
    return currentSession().createCriteria(Institute.class).list();
}


@Override
@Transactional
public Institute edit(int id){
    return find(id);
}


@Override
@Transactional
public void update(Institute institute){
    currentSession().update(institute);
}


@Override
@Transactional
public void delet(int id){
    currentSession().delete(id);
}


@Override
@Transactional
public String TreeStructureSujectsOFExam(int InstId,int ExamId){
    ProcedureCall procedure1 = currentSession().createStoredProcedureCall("TreeViewInstitute");
    procedure1.registerParameter("InstID", Integer.class, ParameterMode.IN).bindValue(InstId);
    procedure1.registerParameter("spMode", Integer.class, ParameterMode.IN).bindValue(4);
    procedure1.registerParameter("param", Integer.class, ParameterMode.IN).bindValue(ExamId);
    procedure1.registerParameter("JSON", String.class, ParameterMode.OUT);
    ProcedureOutputs procedureResult = procedure1.getOutputs();
    String JSON = (String) procedureResult.getOutputParameterValue("JSON");
    return JSON;
}


@Override
@Transactional
public List<Student> getallStudentWhoAreNotInAnyDivision(Institute institute){
    Query query = currentSession().createQuery("SELECT s FROM Student s, Login l WHERE s.division IS NULL AND s.institute= :institute AND l.id=s.login AND l.enableInstitute=TRUE");
    query.setParameter("institute", institute);
    return query.list();
}


@Override
@Transactional
public String GetInstituteTree(int InstId){
    ProcedureCall procedure1 = currentSession().createStoredProcedureCall("TreeViewInstitute");
    procedure1.registerParameter("InstID", Integer.class, ParameterMode.IN).bindValue(InstId);
    procedure1.registerParameter("spMode", Integer.class, ParameterMode.IN).bindValue(2);
    procedure1.registerParameter("param", Integer.class, ParameterMode.IN).bindValue(0);
    procedure1.registerParameter("JSON", String.class, ParameterMode.OUT);
    ProcedureOutputs procedureResult = procedure1.getOutputs();
    String JSON = (String) procedureResult.getOutputParameterValue("JSON");
    return JSON;
}


@Override
@Transactional
public List<Student> getallPendingStudentForApproval(Institute institute){
    Query query = currentSession().createQuery("SELECT s FROM Login l, Student s WHERE l.enableInstitute=FALSE AND l.id=s.login AND s.institute= :institute");
    query.setParameter("institute", institute);
    return query.list();
}


@Override
@Transactional
public String TreeStructureSujectsNotInExam(int InstId,int ExamId){
    ProcedureCall procedure1 = currentSession().createStoredProcedureCall("TreeViewInstitute");
    procedure1.registerParameter("InstID", Integer.class, ParameterMode.IN).bindValue(InstId);
    procedure1.registerParameter("spMode", Integer.class, ParameterMode.IN).bindValue(3);
    procedure1.registerParameter("param", Integer.class, ParameterMode.IN).bindValue(ExamId);
    procedure1.registerParameter("JSON", String.class, ParameterMode.OUT);
    ProcedureOutputs procedureResult = procedure1.getOutputs();
    String JSON = (String) procedureResult.getOutputParameterValue("JSON");
    return JSON;
}


public Session currentSession(){
    return this.sessionFactory.getCurrentSession();
}


@Override
@Transactional
public Institute find(String name){
    Query query = currentSession().createQuery("from Institute  where name = :name");
    query.setParameter("name", name);
    return (Institute) query.uniqueResult();
}


@Override
@Transactional
public void create(Institute institute){
    currentSession().save(institute);
}


@Override
@Transactional
public String GetSubjectTree(int InstId){
    ProcedureCall procedure1 = currentSession().createStoredProcedureCall("TreeViewInstitute");
    procedure1.registerParameter("InstID", Integer.class, ParameterMode.IN).bindValue(InstId);
    procedure1.registerParameter("spMode", Integer.class, ParameterMode.IN).bindValue(1);
    procedure1.registerParameter("param", Integer.class, ParameterMode.IN).bindValue(0);
    procedure1.registerParameter("JSON", String.class, ParameterMode.OUT);
    ProcedureOutputs procedureResult = procedure1.getOutputs();
    String JSON = (String) procedureResult.getOutputParameterValue("JSON");
    return JSON;
}


@Override
@Transactional
public List<Teacher> getallPendingTeacherForApproval(Institute institute){
    Query query = currentSession().createQuery("SELECT t FROM Login l, Teacher t WHERE l.enableInstitute=FALSE AND l.id=t.login AND t.institute= :institute");
    query.setParameter("institute", institute);
    return query.list();
}


}