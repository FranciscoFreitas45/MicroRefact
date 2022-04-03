package de.metas.ui.web.process.adprocess;
 import java.util.List;
import java.util.Map;
import de.metas.process.IADPInstanceDAO;
import de.metas.process.PInstanceId;
import de.metas.process.ProcessInfoParameter;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DateRangeValue;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.Password;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.DocumentValuesSupplier;
import de.metas.ui.web.window.model.DocumentQuery;
import de.metas.ui.web.window.model.DocumentsRepository;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentEvaluatee;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.ui.web.window.model.OrderedDocumentsList;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.RepoIdAware;
public class ADProcessParametersRepository implements DocumentsRepository{

 public  ADProcessParametersRepository instance;

 private  IADPInstanceDAO adPInstanceDAO;

 private  String VERSION_DEFAULT;

 private  DocumentId adPInstanceId;

 private  Map<String,ProcessInfoParameter> processInfoParameters;


@Override
public String getVersion(){
    return VERSION_DEFAULT;
}


@Override
public DocumentId getDocumentId(){
    return adPInstanceId;
}


@Override
public DocumentId retrieveParentDocumentId(DocumentEntityDescriptor parentEntityDescriptor,DocumentQuery childDocumentQuery){
    throw new EntityNotFoundException("Process documents does not have parents").setParameter("parentEntityDescriptor", parentEntityDescriptor).setParameter("childDocumentQuery", childDocumentQuery);
}


@Override
public Document retrieveDocumentById(DocumentEntityDescriptor parametersDescriptor,DocumentId adPInstanceId,IDocumentChangesCollector changesCollector){
    // 
    // Get parameters
    final Map<String, ProcessInfoParameter> processInfoParameters = retrieveProcessInfoParameters(adPInstanceId);
    // 
    // Build the parameters (as document)
    return Document.builder(parametersDescriptor).setChangesCollector(changesCollector).initializeAsExistingRecord(new ProcessInfoParameterDocumentValuesSupplier(adPInstanceId, processInfoParameters));
}


@Override
public SaveResult save(Document processParameters){
    final PInstanceId pinstanceId = processParameters.getDocumentId().toId(PInstanceId::ofRepoId);
    final List<ProcessInfoParameter> piParams = processParameters.getFieldViews().stream().map(field -> createProcessInfoParameter(field)).collect(GuavaCollectors.toImmutableList());
    adPInstanceDAO.saveParameterToDB(pinstanceId, piParams);
    return SaveResult.SAVED;
}


@Override
public void refresh(Document processParameters){
    final DocumentId adPInstanceId = processParameters.getDocumentId();
    final Map<String, ProcessInfoParameter> processInfoParameters = retrieveProcessInfoParameters(adPInstanceId);
    processParameters.refreshFromSupplier(new ProcessInfoParameterDocumentValuesSupplier(adPInstanceId, processInfoParameters));
}


public ProcessInfoParameter createProcessInfoParameter(IDocumentFieldView field){
    final String parameterName = field.getFieldName();
    final Object fieldValue = field.getValue();
    final Object parameter;
    final String info;
    final Object parameterTo;
    final String infoTo;
    if (fieldValue instanceof LookupValue) {
        final LookupValue lookupValue = (LookupValue) fieldValue;
        parameter = lookupValue.getId();
        info = lookupValue.getDisplayName();
        parameterTo = null;
        infoTo = null;
    } else if (fieldValue instanceof RepoIdAware) {
        parameter = ((RepoIdAware) fieldValue).getRepoId();
        info = parameter.toString();
        parameterTo = null;
        infoTo = null;
    } else if (fieldValue instanceof DateRangeValue) {
        final DateRangeValue dateRange = (DateRangeValue) fieldValue;
        parameter = dateRange.getFrom();
        info = parameter == null ? null : parameter.toString();
        parameterTo = dateRange.getTo();
        infoTo = parameterTo == null ? null : parameterTo.toString();
    } else if (fieldValue instanceof Password) {
        final Password password = Password.cast(fieldValue);
        parameter = password.getAsString();
        info = Password.OBFUSCATE_STRING;
        parameterTo = null;
        infoTo = null;
    } else {
        parameter = fieldValue;
        info = fieldValue == null ? null : fieldValue.toString();
        parameterTo = null;
        infoTo = null;
    }
    return new ProcessInfoParameter(parameterName, parameter, parameterTo, info, infoTo);
}


@Override
public void delete(Document processParameters){
    throw new UnsupportedOperationException();
}


public Map<String,ProcessInfoParameter> retrieveProcessInfoParameters(DocumentId adPInstanceDocumentId){
    final PInstanceId pinstanceId = adPInstanceDocumentId.toId(PInstanceId::ofRepoId);
    return adPInstanceDAO.retrieveProcessInfoParameters(pinstanceId).stream().collect(GuavaCollectors.toImmutableMapByKey(ProcessInfoParameter::getParameterName));
}


public Document createNewParametersDocument(DocumentEntityDescriptor parametersDescriptor,DocumentId adPInstanceId,IDocumentEvaluatee evalCtx){
    final IDocumentEvaluatee evalCtxEffective;
    if (evalCtx != null) {
        evalCtxEffective = evalCtx.excludingFields(WindowConstants.FIELDNAME_Processed, WindowConstants.FIELDNAME_Processing, WindowConstants.FIELDNAME_IsActive);
    } else {
        evalCtxEffective = null;
    }
    return Document.builder(parametersDescriptor).setShadowParentDocumentEvaluatee(evalCtxEffective).initializeAsNewDocument(adPInstanceId, VERSION_DEFAULT);
}


@Override
public Document retrieveDocument(DocumentQuery query,IDocumentChangesCollector changesCollector){
    throw new UnsupportedOperationException();
}


@Override
public Object getValue(DocumentFieldDescriptor parameterDescriptor){
    return extractParameterValue(processInfoParameters, parameterDescriptor);
}


@Override
public OrderedDocumentsList retrieveDocuments(DocumentQuery query,IDocumentChangesCollector changesCollector){
    throw new UnsupportedOperationException();
}


public Object extractParameterValue(Map<String,ProcessInfoParameter> processInfoParameters,DocumentFieldDescriptor parameterDescriptor){
    final String fieldName = parameterDescriptor.getFieldName();
    final ProcessInfoParameter processInfoParameter = processInfoParameters.get(fieldName);
    if (processInfoParameter == null) {
        return null;
    }
    final Object parameterValue = processInfoParameter.getParameter();
    final String parameterDisplay = processInfoParameter.getInfo();
    final Object parameterValueConv = parameterDescriptor.convertToValueClass(parameterValue, id -> LookupValue.fromObject(id, parameterDisplay));
    return parameterValueConv;
}


@Override
public int retrieveLastLineNo(DocumentQuery query){
    throw new UnsupportedOperationException();
}


@Override
public String retrieveVersion(DocumentEntityDescriptor entityDescriptor,int documentIdAsInt){
    return VERSION_DEFAULT;
}


@Override
public Document createNewDocument(DocumentEntityDescriptor parametersDescriptor,Document parentProcessParameters,IDocumentChangesCollector changesCollector){
    throw new UnsupportedOperationException();
}


}