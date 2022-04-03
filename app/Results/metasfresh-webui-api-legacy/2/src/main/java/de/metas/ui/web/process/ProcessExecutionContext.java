package de.metas.ui.web.process;
 import java.util.Properties;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.window.model.DocumentCollection;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class ProcessExecutionContext {

 private Properties ctx;

 private String adLanguage;

 private IViewsRepository viewsRepo;

 private DocumentCollection documentsCollection;


}