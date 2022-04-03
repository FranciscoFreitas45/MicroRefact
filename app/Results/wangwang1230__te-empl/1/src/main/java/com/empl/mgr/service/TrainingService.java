package com.empl.mgr.service;
 import com.empl.mgr.support.JSONReturn;
public interface TrainingService {


public JSONReturn modifyTraining(long id,String name,String description,String startTime,String endTime,boolean isInsertAttend,String acctName)
;

public JSONReturn findTrainingList(String searchValue,int page,String acctName)
;

public JSONReturn addTraining(String name,String description,String startTime,String endTime,boolean isInsertAttend,String acctName)
;

public JSONReturn start(long id,String acctName)
;

public JSONReturn evaluationEmployeesTraining(long id,String note,String acctName)
;

public JSONReturn stopEmployeesTraining(long id,String note,String acctName)
;

public JSONReturn delete(long id,String acctName)
;

public JSONReturn findTrainingInfoById(long id,String acctName)
;

public JSONReturn stop(long id,String note,String enddate,String acctName)
;

public JSONReturn findTrainingPage(String searchValue,int page,String acctName)
;

public JSONReturn delEmployeesTrainingRecord(long id,String acctName)
;

public JSONReturn findRecord(long trainingId,String acctName)
;

public JSONReturn addTrainingByEmplId(long emplId,long trainingId,String acctName)
;

}