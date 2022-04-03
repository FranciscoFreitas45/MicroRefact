package com.hmm.calendars.controller;
 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.hmm.Work.entity.Work;
import com.hmm.calendars.entity.Calendar;
import com.hmm.calendars.entity.ExtResultJson;
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.calendars.entity.SchedulEventDto;
import com.hmm.calendars.entity.SchedulEventEmpDTO;
import com.hmm.calendars.entity.SchedulQueryDTO;
import com.hmm.calendars.service.CalendarService;
import com.hmm.calendars.service.SchedulEventService;
import com.hmm.common.SessionUtil;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.department.entity.Department;
import com.hmm.department.service.DeptService;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.DeptService;
@RestController
@RequestMapping("/CalendarEvent")
public class CalendarEventController {

@Autowired
 private  CalendarService calendarServiceImpl;

@Autowired
 private  SchedulEventService schedulEventServiceImpl;

@Autowired
 private  EmployeeService employServiceImpl;

@Autowired
 private  EmployeeService employService;

@Autowired
 private  DeptService iDeptService;

 private  Logger logger;


@DeleteMapping("/findEvents/{id}")
public ExtAjaxResponse deleterow(Long id){
    try {
        if (null != id) {
            schedulEventServiceImpl.deleteById(id);
            return new ExtAjaxResponse(true, "成功");
        }
        return new ExtAjaxResponse(false, "失败");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "失败");
    }
}


@RequestMapping("/findEvents")
@ResponseBody
public ExtResultJson<SchedulEventDto> findEvents(Long calendar,Date startDate,Date endDate){
    return schedulEventServiceImpl.findEvents(calendar, startDate, endDate);
}


@PostMapping(value = "/edit")
public ExtAjaxResponse edit(SchedulEventDto eventDto){
    try {
        SchedulEvent event = schedulEventServiceImpl.findById(eventDto.getId()).get();
        Calendar calendar = calendarServiceImpl.findById(eventDto.getCalendarId()).get();
        Employee employ = employService.findByEmpName(eventDto.getEmpName());
        if (null != calendar && null != employ) {
            BeanUtils.copyProperties(eventDto, event);
            event.setCalendar(calendar);
            event.setEmploy(employ);
            schedulEventServiceImpl.save(event);
            return new ExtAjaxResponse(true, "成功");
        }
        return new ExtAjaxResponse(true, "失败");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "失败");
    }
}


@RequestMapping("deletes")
public ExtAjaxResponse deleteRows(Long[] ids){
    try {
        if (null != ids) {
            schedulEventServiceImpl.deleteAll(ids);
            return new ExtAjaxResponse(true, "成功");
        } else {
            return new ExtAjaxResponse(false, "失败");
        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return new ExtAjaxResponse(false, "失败");
    }
}


@PostMapping(value = "/add")
public ExtAjaxResponse Save(SchedulEventDto eventDto){
    try {
        Calendar calendar = calendarServiceImpl.findById(eventDto.getCalendarId()).get();
        logger.info(eventDto.getStartDate().toString());
        Employee employ = employService.findByEmpNameAndEmpNo(eventDto.getEmpName(), eventDto.getEmpNo());
        if (null != eventDto) {
            SchedulEvent event = new SchedulEvent();
            SchedulEventDto.dtoToEntity(eventDto, event);
            if (null != calendar && null != employ) {
                event.setCalendar(calendar);
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String EventDate = sDateFormat.format(eventDto.getStartDate());
                event.setEventDate(EventDate);
                event.setEmploy(employ);
                schedulEventServiceImpl.save(event);
            }
        }
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@RequestMapping("/findEmpEvents")
public Page<SchedulEventEmpDTO> findEmp(SchedulQueryDTO schedulQueryDTO,Pageable pageable){
    if (null != schedulQueryDTO.getDeptName()) {
        Department department = iDeptService.findByDeptName(schedulQueryDTO.getDeptName());
        schedulQueryDTO.setDepartment(department);
    }
    return schedulEventServiceImpl.findAllByEmpDto(SchedulQueryDTO.getWhereClause(schedulQueryDTO), pageable);
}


@InitBinder
public void initBinder(WebDataBinder binder,WebRequest request){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
}


@RequestMapping("/findDATE")
@ResponseBody
public List<SchedulEventDto> findByWorkDateAndEmploy(HttpSession session) throws ParseException{
    String userId = SessionUtil.getUserName(session);
    Employee employee = employServiceImpl.findByUserName("durenniu");
    String currentTime = "2018-10-16";
    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date currentDate = sDateFormat.parse(currentTime);
    System.out.println(currentDate);
    Department department = employee.getDepartmentes();
    SchedulQueryDTO queryDTO = new SchedulQueryDTO();
    // queryDTO.setUserName("admin");
    queryDTO.setDepartment(department);
    // queryDTO.setEventDate(currentTime);
    List<SchedulEvent> events = schedulEventServiceImpl.findByDTO(SchedulQueryDTO.getWhereClause(queryDTO));
    System.out.println(events);
    List<SchedulEventDto> schedulEvents = new ArrayList<SchedulEventDto>();
    for (SchedulEvent schedulEvent : events) {
        SchedulEventDto dto = new SchedulEventDto();
        SchedulEventDto.entityToDto(schedulEvent, dto);
        dto.setId(schedulEvent.getCalendar().getId());
        dto.setEmpName(schedulEvent.getEmploy().getEmpName());
        dto.setEmpNo(schedulEvent.getEmploy().getEmpNo());
        schedulEvents.add(dto);
    }
    return schedulEvents;
}


@PostMapping("/delete")
public ExtAjaxResponse delete(Long id){
    try {
        if (null != id) {
            schedulEventServiceImpl.deleteById(id);
            return new ExtAjaxResponse(true, "成功");
        }
        return new ExtAjaxResponse(true, "失败");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "失败");
    }
}


@GetMapping("/Events")
public Page<SchedulEventDto> findAll(SchedulQueryDTO employQueryDTO,Pageable pageable){
    return schedulEventServiceImpl.findAll(employQueryDTO, pageable);
}


}