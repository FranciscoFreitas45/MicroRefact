package de.metas.ui.web.view.descriptor;
 import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.OrderUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.view.SqlViewCustomizer;
import de.metas.ui.web.view.ViewProfile;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import lombok.ToString;
@ToString(of = "viewCustomizers")
public class SqlViewCustomizerMap {

 private  Comparator<SqlViewCustomizer> ORDERED_COMPARATOR;

 private  ImmutableList<SqlViewCustomizer> viewCustomizers;

 private  ImmutableListMultimap<WindowId,ViewProfile> viewProfilesByWindowId;

 private  ImmutableMap<WindowId,ImmutableMap<ViewProfileId,SqlViewCustomizer>> map;


public ImmutableMap<WindowId,ImmutableMap<ViewProfileId,SqlViewCustomizer>> makeViewCustomizersMap(ImmutableList<SqlViewCustomizer> viewCustomizers){
    final Map<WindowId, ImmutableMap<ViewProfileId, SqlViewCustomizer>> map = viewCustomizers.stream().sorted(ORDERED_COMPARATOR).collect(Collectors.groupingBy(SqlViewCustomizer::getWindowId, ImmutableMap.toImmutableMap(viewCustomizer -> viewCustomizer.getProfile().getProfileId(), viewCustomizer -> viewCustomizer)));
    return ImmutableMap.copyOf(map);
}


public void forEachWindowIdAndProfileId(BiConsumer<WindowId,ViewProfileId> consumer){
    viewCustomizers.forEach(viewCustomizer -> consumer.accept(viewCustomizer.getWindowId(), viewCustomizer.getProfile().getProfileId()));
}


public ImmutableListMultimap<WindowId,ViewProfile> getViewProfilesIndexedByWindowId(){
    return viewCustomizers.stream().collect(ImmutableListMultimap.toImmutableListMultimap(viewCustomizer -> viewCustomizer.getWindowId(), viewCustomizer -> viewCustomizer.getProfile()));
}


public int getOrder(SqlViewCustomizer viewCustomizer){
    if (viewCustomizer instanceof Ordered) {
        return ((Ordered) viewCustomizer).getOrder();
    } else {
        return OrderUtils.getOrder(viewCustomizer.getClass(), Ordered.LOWEST_PRECEDENCE);
    }
}


public SqlViewCustomizerMap ofCollection(Collection<SqlViewCustomizer> viewCustomizers){
    return new SqlViewCustomizerMap(viewCustomizers);
}


public SqlViewCustomizer getOrNull(WindowId windowId,ViewProfileId profileId){
    if (ViewProfileId.isNull(profileId)) {
        return null;
    }
    final ImmutableMap<ViewProfileId, SqlViewCustomizer> viewCustomizersByProfileId = map.get(windowId);
    if (viewCustomizersByProfileId == null) {
        return null;
    }
    return viewCustomizersByProfileId.get(profileId);
}


public List<ViewProfile> getViewProfilesByWindowId(WindowId windowId){
    return viewProfilesByWindowId.get(windowId);
}


}