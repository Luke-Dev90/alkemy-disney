package com.lchalela.disnet.api.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.records.CharacterRecord;

@Repository
public interface ICharacterRepository extends CrudRepository<Character, Long>{
	
	@Query(value="SELECT name, image FROM characters",nativeQuery = true)
	public List<CharacterRecord> listAll();

	@Query("SELECT c FROM Character AS c WHERE c.name =?1")
	public Character getCharacterByName(String name);
	
	@Query("SELECT c FROM Character AS c WHERE c.age =?1")
	public List<Character> getCharacterByAge(Long age);
	
}
