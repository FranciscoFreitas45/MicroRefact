package org.live.dictionary.service;
 import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.dictionary.entity.Dictionary;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
public interface DictService extends BaseService<Dictionary, String>{


public String getDictKey(String dicVal)
;

public Map<String,String> getDictMap(String dictTypeKey)
;

public void cacheDic()
;

public String getDicVal(String dicKey)
;

public DataTableModel findPage(HttpServletRequest request)
;

public List<Dictionary> getDictList(String dictTypeKey)
;

}