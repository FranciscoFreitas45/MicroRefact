package com.gs.service;
 import com.gs.bean.CarModel;
import java.util.List;
public interface CarModelService extends BaseService<String, CarModel>{


public int querymodelName(String modelName,String modelId)
;

public List<CarModel> queryByBrandId(String id)
;

}