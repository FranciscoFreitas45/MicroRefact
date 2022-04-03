package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.LogDTO;
import run.halo.app.model.entity.Log;
import run.halo.app.service.LogService;
@RestController
@RequestMapping("/api/admin/logs")
public class LogController {

 private  LogService logService;

public LogController(LogService logService) {
    this.logService = logService;
}
@GetMapping
@ApiOperation("Lists logs")
public Page<LogDTO> pageBy(Pageable pageable){
    Page<Log> logPage = logService.listAll(pageable);
    return logPage.map(log -> new LogDTO().convertFrom(log));
}


@GetMapping("clear")
@ApiOperation("Clears all logs")
public void clear(){
    logService.removeAll();
}


@GetMapping("latest")
@ApiOperation("Pages latest logs")
public List<LogDTO> pageLatest(int top){
    return logService.pageLatest(top).getContent();
}


}