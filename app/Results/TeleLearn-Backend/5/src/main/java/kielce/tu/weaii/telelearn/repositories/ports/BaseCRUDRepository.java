package kielce.tu.weaii.telelearn.repositories.ports;
 import java.util.List;
public interface BaseCRUDRepository extends BaseRepository<E>{


public List<E> getAll()
;

}