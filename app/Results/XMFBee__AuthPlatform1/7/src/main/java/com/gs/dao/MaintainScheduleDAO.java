package com.gs.dao;
 import com.gs.bean.MaintainSchedule;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MaintainScheduleDAO extends BaseDAO<String, MaintainSchedule>{


public List<MaintainSchedule> queryScheduleByRecord(String recordId)
;

public void updateById(String maintainScheduleId)
;

}