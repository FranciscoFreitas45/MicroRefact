package run.halo.app.service;
 import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import run.halo.app.model.dto.IndependentSheetDTO;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.entity.SheetMeta;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.SheetDetailVO;
import run.halo.app.model.vo.SheetListVO;
import run.halo.app.service.base.BasePostService;
public interface SheetService extends BasePostService<Sheet>{


public Sheet createBy(Sheet sheet,Set<SheetMeta> metas,boolean autoSave)
;

public Sheet updateBy(Sheet sheet,Set<SheetMeta> metas,boolean autoSave)
;

@NonNull
public String exportMarkdown(Sheet sheet)
;

@Override
public Sheet getBy(PostStatus status,String slug)
;

@NonNull
public Sheet importMarkdown(String markdown)
;

public void publishVisitEvent(Integer sheetId)
;

@NonNull
public Page<SheetListVO> convertToListVo(Page<Sheet> sheetPage)
;

@NonNull
public List<IndependentSheetDTO> listIndependentSheets()
;

@NonNull
public SheetDetailVO convertToDetailVo(Sheet sheet)
;

}