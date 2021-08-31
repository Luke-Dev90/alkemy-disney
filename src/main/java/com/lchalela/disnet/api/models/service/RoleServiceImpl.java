package com.lchalela.disnet.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lchalela.disnet.api.models.entity.Role;
import com.lchalela.disnet.api.models.repository.IRoleRepository;

public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public List<Role> role() {
		return (List<Role>) this.roleRepository.findAll() ;
	}

	@Override
	public Role saveRole(Role role) {
		return this.roleRepository.save(role);
	}

	@Override
	public Role getById(Long id) {
		return this.roleRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteRoleByid(Long id) {
		this.roleRepository.deleteById(id);
		
	}

}
