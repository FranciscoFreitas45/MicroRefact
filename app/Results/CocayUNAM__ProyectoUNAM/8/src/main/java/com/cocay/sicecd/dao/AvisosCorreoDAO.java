package com.cocay.sicecd.dao;
 import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.mail;
import javax.mail.internet;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.FiltroCorreoDTO;
import com.cocay.sicecd.dto.ProfesorDto;
import com.cocay.sicecd.model.Usuario_sys;
import com.cocay.sicecd.repo.Usuario_sysRep;
import com.cocay.sicecd.service.Logging;
import com.cocay.sicecd.service.SendMailService;
import com.cocay.sicecd.Interface.SendMailService;
import com.cocay.sicecd.Interface.SendMailService;
@SuppressWarnings("deprecation")
@Component
public class AvisosCorreoDAO {

@Autowired
 private  EntityManager em;

@Autowired
 private SendMailService _email;

@Autowired
 private SendMailService emailSender;


public List<ProfesorDto> envioCorreos(List<ProfesorDto> profesorDTO){
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    // inside your getSalesUserData() method
    executor.execute(new Runnable() {

        @Override
        public void run() {
            for (ProfesorDto p : profesorDTO) {
                System.out.print(p.getCorreo());
                String from = "cocayprueba@gmail.com";
                String to = p.getCorreo();
                String subject = p.getCdAsunto();
                String body = p.getCdMensaje();
                emailSender.sendMailSender(from, to, subject, body);
                System.out.println(Thread.currentThread().getName());
            }
        }
    });
    return profesorDTO;
}


@Override
public void run(){
    for (ProfesorDto p : profesorDTO) {
        System.out.print(p.getCorreo());
        String from = "cocayprueba@gmail.com";
        String to = p.getCorreo();
        String subject = p.getCdAsunto();
        String body = p.getCdMensaje();
        emailSender.sendMailSender(from, to, subject, body);
        System.out.println(Thread.currentThread().getName());
    }
}


@SuppressWarnings("unchecked")
public List<ProfesorDto> filtrosCorreos(FiltroCorreoDTO filtroCorreoDTO){
    List<ProfesorDto> lstProfesores = new ArrayList<ProfesorDto>();
    boolean bool = false;
    String consulta = "SELECT DISTINCT nombres, correo FROM (SELECT \n" + "   PROF.pk_id_profesor idProfesor,\n" + "	PROF.NOMBRE ||' '|| PROF.apellido_paterno ||' '|| PROF.apellido_materno nombres,\n" + "	PROF.CORREO correo,\n" + "	CUR.CLAVE CLAVE,\n" + "	CUR.NOMBRE NOMBRE,\n" + "	CUR.FK_ID_TIPO_CURSO FK_ID_TIPO_CURSO,\n" + "	GRP.FECHA_INICIO F_INICIO,\n" + "	GRP.fecha_fin,\n" + "	PROF.rfc rfc,\n" + "	PROF.fk_id_estado fk_id_estado,\n" + "	PROF.fk_id_turno fk_id_turno,\n" + "	INS.fk_id_grupo fk_id_grupo\n" + "FROM INSCRIPCION INS \n" + "	FULL OUTER JOIN PROFESOR PROF ON PROF.pk_id_profesor = INS.fk_id_profesor\n" + "	FULL OUTER JOIN GRUPO GRP ON INS.fk_id_grupo = GRP.pk_id_grupo\n" + "	FULL OUTER JOIN CURSO CUR ON GRP.fk_id_curso = CUR.pk_id_curso) PROFESORES";
    System.out.println("filtros " + filtroCorreoDTO);
    if (filtroCorreoDTO != null) {
        consulta += " WHERE ";
        if (filtroCorreoDTO.getNombre() != null && !filtroCorreoDTO.getNombre().equals("")) {
            consulta += " NOMBRE ILIKE '%" + filtroCorreoDTO.getNombre() + "%'";
            bool = true;
        }
        if (filtroCorreoDTO.getClave() != null && !filtroCorreoDTO.getClave().equals("")) {
            consulta += (bool ? " OR " : " ") + "CLAVE ILIKE '%" + filtroCorreoDTO.getClave() + "%'";
            bool = true;
        }
        if (filtroCorreoDTO.getTipo() != null && !filtroCorreoDTO.getTipo().equals("")) {
            consulta += (bool ? " OR " : " ") + " FK_ID_TIPO_CURSO = " + filtroCorreoDTO.getTipo() + "";
            bool = true;
        }
        if (filtroCorreoDTO.getfInicio() != null && !filtroCorreoDTO.getfInicio().equals("")) {
            consulta += (bool ? " OR " : " ") + " TO_CHAR( FECHA_INICIO, 'YYYY-MM-DD') = '" + filtroCorreoDTO.getfInicio() + "'";
            bool = true;
        }
        if (filtroCorreoDTO.getfTermino() != null && !filtroCorreoDTO.getfTermino().equals("")) {
            consulta += (bool ? " OR " : " ") + " TO_CHAR( fecha_fin, 'YYYY-MM-DD') = '" + filtroCorreoDTO.getfTermino() + "'";
            bool = true;
        }
        if (filtroCorreoDTO.getNombres() != null && !filtroCorreoDTO.getNombres().equals("")) {
            consulta += (bool ? " OR " : " ") + " NOMBRES ILIKE '%" + filtroCorreoDTO.getNombres() + "%'";
            bool = true;
        }
        if (filtroCorreoDTO.getRfc() != null && !filtroCorreoDTO.getRfc().equals("")) {
            consulta += (bool ? " OR " : " ") + " rfc = '" + filtroCorreoDTO.getRfc() + "'";
            bool = true;
        }
        if (filtroCorreoDTO.getEstado() != null && !filtroCorreoDTO.getEstado().equals("")) {
            consulta += (bool ? " OR " : " ") + " fk_id_estado = " + filtroCorreoDTO.getEstado() + "";
            bool = true;
        }
        if (filtroCorreoDTO.getTurno() != null && !filtroCorreoDTO.getTurno().equals("")) {
            consulta += (bool ? " OR " : " ") + " fk_id_turno = " + filtroCorreoDTO.getTurno() + "";
            bool = true;
        }
        if (filtroCorreoDTO.getIdGrupo() != null && !filtroCorreoDTO.getIdGrupo().equals("")) {
            consulta += (bool ? " OR " : " ") + " fk_id_grupo = " + filtroCorreoDTO.getIdGrupo() + "";
            bool = true;
        }
    }
    Query query = em.createNativeQuery(consulta);
    query.unwrap(SQLQuery.class).addScalar("nombres", StringType.INSTANCE).addScalar("correo", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ProfesorDto.class));
    lstProfesores = (List<ProfesorDto>) query.getResultList();
    return lstProfesores;
}


}