package com.meli.weather.fixtures;
 import java.lang;
import java.util;
import java.io;
import java.net;
import groovy.lang;
import groovy.util;
@groovy.transform.Trait()
public interface EntityManagerFixtures {


public void clear(javax.persistence.EntityManager entityManager)
;

public void persist(javax.persistence.EntityManager entityManager,java.lang.Object objects)
;

public void remove(javax.persistence.EntityManager entityManager,java.lang.Object objects)
;

}