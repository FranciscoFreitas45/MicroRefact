public interface CertificadoRequest {

   public void setCertificados(List<Certificado> certificados,int pk_id_grupo);
   public List<Certificado> getCertificados(int pk_id_grupo);
}