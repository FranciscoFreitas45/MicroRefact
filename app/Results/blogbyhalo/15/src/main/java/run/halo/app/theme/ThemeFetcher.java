package run.halo.app.theme;
 import run.halo.app.handler.theme.config.support.ThemeProperty;
public interface ThemeFetcher {


public ThemeProperty fetch(Object source)
;

public boolean support(Object source)
;

}