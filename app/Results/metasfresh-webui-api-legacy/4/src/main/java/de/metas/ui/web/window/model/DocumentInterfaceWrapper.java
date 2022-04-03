package de.metas.ui.web.window.model;
 import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Set;
import org.adempiere.ad.persistence.IModelInternalAccessor;
import org.adempiere.ad.wrapper.IInterfaceWrapper;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.PO;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.exceptions.DocumentFieldNotFoundException;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.util.Check;
public class DocumentInterfaceWrapper implements IModelInternalAccessor,IInterfaceWrapper,InvocationHandler{

 private  Logger logger;

 private  ReasonSupplier REASON_Value_DirectSetFromDocumentWrapper;

 private  Document document;

 private  boolean useOldValues;

 private  boolean failOnColumnNotFound;


@Override
public Set<String> getColumnNames(){
    return document.getFieldNames();
}


@Override
public boolean isCalculated(String columnName){
    final IDocumentFieldView field = document.getFieldViewOrNull(columnName);
    return field != null && field.isCalculated();
}


public String getTrxName(){
    return null;
}


public void save(Object model){
    final Document document = getDocument(model);
    document.saveIfHasChanges();
}


@Override
public Object invoke(Object proxy,Method method,Object[] args){
    final String methodName = method.getName();
    if (methodName.startsWith("set") && args.length == 1) {
        final Class<?> paramType = method.getParameterTypes()[0];
        final String propertyName;
        final Object value;
        if (isModelInterface(paramType)) {
            // Model setter - 03374
            propertyName = methodName.substring(3) + "_ID";
            value = InterfaceWrapperHelper.getId(args[0]);
        } else {
            propertyName = methodName.substring(3);
            value = InterfaceWrapperHelper.checkZeroIdValue(propertyName, args[0]);
        }
        setValue(propertyName, value);
        return null;
    } else if (methodName.startsWith("get") && (args == null || args.length == 0) && // metas: Document direct calls should be forwarded to Document directly
    !methodName.startsWith("get_")) {
        final String propertyName = methodName.substring(3);
        final IDocumentFieldView documentField = document.getFieldViewOrNull(propertyName);
        if (documentField != null) {
            final Object value = getValue(documentField, method.getReturnType());
            if (value != null) {
                return value;
            }
        } else if (documentField == null && isModelInterface(method.getReturnType())) {
            return getReferencedObject(propertyName, method);
        } else {
            logger.warn("Field " + propertyName + " not found for " + document + ". Assuming default value.");
        }
        // 
        // Return default value
        Object defaultValue = null;
        if (method.getReturnType() == int.class) {
            defaultValue = Integer.valueOf(0);
        } else if (method.getReturnType() == BigDecimal.class) {
            defaultValue = BigDecimal.ZERO;
        } else if (PO.class.isAssignableFrom(method.getReturnType())) {
            throw new IllegalArgumentException("Method not supported - " + methodName);
        }
        return defaultValue;
    } else if (methodName.startsWith("is") && (args == null || args.length == 0)) {
        final String propertyName = methodName.substring(2);
        IDocumentFieldView field = document.getFieldViewOrNull(propertyName);
        if (field != null) {
            final Object value = getValue(field, method.getReturnType());
            return value instanceof Boolean ? value : "Y".equals(value);
        }
        // 
        field = document.getFieldViewOrNull("Is" + propertyName);
        if (field != null) {
            final Object value = getValue(field, method.getReturnType());
            return DisplayType.toBoolean(value);
        }
        // 
        throw new IllegalArgumentException("Method not supported - " + methodName);
    } else {
        // TODO: this is not working; we need to find the similar method in document's class
        return method.invoke(document, args);
    }
}


public int getId(Object model){
    return getDocument(model).getDocumentIdAsInt();
}


@Override
public boolean isVirtualColumn(String columnName){
    final IDocumentFieldView field = document.getFieldViewOrNull(columnName);
    return field != null && field.isVirtualField();
}


public boolean isHandled(Object model){
    return getDocument(model) != null;
}


public T wrapUsingOldValues(Object model,Class<T> modelClass){
    final Boolean useOldValues = Boolean.TRUE;
    return wrap(model, modelClass, useOldValues);
}


@Override
public boolean setValueNoCheck(String columnName,Object value){
    return setValue(columnName, value);
}


public int getWindowNo(Object model){
    final Document document = getDocument(model);
    if (document == null) {
        return Env.WINDOW_None;
    }
    return document.getWindowNo();
}


@Override
public boolean invokeEquals(Object[] methodArgs){
    // TODO: implement
    throw new UnsupportedOperationException();
}


public T getDynAttribute(String attributeName){
    return getDocument().getDynAttribute(attributeName);
}


public Object getReferencedObjectFromParentDocument(Class<?> modelClass,Integer parentRecordId){
    final Document parentDocument = document.getParentDocument();
    if (parentDocument == null) {
        logger.warn("Failed fetching the parent link document because there is none for {}", document);
        return null;
    }
    // 
    // Make sure the parent has the expected ID.
    // In case the parentRecordId is null, we assume the parent is NEW and that's why it was not set.
    if (parentRecordId != null && parentDocument.getDocumentIdAsInt() != parentRecordId) {
        logger.warn("Failed fetching the parent link document because parent document does not have the expected ID. \n Document: {} \n Parent document: {} \n Expected parent ID: {}", document, parentDocument, parentRecordId);
        return null;
    }
    final String modelTableName = InterfaceWrapperHelper.getTableNameOrNull(modelClass);
    if (modelTableName == null) {
        logger.warn("Failed fetching the parent link document because required model class {} has no TableName", modelClass);
        return null;
    }
    final String parentTableName = parentDocument.getEntityDescriptor().getTableName();
    if (!modelTableName.equals(parentTableName)) {
        logger.warn("Failed fetching the parent link document because parent document's table name ({}) is not matching the expected table name ({})", parentTableName, modelTableName);
        return null;
    }
    return wrap(parentDocument, modelClass);
}


public Object setDynAttribute(String attributeName,Object value){
    return getDocument().setDynAttribute(attributeName, value);
}


public Document getDocument(){
    return document;
}


public Object convertValueToType(Object value,IDocumentFieldView field,Class<?> returnType){
    if (Object.class.equals(returnType)) {
        if (value == null) {
            return null;
        } else if (value instanceof LookupValue) {
            final LookupValue lookupValue = (LookupValue) value;
            return lookupValue.getId();
        }
    }
    return DataTypes.convertToValueClass(field.getFieldName(), value, field.getWidgetType(), returnType, // lookupDataSource
    null);
}


@Override
public Object invokeParent(Method method,Object[] methodArgs){
    // TODO: implement
    throw new UnsupportedOperationException();
}


public void refresh(Object model){
    final Document document = getDocument(model);
    if (document != null) {
        document.refreshFromRepository();
    } else {
        logger.debug("Wrapped object is not a Document [SKIP]");
    }
}


public boolean isNew(Object model){
    return getDocument(model).isNew();
}


@Override
public boolean hasColumnName(String columnName){
    return getDocument().hasField(columnName);
}


public Object getValue(IDocumentFieldView field,Class<?> returnType){
    final Object value;
    if (useOldValues) {
        value = field.getOldValue();
    } else {
        value = field.getValue();
    }
    return convertValueToType(value, field, returnType);
}


public DocumentInterfaceWrapper getOrCreateWrapper(Object model){
    if (model == null) {
        return null;
    }
    // 
    // Try to get the wrapper directly
    if (Proxy.isProxyClass(model.getClass())) {
        final InvocationHandler ih = Proxy.getInvocationHandler(model);
        if (ih instanceof DocumentInterfaceWrapper) {
            final DocumentInterfaceWrapper wrapper = (DocumentInterfaceWrapper) ih;
            return wrapper;
        }
        return null;
    } else if (model instanceof DocumentInterfaceWrapper) {
        return (DocumentInterfaceWrapper) model;
    }
    // 
    // Try getting the document and create a wrapper for it
    if (model instanceof Document) {
        final Document document = (Document) model;
        final boolean useOldValues = false;
        return new DocumentInterfaceWrapper(document, useOldValues);
    } else if (model instanceof IDocumentAware) {
        final Document document = ((IDocumentAware) model).getDocument();
        final boolean useOldValues = false;
        return new DocumentInterfaceWrapper(document, useOldValues);
    }
    // 
    // Wrapper could not be found or create
    throw new IllegalArgumentException("Cannot get or create the " + DocumentInterfaceWrapper.class + " from " + model);
}


public boolean isModelInterface(Class<?> cl){
    try {
        final String tableName = (String) cl.getField("Table_Name").get(null);
        return tableName != null;
    } catch (final Exception e) {
        return false;
    }
}


public boolean setValue(Document document,String propertyName,Object value,boolean failOnColumnNotFound){
    final Object valueFixed = InterfaceWrapperHelper.checkZeroIdValue(propertyName, value);
    try {
        document.setValue(propertyName, valueFixed, REASON_Value_DirectSetFromDocumentWrapper);
        return true;
    } catch (final DocumentFieldNotFoundException e) {
        final String errorMsg = e.getLocalizedMessage();
        final String msg = "Attempt to set field " + propertyName + " of document " + document + " to value '" + valueFixed + "' (original: '" + value + "')" + "' returned an error message: " + errorMsg;
        if (failOnColumnNotFound) {
            throw new AdempiereException(msg, e);
        } else {
            logger.error(msg, e);
            return false;
        }
    }
}


public boolean isNull(Object model,String columnName){
    final Document document = getDocument(model);
    if (document == null) {
        return true;
    }
    final IDocumentFieldView field = document.getFieldViewOrNull(columnName);
    if (field == null) {
        return true;
    }
    final Object value = field.getValue();
    return value == null;
}


@Override
public boolean isKeyColumnName(String columnName){
    final IDocumentFieldView field = document.getFieldViewOrNull(columnName);
    return field != null && field.isKey();
}


public Properties getCtx(){
    return document.getCtx();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("document", document).add("useOldValues", useOldValues ? Boolean.TRUE : null).toString();
}


@Override
public int getColumnIndex(String columnName){
    throw new UnsupportedOperationException("DocumentInterfaceWrapper has no supported for column indexes");
}


public DocumentInterfaceWrapper getWrapper(Object model){
    if (model == null) {
        return null;
    }
    if (Proxy.isProxyClass(model.getClass())) {
        final InvocationHandler ih = Proxy.getInvocationHandler(model);
        if (ih instanceof DocumentInterfaceWrapper) {
            final DocumentInterfaceWrapper wrapper = (DocumentInterfaceWrapper) ih;
            return wrapper;
        }
        return null;
    } else if (model instanceof DocumentInterfaceWrapper) {
        return (DocumentInterfaceWrapper) model;
    }
    return null;
}


@Override
public Object getReferencedObject(String propertyName,Method method){
    final String idPropertyName = propertyName + "_ID";
    final IDocumentFieldView idField = document.getFieldViewOrNull(idPropertyName);
    if (idField == null) {
        logger.warn("Field {} not found for {}. Assuming null value.", idPropertyName, document);
        return null;
    }
    // 
    // Parent link
    if (idField.isParentLink()) {
        final Class<?> returnType = method.getReturnType();
        final Integer record_id = (Integer) getValue(idField, Integer.class);
        final Object parentModel = getReferencedObjectFromParentDocument(returnType, record_id);
        return parentModel;
    }
    // Fetch Record_ID
    final Integer record_id = (Integer) getValue(idField, Integer.class);
    if (record_id == null || record_id <= 0) {
        return null;
    }
    // Load and return
    final Class<?> returnType = method.getReturnType();
    final Object model = InterfaceWrapperHelper.create(getCtx(), record_id, returnType, getTrxName());
    return model;
}


public T wrap(Object model,Class<T> modelClass,Boolean useOldValues){
    if (model == null) {
        return null;
    }
    if (modelClass.isInstance(model) && (useOldValues == null || useOldValues == Boolean.FALSE)) {
        @SuppressWarnings("unchecked")
        final T modelCasted = (T) model;
        return modelCasted;
    }
    Document document = null;
    Boolean useOldValuesDefault = null;
    if (model instanceof Document) {
        document = (Document) model;
        useOldValuesDefault = false;
    } else if (model instanceof IDocumentAware) {
        document = ((IDocumentAware) model).getDocument();
        useOldValuesDefault = false;
    }
    if (document == null) {
        final DocumentInterfaceWrapper wrapper = getWrapper(model);
        if (wrapper != null) {
            document = wrapper.getDocument();
            // by default, preserve it
            useOldValuesDefault = wrapper.isOldValues();
        }
    }
    if (document == null) {
        throw new AdempiereException("Cannot wrap " + model + " (class:" + model.getClass());
    }
    // NOTE: if document is not null then "useOldValuesDefault" shall not be null
    // 
    // Shall we use old values?
    final boolean useOldValuesEffective = useOldValues == null ? useOldValuesDefault : useOldValues;
    // 
    // Check if given interface is compatible with document, i.e.
    // * interface does not define a Table_Name field
    // * interface has a Table_Name static field, which is equal with document's Table_Name
    final String interfaceTableName = InterfaceWrapperHelper.getTableNameOrNull(modelClass);
    if (interfaceTableName != null) {
        final String documentTableName = document.getEntityDescriptor().getTableName();
        if (!interfaceTableName.equals(documentTableName)) {
            throw new AdempiereException("Interface " + modelClass + " (tableName=" + interfaceTableName + ") is not compatible with " + document + " (tableName=" + documentTableName + ")");
        }
    }
    @SuppressWarnings("unchecked")
    final T result = (T) Proxy.newProxyInstance(modelClass.getClassLoader(), new Class<?>[] { modelClass }, new DocumentInterfaceWrapper(document, useOldValuesEffective));
    return result;
}


public boolean isOldValues(Object model){
    final DocumentInterfaceWrapper wrapper = getWrapper(model);
    return wrapper == null ? false : wrapper.isOldValues();
}


@Override
public void setValueFromPO(String idColumnName,Class<?> parameterType,Object value){
    // TODO: implement
    throw new UnsupportedOperationException();
}


}