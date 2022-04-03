package run.halo.app.theme;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import run.halo.app.handler.theme.config.support.ThemeProperty;
public class ThemeFetcherComposite implements ThemeFetcher{

 private  List<ThemeFetcher> themeFetchers;

 private  ThemeFetcher fallbackFetcher;


public ThemeFetcherComposite addFetcher(ThemeFetcher fetchers){
    if (fetchers != null) {
        Collections.addAll(this.themeFetchers, fetchers);
    }
    return this;
}


@Override
public ThemeProperty fetch(Object source){
    final var themeFetcher = getThemeFetcher(source).orElse(fallbackFetcher);
    return themeFetcher.fetch(source);
}


public void clear(){
    this.themeFetchers.clear();
}


@NonNull
public Optional<ThemeFetcher> getThemeFetcher(Object source){
    return themeFetchers.stream().filter(fetcher -> fetcher.support(source)).findFirst();
}


@Override
public boolean support(Object source){
    return getThemeFetcher(source).isPresent();
}


public List<ThemeFetcher> getFetchers(){
    return Collections.unmodifiableList(this.themeFetchers);
}


}