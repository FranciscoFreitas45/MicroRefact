package de.metas.ui.web.material.cockpit;
 import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.compiere.model.I_M_Product;
import org.springframework.stereotype.Service;
import de.metas.cache.CacheMgt;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.ui.web.view.DefaultViewsRepositoryStorage;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
@Service
public class MaterialCockpitViewsIndexStorage implements IViewsIndexStorage{

 private  DefaultViewsRepositoryStorage defaultViewsRepositoryStorage;


@Override
public IView getByIdOrNull(ViewId viewId){
    return defaultViewsRepositoryStorage.getByIdOrNull(viewId);
}


@Override
public void invalidateView(ViewId viewId){
    defaultViewsRepositoryStorage.invalidateView(viewId);
}


@Override
public void put(IView view){
    defaultViewsRepositoryStorage.put(view);
}


@Override
public Stream<IView> streamAllViews(){
    return defaultViewsRepositoryStorage.streamAllViews();
}


@Override
public WindowId getWindowId(){
    return MaterialCockpitUtil.WINDOWID_MaterialCockpitView;
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
    defaultViewsRepositoryStorage.closeById(viewId, closeAction);
}


}