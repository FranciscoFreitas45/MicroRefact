package com.evolvingreality.onleave.web.rest;
 import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.annotation.Timed;
import com.evolvingreality.onleave.web.rest.dto.LoggerDTO;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class LogsResource {


@RequestMapping(value = "/logs", method = RequestMethod.PUT)
@ResponseStatus(HttpStatus.NO_CONTENT)
@Timed
public void changeLevel(LoggerDTO jsonLogger){
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));
}


@RequestMapping(value = "/logs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public List<LoggerDTO> getList(){
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    return context.getLoggerList().stream().map(LoggerDTO::new).collect(Collectors.toList());
}


}