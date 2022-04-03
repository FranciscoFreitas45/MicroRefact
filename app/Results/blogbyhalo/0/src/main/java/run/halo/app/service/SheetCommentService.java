package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.entity.SheetComment;
import run.halo.app.model.vo.SheetCommentWithSheetVO;
import run.halo.app.service.base.BaseCommentService;
public interface SheetCommentService extends BaseCommentService<SheetComment>{


@NonNull
public Page<SheetCommentWithSheetVO> convertToWithSheetVo(Page<SheetComment> sheetCommentPage)
;

}