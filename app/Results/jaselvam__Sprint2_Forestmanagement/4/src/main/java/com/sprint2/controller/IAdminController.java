package com.sprint2.controller;
 import java.util.List;
import com.sprint2.model.Admin;
public interface IAdminController {


public boolean deleteAdmin(Integer id)
;

public Admin updateAdmin(Admin admin)
;

public Admin getAdminById(Integer id)
;

public Admin insertAdmin(Admin admin)
;

public List<Admin> getAllAdmin()
;

}