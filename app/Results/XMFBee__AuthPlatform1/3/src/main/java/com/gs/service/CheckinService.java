package com.gs.service;
 import com.gs.bean.Checkin;
import java.util.List;
public interface CheckinService extends BaseService<String, Checkin>{


public List<Checkin> queryByPhone(String userPhone)
;

public void updateUserIds(String userId,String chIds)
;

}