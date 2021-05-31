import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.hmm.calendars.dao.SchedulEventDao;
import com.hmm.calendars.entity.Calendar;
import com.hmm.calendars.entity.EventCalendarDTO;
import com.hmm.calendars.entity.ExtResultJson;
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.calendars.entity.SchedulEventDto;
import com.hmm.calendars.entity.SchedulEventEmpDTO;
import com.hmm.calendars.entity.SchedulQueryDTO;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
@Service
@Transactional
public class SchedulEventServiceImpl implements com.hmm.calendars.service.SchedulEventService,SchedulEventService{

@Autowired
 private  SchedulEventDao schedulEventdao;

@Autowired
 private  CalendarService calendarServiceImpl;


@Override
public ExtResultJson<SchedulEventDto> findEvents(Long calendar,Date startDate,Date endDate){
    // TODO Auto-generated method stub
    ExtResultJson<SchedulEventDto> json = new ExtResultJson<SchedulEventDto>(new ArrayList<SchedulEventDto>());
    try {
        Calendar calendars = calendarServiceImpl.findById(calendar).get();
        List<SchedulEvent> events = calendars.getEventStore();
        List<SchedulEventDto> dtos = new ArrayList<>();
        for (SchedulEvent schedulEvent : events) {
            SchedulEventDto dto = new SchedulEventDto();
            SchedulEventDto.entityToDto(schedulEvent, dto);
            dto.setCalendarId(calendar);
            Employee employee = schedulEvent.getEmploy();
            dto.setEmpName(employee.getEmpName());
            dto.setEmpNo(employee.getEmpNo());
            dtos.add(dto);
        }
        json.setLists(dtos);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return json;
}


@Override
public int findWorkTotalDay(String username){
    // TODO Auto-generated method stub
    Integer totalday = schedulEventdao.findWorkTotalDay(username);
    if (null != totalday) {
        return totalday;
    } else {
        return 0;
    }
}


@Override
public List<SchedulEvent> findByDTO(Specification<SchedulEvent> spec){
    // TODO Auto-generated method stub
    return schedulEventdao.findAll(spec);
}


@Override
public Page<SchedulEventEmpDTO> findAllByEmpDto(Specification<SchedulEvent> spec,Pageable pageable){
    // TODO Auto-generated method stub
    List<SchedulEvent> events = schedulEventdao.findAll(spec);
    List<SchedulEventEmpDTO> dtos = null;
    if (null != events) {
        dtos = new ArrayList<>();
        for (SchedulEvent schedulEvent : events) {
            SchedulEventEmpDTO eventDto = new SchedulEventEmpDTO();
            SchedulEventEmpDTO.entityToDto(schedulEvent, eventDto);
            Employee employee = schedulEvent.getEmploy();
            Department department = employee.getDepartmentes();
            eventDto.setDeptName(department.getDeptName());
            eventDto.setEmpName(employee.getEmpName());
            eventDto.setEmpNo(employee.getEmpNo());
            eventDto.setCalendar(schedulEvent.getCalendar().getTitle());
            dtos.add(eventDto);
        }
    }
    return new PageImpl<SchedulEventEmpDTO>(dtos, pageable, null != events ? events.size() : 0);
}


@Override
public void save(SchedulEvent entity){
    // TODO Auto-generated method stub
    schedulEventdao.save(entity);
}


@Override
public long count(Specification<SchedulEvent> spec){
    // TODO Auto-generated method stub
    return schedulEventdao.count(spec);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> longs = new ArrayList<>(Arrays.asList(ids));
    List<SchedulEvent> events = (List<SchedulEvent>) schedulEventdao.findAllById(longs);
    schedulEventdao.deleteAll(events);
}


@Override
public SchedulEventDto findDTOByID(Long id){
    // TODO Auto-generated method stub
    return null;
}


@Override
public float findattenceTotalTime(String userbname){
    // TODO Auto-generated method stub
    Float totalTime = schedulEventdao.findattenceTotalTime(userbname);
    if (null != totalTime) {
        return totalTime;
    } else {
        return 0;
    }
}


@Override
public List<SchedulEvent> findPassDay(){
    // TODO Auto-generated method stub
    return schedulEventdao.findPassDay();
}


@Override
public Page<SchedulEventDto> findAll(SchedulQueryDTO schedulQueryDTO,Pageable pageable){
    // TODO Auto-generated method stub
    List<SchedulEvent> events = (List<SchedulEvent>) schedulEventdao.findAll();
    List<SchedulEventDto> dtos = null;
    if (null != events) {
        dtos = new ArrayList<>();
        for (SchedulEvent schedulEvent : events) {
            SchedulEventDto eventDto = new SchedulEventDto();
            eventDto.setCalendarId(schedulEvent.getCalendar().getId());
            Employee employee = schedulEvent.getEmploy();
            eventDto.setEmpName(employee.getEmpName());
            eventDto.setEmpNo(employee.getEmpNo());
            dtos.add(eventDto);
        }
    }
    return new PageImpl<SchedulEventDto>(dtos, pageable, null != events ? events.size() : 0);
}


@Override
public SchedulEvent findStartDate(String date,String userName){
    // TODO Auto-generated method stub
    return schedulEventdao.findStartDate(date, userName);
}


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return schedulEventdao.existsById(id);
}


@SuppressWarnings("unchecked")
@Override
public List<SchedulEvent> findAllEmp(SchedulQueryDTO employQueryDTO){
    // TODO Auto-generated method stub
    return schedulEventdao.findAll((Specification<SchedulEvent>) employQueryDTO);
}


@Override
public Integer findTotalPerson(){
    // TODO Auto-generated method stub
    Integer totalday = schedulEventdao.findTotalPerson();
    if (null != totalday) {
        return totalday;
    } else {
        return 0;
    }
}


@Override
public SchedulEvent findByEventDateAndEmploy(Date EventDate,Employee employee){
    // TODO Auto-generated method stub
    SchedulEvent event = schedulEventdao.findByEventDateAndEmploy(EventDate, employee);
    event.setCalendar(null);
    return event;
}


@Override
public Optional<SchedulEvent> findById(Long id){
    // TODO Auto-generated method stub
    return schedulEventdao.findById(id);
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    schedulEventdao.deleteById(id);
}


}