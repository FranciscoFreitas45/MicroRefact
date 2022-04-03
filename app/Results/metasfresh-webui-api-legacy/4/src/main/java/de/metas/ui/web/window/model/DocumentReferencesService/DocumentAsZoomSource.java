package de.metas.ui.web.window.model.DocumentReferencesService;
 import java.util.List;
import java.util.Properties;
import javax.annotation.Nullable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.ZoomInfoFactory;
import org.adempiere.model.ZoomInfoFactory.IZoomSource;
import org.adempiere.model.ZoomInfoFactory.ZoomInfo;
import org.compiere.model.I_AD_Column;
import org.compiere.util.Evaluatee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.document.filter.provider.userQuery.MQueryDocumentFilterHelper;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.util.Services;
import lombok.Getter;
import lombok.NonNull;
public class DocumentAsZoomSource implements IZoomSource{

 private  Properties ctx;

 private  Evaluatee evaluationContext;

 private  AdWindowId adWindowId;

 private  int adTableId;

 private  int recordId;

 private  String keyColumnName;

 private  Document document;

@Getter
 private  boolean genericZoomOrigin;

@Getter
 private  String tableName;


@Override
public AdWindowId getAD_Window_ID(){
    return adWindowId;
}


public boolean extractGenericZoomOrigin(String tableName,String keyColumnName){
    if (keyColumnName != null) {
        final IADTableDAO adTableDAO = Services.get(IADTableDAO.class);
        final I_AD_Column idColumn = adTableDAO.retrieveColumn(tableName, keyColumnName);
        return idColumn.isGenericZoomOrigin();
    }
    return false;
}


@Override
public Evaluatee createEvaluationContext(){
    return evaluationContext;
}


@Override
public String getTrxName(){
    return ITrx.TRXNAME_ThreadInherited;
}


@Override
public int getAD_Table_ID(){
    return adTableId;
}


@Override
public int getRecord_ID(){
    return recordId;
}


@Override
public Object getFieldValue(String columnName){
    return document.getFieldView(columnName).getValue();
}


@Override
public boolean getFieldValueAsBoolean(String columnName){
    return document.getFieldView(columnName).getValueAsBoolean();
}


public String extractSingleKeyColumNameOrNull(DocumentEntityDescriptor entityDescriptor){
    final DocumentFieldDescriptor idField = entityDescriptor.getSingleIdFieldOrNull();
    if (idField == null) {
        return null;
    }
    final DocumentFieldDataBindingDescriptor idFieldBinding = idField.getDataBinding().orElse(null);
    if (idFieldBinding == null) {
        return null;
    }
    final String keyColumnName = idFieldBinding.getColumnName();
    return keyColumnName;
}


@Override
public Properties getCtx(){
    return ctx;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("tableName", tableName).add("recordId", recordId).add("AD_Window_ID", adWindowId).toString();
}


@Override
public String getKeyColumnNameOrNull(){
    return keyColumnName;
}


@Override
public boolean hasField(String columnName){
    return document.hasField(columnName);
}


}