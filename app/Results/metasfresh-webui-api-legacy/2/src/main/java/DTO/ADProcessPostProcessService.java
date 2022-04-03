package DTO;
 import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.PlainContextAware;
import org.adempiere.model.RecordZoomWindowFinder;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.MimeType;
import org.compiere.util.Util;
import org.slf4j.Logger;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessExecutionResult.RecordsToOpen;
import de.metas.process.ProcessExecutionResult.RecordsToOpen.OpenTarget;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessInfo;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.ProcessInstanceResult.DisplayQRCodeAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenIncludedViewAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenReportAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenSingleDocument;
import de.metas.ui.web.process.ProcessInstanceResult.OpenViewAction;
import de.metas.ui.web.process.ProcessInstanceResult.ResultAction;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
public class ADProcessPostProcessService {

 private  Logger logger;

 private  IViewsRepository viewsRepo;

 private  DocumentCollection documentsCollection;

 private  int MAX_REFERENCED_DOCUMENTPATHS_ALLOWED;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public ProcessInstanceResult postProcess(ADProcessPostProcessRequest request){
    final ProcessInfo processInfo = request.getProcessInfo();
    final ProcessExecutionResult processExecutionResult = request.getProcessExecutionResult();
    invalidateDocumentsAndViews(request.getViewId(), processExecutionResult);
    return ProcessInstanceResult.builder(extractInstanceId(request)).summary(extractSummary(processExecutionResult)).error(processExecutionResult.isError()).action(createResultAction(processInfo, processExecutionResult)).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/postProcess"))

.queryParam("request",request);
ProcessInstanceResult aux = restTemplate.getForObject(builder.toUriString(),ProcessInstanceResult.class);
return aux;
}


}