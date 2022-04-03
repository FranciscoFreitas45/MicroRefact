package app.qienuren.Request;
import app.qienuren.DTO.Trainee;
public interface TraineeRequest {

   public void koppelTrainee(Trainee trainee,long id);
   public List<Trainee> getTrainees(long id);
   public void setTrainees(List<Trainee> trainees,long id);
}