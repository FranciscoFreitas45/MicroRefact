package br.com.fatecmogidascruzes.Interface;
public interface EventWebService {

   public List<EventHomeDTO> getHomeEvents();
   public Event abrirLink(UUID hash);
   public EventDetailDTO getEventDetail(UUID hash);
   public List<EventHomeDTO> getEnabledEvents();
}