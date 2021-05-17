import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cocay.sicecd.model.Certificado;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CertificadoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
@Controller
public class DescargasController {

@Autowired
 private InscripcionRep _incripcion;

@Autowired
 private ProfesorRep _profesor;

@Autowired
 private CertificadoRep _certificado;

@Value("${path_constancia}")
 private String path;

@Value("${ws.ruta_local}")
 private String path2;


@RequestMapping("descargas")
@ResponseBody
public void show(int id,String tipo,HttpServletResponse response){
    String file = "";
    Profesor profesor = null;
    Certificado certificado = null;
    switch(tipo) {
        case "comprobante":
            profesor = _profesor.findById(id).get();
            file = profesor.getComprobante_doc();
            break;
        case "rfc":
            profesor = _profesor.findById(id).get();
            file = profesor.getRfc_doc();
            break;
        case "curp":
            profesor = _profesor.findById(id).get();
            file = profesor.getCurp_doc();
            break;
        case "certificado":
            profesor = _profesor.findById(id).get();
            file = profesor.getCertificado_doc();
            break;
        case "certificadoqr":
            certificado = _certificado.findById(id).get();
            file = certificado.getRuta();
            break;
    }
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=" + (new File(file)).getName());
    response.setHeader("Content-Transfer-Encoding", "binary");
    try {
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        FileInputStream fis = null;
        if (tipo.equals("certificadoqr")) {
            System.out.println("[ENTRA]----" + file);
            fis = new FileInputStream(file);
        } else {
            // luisos
            fis = new FileInputStream(path + profesor.getPk_id_profesor() + "/" + file);
        }
        int len;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) > 0) {
            bos.write(buf, 0, len);
        }
        bos.close();
        response.flushBuffer();
        fis.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}