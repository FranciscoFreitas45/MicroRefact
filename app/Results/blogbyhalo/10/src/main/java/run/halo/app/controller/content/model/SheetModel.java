package run.halo.app.controller.content.model;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import run.halo.app.cache.AbstractStringCacheStore;
import run.halo.app.exception.ForbiddenException;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.entity.SheetMeta;
import run.halo.app.model.enums.PostEditorType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.support.HaloConst;
import run.halo.app.model.vo.SheetDetailVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.SheetMetaService;
import run.halo.app.service.SheetService;
import run.halo.app.service.ThemeService;
import run.halo.app.utils.MarkdownUtils;
import run.halo.app.Interface.AbstractStringCacheStore;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.OptionService;
@Component
public class SheetModel {

 private  SheetService sheetService;

 private  SheetMetaService sheetMetaService;

 private  AbstractStringCacheStore cacheStore;

 private  ThemeService themeService;

 private  OptionService optionService;

public SheetModel(SheetService sheetService, SheetMetaService sheetMetaService, AbstractStringCacheStore cacheStore, ThemeService themeService, OptionService optionService) {
    this.sheetService = sheetService;
    this.sheetMetaService = sheetMetaService;
    this.cacheStore = cacheStore;
    this.themeService = themeService;
    this.optionService = optionService;
}
public String content(Sheet sheet,String token,Model model){
    if (StringUtils.isEmpty(token)) {
        sheet = sheetService.getBy(PostStatus.PUBLISHED, sheet.getSlug());
    } else {
        // verify token
        String cachedToken = cacheStore.getAny(token, String.class).orElseThrow(() -> new ForbiddenException("您没有该页面的访问权限"));
        if (!cachedToken.equals(token)) {
            throw new ForbiddenException("您没有该页面的访问权限");
        }
        // render markdown to html when preview sheet
        if (sheet.getEditorType().equals(PostEditorType.MARKDOWN)) {
            sheet.setFormatContent(MarkdownUtils.renderHtml(sheet.getOriginalContent()));
        } else {
            sheet.setFormatContent(sheet.getOriginalContent());
        }
    }
    sheetService.publishVisitEvent(sheet.getId());
    SheetDetailVO sheetDetailVO = sheetService.convertToDetailVo(sheet);
    List<SheetMeta> metas = sheetMetaService.listBy(sheet.getId());
    // Generate meta keywords.
    if (StringUtils.isNotEmpty(sheet.getMetaKeywords())) {
        model.addAttribute("meta_keywords", sheet.getMetaKeywords());
    } else {
        model.addAttribute("meta_keywords", optionService.getSeoKeywords());
    }
    // Generate meta description.
    if (StringUtils.isNotEmpty(sheet.getMetaDescription())) {
        model.addAttribute("meta_description", sheet.getMetaDescription());
    } else {
        model.addAttribute("meta_description", sheetService.generateDescription(sheet.getFormatContent()));
    }
    // sheet and post all can use
    model.addAttribute("sheet", sheetDetailVO);
    model.addAttribute("post", sheetDetailVO);
    model.addAttribute("is_sheet", true);
    model.addAttribute("metas", sheetMetaService.convertToMap(metas));
    if (themeService.templateExists(ThemeService.CUSTOM_SHEET_PREFIX + sheet.getTemplate() + HaloConst.SUFFIX_FTL)) {
        return themeService.render(ThemeService.CUSTOM_SHEET_PREFIX + sheet.getTemplate());
    }
    return themeService.render("sheet");
}


}