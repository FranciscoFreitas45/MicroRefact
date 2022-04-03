package de.metas.ui.web.window.model;
 import java.util.Properties;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.callout.api.ICalloutRecord;
import org.compiere.util.DisplayType;
import org.compiere.util.ValueNamePair;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import de.metas.i18n.IMsgBL;
import de.metas.logging.LogManager;
import de.metas.process.IProcessDefaultParameter;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.util.Services;
import lombok.NonNull;
public class DocumentFieldAsCalloutField implements IProcessDefaultParameter,ICalloutField{

 private  Logger logger;

 private  IDocumentField documentField;


public DocumentFieldDescriptor getDescriptor(){
    return documentField.getDescriptor();
}


@Override
public void fireDataStatusEEvent(ValueNamePair errorLog){
    final boolean isError = true;
    fireDataStatusEEvent(errorLog.getValue(), errorLog.getName(), isError);
}


@Override
public void putContext(String name,int value){
    getDocument().setDynAttribute(name, value);
}


@Override
public boolean isTriggerCalloutAllowed(){
    if (!getDescriptor().isAlwaysUpdateable() && getDocument().isProcessed()) {
        return false;
    }
    return true;
}


@Override
public boolean getContextAsBoolean(String name){
    final Object valueObj = getDocument().getDynAttribute(name);
    return DisplayType.toBoolean(valueObj);
}


public Document getDocument(){
    return documentField.getDocument();
}


@Override
public String getTableName(){
    return getDocument().getEntityDescriptor().getTableName();
}


@Override
public String getColumnName(){
    return documentField.getFieldName();
}


@Override
public Object getOldValue(){
    return documentField.getOldValue();
}


@Override
public ICalloutExecutor getCurrentCalloutExecutor(){
    return getDocument().getFieldCalloutExecutor();
}


@Override
public ICalloutRecord getCalloutRecord(){
    return getDocument().asCalloutRecord();
}


public IDocumentField unwrap(ICalloutField calloutField){
    final DocumentFieldAsCalloutField documentFieldAsCalloutField = (DocumentFieldAsCalloutField) calloutField;
    return documentFieldAsCalloutField.documentField;
}


public Document unwrapDocument(ICalloutField calloutField){
    final DocumentFieldAsCalloutField documentFieldAsCalloutField = (DocumentFieldAsCalloutField) calloutField;
    return documentFieldAsCalloutField.getDocument();
}


@Override
public Object getValue(){
    return documentField.getValue();
}


@Override
public boolean isRecordCopyingMode(){
    // TODO Auto-generated method stub
    return false;
}


@Override
public Properties getCtx(){
    return getDocument().getCtx();
}


@Override
public int getWindowNo(){
    return getDocument().getWindowNo();
}


@Override
public int getContextAsInt(String name){
    final Object valueObj = getDocument().getDynAttribute(name);
    if (valueObj == null) {
        return -1;
    } else if (valueObj instanceof Number) {
        return ((Number) valueObj).intValue();
    } else {
        return Integer.parseInt(valueObj.toString());
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(documentField).toString();
}


@Override
public boolean isRecordCopyingModeIncludingDetails(){
    // TODO Auto-generated method stub
    return false;
}


}