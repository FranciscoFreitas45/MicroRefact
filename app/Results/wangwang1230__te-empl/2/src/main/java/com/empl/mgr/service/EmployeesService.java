package com.empl.mgr.service;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.empl.mgr.dto.EmployeesInfoDto;
import com.empl.mgr.support.JSONReturn;
public interface EmployeesService {


public JSONReturn saveEmployeesInfo(EmployeesInfoDto dto,String acctName)
;

public JSONReturn modifyEmployeesInfo(EmployeesInfoDto dto,String acctName)
;

public JSONReturn findEmployeesList(long trainingId,long deptId,String searchVal,String acctName)
;

public JSONReturn findDepartureNote(long emplId,String acctName)
;

public JSONReturn addAllTraining(long trainingId,long deptId,String searchVal,String acctName)
;

public JSONReturn destroy(long emplId,String acctName)
;

public JSONReturn eliminate(long emId,String note,String string)
;

public JSONReturn findEmployeesRecord(long emplId)
;

public JSONReturn findEmployeesInfo(long emplId)
;

public JSONReturn findEmployeesPage(int serType,String serVal,long department,long position,int page,String acctName,int emplType)
;

public JSONReturn findChoose()
;

public JSONReturn uploadImg(MultipartFile imgFile,HttpServletRequest request,HttpServletResponse response)
;

public JSONReturn enroll(long emId,String acctName)
;

public JSONReturn departure(long id,String note,String acctName)
;

public JSONReturn findTrainingRecord(long emplId,String acctName)
;

public JSONReturn enrollEmployees(long emplId,String note,String acctName)
;

}