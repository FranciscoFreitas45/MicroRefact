package de.metas.ui.web.dataentry.interceptor;
 import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.compiere.model.ModelValidator;
import org.springframework.stereotype.Component;
import de.metas.dataentry.model.I_DataEntry_Line;
import lombok.NonNull;
@Component
@Interceptor(I_DataEntry_Line.class)
public class DataEntry_Line {

 private  DataEntryInterceptorUtil dataEntryInterceptorUtil;


@ModelChange(timings = { ModelValidator.TYPE_AFTER_NEW, ModelValidator.TYPE_AFTER_CHANGE, ModelValidator.TYPE_BEFORE_DELETE })
public void invalidateDocumentDescriptorCache(I_DataEntry_Line dataEntryLineRecord){
    dataEntryInterceptorUtil.resetCacheFor(dataEntryLineRecord);
}


}