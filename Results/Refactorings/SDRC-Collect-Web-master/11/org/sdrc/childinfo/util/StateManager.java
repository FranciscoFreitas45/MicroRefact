import java.util.List;
import org.sdrc.childinfo.model.Error;
public interface StateManager {


public List<Error> getError()


public Object getValue(String key)


public void setError(List<Error> errModels)


public void setValue(String Key,Object Value)


}