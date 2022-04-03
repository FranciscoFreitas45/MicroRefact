package run.halo.app.handler.theme.config;
 import java.io.IOException;
import org.springframework.lang.NonNull;
import run.halo.app.handler.theme.config.support.ThemeProperty;
public interface ThemePropertyResolver {


@NonNull
public ThemeProperty resolve(String content)
;

}