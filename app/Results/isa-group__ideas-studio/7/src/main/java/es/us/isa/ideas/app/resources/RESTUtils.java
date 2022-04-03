package es.us.isa.ideas.app.resources;
 import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.google.common.base.Function;
public class RESTUtils {

 public  String SEDL_VERSION;

 public  String SEDL_NAMESPACE;

 public  String SEDL_PREFIX;

 private  long serialVersionUID;


public String[] buildURLArray(Collection<T> items,Function<T,String> getID,UriInfo uriInfo){
    String[] uriArray = new String[items.size()];
    int index = 0;
    for (T item : items) {
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI userUri = ub.path(getID.apply(item)).build();
        uriArray[index] = userUri.toASCIIString();
        index++;
    }
    return uriArray;
}


public List<String> buildListOfURIs(Collection<T> items,Function<T,String> getID,UriInfo uriInfo){
    List<String> uriList = new JAXBList<String>();
    URI userUri = null;
    for (T item : items) {
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        userUri = ub.path(getID.apply(item)).build();
        uriList.add(userUri.toASCIIString());
    }
    return uriList;
}


@XmlElement(name = "item")
@JsonIgnore
public List<T> getList(){
    return this;
}


@JsonIgnore
public void setList(List<T> value){
    this.clear();
    this.addAll(value);
}


public void signRequest(Request req){
}


}