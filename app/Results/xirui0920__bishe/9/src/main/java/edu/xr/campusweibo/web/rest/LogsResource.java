package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.web.rest.vm.LoggerVM;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/management")
public class LogsResource {


@PutMapping("/logs")
@ResponseStatus(HttpStatus.NO_CONTENT)
@Timed
public void changeLevel(LoggerVM jsonLogger){
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));
}


@GetMapping("/logs")
@Timed
public List<LoggerVM> getList(){
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    return context.getLoggerList().stream().map(LoggerVM::new).collect(Collectors.toList());
}


}