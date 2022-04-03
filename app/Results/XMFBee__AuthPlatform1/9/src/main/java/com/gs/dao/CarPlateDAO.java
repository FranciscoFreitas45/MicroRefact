package com.gs.dao;
 import com.gs.bean.CarPlate;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Repository
public interface CarPlateDAO extends BaseDAO<String, CarPlate>{


public int countByBlurred(CarPlate carPlate,User user)
;

public int queryplateName(String plateName,String plateId)
;

public List<CarPlate> blurredQuery(Pager pager,CarPlate carPlate)
;

}