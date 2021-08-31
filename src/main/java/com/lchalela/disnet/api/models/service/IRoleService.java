package com.lchalela.disnet.api.models.service;

import java.util.List;

import com.lchalela.disnet.api.models.entity.Role;

public interface IRoleService {

	public List<Role> role();
	
	public Role saveRole(Role role);
	
	public Role getById(Long id);
	
	public void deleteRoleByid(Long id);
	
}
