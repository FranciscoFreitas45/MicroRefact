package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.entity.JournalComment;
import run.halo.app.model.vo.JournalCommentWithJournalVO;
import run.halo.app.service.base.BaseCommentService;
public interface JournalCommentService extends BaseCommentService<JournalComment>{


@NonNull
public Page<JournalCommentWithJournalVO> convertToWithJournalVo(Page<JournalComment> journalCommentPage)
;

}