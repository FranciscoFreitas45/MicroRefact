package de.metas.ui.web.dataentry.interceptor;
 import org.springframework.stereotype.Component;
import de.metas.dataentry.model.I_DataEntry_Field;
import de.metas.dataentry.model.I_DataEntry_Line;
import de.metas.dataentry.model.I_DataEntry_ListValue;
import de.metas.dataentry.model.I_DataEntry_Section;
import de.metas.dataentry.model.I_DataEntry_SubTab;
import de.metas.dataentry.model.I_DataEntry_Tab;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.model.DocumentCollection;
import lombok.NonNull;
@Component
public class DataEntryInterceptorUtil {

 private  DocumentDescriptorFactory documentDescriptorFactory;

 private  DocumentCollection documentCollection;


public void resetCacheFor(I_DataEntry_Tab dataEntryGroupRecord){
    final int windowId = dataEntryGroupRecord.getDataEntry_TargetWindow_ID();
    if (windowId > 0) {
        documentDescriptorFactory.invalidateForWindow(WindowId.of(windowId));
        final boolean forgetNotSavedDocuments = false;
        documentCollection.cacheReset(forgetNotSavedDocuments);
    }
}


}