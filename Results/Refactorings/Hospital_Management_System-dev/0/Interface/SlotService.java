public interface SlotService {

   public Long countAllByIsAvailableAndDoctorAndDateBetween(boolean isAvailable,Doctor doctor,LocalDate dateBegin,LocalDate dateEnd);
   public Long countAllByIsAvailableAndDoctorSpecialityAndDateBetween(boolean isAvailable,Speciality speciality,LocalDate dateBegin,LocalDate dateEnd);
}