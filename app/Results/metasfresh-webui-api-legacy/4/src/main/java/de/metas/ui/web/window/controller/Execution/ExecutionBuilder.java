package de.metas.ui.web.window.controller.Execution;
 import java.util.concurrent.Callable;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.IAutoCloseable;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import de.metas.logging.LogManager;
import de.metas.ui.web.exceptions.InvalidDocumentVersionException;
import de.metas.ui.web.window.model.DocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.util.Services;
public class ExecutionBuilder {

 private  String name;

 private  int versionErrorRetryCount;

 private  boolean inTransaction;


public ExecutionBuilder name(String name){
    this.name = name;
    return this;
}


public ExecutionBuilder retryOnVersionError(int retryCount){
    Preconditions.checkArgument(retryCount > 0);
    versionErrorRetryCount = retryCount;
    return this;
}


public T execute(Callable<T> callable){
    Callable<T> callableEffective = callable;
    // 
    // First(important). Run in transaction
    if (inTransaction) {
        final Callable<T> beforeCall = callableEffective;
        callableEffective = () -> {
            final ITrxManager trxManager = Services.get(ITrxManager.class);
            try {
                return trxManager.callInNewTrx(beforeCall);
            } catch (final Exception ex) {
                logger.info("Changes that will be discarded: {}", getCurrentDocumentChangesCollectorOrNull());
                throw AdempiereException.wrapIfNeeded(ex);
            }
        };
    }
    // 
    // Retry on version error
    if (versionErrorRetryCount > 0) {
        final Callable<T> beforeCall = callableEffective;
        callableEffective = () -> {
            InvalidDocumentVersionException versionException = null;
            for (int retry = 0; retry < versionErrorRetryCount; retry++) {
                try {
                    return beforeCall.call();
                } catch (final InvalidDocumentVersionException ex) {
                    versionException = ex;
                    logger.info("Version error on run {}/{}", retry + 1, versionErrorRetryCount, versionException);
                }
            }
            throw versionException;
        };
    }
    // 
    // Last, measure and log
    {
        final Callable<T> beforeCall = callableEffective;
        callableEffective = () -> {
            boolean error = true;
            final Stopwatch stopwatch = Stopwatch.createStarted();
            try {
                final T result = beforeCall.call();
                error = false;
                return result;
            } finally {
                if (!error) {
                    logger.debug("Executed {} in {} ({})", name, stopwatch, callable);
                } else {
                    logger.warn("Failed executing {} (took {}) ({})", name, stopwatch, callable);
                }
            }
        };
    }
    // 
    // Run the effective callable in a new execution
    try (final Execution execution = startExecution()) {
        return callableEffective.call();
    } catch (final Exception ex) {
        throw AdempiereException.wrapIfNeeded(ex);
    }
}


public ExecutionBuilder outOfTransaction(){
    inTransaction = false;
    return this;
}


}