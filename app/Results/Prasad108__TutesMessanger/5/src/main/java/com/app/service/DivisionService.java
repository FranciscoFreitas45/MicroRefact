package com.app.service;
 import java.util.List;
import com.app.pojo.Classes;
import com.app.pojo.Division;
public interface DivisionService {


public List<Division> getallOfParticularClass(Classes classes)
;

public List<Division> getall()
;

public Division edit(int id)
;

public Division find(int id)
;

public void create(Division div)
;

public void update(Division classes)
;

public void delet(int id)
;

}