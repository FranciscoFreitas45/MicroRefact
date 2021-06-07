package com.Request;

import com.DTO.Reservation;

import java.util.List;

public interface ReservationRequest {

   public void setReservations(List<Reservation> reservations, Long rTableId);
   public List<Reservation> getReservations(Long rTableId);
}