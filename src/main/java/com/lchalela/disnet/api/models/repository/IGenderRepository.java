package com.lchalela.disnet.api.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.entity.Gender;

@Repository
public interface IGenderRepository extends CrudRepository<Gender,Long>{

	@Query("SELECT g FROM Gender AS g FROM g.id =?1")
	public Gender getById(Long id);
}
