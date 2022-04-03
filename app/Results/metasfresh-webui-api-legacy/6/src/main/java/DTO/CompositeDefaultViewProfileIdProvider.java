package DTO;
 import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.ToString;
import java.util.List;
import java.util.Objects;
public class CompositeDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider{

 private  PlainDefaultViewProfileIdProvider overrides;

 private  ImmutableList<DefaultViewProfileIdProvider> providers;

 private  PlainDefaultViewProfileIdProvider fallback;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Override
public ViewProfileId getDefaultProfileIdByWindowId(WindowId windowId){
    return providers.stream().map(provider -> provider.getDefaultProfileIdByWindowId(windowId)).filter(Objects::nonNull).findFirst().orElse(ViewProfileId.NULL);
}


public CompositeDefaultViewProfileIdProvider of(List<DefaultViewProfileIdProvider> providers){
    return new CompositeDefaultViewProfileIdProvider(providers);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("providers",providers);
CompositeDefaultViewProfileIdProvider aux = restTemplate.getForObject(builder.toUriString(),CompositeDefaultViewProfileIdProvider.class);
return aux;
}


}