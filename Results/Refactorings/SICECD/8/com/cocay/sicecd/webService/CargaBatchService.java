import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.cocay.sicecd.dto.ResponseGeneric;
@Path("/CargaBatchService")
public interface CargaBatchService {


@POST
@Path("/lstLimpiar/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public ResponseGeneric lstLimpiar()


@GET
@Path("/lstInscripciones/")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric lstInscripciones(Integer limit,Integer offset,String search,String name,String order)


@GET
@Path("/limpiarTabla/{tabla}")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric limpiarTabla(String tabla)


@GET
@Path("/lstProfesores/")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric lstProfesores(Integer limit,Integer offset,String search,String name,String order)


@GET
@Path("/lstCursos/")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric lstCursos(Integer limit,Integer offset,String search,String name,String order)


@GET
@Path("/lstGrupo/")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric lstGrupo(Integer limit,Integer offset,String search,String name,String order)


@GET
@Path("/lstErrores/")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric lstErrores(Integer limit,Integer offset,String search,String name,String order)


@GET
@Path("/ultimoValor/")
@Produces(MediaType.APPLICATION_JSON)
public ResponseGeneric ultimoValor()


}