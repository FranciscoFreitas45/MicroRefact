package org.live.dictionary.service;
 import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.dictionary.entity.DictType;
import javax.servlet.http.HttpServletRequest;
public interface DictTypeService extends BaseService<DictType, String>{


public DataTableModel findPage(HttpServletRequest request)
;

}