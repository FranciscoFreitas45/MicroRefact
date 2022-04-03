package com.circle.service.receive;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.payship.PayAndShip;
import com.circle.pojo.receive.ReceiveInfo;
import com.circle.pojo.user.User;
public interface IReceiveInfoService {


public boolean deleteReceiveInfo(int id)
;

public List<Map<String,Object>> queryReceiveInfo(int userId)
;

public boolean defaultReceiveInfo(int id,int userId)
;

public boolean saveDefaultReceiveInfo(User user)
;

public boolean savePayAndShipInfo(PayAndShip payAndShip)
;

public Map<String,Object> queryPayAndShipInfo(int userId)
;

public boolean saveReceiveInfo(ReceiveInfo receiveInfo)
;

public boolean isExistsReceiveInfo(int userId)
;

}