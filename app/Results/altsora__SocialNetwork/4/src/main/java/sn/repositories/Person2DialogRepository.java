package sn.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.model.Person2Dialog;
@Repository
public interface Person2DialogRepository extends JpaRepository<Person2Dialog, Long>{


@Query("delete from Person2Dialog pd WHERE pd.person.id = :personId AND pd.dialog.id = :dialogId")
public void deleleByPersonIdAndDialogId(long personId,long dialogId)
;

@Query("SELECT pd FROM Person2Dialog pd WHERE pd.person.id = :personId AND pd.dialog.id = :dialogId")
public Person2Dialog find(long personId,long dialogId)
;

}