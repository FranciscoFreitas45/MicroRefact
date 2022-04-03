package run.halo.app.Interface;
public interface CommentBlackListService {

   public CommentViolationTypeEnum commentsBanStatus(String ipAddress);
}