package org.sdrc.devinfo.repository;
 import java.util.List;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.springframework.dao.DataAccessException;
public interface UtAreaEnRepository {


public int findAreaNidByParent(String area_name,int parent_nid)
;

public String areaNamebyAreaID(String areaCode)
;

public List<UtAreaEn> findByAreaLevel(int area_Level)
;

public UtAreaEn findByAreaID(String area_ID)
;

public List<UtAreaEn> findByAreaParentId(String areaCode)
;

public UtAreaEn findbyAreaNId(int areaNId)
;

public List<UtAreaEn> findAll()
;

public UtAreaEn findParentAreaByAreaId(String areaId)
;

public List<UtAreaEn> getClustersByDistrictAreaId(String areaCode)
;

public int findAreaNid(String area_name)
;

public String findAreaIdbyAreaName(String area_name)
;

public List<Integer> findAreaNidbyGranularity(String selectedGranularity)
;

public List<UtAreaEn> findByAreaShortName(String string)
;

public List<UtAreaEn> findByAreaParentNId(int area_Parent_NId)
;

}