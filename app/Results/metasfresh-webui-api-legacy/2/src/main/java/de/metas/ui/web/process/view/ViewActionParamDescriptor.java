package de.metas.ui.web.process.view;
 import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.DisplayType;
import org.slf4j.Logger;
import com.google.common.base.Preconditions;
import de.metas.logging.LogManager;
import de.metas.ui.web.process.view.ViewActionDescriptor.ViewActionMethodArgumentExtractor;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
@Builder
public class ViewActionParamDescriptor {

 private  Logger logger;

@NonNull
 private  String parameterName;

@NonNull
 private  Class<?> parameterValueClass;

 private  ViewActionParam parameterAnnotation;

@NonNull
 private  ViewActionMethodArgumentExtractor methodArgumentExtractor;


public Object extractArgument(IView view,Document processParameters,DocumentIdsSelection selectedDocumentIds){
    return methodArgumentExtractor.extractArgument(view, processParameters, selectedDocumentIds);
}


public boolean isUserParameter(){
    return parameterAnnotation != null;
}


public DocumentFieldDescriptor.Builder createParameterFieldDescriptor(){
    Preconditions.checkState(isUserParameter(), "parameter is internal");
    LookupDescriptorProvider lookupDescriptorProvider = LookupDescriptorProviders.NULL;
    if (parameterAnnotation.widgetType().isLookup()) {
        if (Check.isEmpty(parameterAnnotation.sqlLookupTableName(), true)) {
            logger.warn("sqlLookupTableName not provided in " + parameterAnnotation + " (" + this + "). Continuing...");
        } else {
            lookupDescriptorProvider = SqlLookupDescriptor.builder().setCtxTableName(// tableName
            null).setCtxColumnName(InterfaceWrapperHelper.getKeyColumnName(parameterAnnotation.sqlLookupTableName())).setDisplayType(DisplayType.Search).setReadOnlyAccess().buildProvider();
        }
    }
    return DocumentFieldDescriptor.builder(parameterName).setCaption(parameterAnnotation.caption()).setValueClass(parameterValueClass).setWidgetType(parameterAnnotation.widgetType()).setLookupDescriptorProvider(lookupDescriptorProvider != null ? lookupDescriptorProvider : LookupDescriptorProviders.NULL).setReadonlyLogic(false).setDisplayLogic(true).setMandatoryLogic(parameterAnnotation.mandatory()).addCharacteristic(Characteristic.PublicField);
}


}