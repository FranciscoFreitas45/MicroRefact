package com.evolvingreality.onleave.web.rest;
 import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.HolidayCalendarService;
import com.evolvingreality.onleave.web.propertyeditors.LocaleDateTimeEditor;
import com.evolvingreality.onleave.web.rest.util.UriUtil;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
@RestController
@RequestMapping("/api/v1.0")
public class HolidayCalendarResource {

 private  HolidayCalendarService holidayCalendarService;

@Autowired
public HolidayCalendarResource(final HolidayCalendarService holidayCalendarService) {
    this.holidayCalendarService = holidayCalendarService;
}
@RequestMapping(value = "/holidaycalendar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public Page<HolidayCalendar> getAll(Pageable pageable){
    return holidayCalendarService.getPage(pageable);
}


@RequestMapping(value = "/holidaycalendar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> post(HolidayCalendar holidayCalendar){
    holidayCalendarService.save(holidayCalendar);
    return ResponseEntity.created(UriUtil.buildGetUri(this, holidayCalendar.getId())).build();
}


@RequestMapping(value = "/holidaycalendar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<HolidayCalendar> get(Long id){
    Optional<HolidayCalendar> holidayCalendar = holidayCalendarService.get(id);
    if (!holidayCalendar.isPresent())
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<HolidayCalendar>(holidayCalendar.get(), HttpStatus.OK);
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
}


@RequestMapping(value = "/holidaycalendar/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> delete(Long id){
    holidayCalendarService.delete(id);
    return ResponseEntity.noContent().build();
}


@RequestMapping(value = "/holidaycalendar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<HolidayCalendar> put(HolidayCalendar holidayCalendar){
    holidayCalendarService.save(holidayCalendar);
    return ResponseEntity.ok().body(holidayCalendar);
}


}