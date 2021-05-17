import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.sdrc.childinfo.domain.CounterCount;
import org.sdrc.childinfo.model.LineSeries;
import org.sdrc.childinfo.model.UtDataCollection;
import org.sdrc.childinfo.model.UtDataModel;
import org.sdrc.childinfo.model.ValueObject;
public interface DashboardService {


public List<ValueObject> fetchUtTimeperiod(Integer iusNid,Integer SourceNid)


public CounterCount getCounter()


public UtDataCollection fetchBurdenData(Integer iusNIdForProjection,Integer iusNId,Integer iusNIdForIMRorU5MR,String parentAreaCode,String timeperiodId,Integer childLevel,Integer sourceNIdForProjection,Integer sourceNId,Integer sourceNIdForIMRorU5MR)


public CounterCount fetchCounter()


public List<UtDataModel> fetchPdfData(String indicatorId,String sourceId,String areaId,String timePeriodId,Integer childLevel)


public List<ValueObject> fetchSources(String param)


public List<List<LineSeries>> fetchChartData(Integer iusNid,Integer areaNid)


public UtDataCollection fetchData(String indicatorId,String sourceId,String parentAreaCode,String timeperiodId,Integer childLevel)


public List<ValueObject> fetchAllSectors(String ic_type)


public List<ValueObject> fetchSourcesByLevel(String param,Integer childLevel)


public List<List<Map<Object,String>>> fetchColChartData(Integer iusNid,Integer areaNid)


public UtDataCollection fetchComputeData(String indicatorId,String parentAreaCode,String timeperiodId,Integer childLevel,Integer sourceNId)


public List<ValueObject> fetchIndicators(String indicatorType)


}