package com.gs.service;
 import com.gs.bean.CarPlate;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface CarPlateService extends BaseService<String, CarPlate>{


public int queryplateName(String plateName,String plateId)
;

}