import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cocay.sicecd.dto.ResponseGeneric;
public class ejemploServiceImpl implements com.cocay.sicecd.controller.ejemploService,ejemploService{


@GetMapping(value = "/dametucosita", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseGeneric lstDameTuCosita(){
    ResponseGeneric response = new ResponseGeneric();
    Map<String, String> lstDameTuCosita = new HashMap<String, String>();
    lstDameTuCosita.put("dame", "ah ah");
    lstDameTuCosita.put("tu", "ah ah");
    lstDameTuCosita.put("cosita", "ah ah");
    response.setCdMensaje("A huevo");
    response.setNbMensaje("Correct");
    response.setResponse(lstDameTuCosita);
    return response;
}


@Override
public ResponseGeneric leerExcel(InputStream inputstream){
    ResponseGeneric response = new ResponseGeneric();
    try {
        // if (file == null) {
        // throw new RuntimeException("You must select the a file for uploading");
        // } else {
        // System.out.println("PROBANDO LEER ARCHIVO ");
        // Workbook hssfWorkBook = WorkbookFactory.create(file.getInputStream());
        // }
        System.out.println(inputstream);
        response.setCdMensaje("1");
        response.setNbMensaje("leyo el pinche excel");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje(e.getMessage());
    }
    return response;
}


@Override
public ResponseGeneric lstCorreos(){
    // TODO Auto-generated method stub
    ResponseGeneric response = new ResponseGeneric();
    try {
        response.setCdMensaje("1");
        response.setNbMensaje("ok");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje(e.getMessage());
    }
    return null;
}


}