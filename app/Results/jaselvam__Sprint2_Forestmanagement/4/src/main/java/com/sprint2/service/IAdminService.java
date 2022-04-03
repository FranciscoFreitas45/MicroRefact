package com.sprint2.service;
 import java.util.List;
import com.sprint2.model.Admin;
public interface IAdminService {


public Admin updateAdmin(Admin admin)
;

public boolean deleteAdminbyId(Integer id)
;

public List<Admin> getAllAdmins()
;

public Admin getAdminById(Integer id)
;

public Admin insertAdmin(Admin admin)
;

}