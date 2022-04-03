package com.app.DAO;
 import java.util.HashMap;
import java.util.List;
import com.app.pojo.Result;
public interface ResultDAO {


public Result findByESSCT(int id)
;

public String smsSubjectStudentResult(int ExamId,int SubdivId)
;

public Result find(int id)
;

public int InsertNewRecord(int id,String remarks)
;

public void create(Result result)
;

public void update(Result result)
;

public void delet(int id)
;

public boolean resultExist(int essct)
;

public int updateResult(List<HashMap<String,String>> StudResultList)
;

public int updateRecord(int id,String remarks)
;

}