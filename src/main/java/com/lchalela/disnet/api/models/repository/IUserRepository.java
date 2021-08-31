package com.lchalela.disnet.api.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.entity.Users;

@Repository
public interface IUserRepository extends CrudRepository<Users, Long> {

	public Users findByUsername(String username);
	
	public Users findByUsernameOrEmail(String username,String email);
}
