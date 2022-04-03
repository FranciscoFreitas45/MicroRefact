package de.metas.ui.web.handlingunits;
 import java.util.List;
import java.util.Optional;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.view.SqlViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.json.JSONViewDataType;
@ViewFactory(windowId = WEBUI_HU_Constants.WEBUI_HU_Window_ID_String, viewTypes = { JSONViewDataType.grid, JSONViewDataType.includedView })
public class DefaultHUEditorViewFactory extends HUEditorViewFactoryTemplate{


@Override
public void customizeHUEditorView(HUEditorViewBuilder huViewBuilder){
    if (huViewBuilder.isUseAutoFilters() && huViewBuilder.getFilters().isEmpty()) {
        final List<DocumentFilter> autoFilters = SqlViewFactory.createAutoFilters(huViewBuilder.getFilterDescriptors().getAll());
        huViewBuilder.setFilters(DocumentFilterList.ofList(autoFilters));
    }
}


}