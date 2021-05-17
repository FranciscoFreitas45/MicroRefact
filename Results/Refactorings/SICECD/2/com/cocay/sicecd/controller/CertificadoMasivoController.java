import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application-cert.properties")
public class CertificadoMasivoController {

@Value("${ws.ruta_local}")
 private  String RUTA_LOCAL;

@Value("${ws.temp_zip}")
 private  String TEMP_ZIP;

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


@Transactional
@Scheduled(cron = "${ws.scheduleTransfer}")
public void scheduleTaskWithCronExpression(){
    LinkedList<Url_ws> links = new LinkedList<>(urls.findVarios());
    if (links.size() == 0) {
        LOGGER.debug("No hay urls!");
        // throw new Exception("No hay urls!");
        return;
    }
    JSONObject json = new JSONObject();
    LinkedList<Profesor> profesores = new LinkedList<>(bd_profesor.findAll());
    if (profesores.size() == 0) {
        LOGGER.info("No hay profesores");
        return;
    }
    int nuevas = 0;
    int actual = 0;
    System.out.println("A punto de buscar profesores.");
    int k = 0;
    for (Profesor p : profesores) {
        LinkedList<Certificado> cert = new LinkedList<>(p.getCertificados());
        if (cert.size() == 0) {
            LinkedList<Inscripcion> ins = new LinkedList<>(p.getInscripciones());
            if (ins.size() == 0) {
                System.out.println("No hay inscripciones asociadas!");
                continue;
            }
            for (Inscripcion i : ins) {
                // int fkc = i.getFk_id_grupo().getFk_id_curso();
                // Curso caux = bd_curso.findByID(fkc);
                Curso caux = i.getFk_id_grupo().getFk_id_curso();
                Grupo gaux = i.getFk_id_grupo();
                // if(!caux.getNombre().equals("COSDAC 2018")) {
                // continue;
                // }
                System.out.println("**\n" + p.getCorreo() + "\n" + caux.getNombre() + "\n**");
                json.put("correo" + k, p.getCorreo());
                json.put("id_curso" + k, caux.getClave() + "_" + gaux.getClave());
                json.put("tiempo" + k, 0);
                System.out.println("Se insertaron elementos en el JSON (certificados no presentes)");
                nuevas++;
                k++;
            }
            continue;
        }
        for (Certificado c : cert) {
            String codigo = c.getFk_id_curso().getClave() + "_" + c.getFk_id_grupo().getClave();
            // System.out.println("**\n" + p.getCorreo() + "\n" + c.getFk_id_curso().getNombre() + "\n**");
            json.put("correo" + k, p.getCorreo());
            json.put("id_curso" + k, codigo);
            json.put("tiempo" + k, c.getTiempo_creado());
            System.out.println("Se insertaron elementos en el JSON (certificadospresentes)");
            actual++;
            k++;
        }
    }
    json.put("cuenta", k);
    int nuevas2 = 0;
    int actual2 = 0;
    for (Url_ws u : links) {
        System.out.println("Intentando con " + u.getUrl());
        int[] x = extraccionPorURL(u.getUrl(), json);
        nuevas2 += x[0];
        actual2 += x[1];
    }
    // if (nuevas != 0) {
    log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_NUEVAS, nuevas2 + " constancias nuevas extraídas de " + nuevas + "solicitadas");
    // }
    // if (actual != 0) {
    log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_ACTUALIZACION, actual2 + "constancias actualizadas de " + actual + "solicitadas");
// }
// tarea completada*/
}


