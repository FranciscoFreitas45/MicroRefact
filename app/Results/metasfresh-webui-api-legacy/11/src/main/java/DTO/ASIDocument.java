package DTO;
 import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.adempiere.mm.attributes.AttributeSetId;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.I_M_AttributeSetInstance;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import de.metas.logging.LogManager;
import de.metas.ui.web.pattribute.ASIDescriptorFactory.ASIAttributeFieldBinding;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class ASIDocument {

 private  Logger logger;

 private  ASIDescriptor descriptor;

 private  Document data;

 private  ReentrantReadWriteLock _lock;

 private  boolean completed;


public DocumentId getDocumentId(){
    return data.getDocumentId();
}


public AttributeSetId getAttributeSetId(){
    return descriptor.getAttributeSetId();
}


public LookupValuesList getFieldLookupValues(String attributeName){
    return data.getFieldLookupValues(attributeName);
}


public ASILayout getLayout(){
    return descriptor.getLayout();
}


public Collection<IDocumentFieldView> getFieldViews(){
    return data.getFieldViews();
}


public LookupValuesList getFieldLookupValuesForQuery(String attributeName,String query){
    return data.getFieldLookupValuesForQuery(attributeName, query);
}


}