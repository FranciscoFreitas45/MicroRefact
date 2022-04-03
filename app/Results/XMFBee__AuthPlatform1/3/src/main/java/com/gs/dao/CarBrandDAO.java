package com.gs.dao;
 import com.gs.bean.CarBrand;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CarBrandDAO extends BaseDAO<String, CarBrand>{


public int countByBlurred(CarBrand carBrand,User user)
;

public int querybrandName(String brandName,String brandId)
;

public List<CarBrand> blurredQuery(Pager pager,CarBrand carBrand)
;

}