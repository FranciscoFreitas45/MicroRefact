package org.sdrc.devinfo.repository;
 import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorEn;
import org.springframework.dao.DataAccessException;
public interface IndicatorRepository {


public List<Object[]> getIUSNIdList(Integer sectorNid)
;

public List<Object[]> findByIC_Type(String indicatorType)
;

public UtIndicatorEn findUtDataByIndicator_NId(int indicator_NId)
;

public List<UtIndicatorEn> findAll()
;

public List<Object[]> getFilteredIUSFromUTDataList(Integer[] iusNid,Integer[] areaNids)
;

public UtIndicatorEn findByIndicator_NId(int indicator_NId)
;

}