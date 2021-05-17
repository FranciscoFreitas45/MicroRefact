import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.dao.AvisosCorreoDAO;
import com.cocay.sicecd.dto.FiltroCorreoDTO;
import com.cocay.sicecd.dto.ProfesorDto;
import com.cocay.sicecd.dto.ResponseGeneric;
public class CorreoServiceImpl implements CorreoService,com.cocay.sicecd.webService.CorreoService{

@Autowired
 private  AvisosCorreoDAO avisosCorreoDAO;


@Override
public ResponseGeneric enviarCorreosSeleccionados(List<ProfesorDto> profesorDto){
    ResponseGeneric response = new ResponseGeneric();
    try {
        response.setResponse(avisosCorreoDAO.envioCorreos(profesorDto));
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric filtrosCorreos(FiltroCorreoDTO filtroCorreoDTO){
    ResponseGeneric response = new ResponseGeneric();
    try {
        response.setResponse(avisosCorreoDAO.filtrosCorreos(filtroCorreoDTO));
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


}