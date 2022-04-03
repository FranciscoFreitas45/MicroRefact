package de.metas.ui.web.view;
 import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.ToString;
import java.util.List;
import java.util.Objects;
@ToString
public class CompositeDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider{

 private  PlainDefaultViewProfileIdProvider overrides;

 private  ImmutableList<DefaultViewProfileIdProvider> providers;

 private  PlainDefaultViewProfileIdProvider fallback;


public void setDefaultProfileIdFallbackIfAbsent(WindowId windowId,ViewProfileId profileId){
    fallback.setDefaultProfileIdIfAbsent(windowId, profileId);
}


public void setDefaultProfileIdFallback(WindowId windowId,ViewProfileId profileId){
    fallback.setDefaultProfileId(windowId, profileId);
}


public CompositeDefaultViewProfileIdProvider of(List<DefaultViewProfileIdProvider> providers){
    return new CompositeDefaultViewProfileIdProvider(providers);
}


public void setDefaultProfileIdOverride(WindowId windowId,ViewProfileId profileId){
    overrides.setDefaultProfileId(windowId, profileId);
}


@Override
public ViewProfileId getDefaultProfileIdByWindowId(WindowId windowId){
    return providers.stream().map(provider -> provider.getDefaultProfileIdByWindowId(windowId)).filter(Objects::nonNull).findFirst().orElse(ViewProfileId.NULL);
}


}