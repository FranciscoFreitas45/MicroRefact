package de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
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
@Value
public class ValueAndDuration {

 private  T value;

 private  Duration duration;


public ValueAndDuration<T> fromSupplier(Supplier<T> supplier){
    final Stopwatch stopwatch = Stopwatch.createStarted();
    final T value = supplier.get();
    final Duration duration = Duration.ofNanos(stopwatch.stop().elapsed(TimeUnit.NANOSECONDS));
    return new ValueAndDuration<>(value, duration);
}


}