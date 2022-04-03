package run.halo.app.service;
 import org.springframework.data.domain.Page;
import run.halo.app.model.dto.LogDTO;
import run.halo.app.model.entity.Log;
import run.halo.app.service.base.CrudService;
public interface LogService extends CrudService<Log, Long>{


public Page<LogDTO> pageLatest(int top)
;

}