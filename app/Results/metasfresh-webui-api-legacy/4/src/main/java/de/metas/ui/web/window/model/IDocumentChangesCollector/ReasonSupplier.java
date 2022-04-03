package de.metas.ui.web.window.model.IDocumentChangesCollector;
 import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
@FunctionalInterface
public interface ReasonSupplier {

 public  ReasonSupplier NONE;


public ReasonSupplier add(String name,Object value){
    return () -> this.get() + " | " + name + "=" + value;
}
;

public String get()
;

public ReasonSupplier addPreviousReason(ReasonSupplier previousReason,Object previousValue){
    if (previousReason == null && previousValue == null) {
        return this;
    }
    return () -> {
        final String reason = this.get();
        final StringBuilder reasonNew = new StringBuilder();
        reasonNew.append(reason == null ? "unknown reason" : reason);
        if (previousReason != null) {
            reasonNew.append(" | previous reason: ").append(previousReason);
        }
        if (previousValue != null) {
            reasonNew.append(" | previous value: ").append(previousValue);
        }
        return reasonNew.toString();
    };
}
;

public String toDebugString(ReasonSupplier reasonSupplier){
    if (reasonSupplier == null) {
        return null;
    }
    // Extract the reason only if debugging is enabled
    if (!WindowConstants.isProtocolDebugging()) {
        return null;
    }
    return reasonSupplier.get();
}
;

}