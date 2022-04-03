package com.gs.dao;
 import com.gs.bean.CarModel;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CarModelDAO extends BaseDAO<String, CarModel>{


public int countByBlurred(CarModel carModel,User user)
;

public List<CarModel> blurredQuery(Pager pager,CarModel carModel)
;

public int querymodelName(String modelName,String modelId)
;

public List<CarModel> queryByBrandId(String id)
;

}