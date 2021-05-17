public interface ReservationRequest {

   public void setReservations(List<Reservation> reservations,Long userId);
   public List<Reservation> getReservations(Long userId);
}