package com.lchalela.disnet.api.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

}
