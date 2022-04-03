package com.gs.service;
 import com.gs.bean.Remind;
import java.util.List;
public interface RemindService {


public int addRemind(Remind remind)
;

public int updateRemind(Remind remind)
;

public void deleteRemind(String remindId)
;

public List<Remind> selectRemind()
;

}