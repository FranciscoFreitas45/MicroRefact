package de.metas.ui.web.window.model;
 import java.util.Properties;
import java.util.Set;
import org.adempiere.ad.persistence.TableModelLoader;
import org.adempiere.ad.service.IDeveloperModeBL;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.wrapper.AbstractInterfaceWrapperHelper;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;
import de.metas.logging.LogManager;
import de.metas.util.Services;
public class DocumentInterfaceWrapperHelper extends AbstractInterfaceWrapperHelper{

 private  Logger logger;


@Override
public T getDynAttribute(Object model,String attributeName){
    return DocumentInterfaceWrapper.getDocument(model).getDynAttribute(attributeName);
}


@Override
public Object setDynAttribute(Object model,String attributeName,Object value){
    return DocumentInterfaceWrapper.getDocument(model).setDynAttribute(attributeName, value);
}


@Override
public String getTrxName(Object model,boolean ignoreIfNotHandled){
    return ITrx.TRXNAME_None;
}


@Override
public Evaluatee getEvaluatee(Object model){
    return DocumentInterfaceWrapper.getDocument(model).asEvaluatee();
}


@Override
public void refresh(Object model,String trxName){
    DocumentInterfaceWrapper.refresh(model);
}


@Override
public int getId(Object model){
    return DocumentInterfaceWrapper.getId(model);
}


@Override
public String getModelTableNameOrNull(Object model){
    final Document document = DocumentInterfaceWrapper.getDocument(model);
    return document.getEntityDescriptor().getTableNameOrNull();
}


@Override
public boolean isNew(Object model){
    return DocumentInterfaceWrapper.isNew(model);
}


@Override
public boolean isValueChanged(Object model,Set<String> columnNames){
    if (columnNames == null || columnNames.isEmpty()) {
        return false;
    }
    final Document document = DocumentInterfaceWrapper.getDocument(model);
    if (document == null) {
        return false;
    }
    for (final String columnName : columnNames) {
        final IDocumentFieldView field = document.getFieldViewOrNull(columnName);
        if (field == null) {
            continue;
        }
        if (field.hasChangesToSave()) {
            return true;
        }
    }
    return false;
}


@Override
public T getPO(Object model,boolean strict){
    if (strict) {
        return null;
    }
    final Document document = DocumentInterfaceWrapper.getDocument(model);
    if (document == null) {
        throw new AdempiereException("Cannot extract " + Document.class + " from " + model);
    }
    final String tableName = document.getEntityDescriptor().getTableName();
    final boolean checkCache = false;
    @SuppressWarnings("unchecked")
    final T po = (T) TableModelLoader.instance.getPO(document.getCtx(), tableName, document.getDocumentIdAsInt(), checkCache, ITrx.TRXNAME_ThreadInherited);
    return po;
}


@Override
public boolean hasModelColumnName(Object model,String columnName){
    return DocumentInterfaceWrapper.hasColumnName(model, columnName);
}


@Override
public T getValue(Object model,String columnName,boolean throwExIfColumnNotFound,boolean useOverrideColumnIfAvailable){
    final DocumentInterfaceWrapper wrapper = DocumentInterfaceWrapper.getOrCreateWrapper(model);
    // 
    // Get <columnName>_Override's value, if any
    if (useOverrideColumnIfAvailable) {
        final T value = getValueOverrideOrNull(wrapper, columnName);
        if (value != null) {
            return value;
        }
    }
    // 
    // Get columnName's value
    if (!wrapper.hasColumnName(columnName)) {
        if (throwExIfColumnNotFound) {
            throw new AdempiereException("No field with ColumnName=" + columnName + " found in " + wrapper + " for " + model);
        } else {
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    final T value = (T) wrapper.getValue(columnName, Object.class);
    return value;
}


@Override
public boolean canHandled(Object model){
    return DocumentInterfaceWrapper.isHandled(model);
}


@Override
public boolean setValue(Object model,String columnName,Object value,boolean throwExIfColumnNotFound){
    final Document document = DocumentInterfaceWrapper.getDocument(model);
    return DocumentInterfaceWrapper.setValue(document, columnName, value, throwExIfColumnNotFound);
}


@Override
public boolean isNull(Object model,String columnName){
    final Document document = DocumentInterfaceWrapper.getDocument(model);
    final IDocumentFieldView field = document.getFieldViewOrNull(columnName);
    if (field == null) {
        return true;
    }
    final Object value = field.getValue();
    return value == null;
}


@Override
public Properties getCtx(Object model,boolean useClientOrgFromModel){
    final Document document = DocumentInterfaceWrapper.getDocument(model);
    if (document != null) {
        return document.getCtx();
    }
    // Notify developer that (s)he is using wrong models
    if (Services.get(IDeveloperModeBL.class).isEnabled()) {
        final AdempiereException e = new AdempiereException("Cannot get context from model " + model + " because is not supported. Returning global context.");
        logger.warn(e.getLocalizedMessage(), e);
    }
    // fallback to global context
    return Env.getCtx();
}


@Override
public T wrap(Object model,Class<T> modelClass,boolean useOldValues){
    if (useOldValues) {
        return DocumentInterfaceWrapper.wrapUsingOldValues(model, modelClass);
    } else {
        // preserve "old values" flag
        return DocumentInterfaceWrapper.wrap(model, modelClass);
    }
}


}