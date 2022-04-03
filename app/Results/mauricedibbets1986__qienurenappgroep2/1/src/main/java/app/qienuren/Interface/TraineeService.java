package app.qienuren.Interface;
public interface TraineeService {

   public Trainee addTrainee(Trainee trainee);
   public Iterable<Trainee> getAllTrainees();
   public Trainee traineeKoppelContactPersoon(long traineeID,long kcpID);
   public Trainee wijzigGegevens(long oorspronkelijkeId,long id);
}