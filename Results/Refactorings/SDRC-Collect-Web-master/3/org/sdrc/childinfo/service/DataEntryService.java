import java.io.File;
import java.util.List;
import org.sdrc.childinfo.model.DataEntryModel;
import org.sdrc.childinfo.model.DataModel;
import org.sdrc.childinfo.model.IUSModel;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.childinfo.util.CustomErrorMessageModel;
public interface DataEntryService {


public List<ValueObject> getSubsector()


public List<ValueObject> getTimePeriod()


public void createPreviousMonth()


public String downloadExcelFile(DataEntryModel dataEntryModel)


public String downloadFactsheet(DataEntryModel dataEntryModel)


public List<IUSModel> getIUS(int id)


public File generateRawDataExcel(ValueObject timeperiodId)


public CustomErrorMessageModel excelFileUpload(byte[] bytes)


public List<DataModel> getArea()


}