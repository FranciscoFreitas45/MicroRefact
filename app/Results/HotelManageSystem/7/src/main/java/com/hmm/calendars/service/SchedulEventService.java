package com.hmm.calendars.service;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import com.hmm.calendars.entity.EventCalendarDTO;
import com.hmm.calendars.entity.ExtResultJson;
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.calendars.entity.SchedulEventDto;
import com.hmm.calendars.entity.SchedulEventEmpDTO;
import com.hmm.calendars.entity.SchedulQueryDTO;
import com.hmm.employee.entity.Employee;
public interface SchedulEventService {


public ExtResultJson<SchedulEventDto> findEvents(Long calendar,Date startDate,Date endDate)
;

public int findWorkTotalDay(String username)
;

public List<SchedulEvent> findByDTO(Specification<SchedulEvent> spec)
;

public Page<SchedulEventEmpDTO> findAllByEmpDto(Specification<SchedulEvent> spec,Pageable pageable)
;

public void save(SchedulEvent entity)
;

public long count(Specification<SchedulEvent> spec)
;

public void deleteAll(Long[] ids)
;

public SchedulEventDto findDTOByID(Long id)
;

public float findattenceTotalTime(String userbname)
;

public List<SchedulEvent> findPassDay()
;

public Page<SchedulEventDto> findAll(SchedulQueryDTO employQueryDTO,Pageable pageable)
;

public SchedulEvent findStartDate(String date,String userName)
;

public boolean existsById(Long id)
;

public List<SchedulEvent> findAllEmp(SchedulQueryDTO employQueryDTO)
;

public Integer findTotalPerson()
;

public SchedulEvent findByEventDateAndEmploy(Date EventDate,Employee employee)
;

public Optional<SchedulEvent> findById(Long id)
;

public void deleteById(Long id)
;

}