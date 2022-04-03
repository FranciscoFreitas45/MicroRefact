package DTO;
 import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.compiere.model.I_AD_Process;
import org.slf4j.MDC.MDCCloseable;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.logging.TableRecordMDC;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.process.ProcessId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
public class WebuiRelatedProcessDescriptor {

 private  ProcessId processId;

 private  InternalName internalName;

 private  ITranslatableString processCaption;

 private  ITranslatableString processDescription;

 private  ImmutableSet<DisplayPlace> displayPlaces;

 private  boolean defaultQuickAction;

 private  String shortcut;

 private  Supplier<ValueAndDuration<ProcessPreconditionsResolution>> preconditionsResolutionSupplier;

 private  String debugProcessClassname;

 private  int sortNo;

 private  T value;

 private  Duration duration;


public Duration getPreconditionsResolutionCalcDuration(){
    return preconditionsResolutionSupplier.get().getDuration();
}


public ProcessPreconditionsResolution getPreconditionsResolution(){
    return preconditionsResolutionSupplier.get().getValue();
}


public Map<String,Object> getDebugProperties(){
    final ImmutableMap.Builder<String, Object> debugProperties = ImmutableMap.<String, Object>builder();
    if (debugProcessClassname != null) {
        debugProperties.put("debug-classname", debugProcessClassname);
    }
    return debugProperties.build();
}


public boolean isEnabled(){
    final ProcessPreconditionsResolution preconditionsResolution = getPreconditionsResolution();
    return preconditionsResolution.isAccepted();
}


public boolean isDisabled(){
    return getPreconditionsResolution().isRejected();
}


public String getCaption(String adLanguage){
    return getPreconditionsResolution().computeCaption(processCaption, adLanguage);
}


public String getDescription(String adLanguage){
    return processDescription.translate(adLanguage);
}


public String getDisabledReason(String adLanguage){
    return getPreconditionsResolution().getRejectReason().translate(adLanguage);
}


}