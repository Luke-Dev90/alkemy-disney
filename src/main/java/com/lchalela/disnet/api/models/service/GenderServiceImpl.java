package com.lchalela.disnet.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.disnet.api.models.entity.Gender;
import com.lchalela.disnet.api.models.repository.IGenderRepository;

@Service
public class GenderServiceImpl implements IGenderService{

	@Autowired
	private IGenderRepository genderService;
	
	@Override
	public List<Gender> listAllGender() {
		return (List<Gender>)this.genderService.findAll();
	}

	@Override
	public Gender save(Gender gender) {
		return this.genderService.save(gender);
	}

	@Override
	public void deleteGender(Long id) {
		this.genderService.deleteById(id);
	}

	@Override
	public Gender getById(Long id) {
		return this.genderService.getById(id);
	}
	
}
