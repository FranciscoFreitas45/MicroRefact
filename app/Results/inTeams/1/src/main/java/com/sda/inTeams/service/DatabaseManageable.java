package com.sda.inTeams.service;
 import java.util.List;
import java.util.Optional;

public interface DatabaseManageable<T> {

    List<T> getAll();
    Optional<T> getById(long id);
    T getByIdOrThrow(long id) throws Exception;
    T add(T entity) throws Exception;
    void delete(long id) throws Exception;
    T saveToDatabase(T entity);
}