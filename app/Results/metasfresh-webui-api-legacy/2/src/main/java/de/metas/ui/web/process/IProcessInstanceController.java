package de.metas.ui.web.process;
 import java.util.Collection;
import java.util.List;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
public interface IProcessInstanceController {


public ProcessInstanceResult getExecutionResult()
;

public DocumentId getInstanceId()
;

public LookupValuesList getParameterLookupValues(String parameterName)
;

public ProcessInstanceResult startProcess(ProcessExecutionContext context)
;

public void processParameterValueChanges(List<JSONDocumentChangedEvent> events,ReasonSupplier reason)
;

public LookupValuesList getParameterLookupValuesForQuery(String parameterName,String query)
;

public Collection<IProcessInstanceParameter> getParameters()
;

public boolean isStartProcessDirectly()
;

}