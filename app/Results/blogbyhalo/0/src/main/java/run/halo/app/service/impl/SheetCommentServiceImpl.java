package run.halo.app.service.impl;
 import run.halo.app.model.support.HaloConst.URL_SEPARATOR;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.BadRequestException;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.post.BasePostMinimalDTO;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.entity.SheetComment;
import run.halo.app.model.enums.SheetPermalinkType;
import run.halo.app.model.vo.SheetCommentWithSheetVO;
import run.halo.app.repository.SheetCommentRepository;
import run.halo.app.repository.SheetRepository;
import run.halo.app.service.OptionService;
import run.halo.app.service.SheetCommentService;
import run.halo.app.service.UserService;
import run.halo.app.utils.ServiceUtils;
import run.halo.app.Interface.SheetRepository;
@Service
public class SheetCommentServiceImpl extends BaseCommentServiceImpl<SheetComment>implements SheetCommentService{

 private  SheetRepository sheetRepository;

public SheetCommentServiceImpl(SheetCommentRepository sheetCommentRepository, OptionService optionService, UserService userService, ApplicationEventPublisher eventPublisher, SheetRepository sheetRepository) {
    super(sheetCommentRepository, optionService, userService, eventPublisher);
    this.sheetRepository = sheetRepository;
}
public BasePostMinimalDTO buildSheetFullPath(BasePostMinimalDTO basePostMinimalDto){
    StringBuilder fullPath = new StringBuilder();
    SheetPermalinkType permalinkType = optionService.getSheetPermalinkType();
    if (optionService.isEnabledAbsolutePath()) {
        fullPath.append(optionService.getBlogBaseUrl());
    }
    if (permalinkType.equals(SheetPermalinkType.SECONDARY)) {
        fullPath.append(URL_SEPARATOR).append(optionService.getSheetPrefix()).append(URL_SEPARATOR).append(basePostMinimalDto.getSlug()).append(optionService.getPathSuffix());
    } else if (permalinkType.equals(SheetPermalinkType.ROOT)) {
        fullPath.append(URL_SEPARATOR).append(basePostMinimalDto.getSlug()).append(optionService.getPathSuffix());
    }
    basePostMinimalDto.setFullPath(fullPath.toString());
    return basePostMinimalDto;
}


@Override
public void validateTarget(Integer sheetId){
    Sheet sheet = sheetRepository.findById(sheetId).orElseThrow(() -> new NotFoundException("查询不到该页面的信息").setErrorData(sheetId));
    if (sheet.getDisallowComment()) {
        throw new BadRequestException("该页面已被禁止评论").setErrorData(sheetId);
    }
}


@Override
@NonNull
public Page<SheetCommentWithSheetVO> convertToWithSheetVo(Page<SheetComment> sheetCommentPage){
    Assert.notNull(sheetCommentPage, "Sheet comment page must not be null");
    return new PageImpl<>(convertToWithSheetVo(sheetCommentPage.getContent()), sheetCommentPage.getPageable(), sheetCommentPage.getTotalElements());
}


}