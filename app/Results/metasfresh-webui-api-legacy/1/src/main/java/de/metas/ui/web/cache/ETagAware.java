package de.metas.ui.web.cache;
 import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
public interface ETagAware {


public ETag getETag()
;

public Supplier<ETag> newETagGenerator(){
    final AtomicInteger nextVersionSupplier = new AtomicInteger(1);
    return () -> ETag.of(nextVersionSupplier.getAndIncrement());
}
;

}