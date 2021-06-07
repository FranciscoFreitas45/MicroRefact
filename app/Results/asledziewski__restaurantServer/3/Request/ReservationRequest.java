public interface ReservationRequest {

   public void setReservations(List<Reservation> reservations,Long rTableId);
   public List<Reservation> getReservations(Long rTableId);
}