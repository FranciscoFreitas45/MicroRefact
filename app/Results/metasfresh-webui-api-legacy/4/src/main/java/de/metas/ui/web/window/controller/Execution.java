package de.metas.ui.web.window.controller;
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
public class Execution implements IAutoCloseable{

 private  Logger logger;

 private  ThreadLocal<Execution> currentExecutionHolder;

 private  String threadName;

 private  boolean closed;

 private  IDocumentChangesCollector documentChangesCollector;

 private  String name;

 private  int versionErrorRetryCount;

 private  boolean inTransaction;


public ExecutionBuilder prepareNewExecution(){
    return new ExecutionBuilder();
}


public IDocumentChangesCollector getCurrentDocumentChangesCollector(){
    return getCurrent().getDocumentChangesCollector();
}


public Execution startExecution(){
    final Execution executionOld = currentExecutionHolder.get();
    if (executionOld != null) {
        throw new AdempiereException("Cannot start execution on thread '" + Thread.currentThread().getName() + "' because there is another execution currently running: " + executionOld);
    }
    final Execution execution = new Execution();
    currentExecutionHolder.set(execution);
    return execution;
}


public T callInNewExecution(String name,Callable<T> callable){
    return new ExecutionBuilder().name(name).execute(callable);
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


public ExecutionBuilder name(String name){
    this.name = name;
    return this;
}


public IDocumentChangesCollector getCurrentDocumentChangesCollectorOrNull(){
    final Execution execution = currentExecutionHolder.get();
    if (execution == null) {
        return NullDocumentChangesCollector.instance;
    }
    return execution.getDocumentChangesCollector();
}


public ExecutionBuilder retryOnVersionError(int retryCount){
    Preconditions.checkArgument(retryCount > 0);
    versionErrorRetryCount = retryCount;
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("threadName", threadName).toString();
}


public Execution getCurrent(){
    final Execution execution = currentExecutionHolder.get();
    if (execution == null) {
        throw new AdempiereException("No current execution found for thread: " + Thread.currentThread().getName());
    }
    return execution;
}


public IDocumentChangesCollector getDocumentChangesCollector(){
    if (documentChangesCollector == null) {
        synchronized (this) {
            if (documentChangesCollector == null) {
                documentChangesCollector = DocumentChangesCollector.newInstance();
            }
        }
    }
    return documentChangesCollector;
}


@Override
public void close(){
    if (closed) {
        return;
    }
    final Execution executionNow = currentExecutionHolder.get();
    if (this != executionNow) {
        throw new AdempiereException("Cannot close the execution because current execution is not the one we expected." + "\n Expected: " + this + "\n Current: " + executionNow + "\n Current thread: " + Thread.currentThread().getName() + "\n HINT: make sure you are closing the execution on the same thread where you started it.");
    }
    currentExecutionHolder.set(null);
    closed = true;
}


public ExecutionBuilder outOfTransaction(){
    inTransaction = false;
    return this;
}


}