package br.com.fatecmogidascruzes.employee.service.web;
 import java.util.List;
import br.com.fatecmogidascruzes.course.Course;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
public interface EmployeeWebService {


public List<EmployeeEntryDTO> getEnabledByCourse(Course course)
;

public TableDTO<EmployeeTableRowDTO> getTable(SearchCriteria searchCriteria,int draw)
;

public void save(EmployeeEditDTO expenseEditDTO) throws BadRequestException
;

public List<EmployeeEntryDTO> getEnabledProfessors()
;

}