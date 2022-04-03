package pl.edu.wat.wcy.pz.restaurantServer.Interface;
public interface RTableService {

   public Optional<RTable> getRTableById(Long id);
   public List<RTable> getRTablesBySize(int size);
}