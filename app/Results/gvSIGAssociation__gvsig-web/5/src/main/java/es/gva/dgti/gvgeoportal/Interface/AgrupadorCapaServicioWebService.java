package es.gva.dgti.gvgeoportal.Interface;
public interface AgrupadorCapaServicioWebService {

   public TypedQuery<Long> findServicesByGroup(Long agrupadorCapaId);
   public Object findAgrupadorCapaServicioWeb(Object Object);
}