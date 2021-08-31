package com.lchalela.disnet.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.disnet.api.models.records.CharacterRecord;
import com.lchalela.disnet.api.models.entity.Character;
import com.lchalela.disnet.api.models.exception.ErrorDeletedException;
import com.lchalela.disnet.api.models.exception.NotCreateCharacter;
import com.lchalela.disnet.api.models.service.ICharacterService;

@RestController
@RequestMapping(value = "/characters")
public class CharacterRestController {

	@Autowired
	private ICharacterService characterService;
	private Map<String, Object> response = new HashMap<>();

	@GetMapping
	public ResponseEntity<?> getAllMovie() {
		List<CharacterRecord> characters = this.characterService.listAllCharacters();
		return new ResponseEntity<>(characters, HttpStatus.OK);
	}

	
	@RequestMapping(value = "", params = "name", method = RequestMethod.GET)
	public ResponseEntity<?> getCharacterByName(@RequestParam(name = "name") String name) {

		Character character = Optional.of(this.characterService.getCharacterByName(name))
				.orElseThrow( () -> new NumberFormatException());
		
		return new ResponseEntity<>(character, HttpStatus.OK);
	}

	
	@RequestMapping(value = "", params = "id", method = RequestMethod.GET)
	public ResponseEntity<?> getCharacterById(@RequestParam(name = "id") String id) {
		
		Character character = Optional.of(this.characterService.getCharacterById(Long.parseLong(id)))
				.orElseThrow( () -> new NumberFormatException());		
		
		return new ResponseEntity<>(character, HttpStatus.OK);
	}

	
	@RequestMapping(value = "", params = "age", method = RequestMethod.GET)
	public ResponseEntity<?> getCharactersByAge(@RequestParam(name = "age") String age) {
		List<Character> characters = null;
		
		characters = Optional.of(this.characterService.getAllCharacterByAge(Long.parseLong(age)))
				.orElseThrow( () -> new NumberFormatException());
		
		return new ResponseEntity<>(characters, HttpStatus.OK);
	}

	
	@RequestMapping(value = "", params = "movies", method = RequestMethod.GET)
	public ResponseEntity<?> listCharacterByIdMovie(@RequestParam(name = "movies") String movies) {
		
		List<Character> characters = Optional.of(this.characterService
				.getAllByMovieId(Long.parseLong(movies)))
			    .orElseThrow( () -> new NumberFormatException());

		return new ResponseEntity<>(characters, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveCharacter(@Valid @RequestBody Character character) {

		Character characterNew = null;

			characterNew = Optional.of( this.characterService.saveCharacter(character) )
					.orElseThrow( () -> new NotCreateCharacter("#ERROR: Character not created"));
			
		response.put("character", characterNew);
		response.put("message", "The character save in database: " + characterNew.getName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCharacter(@Valid @RequestBody Character character,
			@PathVariable String id) {
		
		Character characterResult = Optional.of(this.characterService.updateCharacter(Long.parseLong(id),character))
				.orElseThrow( () -> new NumberFormatException());

		response.put("message", "The character updated successfully");
		response.put("character",characterResult);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCharacterById(@PathVariable String id) throws ErrorDeletedException{
			
		this.characterService.deleteCharacter(Optional.of(Long.parseLong(id)).orElseThrow( () -> new NumberFormatException()));
		response.put("message", "Character deleted by id: " + id);		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
