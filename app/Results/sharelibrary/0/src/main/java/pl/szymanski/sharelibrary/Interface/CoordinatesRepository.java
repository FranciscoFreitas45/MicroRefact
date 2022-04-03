package pl.szymanski.sharelibrary.Interface;
public interface CoordinatesRepository {

   public Coordinates saveCoordinates(Coordinates coordinates);
   public Optional<Coordinates> findByLatitudeAndLongitude(Double latitude,Double longitude);
}