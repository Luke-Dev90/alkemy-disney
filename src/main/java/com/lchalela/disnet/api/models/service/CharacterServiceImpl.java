package com.lchalela.disnet.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.disnet.api.models.records.CharacterRecord;
import com.lchalela.disnet.api.models.repository.ICharacterRepository;


@Service
public class CharacterServiceImpl implements ICharacterService{
	
	@Autowired
	private ICharacterRepository characterRepository;

	@Override
	public List<CharacterRecord> listAllCharacters() {
		return characterRepository.listAll();
	}

	@Override
	public List<Character> getAllByMovieId(Long id) {
		return this.getAllByMovieId(id);
	}
	

	@Override
	public List<Character> getAllCharacterByAge(Long age) {
		return this.characterRepository.getCharacterByAge(age);
	}

	@Override
	public Character getCharacterById(Long id) {
		return this.characterRepository.findById(id).orElse(null);
	}

	@Override
	public Character getCharacterByName(String name) {
		return this.characterRepository.getCharacterByName(name);
	}

	@Override
	public Character saveCharacter(Character character) {
		return this.characterRepository.save(character);
	}

	@Override
	public void deleteCharacter(Long id) {
		this.characterRepository.deleteById(id);
	}

}