public int[] extraccionPorURL(String URL_RSM,JSONObject json){
    HttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(URL_RSM);
    // System.out.println(json.toString());
    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
    params.add(new BasicNameValuePair("json", json.toString()));
    params.add(new BasicNameValuePair("clave", clave));
    post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    // esperar respuesta
    HttpResponse response = client.execute(post);
    // BufferedReader rd = new BufferedReader(new
    // InputStreamReader(response.getEntity().getContent(), "ISO_8859_1"));
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
    String jsonText = "";
    String linea = null;
    while ((linea = rd.readLine()) != null) {
        // System.out.println(linea);
        jsonText += linea;
    }
    JSONObject json_r = null;
    try {
        json_r = new JSONObject(jsonText);
    } catch (Exception e) {
        LOGGER.error(e.getMessage());
        // System.out.println(jsonText);
        return new int[] { 0, 0 };
    }
    // JSONObject json_r = new JSONObject(jsonText);
    String msg = (String) json_r.get("mensaje");
    // System.out.println(msg);
    // msg = new
    // String(java.util.Base64.getDecoder().decode(msg),Charset.forName("UTF-8"));
    if (!msg.equals("NULL")) {
        /*
			 * System.out.println("No hay certificados nuevos!");
			 * log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_NUEVAS,
			 * "0 constancias nuevas extraídas de " + nuevas + " solicitadas");
			 * log.setTrace(LogTypes.EXTRACCION_CONSTANCIAS_ACTUALIZACION,
			 * "0 constancias actualizadas de " + actual + " solicitadas");
			 */
        return new int[] { 0, 0 };
    }
    String mns = (String) json_r.get("zip");
    // System.out.println(mns);
    byte[] bytearray = java.util.Base64.getDecoder().decode(mns);
    String path = RUTA_LOCAL + "certificados.zip";
    File tempdir = Paths.get(RUTA_LOCAL).normalize().toFile();
    if (!tempdir.exists()) {
        tempdir.mkdirs();
    }
    String pathF = "", pathOut = "", pathAux = "";
    File out = new File(path);
    pathOut = out.getAbsolutePath();
    try (FileOutputStream os = new FileOutputStream(out)) {
        os.write(bytearray);
        System.out.println("Archivo escrito!");
    } catch (Exception e) {
        LOGGER.error(e.getMessage());
        System.out.println(e);
    }
    byte[] buffer = new byte[1024];
    File tmp = Paths.get(TEMP_ZIP).normalize().toFile();
    if (!tmp.exists()) {
        tmp.mkdirs();
    }
    try {
        ZipFile zpf = new ZipFile(out, Charset.forName("Cp437"));
        Enumeration<? extends ZipEntry> e = zpf.entries();
        ZipEntry ze;
        // System.out.println("PASE");
        while (e.hasMoreElements()) {
            ze = (ZipEntry) e.nextElement();
            String fileName = ze.getName();
            File newFile = new File(TEMP_ZIP + fileName);
            new File(newFile.getParent()).mkdirs();
            BufferedInputStream bis = new BufferedInputStream(zpf.getInputStream(ze));
            FileOutputStream fos = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int len;
            while ((len = bis.read(buffer, 0, 1024)) != -1) {
                fos.write(buffer, 0, len);
            // System.out.println("Escribiendo");
            }
            bos.flush();
            bos.close();
            bis.close();
        }
        zpf.close();
    } catch (java.util.zip.ZipException zx) {
        LOGGER.error("Zip file is empty!");
        out = null;
        delete(pathOut);
        // System.out.println("Zip file is empty!");
        return new int[] { 0, 0 };
    } catch (Exception e) {
        LOGGER.error(e.getMessage());
        out = null;
        delete(pathOut);
        // System.out.println(e);
        // e.printStackTrace();
        return new int[] { 0, 0 };
    }
    int nuevas2 = 0;
    int actual2 = 0;
    // comienza a mover los pdfs a la ruta elegida
    for (File f : tmp.listFiles()) {
        String[] ars = f.getName().split("_");
        Curso c = bd_curso.findByUniqueClave(ars[0]);
        // clave del grupo
        String gg = ars[1];
        // se obtiene el grupo
        Grupo g = null;
        for (Grupo ggg : c.getGrupos()) {
            if (ggg.getClave().equals(gg)) {
                g = ggg;
            }
        }
        // se obtiene el codigo completo
        String codigo = c.getClave() + "_" + g.getClave();
        // Curso c = bd_curso.findByID(Integer.parseInt(f.getName()));
        // Curso c = bd_curso.findByNombre(f.getName());
        JSONObject ar = new JSONObject(json_r.get(f.getName()).toString());
        for (File f2 : f.listFiles()) {
            Profesor p = bd_profesor.findByCorreo(f2.getName());
            for (File f3 : f2.listFiles()) {
                // +
                String pt = RUTA_LOCAL + codigo + "/" + codigo + "_" + p.getPk_id_profesor() + ".pdf";
                // f3.getName();
                FileInputStream fs = new FileInputStream(f3);
                File aux = new File(pt);
                new File(aux.getParent()).mkdirs();
                if (aux.exists()) {
                    pathAux = aux.getAbsolutePath();
                    aux = null;
                    delete(pathAux);
                }
                try (FileOutputStream os = new FileOutputStream(aux)) {
                    os.write(fs.readAllBytes());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    System.out.println(e);
                }
                /**
                 * 					SeguridadPDF spdf = new SeguridadPDF();
                 * 					String nombrec = p.getNombre() + " " + p.getApellido_paterno() + " " + p.getApellido_materno();
                 * 					spdf.cifraPdf(pt, nombrec, codigo);
                 * 					/**
                 */
                // elimina archivo
                f3 = null;
                Certificado cert = bd_certificado.findByRuta(pt);
                if (cert == null) {
                    System.out.println("insertando nuevo certificado!");
                    cert = new Certificado();
                    nuevas2++;
                } else {
                    actual2++;
                }
                // Certificado cert = new Certificado();
                cert.setRuta(pt);
                long tt = Long.parseLong((String) ar.get(p.getCorreo()));
                cert.setTiempo_creado(tt);
                cert.setFk_id_curso(c);
                cert.setFk_id_grupo(g);
                cert.setFk_id_profesor(p);
                bd_certificado.save(cert);
                fs.close();
            }
            // elimina directorio (usuario)
            f2 = null;
        }
        pathF = f.getAbsolutePath();
        // elimina directorio padre (curso)
        f = null;
    }
    // elimina zip
    out = null;
    /**/
    delete(pathF);
    delete(pathOut);
    return new int[] { nuevas2, actual2 };
}


@Scheduled(cron = "${ws.scheduleClean}")
public void deleteBach(){
    LOGGER.debug("Batch Clean:  PDF Temporary Files");
    delete(RUTA_LOCAL, "_ax.pdf");
}


public void delete(String path,String prefix){
    if (path == null || path.equals("")) {
        return;
    }
    File f = new File(path);
    if (f.isDirectory()) {
        for (File c : f.listFiles()) delete(c.getAbsolutePath(), prefix);
    }
    if (f.getAbsolutePath().endsWith(prefix)) {
        if (!f.delete()) {
            LOGGER.error("Error deleting: " + f.getAbsolutePath());
        }
    }
}


}