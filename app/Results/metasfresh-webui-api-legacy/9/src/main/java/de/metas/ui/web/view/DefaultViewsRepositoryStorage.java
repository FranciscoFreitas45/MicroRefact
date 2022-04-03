package de.metas.ui.web.view;
 import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import org.slf4j.Logger;
import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
public class DefaultViewsRepositoryStorage implements IViewsIndexStorage{

 private  Logger logger;

 private  Cache<ViewId,IView> views;


@Nullable
@Override
public IView getByIdOrNull(ViewId viewId){
    return views.getIfPresent(viewId);
}


@Override
public void invalidateView(ViewId viewId){
    final IView view = getByIdOrNull(viewId);
    if (view == null) {
        return;
    }
    view.invalidateAll();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(view);
}


public void onViewRemoved(RemovalNotification<Object,Object> notification){
    final IView view = (IView) notification.getValue();
    logger.debug("View <" + view.getViewId() + "> removed from cache. Cause: " + notification.getCause());
    view.afterDestroy();
}


@Override
public void put(IView view){
    views.put(view.getViewId(), view);
}


@Override
public Stream<IView> streamAllViews(){
    return views.asMap().values().stream();
}


@Override
public WindowId getWindowId(){
    throw new UnsupportedOperationException("windowId not available");
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
    // Don't remove the view if not allowed.
    // Will be removed when it will expire.
    final IView view = views.getIfPresent(viewId);
    if (view == null || !view.isAllowClosingPerUserRequest()) {
        return;
    }
    // 
    // Notify the view that the user requested to be closed
    // IMPORTANT: fire this event before removing the view from storage.
    view.close(closeAction);
    // 
    // Remove the view from storage
    // => will fire #onViewRemoved
    views.invalidate(viewId);
    // also cleanup to prevent views cache to grow.
    views.cleanUp();
}


}