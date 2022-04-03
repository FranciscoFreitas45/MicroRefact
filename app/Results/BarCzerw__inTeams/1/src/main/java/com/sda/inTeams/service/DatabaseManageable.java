package com.sda.inTeams.service;
 import java.util.List;
import java.util.Optional;
public interface DatabaseManageable {


public T add(T entity)
;

public List<T> getAll()
;

public Optional<T> getById(long id)
;

public T getByIdOrThrow(long id)
;

public void delete(long id)
;

public T saveToDatabase(T entity)
;

}