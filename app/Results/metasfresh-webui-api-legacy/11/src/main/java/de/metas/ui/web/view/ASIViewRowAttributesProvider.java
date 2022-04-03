package de.metas.ui.web.view;
 import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import de.metas.ui.web.pattribute.ASIDocument;
import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.pattribute.ASIRepository;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;
public class ASIViewRowAttributesProvider implements IViewRowAttributesProvider{

 private  ASIRepository asiRepository;

 private  Map<DocumentId,ASIViewRowAttributes> attributesById;


@Override
public IViewRowAttributes getAttributes(DocumentId rowId_NOTUSED,DocumentId asiId){
    return attributesById.computeIfAbsent(asiId, this::createAttributes);
}


@Override
public void invalidateAll(){
    attributesById.clear();
}


public ASIViewRowAttributesProvider newInstance(ASIRepository asiRepository){
    return new ASIViewRowAttributesProvider(asiRepository);
}


public ASIViewRowAttributes createAttributes(DocumentId asiDocumentId){
    final AttributeSetInstanceId asiId = AttributeSetInstanceId.ofRepoIdOrNull(asiDocumentId.toInt());
    if (asiId == null) {
        throw new AdempiereException("Invalid ASI document ID: " + asiDocumentId);
    }
    final ASIDocument asiDoc = asiRepository.loadReadonly(asiId);
    final ASILayout asiLayout = asiDoc.getLayout();
    return new ASIViewRowAttributes(asiDoc, asiLayout);
}


}