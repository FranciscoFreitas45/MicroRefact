package run.halo.app.listener.theme;
 import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import run.halo.app.cache.AbstractStringCacheStore;
import run.halo.app.event.options.OptionUpdatedEvent;
import run.halo.app.event.theme.ThemeUpdatedEvent;
import run.halo.app.service.ThemeService;
@Component
public class ThemeUpdatedListener {

 private  AbstractStringCacheStore cacheStore;

public ThemeUpdatedListener(AbstractStringCacheStore cacheStore) {
    this.cacheStore = cacheStore;
}
@EventListener
public void onOptionUpdatedEvent(OptionUpdatedEvent optionUpdatedEvent){
    cacheStore.delete(ThemeService.THEMES_CACHE_KEY);
}


@EventListener
public void onApplicationEvent(ThemeUpdatedEvent event){
    cacheStore.delete(ThemeService.THEMES_CACHE_KEY);
}


}