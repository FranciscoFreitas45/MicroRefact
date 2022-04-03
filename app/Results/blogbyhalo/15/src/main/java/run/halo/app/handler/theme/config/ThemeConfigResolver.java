package run.halo.app.handler.theme.config;
 import java.io.IOException;
import java.util.List;
import org.springframework.lang.NonNull;
import run.halo.app.handler.theme.config.support.Group;
public interface ThemeConfigResolver {


@NonNull
public List<Group> resolve(String content)
;

}