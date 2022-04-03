package com.gs.dao;
 import com.gs.bean.Remind;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RemindDAO {


public int addRemind(Remind remind)
;

public int updateRemind(Remind remind)
;

public void deleteRemind(String remindId)
;

public List<Remind> selectRemind()
;

}