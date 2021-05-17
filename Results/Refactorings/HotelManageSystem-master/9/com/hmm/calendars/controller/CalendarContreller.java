import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.calendars.entity.Calendar;
import com.hmm.calendars.entity.CalendarDTO;
import com.hmm.calendars.entity.ExtResultJson;
import com.hmm.calendars.service.CalendarService;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
@RestController
@RequestMapping("/calendar")
public class CalendarContreller {

@Autowired
 private  CalendarService calendarServiceImpl;

 private  Logger logger;


@DeleteMapping("{id}")
public ExtAjaxResponse Delete(Long id){
    try {
        if (null != id) {
            calendarServiceImpl.deleteById(id);
            return new ExtAjaxResponse(true, "删除成功");
        } else {
            return new ExtAjaxResponse(true, "不存在");
        }
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "删除失败");
    }
}


@PostMapping
public ExtAjaxResponse saveCalendar(Calendar entity){
    try {
        if (null != entity) {
            calendarServiceImpl.save(entity);
        }
        return new ExtAjaxResponse(true, "添加成功");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "添加失败");
    }
}


@GetMapping("/findCalendars")
@ResponseBody
public ExtResultJson<CalendarDTO> findCalendars(){
    List<Calendar> calendars = calendarServiceImpl.findAll();
    List<CalendarDTO> calendarDTOs = new ArrayList<>();
    for (Calendar calendar : calendars) {
        CalendarDTO calendarDTO = new CalendarDTO();
        BeanUtils.copyProperties(calendar, calendarDTO);
        // List<SchedulEvent> events = calendar.getEventStore();
        calendarDTO.setEventStore(null);
        calendarDTOs.add(calendarDTO);
    }
    return new ExtResultJson<CalendarDTO>(calendarDTOs);
}


@GetMapping(value = "id")
public Calendar findOne(Long id){
    return calendarServiceImpl.findById(id).get();
}


@PutMapping(value = "{id}")
public ExtAjaxResponse Update(Long id,Calendar dto){
    try {
        Calendar calendar = calendarServiceImpl.findById(id).get();
        if (null != calendar) {
            BeanUtils.copyProperties(dto, calendar);
            calendarServiceImpl.save(calendar);
            return new ExtAjaxResponse(true, "修改成功");
        }
        return new ExtAjaxResponse(true, "不存在");
    } catch (Exception e) {
        // TODO: handle exception
        return new ExtAjaxResponse(true, "修改失败");
    }
}


@GetMapping()
@ResponseBody
public List<Calendar> findAll(){
    List<Calendar> calendars = calendarServiceImpl.findAll();
    for (Calendar calendar : calendars) {
        calendar.setEventStore(null);
    }
    return calendars;
}


}