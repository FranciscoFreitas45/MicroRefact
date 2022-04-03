package de.metas.ui.web.window.descriptor.factory.standard;
 import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
public class SpecialDocumentFieldsCollector {

 private  Logger logger;

 private  Set<String> COLUMNNAMES_DocumentNos;

 private  Set<String> COLUMNNAMES_DocumentSummary;

 private  Set<String> COLUMNNAMES;

 private  boolean allowCollecting;

 private  Map<String,DocumentFieldDescriptor.Builder> collectedFields;


public void collectFinish(){
    Preconditions.checkState(allowCollecting, "allowCollecting shall be true");
    allowCollecting = false;
    // 
    // Update DocumentNo field flags
    for (final String fieldName : COLUMNNAMES_DocumentNos) {
        final DocumentFieldDescriptor.Builder field = collectedFields.get(fieldName);
        if (field == null) {
            continue;
        }
        field.addCharacteristic(Characteristic.PublicField);
        field.addCharacteristic(Characteristic.SpecialField_DocumentNo);
        // only first field shall be elected as DocumentNo
        break;
    }
}


public DocumentFieldDescriptor.Builder getDocumentSummary(){
    for (final String fieldName : COLUMNNAMES_DocumentSummary) {
        final DocumentFieldDescriptor.Builder field = collectedFields.get(fieldName);
        if (field == null) {
            continue;
        }
        field.addCharacteristic(Characteristic.PublicField);
        // field.addCharacteristic(Characteristic.SpecialField_DocumentSummary);
        return field;
    }
    return null;
}


public Map<Characteristic,DocumentFieldDescriptor.Builder> getDocStatusAndDocAction(){
    final DocumentFieldDescriptor.Builder fieldDocStatus = collectedFields.get(WindowConstants.FIELDNAME_DocStatus);
    final DocumentFieldDescriptor.Builder fieldDocAction = collectedFields.get(WindowConstants.FIELDNAME_DocAction);
    if (fieldDocStatus == null || fieldDocAction == null) {
        return null;
    }
    fieldDocStatus.addCharacteristic(Characteristic.PublicField);
    fieldDocStatus.addCharacteristic(Characteristic.SpecialField_DocStatus);
    fieldDocAction.addCharacteristic(Characteristic.PublicField);
    fieldDocAction.addCharacteristic(Characteristic.SpecialField_DocAction);
    return ImmutableMap.<Characteristic, DocumentFieldDescriptor.Builder>builder().put(Characteristic.SpecialField_DocStatus, fieldDocStatus).put(Characteristic.SpecialField_DocAction, fieldDocAction).build();
}


public void collect(DocumentFieldDescriptor.Builder field){
    Preconditions.checkState(allowCollecting, "allowCollecting shall be true");
    final String fieldName = field.getFieldName();
    if (!COLUMNNAMES.contains(fieldName)) {
        return;
    }
    final DocumentFieldDescriptor.Builder fieldAlreadyCollected = collectedFields.get(fieldName);
    if (fieldAlreadyCollected != null) {
        logger.warn("Skip collecting {} because we already collected {} for same field name", field, fieldAlreadyCollected);
        return;
    }
    collectedFields.put(fieldName, field);
}


}