package DTO;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class JSONStickyDocumentFilter {

 private  String id;

 private  String caption;


public List<JSONStickyDocumentFilter> ofStickyFiltersList(DocumentFilterList filters,String adLanguage){
    if (filters == null || filters.isEmpty()) {
        return ImmutableList.of();
    }
    return filters.stream().map(filter -> ofStickyFilterOrNull(filter, adLanguage)).filter(filter -> filter != null).collect(GuavaCollectors.toImmutableList());
}


public JSONStickyDocumentFilter ofStickyFilterOrNull(DocumentFilter filter,String adLanguage){
    // Don't expose the sticky filter if it does not have a caption,
    // because usually that's an internal filter.
    // (see https://github.com/metasfresh/metasfresh-webui-api/issues/481)
    final String caption = filter.getCaption(adLanguage);
    if (Check.isEmpty(caption, true)) {
        return null;
    }
    final String filterId = filter.getFilterId();
    return new JSONStickyDocumentFilter(filterId, caption);
}


}