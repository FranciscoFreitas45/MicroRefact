package com.cocay.sicecd.controller;
 import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.model.Certificado;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.model.Url_ws;
import com.cocay.sicecd.repo.CertificadoRep;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.repo.Url_wsRep;
import com.cocay.sicecd.security.pdf.SeguridadPDF;
import com.cocay.sicecd.service.Logging;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.GrupoRep;
import com.cocay.sicecd.Interface.Logging;
@Controller
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application-cert.properties")
public class ClienteCertificadoController {

@Value("${ws.ruta_local}")
 private  String RUTA_LOCAL;

@Value("${ws.clave}")
 private  String clave;

@Autowired
 private CertificadoRep bd_certificado;

@Autowired
 private ProfesorRep bd_profesor;

@Autowired
 private CursoRep bd_curso;

@Autowired
 private GrupoRep bd_grupo;

@Autowired
 private Logging log;

@Autowired
 private Url_wsRep urls;

 private  Logger LOGGER;


@RequestMapping(value = "/certificadoRes", method = RequestMethod.GET)
public String procesaCertificado(Model model,HttpServletRequest request){
    // String correo = request.getParameter("correo");
    // poner correo
    Profesor profesor = bd_profesor.findByCorreo("georgenarvaez@hotmail.com");
    Curso curso = null;
    for (Curso c : bd_curso.findAll()) {
        if (c.getClave().equals("00A")) {
            // poner clave
            curso = c;
            break;
        }
    }
    Grupo grupo = null;
    for (Grupo g : bd_grupo.findAll()) {
        if (g.getClave().equals("00A")) {
            // poner clave
            grupo = g;
            break;
        }
    }
    String path = obtenCertificado(profesor, curso, grupo);
    if (path != null) {
        model.addAttribute("mensaje", path);
    } else {
        model.addAttribute("mensaje", "No se encontro el certificado!");
    }
    return "Certificado/resultado";
}


public String aux_obten(Profesor profesor,Curso curso,Grupo grupo,String URL_RS){
    String correo = profesor.getCorreo();
    String nombre_curso = curso.getClave() + "_" + grupo.getClave();
    Certificado cert = new Certificado();
    cert.setFk_id_curso(curso);
    cert.setFk_id_grupo(grupo);
    cert.setFk_id_profesor(profesor);
    HttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(URL_RS);
    List<NameValuePair> params = new ArrayList<NameValuePair>(3);
    params.add(new BasicNameValuePair("clave", clave));
    params.add(new BasicNameValuePair("email", correo));
    params.add(new BasicNameValuePair("nc", nombre_curso));
    post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    HttpResponse response = client.execute(post);
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    String jsonText = "";
    String linea = null;
    while ((linea = rd.readLine()) != null) {
        // System.out.println(linea);
        jsonText += linea;
    }
    JSONObject json = new JSONObject(jsonText);
    String mensaje = (String) json.get("mensaje");
    if (!mensaje.equals("NULL")) {
        // log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_NUEVAS, "0 constancias nuevas extraídas de 1 solicitadas");
        return null;
    }
    String string_pdf = (String) json.get("bytespdf");
    byte[] bytearray = java.util.Base64.getDecoder().decode(string_pdf);
    String codigo = curso.getClave() + "_" + grupo.getClave();
    String path = RUTA_LOCAL + codigo + "/" + codigo + "_" + profesor.getPk_id_profesor() + ".pdf";
    File out = new File(path);
    new File(out.getParent()).mkdirs();
    try (FileOutputStream os = new FileOutputStream(out)) {
        os.write(bytearray);
    // System.out.println("Archivo escrito!");
    } catch (Exception e) {
        LOGGER.error(e.getMessage());
    // System.out.println(e);
    }
    SeguridadPDF spdf = new SeguridadPDF();
    String nombrec = profesor.getNombre() + " " + profesor.getApellido_paterno() + " " + profesor.getApellido_materno();
    spdf.cifraPdf(path, nombrec, codigo);
    cert.setTiempo_creado(Long.parseLong((String) json.get("tiempo")));
    cert.setRuta(path);
    bd_certificado.save(cert);
    log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_NUEVAS, "1 constancia nueva extraída de 1 solicitada");
    return path;
}


public String obtenCertificado(Profesor profesor,Curso curso,Grupo grupo){
    LinkedList<Url_ws> links = new LinkedList<>(urls.findSimples());
    if (links.size() == 0) {
        throw new Exception("No hay urls!");
    }
    for (Url_ws u : links) {
        String resultado = aux_obten(profesor, curso, grupo, u.getUrl());
        if (resultado != null) {
            return resultado;
        }
    }
    log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_NUEVAS, "0 constancias nuevas extraídas de 1 solicitadas");
    return null;
}


@RequestMapping(value = "/certificado", method = RequestMethod.GET)
public String muestra(Model model){
    return "Certificado/certificado";
}


}