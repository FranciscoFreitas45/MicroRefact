package com.cg.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oms.converter.RoleConverter;
import com.cg.oms.exception.RoleNotFoundException;
import com.cg.oms.model.Role;
import com.cg.oms.repository.RoleRepository;
import com.cg.oms.vo.RoleVo;

@Service
public class RoleServiceImpl implements RoleService
{
	public static final String EXCEPTION_MESSAGE = "No role found with this id ";

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleConverter roleConverter;

	/**
	 * Used to save the role to the role table
	 * 
	 * @param roleVo
	 * @return
	 */
	@Override
	public String saveRole(RoleVo roleVo)
	{
		Role role = roleConverter.voToModel(roleVo);
		role = roleRepository.save(role);
		RoleVo savedVo = roleConverter.modelToVo(role);
		return "Registered SuccessFully!!! " + savedVo.toString();
	}

	/**
	 * Used to delete the role on given ID
	 * 
	 * @param id
	 * @return
	 * @throws RoleNotFoundException
	 */
	@Override
	public String deleteRole(Long id) throws RoleNotFoundException
	{
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(EXCEPTION_MESSAGE + id));
		roleRepository.delete(role);
		return "Record Deleted Successfully!!";
	}

	/**
	 * Used to delete the role by roleID
	 * 
	 * @param id
	 * @return
	 * @throws RoleNotFoundException
	 */
	@Override
	public RoleVo getRoleById(Long id) throws RoleNotFoundException
	{
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(EXCEPTION_MESSAGE + id));
		return roleConverter.modelToVo(role);

	}

}
