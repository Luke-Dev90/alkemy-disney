package com.lchalela.disnet.api.models.service;

import java.util.List;

import com.lchalela.disnet.api.models.records.CharacterRecord;
import  com.lchalela.disnet.api.models.entity.Character;

public interface ICharacterService {
	
	public List<CharacterRecord> listAllCharacters();
	
	public List<Character> getAllByMovieId(Long id);
	
	public List<Character> getAllCharacterByAge(Long age);
	
	public Character getCharacterById(Long id);
	
	public Character getCharacterByName(String name);
	
	public Character saveCharacter(Character character);
	
	public void deleteCharacter(Long id);
	
}
