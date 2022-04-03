package com.gs.dao;
 import com.gs.bean.Flow;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface FlowDAO extends BaseDAO<String, Flow>{


public int countHistory(String companyId)
;

public List queryAcquisitionByPager(String companyId,Pager pager)
;

public List queryReturnedByPager(String companyId,Pager pager)
;

public List queryHistoryByPager(String companyId,Pager pager)
;

public int countReturned(String companyId)
;

public int countAcquisition(String companyId)
;

}