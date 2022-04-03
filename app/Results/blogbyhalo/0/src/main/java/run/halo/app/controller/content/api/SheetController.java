package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.model.dto.BaseCommentDTO;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.entity.SheetComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.SheetCommentParam;
import run.halo.app.model.vo.BaseCommentVO;
import run.halo.app.model.vo.BaseCommentWithParentVO;
import run.halo.app.model.vo.CommentWithHasChildrenVO;
import run.halo.app.model.vo.SheetDetailVO;
import run.halo.app.model.vo.SheetListVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.SheetCommentService;
import run.halo.app.service.SheetService;
import run.halo.app.Interface.SheetService;
import run.halo.app.Interface.OptionService;
@RestController("ApiContentSheetController")
@RequestMapping("/api/content/sheets")
public class SheetController {

 private  SheetService sheetService;

 private  SheetCommentService sheetCommentService;

 private  OptionService optionService;

public SheetController(SheetService sheetService, SheetCommentService sheetCommentService, OptionService optionService) {
    this.sheetService = sheetService;
    this.sheetCommentService = sheetCommentService;
    this.optionService = optionService;
}
@GetMapping
@ApiOperation("Lists sheets")
public Page<SheetListVO> pageBy(Pageable pageable){
    Page<Sheet> sheetPage = sheetService.pageBy(PostStatus.PUBLISHED, pageable);
    return sheetService.convertToListVo(sheetPage);
}


@GetMapping("{sheetId:\\d+}/comments/top_view")
public Page<CommentWithHasChildrenVO> listTopComments(Integer sheetId,int page,Sort sort){
    return sheetCommentService.pageTopCommentsBy(sheetId, CommentStatus.PUBLISHED, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@GetMapping("{sheetId:\\d+}/comments/list_view")
@ApiOperation("Lists comment with list view")
public Page<BaseCommentWithParentVO> listComments(Integer sheetId,int page,Sort sort){
    return sheetCommentService.pageWithParentVoBy(sheetId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@GetMapping("/slug")
@ApiOperation("Gets a sheet by slug")
public SheetDetailVO getBy(String slug,Boolean formatDisabled,Boolean sourceDisabled){
    SheetDetailVO sheetDetailVO = sheetService.convertToDetailVo(sheetService.getBySlug(slug));
    if (formatDisabled) {
        // Clear the format content
        sheetDetailVO.setFormatContent(null);
    }
    if (sourceDisabled) {
        // Clear the original content
        sheetDetailVO.setOriginalContent(null);
    }
    sheetService.publishVisitEvent(sheetDetailVO.getId());
    return sheetDetailVO;
}


@PostMapping("comments")
@ApiOperation("Comments a post")
@CacheLock(autoDelete = false, traceRequest = true)
public BaseCommentDTO comment(SheetCommentParam sheetCommentParam){
    // Escape content
    sheetCommentParam.setContent(HtmlUtils.htmlEscape(sheetCommentParam.getContent(), StandardCharsets.UTF_8.displayName()));
    return sheetCommentService.convertTo(sheetCommentService.createBy(sheetCommentParam));
}


@GetMapping("{sheetId:\\d+}/comments/tree_view")
@ApiOperation("Lists comments with tree view")
public Page<BaseCommentVO> listCommentsTree(Integer sheetId,int page,Sort sort){
    return sheetCommentService.pageVosBy(sheetId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@GetMapping("{sheetId:\\d+}/comments/{commentParentId:\\d+}/children")
public List<BaseCommentDTO> listChildrenBy(Integer sheetId,Long commentParentId,Sort sort){
    // Find all children comments
    List<SheetComment> sheetComments = sheetCommentService.listChildrenBy(sheetId, commentParentId, CommentStatus.PUBLISHED, sort);
    // Convert to base comment dto
    return sheetCommentService.convertTo(sheetComments);
}


}