package de.metas.ui.web.process.adprocess;
 import javax.annotation.Nullable;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessInfo;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class ADProcessPostProcessRequest {

 private ViewId viewId;

 private ProcessInfo processInfo;

 private ProcessExecutionResult processExecutionResult;

 private DocumentId instanceIdOverride;


}