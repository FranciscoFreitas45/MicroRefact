package DTO;
 import java.util.concurrent.ConcurrentHashMap;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_QuickInput;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.metas.bpartner.service.IBPartnerBL;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.NewRecordDescriptor;
import de.metas.util.Services;
public class NewRecordDescriptorsProvider {

 private  Logger logger;

 private  DocumentDescriptorFactory documentDescriptors;

 private  ConcurrentHashMap<String,NewRecordDescriptor> newRecordDescriptorsByTableName;


public NewRecordDescriptor getNewRecordDescriptorOrNull(String tableName){
    return newRecordDescriptorsByTableName.get(tableName);
}


public NewRecordDescriptor getNewRecordDescriptor(DocumentEntityDescriptor entityDescriptor){
    final int newRecordWindowId = entityDescriptor.getWindowId().toInt();
    return newRecordDescriptorsByTableName.values().stream().filter(descriptor -> newRecordWindowId == descriptor.getNewRecordWindowId()).findFirst().orElseThrow(() -> new AdempiereException("No new record quick input defined windowId=" + newRecordWindowId));
}


public DocumentEntityDescriptor getNewRecordEntityDescriptorIfAvailable(String tableName){
    final NewRecordDescriptor newRecordDescriptor = getNewRecordDescriptorOrNull(tableName);
    if (newRecordDescriptor == null) {
        return null;
    }
    try {
        return documentDescriptors.getDocumentEntityDescriptor(newRecordDescriptor.getNewRecordWindowId());
    } catch (final Exception ex) {
        logger.warn("Failed fetching document entity descriptor for {}. Ignored", newRecordDescriptor, ex);
        return null;
    }
}


}