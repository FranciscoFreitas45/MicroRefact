import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class HolidayRepoController {

 private HolidayRepo holidayrepo;


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return holidayrepo.saveAndFlush(Object);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return holidayrepo.saveAll(Object);
}


@GetMapping
("/getHolidayListByDates")
public List<Holiday> getHolidayListByDates(@RequestParam(name = "dates") List<String> dates,@RequestParam(name = "catId") int catId){
  return holidayrepo.getHolidayListByDates(dates,catId);
}


@GetMapping
("/findByHolidayIdAndDelStatus")
public Holiday findByHolidayIdAndDelStatus(@RequestParam(name = "holidayId") int holidayId,@RequestParam(name = "i") int i){
  return holidayrepo.findByHolidayIdAndDelStatus(holidayId,i);
}


@GetMapping
("/getHolidayByYearIdAndCateId")
public List<Holiday> getHolidayByYearIdAndCateId(@RequestParam(name = "yearId") int yearId,@RequestParam(name = "catId") int catId){
  return holidayrepo.getHolidayByYearIdAndCateId(yearId,catId);
}


@GetMapping
("/deleteHoliday")
public int deleteHoliday(@RequestParam(name = "holidayId") int holidayId){
  return holidayrepo.deleteHoliday(holidayId);
}


@GetMapping
("/deleteHolidayByGroup")
public int deleteHolidayByGroup(@RequestParam(name = "yearId") int yearId,@RequestParam(name = "catid") int catid){
  return holidayrepo.deleteHolidayByGroup(yearId,catid);
}


}