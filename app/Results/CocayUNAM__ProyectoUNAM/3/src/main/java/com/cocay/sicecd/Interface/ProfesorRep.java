package com.cocay.sicecd.Interface;
public interface ProfesorRep {

   public Profesor findByCurp(String curp);
   public Profesor findByRFC(String rfc);
   public void updateProfesorByRFC(String nombre,String apellido_paterno,String apellido_materno,String correo,String clave_plantel,String ciudad_localidad,String rfc);
   public void saveT(String firstname,String apellido_paterno,String apellido_materno,String curp,String rfc,String email,String institution,String city);
}