package com.lchalela.disnet.api.models.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lchalela.disnet.api.models.records.CharacterRecord;
import com.lchalela.disnet.api.models.repository.ICharactersRepository;
import com.lchalela.disnet.api.models.entity.Character;
import com.lchalela.disnet.api.models.exception.ListCharacterException;
import com.lchalela.disnet.api.models.exception.NotCharacterAge;
import com.lchalela.disnet.api.models.exception.NotCharacterByMovieID;
import com.lchalela.disnet.api.models.exception.NotCharacterByName;
import com.lchalela.disnet.api.models.exception.NotCharacterID;
import com.lchalela.disnet.api.models.exception.NotCreateCharacter;
import com.lchalela.disnet.api.models.exception.NotFoundGenderID;

@Service
public class CharacterServiceImpl implements ICharacterService{
	
	@Autowired
	private ICharactersRepository characterRepository;

	@Override
	@Transactional(readOnly=true)
	public List<CharacterRecord> listAllCharacters() {

		List<CharacterRecord> listReturn = this.characterRepository.listAll();
		
		if(listReturn.isEmpty()) {
			throw new ListCharacterException("Character List not found");
		}else {
			return listReturn;
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Character> getAllByMovieId(Long id) {
		
		List<Character>characters = this.characterRepository.getMovieId(id);
		
		if(characters.isEmpty()) {
			throw new NotCharacterByMovieID("Movie id not found");
		}else {			
			return characters;
		}
		
	}
	

	@Override
	@Transactional(readOnly=true)
	public List<Character> getAllCharacterByAge(Long age) {	
		
		List<Character> characters = this.characterRepository.getCharacterByAge(age);
		
		if(characters.isEmpty()) {
			throw new NotCharacterAge("List character by age not found");
		}else {			
			return characters;
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public Character getCharacterById(Long id) {
		return this.characterRepository.findById(id).orElseThrow( ()-> new NotCharacterID(id));
	}

	@Override
	@Transactional(readOnly=true)
	public Character getCharacterByName(String name) {
		Character character = null;
			character = this.characterRepository.getCharacterByName(name);
			if(character == null) {
				throw new NotCharacterByName("The name not found");
			}else {				
				return character;
			}
	}

	@Override
	@Transactional
	public Character saveCharacter(Character character) {
		return this.characterRepository.save(character);
	}
	
	@Override
	@Transactional
	public Character updateCharacter(Long id, Character character ) {
		
		Optional<Character> characterResult =  Optional.of(this.characterRepository.getById(id));
		Character characterUpdate = null;
		
		if(characterResult.isPresent()) {	
			characterUpdate = characterResult.get();
			
			characterUpdate.setAge(character.getAge());
			characterUpdate.setHistory(character.getHistory());
			characterUpdate.setImage(character.getImage());
			characterUpdate.setMovies(character.getMovies());
			characterUpdate.setName(character.getName());
			characterUpdate.setWeight(character.getWeight());
			
			characterUpdate = this.saveCharacter(characterUpdate);
		}else {
			throw new NotCharacterID(id);
		}

		return characterUpdate;
	}

	@Override
	@Transactional
	public void deleteCharacter(Long id) {
		Optional<Character> character = this.characterRepository.findById(id);
		
		if(character.isPresent()) {
			Character characterDelete = character.get();
			characterDelete.removeMovie();
			this.characterRepository.delete(characterDelete);
		}else {
			throw new NotCharacterID(id);
		}
	}

}
