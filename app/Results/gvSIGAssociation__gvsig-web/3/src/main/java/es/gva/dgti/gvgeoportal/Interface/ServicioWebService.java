package es.gva.dgti.gvgeoportal.Interface;
public interface ServicioWebService {

   public TypedQuery<ServicioWeb> findServicesNotInIdListAndNoGroup(List<Long> idList,AgrupadorCapa agrupadorCapa);
   public TypedQuery<ServicioWeb> findServicesInIdList(List<Long> idList);
   public TypedQuery<ServicioWeb> findServicesByNoGroup(AgrupadorCapa agrupadorCapa);
   public Object getResultList(Object Object);
}