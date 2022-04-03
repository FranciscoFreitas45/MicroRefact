package run.halo.app.service;
 import run.halo.app.model.entity.CommentBlackList;
import run.halo.app.model.enums.CommentViolationTypeEnum;
import run.halo.app.service.base.CrudService;
public interface CommentBlackListService extends CrudService<CommentBlackList, Long>{


public CommentViolationTypeEnum commentsBanStatus(String ipAddress)
;

}