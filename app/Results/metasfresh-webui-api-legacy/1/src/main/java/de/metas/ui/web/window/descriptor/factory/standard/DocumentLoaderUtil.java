package de.metas.ui.web.window.descriptor.factory.standard;
 import org.adempiere.ad.element.api.AdWindowId;
import org.compiere.model.GridWindowVO;
import org.compiere.util.Env;
import lombok.experimental.UtilityClass;
@UtilityClass
public class DocumentLoaderUtil {


public GridWindowVO createGridWindoVO(AdWindowId adWindowId){
    final GridWindowVO gridWindowVO = GridWindowVO.builder().ctx(Env.getCtx()).windowNo(// TODO: get rid of WindowNo from GridWindowVO
    0).adWindowId(adWindowId).adMenuId(// N/A
    -1).loadAllLanguages(true).applyRolePermissions(// must be false, unless we know that we do have #AD_User_ID in the context (which oftentimes we don't)
    false).build();
    return gridWindowVO;
}


}