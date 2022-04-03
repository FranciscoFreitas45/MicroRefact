package com.circle.dao.receive;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.payship.PayAndShip;
import com.circle.pojo.receive.ReceiveInfo;
import com.xwtec.xwserver.exception.SPTException;
public interface IReceiveInfoDao {


public boolean addReceiveInfo(ReceiveInfo receiveInfo)
;

public boolean updateReceiveInfo(ReceiveInfo receiveInfo)
;

public boolean deleteReceiveInfo(int id)
;

public List<Map<String,Object>> queryReceiveInfo(int userId)
;

public boolean defaultReceiveInfo(int id,int userId)
;

public boolean isExistsPayAndShipInfo(int userId)
;

public Map<String,Object> queryPayAndShipInfo(int userId)
;

public boolean addPayAndShipInfo(PayAndShip payAndShip)
;

public boolean updatePayAndShipInfo(PayAndShip payAndShip)
;

public boolean isExistsReceiveInfo(int userId)
;

}