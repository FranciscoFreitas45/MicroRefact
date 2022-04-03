package kielce.tu.weaii.telelearn.repositories.ports;
 import java.util.Optional;
public interface BaseRepository {


public Optional<E> getById(Long id)
;

public E save(E entity)
;

public void delete(E entity)
;

}