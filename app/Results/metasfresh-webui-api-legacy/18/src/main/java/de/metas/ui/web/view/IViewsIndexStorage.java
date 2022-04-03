package de.metas.ui.web.view;
 import de.metas.ui.web.window.datatypes.WindowId;
import javax.annotation.Nullable;
import java.util.stream.Stream;
public interface IViewsIndexStorage {


@Nullable
public IView getByIdOrNull(ViewId viewId)
;

public void invalidateView(ViewId viewId)
;

public void setViewsRepository(IViewsRepository viewsRepository){
}
;

public void put(IView view)
;

public Stream<IView> streamAllViews()
;

public WindowId getWindowId()
;

public void closeById(ViewId viewId,ViewCloseAction closeAction)
;

}