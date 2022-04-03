package com.evolvingreality.onleave.web.rest;
 import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.HolidayCalendarService;
import com.evolvingreality.onleave.service.PublicHolidayService;
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
public class PublicHolidayResource {

 private  PublicHolidayService publicHolidayService;

 private  HolidayCalendarService holidayCalendarService;

@Autowired
public PublicHolidayResource(final PublicHolidayService publicHolidayService, final HolidayCalendarService holidayCalendarService) {
    this.publicHolidayService = publicHolidayService;
    this.holidayCalendarService = holidayCalendarService;
}
@RequestMapping(value = "/publicholiday", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public Page<PublicHoliday> getAll(Pageable pageable){
    return publicHolidayService.getPage(pageable);
}


@RequestMapping(value = "/holidaycalendar/{holidayCalendarId}/publicholiday", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> post(Long holidayCalendarId,PublicHoliday publicHoliday){
    Optional<HolidayCalendar> holidayCalendar = holidayCalendarService.get(holidayCalendarId);
    if (publicHolidayService.hasHolidayCalendarPublicHolidayForDate(holidayCalendar.get(), publicHoliday.getHolidayDate()))
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    publicHolidayService.save(publicHoliday);
    return ResponseEntity.created(UriUtil.buildGetUri(this, publicHoliday.getId())).build();
}


@RequestMapping(value = "/publicholiday/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<PublicHoliday> get(Long id){
    Optional<PublicHoliday> publicHoliday = publicHolidayService.get(id);
    if (!publicHoliday.isPresent())
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<PublicHoliday>(publicHoliday.get(), HttpStatus.OK);
}


@RequestMapping(value = "/publicholiday", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<PublicHoliday> update(PublicHoliday publicHoliday){
    publicHolidayService.save(publicHoliday);
    return ResponseEntity.ok().body(publicHoliday);
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
}


@RequestMapping(value = "/publicholiday/year/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public Page<PublicHoliday> getAllForYear(Integer year,Pageable pageable){
    return publicHolidayService.getPage(pageable);
}


@RequestMapping(value = "/publicholiday/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> delete(Long id){
    publicHolidayService.delete(id);
    return ResponseEntity.noContent().build();
}


}