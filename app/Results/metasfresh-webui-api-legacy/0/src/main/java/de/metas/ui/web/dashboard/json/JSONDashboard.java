package de.metas.ui.web.dashboard.json;
 import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.logging.LogManager;
import de.metas.ui.web.dashboard.UserDashboardItem;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.util.GuavaCollectors;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDashboard {

 private  Logger logger;

 private  List<JSONDashboardItem> items;

 private  String websocketEndpoint;


public JSONDashboard of(Collection<UserDashboardItem> items,String websocketEndpoint,JSONDocumentLayoutOptions jsonOpts){
    return new JSONDashboard(items, websocketEndpoint, jsonOpts);
}


}