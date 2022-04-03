package org.live.school.service;
 import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.school.entity.Grade;
import javax.servlet.http.HttpServletRequest;
public interface GradeService extends BaseService<Grade, String>{


public DataTableModel findPage(HttpServletRequest request)
;

}