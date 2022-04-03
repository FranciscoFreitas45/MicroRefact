package com.cocay.sicecd.webService;
 import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import com.cocay.sicecd.dto.FiltroCorreoDTO;
import com.cocay.sicecd.dto.ProfesorDto;
import com.cocay.sicecd.dto.ResponseGeneric;
@Path("/CorreoService")
public interface CorreoService {


@POST
@Path("/enviarCorreosSeleccionados/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public ResponseGeneric enviarCorreosSeleccionados(List<ProfesorDto> profesorDto)
;

@POST
@Path("/filtrosCorreos/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public ResponseGeneric filtrosCorreos(FiltroCorreoDTO filtroCorreoDTO)
;

}