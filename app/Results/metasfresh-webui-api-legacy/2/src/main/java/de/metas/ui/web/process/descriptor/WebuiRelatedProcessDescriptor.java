package de.metas.ui.web.process.descriptor;
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
@ToString
public class WebuiRelatedProcessDescriptor {

@Getter
 private  ProcessId processId;

@Getter
 private  InternalName internalName;

 private  ITranslatableString processCaption;

 private  ITranslatableString processDescription;

@Getter
 private  ImmutableSet<DisplayPlace> displayPlaces;

@Getter
 private  boolean defaultQuickAction;

@Getter
 private  String shortcut;

@NonNull
 private  Supplier<ValueAndDuration<ProcessPreconditionsResolution>> preconditionsResolutionSupplier;

 private  String debugProcessClassname;

@Getter
 private  int sortNo;

 private  T value;

 private  Duration duration;


public boolean isInternal(){
    final ProcessPreconditionsResolution preconditionsResolution = getPreconditionsResolution();
    return preconditionsResolution.isInternal();
}


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


public boolean isEnabledOrNotSilent(){
    try (final MDCCloseable processMDC = TableRecordMDC.putTableRecordReference(I_AD_Process.Table_Name, processId == null ? null : processId.toAdProcessIdOrNull())) {
        final ProcessPreconditionsResolution preconditionsResolution = getPreconditionsResolution();
        return preconditionsResolution.isAccepted() || !preconditionsResolution.isInternal();
    }
}


public boolean isEnabled(){
    final ProcessPreconditionsResolution preconditionsResolution = getPreconditionsResolution();
    return preconditionsResolution.isAccepted();
}


public ValueAndDuration<T> fromSupplier(Supplier<T> supplier){
    final Stopwatch stopwatch = Stopwatch.createStarted();
    final T value = supplier.get();
    final Duration duration = Duration.ofNanos(stopwatch.stop().elapsed(TimeUnit.NANOSECONDS));
    return new ValueAndDuration<>(value, duration);
}


public boolean isDisabled(){
    return getPreconditionsResolution().isRejected();
}


public String getCaption(String adLanguage){
    return getPreconditionsResolution().computeCaption(processCaption, adLanguage);
}


public boolean isDisplayedOn(DisplayPlace displayPlace){
    return getDisplayPlaces().contains(displayPlace);
}


public String getDescription(String adLanguage){
    return processDescription.translate(adLanguage);
}


public String getDisabledReason(String adLanguage){
    return getPreconditionsResolution().getRejectReason().translate(adLanguage);
}


}