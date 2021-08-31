package com.lchalela.disnet.api.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.entity.Role;
@Repository
public interface IRoleRepository extends CrudRepository<Role,Long> {

}
