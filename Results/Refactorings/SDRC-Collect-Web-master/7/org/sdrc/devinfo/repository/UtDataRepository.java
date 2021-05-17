import java.util.List;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.sdrc.devinfo.domain.UtData;
import org.sdrc.devinfo.domain.UtTimeperiod;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
public interface UtDataRepository {


public Double getDataValueForDistrict(int ius_nid,Integer timeperiod_nid,int source_nid,String areaCode)


public List<Object[]> findDataByTimeAndAreaId(Integer indicatorId,String areaId,String timeperiod)


@Transactional
public void save(Iterable<UtData> utDatas)


public UtAreaEn[] getAreaNid(String areaId,Integer childLevel)


public List<Object[]> getABRCCData(List<String> areaNames)


public List<Object[]> findComputeDataByTimePeriod(Integer indicatorId,Integer timePeriodNid,Integer[] areaNid,Integer unitNId,Integer subgroupValNId)


public List<UtTimeperiod> findTimePeriodNIdByDecadeTimePeriod(List<String> timePeriodNid)


public Double getOverAllscore(int timePeriodNid,int sourceNid,int areaNid,String iCName)


public List<Object[]> findDataByTimePeriodAndArea(Integer timeperiodId,List<Integer> areaList,Integer[] iusIds)


public List<UtData> findAll()


public List<Object[]> getByTimePeriod(int timeperiodId)


public Double getOverAllscoreForSectors(int timePeriodNid,int sourceNid,int areaNid,Integer[] iusNids)


public List<Object[]> findDataByTimePeriod(Integer indicatorId,Integer timePeriodNid,Integer sourceNid,Integer[] areaNid)


public void updateDataValue(Double data_Value,int IUSNId,int areaNid,int timeNid,int sourceNid)


public List<Object[]> findByIUSNIdAndTimePeriodNidAndSourceNidAndAreaLevel(int IUSNId,int timeNid,int sourceNid,int areaLevel)


public List<Object[]> findDataForCompositeIndex(Integer iusNId,Integer timePeriodNid,Integer[] areaNid)


public Double getAggregatedDataValueByAreaCode(int iusNid,int timePeriodNid,int sourceNid,String areaId)


public UtData findByIUSNIdAndAreaNidAndTimePeriodNidAndSourcNid(int IUSNId,int areaNid,int timeNid,int sourceNid)


public List<Object[]> findDataValueByAreaLevel(int IUSNId,int timeNid,int sourceNid,int areaLevel)


public Double getDataValueForBlock(int ius_nid,int timeperiod_nid,int source_nid,int area_NId)


public Double getAverageDataValueByAreaCode(int iusNid,int timePeriodNid,int sourceNid,String areaId)


public List<UtData> getData(int subSector,int timePeriod,int indicator,int unit,int subgroup)


public List<Object[]> findData(Integer indicatorId,Integer areaNid)


}