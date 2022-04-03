package pl.szymanski.sharelibrary.Interface;
public interface CoordinatesRepository {

   public Optional<Coordinates> findByLatitudeAndLongitude(Double latitude,Double longitude);
}