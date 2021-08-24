package com.lchalela.disnet.api.models.service;

import java.util.List;

import com.lchalela.disnet.api.models.entity.Gender;

public interface IGenderService {
	
	public List<Gender> listAllGender();
	
	public Gender save(Gender gender);
	
	public void deleteGender(Long id);
	
	public Gender getById(Long id);
	
}
