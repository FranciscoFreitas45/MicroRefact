package es.us.isa.ideas.app.resources.RESTUtils;
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
@XmlRootElement(name = "List")
public class JAXBList extends ArrayList<T>{

 private  long serialVersionUID;


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


}