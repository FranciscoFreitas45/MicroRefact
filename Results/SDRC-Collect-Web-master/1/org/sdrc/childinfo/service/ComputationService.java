import java.text.ParseException;
import java.util.List;
import org.sdrc.childinfo.model.IUSValueObject;
import org.sdrc.childinfo.model.UtDataCollection;
public interface ComputationService {


public List<Object[]> fetchSubgroupByIndicatorAndUnit(Integer indicatorNId,Integer unitNId)


public UtDataCollection fetchCompositeIndexData(List<IUSValueObject> iusDataModelList,String parentAreaCode,Integer childLevel)


public List<Object[]> fetchIndicatorAndUnitBySectorNId(String sectorNId)


}