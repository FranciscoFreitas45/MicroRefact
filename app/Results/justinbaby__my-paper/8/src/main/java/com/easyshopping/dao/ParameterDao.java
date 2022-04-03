package com.easyshopping.dao;
 import java.util.List;
import java.util.Set;
import com.easyshopping.entity.Parameter;
import com.easyshopping.entity.ParameterGroup;
public interface ParameterDao extends BaseDao<Parameter, Long>{


public List<Parameter> findList(ParameterGroup parameterGroup,Set<Parameter> excludes)
;

}