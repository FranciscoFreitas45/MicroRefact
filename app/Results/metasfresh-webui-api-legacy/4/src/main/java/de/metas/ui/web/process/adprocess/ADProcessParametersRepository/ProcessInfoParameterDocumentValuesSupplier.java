package de.metas.ui.web.process.adprocess.ADProcessParametersRepository;
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
public class ProcessInfoParameterDocumentValuesSupplier implements DocumentValuesSupplier{

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
public Object getValue(DocumentFieldDescriptor parameterDescriptor){
    return extractParameterValue(processInfoParameters, parameterDescriptor);
}


}