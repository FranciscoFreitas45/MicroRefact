package com.gs.dao;
 import com.gs.bean.CarColor;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CarColorDAO extends BaseDAO<String, CarColor>{


public int countByBlurred(CarColor carColor,User user)
;

public List<CarColor> blurredQuery(Pager pager,CarColor carColor)
;

public int querycolorName(String colorName,String colorId)
;

}