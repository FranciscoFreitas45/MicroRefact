package de.metas.ui.web.window.model.Document;
 import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.adempiere.ad.ui.spi.ExceptionHandledTabCallout;
import org.adempiere.ad.ui.spi.ITabCallout;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.service.ClientId;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.util.Env;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.document.engine.IDocumentBL;
import de.metas.document.exceptions.DocumentProcessingException;
import de.metas.lang.SOTrx;
import de.metas.letters.model.Letters;
import de.metas.logging.LogManager;
import de.metas.organization.OrgId;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap;
import de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap.DependencyType;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.exceptions.DocumentFieldNotFoundException;
import de.metas.ui.web.window.exceptions.DocumentFieldReadonlyException;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.exceptions.InvalidDocumentStateException;
import de.metas.ui.web.window.model.DocumentsRepository.SaveResult;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.IDocumentField.FieldInitializationMode;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class InitialFieldValueSupplier implements DocumentValuesSupplier{

 private  DocumentValuesSupplier parentSupplier;

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  IDocumentEvaluatee _evaluatee;

 private  Object parentLinkValue;


@Override
public String getVersion(){
    return parentSupplier.getVersion();
}


@Override
public DocumentId getDocumentId(){
    return parentSupplier.getDocumentId();
}


@Override
public Object getValue(DocumentFieldDescriptor fieldDescriptor){
    // 
    // Ask parent first
    {
        final Object value = parentSupplier.getValue(fieldDescriptor);
        if (value != NO_VALUE) {
            return value;
        }
    }
    // 
    // Primary Key field
    if (fieldDescriptor.isKey()) {
        final DocumentId id = parentSupplier.getDocumentId();
        if (id == null) {
            return null;
        } else if (id.isInt()) {
            return id.toInt();
        } else {
            return id.toJson();
        }
    }
    // 
    // Parent link field
    if (fieldDescriptor.isParentLink()) {
        return parentLinkValue;
    }
    // 
    // Default value expression
    final IExpression<?> defaultValueExpression = fieldDescriptor.getDefaultValueExpression().orElse(null);
    if (defaultValueExpression != null) {
        final IDocumentEvaluatee evaluateeEffective = getEvaluatee(fieldDescriptor);
        final Object value = defaultValueExpression.evaluate(evaluateeEffective, OnVariableNotFound.Fail);
        if (value != null && String.class.equals(defaultValueExpression.getValueClass()) && Check.isEmpty(value.toString(), false)) {
            // FIXME: figure out how we can get rid of this hardcoded corner case! ... not sure if is needed
            logger.warn("Development hint: Converting default value empty string to null. Please check how can we avoid this case" + // 
            "\n FieldDescriptor: {}" + // 
            "\n Document: {}", fieldDescriptor, this);
            return null;
        }
        return value;
    }
    // 
    // Window User Preferences (only if it's not a virtual field)
    if (documentType == DocumentType.Window && !fieldDescriptor.isVirtualField()) {
        final Properties ctx = Env.getCtx();
        final AdWindowId adWindowId = documentTypeId.toId(AdWindowId::ofRepoId);
        final String fieldName = fieldDescriptor.getFieldName();
        // 
        // Preference (user) - P|
        {
            // retrieve Window level preferences
            final boolean retrieveGlobalPreferences = false;
            final String valueStr = Env.getPreference(ctx, adWindowId, fieldName, retrieveGlobalPreferences);
            if (!Check.isEmpty(valueStr, false)) {
                return valueStr;
            }
        }
        // 
        // Preference (System) - # $
        {
            final boolean retrieveGlobalPreferences = true;
            final String valueStr = Env.getPreference(ctx, adWindowId, fieldName, retrieveGlobalPreferences);
            if (!Check.isEmpty(valueStr, false)) {
                return valueStr;
            }
        }
    }
    // 
    // Fallback
    return DocumentValuesSupplier.NO_VALUE;
}


public IDocumentEvaluatee getEvaluatee(DocumentFieldDescriptor fieldInScope){
    if (fieldInScope == null) {
        return _evaluatee;
    }
    return _evaluatee.fieldInScope(fieldInScope.getFieldName());
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("type", documentType).add("typeId", documentTypeId).add("evaluatee", _evaluatee).toString();
}


}