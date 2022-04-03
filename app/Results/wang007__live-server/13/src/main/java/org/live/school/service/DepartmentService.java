package org.live.school.service;
 import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.school.entity.Department;
import javax.servlet.http.HttpServletRequest;
public interface DepartmentService extends BaseService<Department, String>{


public DataTableModel findPage(HttpServletRequest request)
;

}