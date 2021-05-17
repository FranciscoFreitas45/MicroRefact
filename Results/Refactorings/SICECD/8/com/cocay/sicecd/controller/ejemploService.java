import java.io.InputStream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.http.MediaType;
import com.cocay.sicecd.dto.ResponseGeneric;
@Path("/ejemploService")
public interface ejemploService {


@POST
@Path("/leerExcel")
@Consumes({ MediaType.MULTIPART_FORM_DATA_VALUE })
@Produces({ MediaType.APPLICATION_JSON_VALUE })
public ResponseGeneric leerExcel(InputStream inputstream)


@GET
@Path("/lstCorreos")
@Produces({ MediaType.APPLICATION_JSON_VALUE })
public ResponseGeneric lstCorreos()


}