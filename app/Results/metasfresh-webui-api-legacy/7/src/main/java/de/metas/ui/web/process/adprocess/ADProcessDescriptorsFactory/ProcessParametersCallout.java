package de.metas.ui.web.process.adprocess.ADProcessDescriptorsFactory;
 import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.element.api.IADElementDAO;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.IExpressionFactory;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.api.IRangeAwareParams;
import org.compiere.model.I_AD_Element;
import org.compiere.model.I_AD_Process;
import org.compiere.model.I_AD_Process_Para;
import org.compiere.model.X_AD_Process;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import de.metas.cache.CCache;
import de.metas.i18n.IModelTranslationMap;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.process.IADProcessDAO;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessParams;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.process.WebuiPreconditionsContext;
import de.metas.ui.web.process.descriptor.InternalName;
import de.metas.ui.web.process.descriptor.ProcessDescriptor;
import de.metas.ui.web.process.descriptor.ProcessDescriptor.ProcessDescriptorType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.datatypes.DateRangeValue;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDataBindingDescriptor.DocumentEntityDataBindingDescriptorBuilder;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.factory.standard.DefaultValueExpressionsFactory;
import de.metas.ui.web.window.descriptor.factory.standard.DescriptorsFactoryHelper;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
import lombok.Builder;
import lombok.NonNull;
public class ProcessParametersCallout {


public IRangeAwareParams createSource(ICalloutField calloutField){
    final String parameterName = calloutField.getColumnName();
    final Object fieldValue = calloutField.getValue();
    if (fieldValue instanceof LookupValue) {
        final Object idObj = ((LookupValue) fieldValue).getId();
        return ProcessParams.ofValueObject(parameterName, idObj);
    } else if (fieldValue instanceof DateRangeValue) {
        final DateRangeValue dateRange = (DateRangeValue) fieldValue;
        return ProcessParams.of(parameterName, TimeUtil.asDate(dateRange.getFrom()), TimeUtil.asDate(dateRange.getTo()));
    } else {
        return ProcessParams.ofValueObject(parameterName, fieldValue);
    }
}


public void forwardValueToCurrentProcessInstance(ICalloutField calloutField){
    final JavaProcess processInstance = JavaProcess.currentInstance();
    final String parameterName = calloutField.getColumnName();
    final IRangeAwareParams source = createSource(calloutField);
    // Ask the instance to load the parameter
    processInstance.loadParameterValueNoFail(parameterName, source);
}


}