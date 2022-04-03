package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CompositeDefaultViewProfileIdProviderController {

 private CompositeDefaultViewProfileIdProvider compositedefaultviewprofileidprovider;


@GetMapping
("/of")
public CompositeDefaultViewProfileIdProvider of(@RequestParam(name = "providers") List<DefaultViewProfileIdProvider> providers){
  return compositedefaultviewprofileidprovider.of(providers);
}


@PutMapping
("/setDefaultProfileIdOverride")
public void setDefaultProfileIdOverride(@RequestParam(name = "windowId") WindowId windowId,@RequestParam(name = "profileId") ViewProfileId profileId){
compositedefaultviewprofileidprovider.setDefaultProfileIdOverride(windowId,profileId);
}


@GetMapping
("/getDefaultProfileIdByWindowId")
public ViewProfileId getDefaultProfileIdByWindowId(@RequestParam(name = "windowId") WindowId windowId){
  return compositedefaultviewprofileidprovider.getDefaultProfileIdByWindowId(windowId);
}


}