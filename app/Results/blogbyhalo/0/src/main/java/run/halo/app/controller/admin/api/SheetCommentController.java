package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.BaseCommentDTO;
import run.halo.app.model.entity.SheetComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.params.CommentQuery;
import run.halo.app.model.params.SheetCommentParam;
import run.halo.app.model.vo.BaseCommentVO;
import run.halo.app.model.vo.BaseCommentWithParentVO;
import run.halo.app.model.vo.SheetCommentWithSheetVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.SheetCommentService;
import run.halo.app.Interface.OptionService;
@RestController
@RequestMapping("/api/admin/sheets/comments")
public class SheetCommentController {

 private  SheetCommentService sheetCommentService;

 private  OptionService optionService;

public SheetCommentController(SheetCommentService sheetCommentService, OptionService optionService) {
    this.sheetCommentService = sheetCommentService;
    this.optionService = optionService;
}
@GetMapping
@ApiOperation("Lists sheet comments")
public Page<SheetCommentWithSheetVO> pageBy(Pageable pageable,CommentQuery commentQuery){
    Page<SheetComment> sheetCommentPage = sheetCommentService.pageBy(commentQuery, pageable);
    return sheetCommentService.convertToWithSheetVo(sheetCommentPage);
}


@GetMapping("latest")
@ApiOperation("Lists latest sheet comments")
public List<SheetCommentWithSheetVO> listLatest(int top,CommentStatus status){
    Page<SheetComment> sheetCommentPage = sheetCommentService.pageLatest(top, status);
    return sheetCommentService.convertToWithSheetVo(sheetCommentPage.getContent());
}


@GetMapping("{sheetId:\\d+}/tree_view")
@ApiOperation("Lists sheet comments with tree view")
public Page<BaseCommentVO> listCommentTree(Integer sheetId,int page,Sort sort){
    return sheetCommentService.pageVosAllBy(sheetId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@PostMapping
@ApiOperation("Creates a sheet comment (new or reply)")
public BaseCommentDTO createBy(SheetCommentParam commentParam){
    SheetComment createdComment = sheetCommentService.createBy(commentParam);
    return sheetCommentService.convertTo(createdComment);
}


@GetMapping("{sheetId:\\d+}/list_view")
@ApiOperation("Lists sheet comment with list view")
public Page<BaseCommentWithParentVO> listComments(Integer sheetId,int page,Sort sort){
    return sheetCommentService.pageWithParentVoBy(sheetId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
}


@PutMapping("{commentId:\\d+}/status/{status}")
@ApiOperation("Updates sheet comment status")
public BaseCommentDTO updateStatusBy(Long commentId,CommentStatus status){
    // Update comment status
    SheetComment updatedSheetComment = sheetCommentService.updateStatus(commentId, status);
    return sheetCommentService.convertTo(updatedSheetComment);
}


@PutMapping("{commentId:\\d+}")
@ApiOperation("Updates a sheet comment")
public BaseCommentDTO updateBy(SheetCommentParam commentParam,Long commentId){
    SheetComment commentToUpdate = sheetCommentService.getById(commentId);
    commentParam.update(commentToUpdate);
    return sheetCommentService.convertTo(sheetCommentService.update(commentToUpdate));
}


@GetMapping("{commentId:\\d+}")
@ApiOperation("Gets a sheet comment by comment id")
public SheetCommentWithSheetVO getBy(Long commentId){
    SheetComment comment = sheetCommentService.getById(commentId);
    return sheetCommentService.convertToWithSheetVo(comment);
}


@DeleteMapping
@ApiOperation("Deletes sheet comments permanently in batch by id array")
public List<SheetComment> deletePermanentlyInBatch(List<Long> ids){
    return sheetCommentService.removeByIds(ids);
}


@PutMapping("status/{status}")
@ApiOperation("Updates sheet comment status in batch")
public List<BaseCommentDTO> updateStatusInBatch(CommentStatus status,List<Long> ids){
    List<SheetComment> comments = sheetCommentService.updateStatusByIds(ids, status);
    return sheetCommentService.convertTo(comments);
}


@DeleteMapping("{commentId:\\d+}")
@ApiOperation("Deletes sheet comment permanently and recursively")
public BaseCommentDTO deletePermanently(Long commentId){
    SheetComment deletedSheetComment = sheetCommentService.removeById(commentId);
    return sheetCommentService.convertTo(deletedSheetComment);
}


}