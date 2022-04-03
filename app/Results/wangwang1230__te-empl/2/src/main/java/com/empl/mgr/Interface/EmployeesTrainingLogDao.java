package com.empl.mgr.Interface;
public interface EmployeesTrainingLogDao {

   public List<EmployeesTrainingRecordDto> findTrainingRecord(long emplId);
   public Object save(Object Object);
   public TeEmployeesTrainingLog findByEmplTraining(long emplId,long trainingId);
   public Object deleteByProperty(Object Object);
}