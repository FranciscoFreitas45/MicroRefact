package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DateUtilController {

 private DateUtil dateutil;


@GetMapping
("/getDBNowDate")
public ZonedDateTime getDBNowDate(@RequestParam(name = "dataFormat") String dataFormat){
  return dateutil.getDBNowDate(dataFormat);
}


@GetMapping
("/compareByDataFormatterTo")
public boolean compareByDataFormatterTo(@RequestParam(name = "firstDateTime") ZonedDateTime firstDateTime,@RequestParam(name = "secondDateTime") ZonedDateTime secondDateTime,@RequestParam(name = "dataFormat") String dataFormat){
  return dateutil.compareByDataFormatterTo(firstDateTime,secondDateTime,dataFormat);
}


@GetMapping
("/getZonedDateByTime")
public ZonedDateTime getZonedDateByTime(@RequestParam(name = "todayDate") String todayDate,@RequestParam(name = "dataFormat") String dataFormat){
  return dateutil.getZonedDateByTime(todayDate,dataFormat);
}


}