package com.cg.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oms.exception.RoleNotFoundException;
import com.cg.oms.service.RoleServiceImpl;
import com.cg.oms.vo.RoleVo;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
@RestController
public class RoleController
{
	/**
	 * We are autowiring the role service layer to this controller layer of role
	 * 
	 * @param roleServiceImpl
	 */
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	/**
	 * This function is used to create a new role. and it redirects to the role
	 * service
	 * 
	 * @param roleVo
	 * @return
	 */
	@PostMapping("/role/newrole")
	public ResponseEntity<RoleVo> roleName(@RequestBody RoleVo roleVo)
	{
		roleServiceImpl.saveRole(roleVo);
		return ResponseEntity.ok(roleVo);
	}

	/**
	 * This function is used to get the role using the primary key column
	 * 
	 * @param id
	 * @return
	 * @throws RoleNotFoundException
	 */
	@GetMapping("/role/getroleId/{id}")
	public ResponseEntity<RoleVo> getRoleId(@PathVariable(value = "id") Long id) throws RoleNotFoundException
	{
		RoleVo roleVo = roleServiceImpl.getRoleById(id);
		return ResponseEntity.ok().body(roleVo);
	}

	/**
	 * This function is used to delete the specific role. It the User of that role
	 * is deleted that will cascade delete this column also
	 * 
	 * @param roleId
	 * @return
	 * @throws RoleNotFoundException
	 */
	@DeleteMapping("/role/deleterole/{roleId}")
	public String deleteRole(@PathVariable(value = "roleId") Long roleId) throws RoleNotFoundException
	{
		return roleServiceImpl.deleteRole(roleId);
	}

}