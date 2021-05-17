import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import cn.com.cnc.fcc.service.dto.QmsEntryControlDetailsDTO;
@Service
public interface QmsEntryControlDetailsService {


public JSONObject uploadData(MultipartFile files)


public List<QmsEntryControlDetailsDTO> getAllInfoNumber(HashMap<String,Object> param)


public List<QmsEntryControlDetailsDTO> selectAllInfo(HashMap<String,Object> param)


public JSONObject uploadUserDepart(MultipartFile files)


}